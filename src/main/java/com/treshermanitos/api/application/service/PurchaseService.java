package com.treshermanitos.api.application.service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.paymentmethod.PaymentMethodClient;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import com.treshermanitos.api.application.dto.OrderMpAddDTO;
import com.treshermanitos.api.application.dto.PurchaseAddDTO;
import com.treshermanitos.api.application.dto.PurchaseDTO;
import com.treshermanitos.api.application.exceptions.NotFoundException;
import com.treshermanitos.api.application.mapper.PurchaseAddDtoMapper;
import com.treshermanitos.api.application.mapper.PurchaseDtoMapper;
import com.treshermanitos.api.application.repository.*;
import com.treshermanitos.api.domain.Product;
import com.treshermanitos.api.domain.Purchase;
import com.treshermanitos.api.domain.PurchaseProduct;
import com.treshermanitos.api.application.dto.SalesStadistics;
import com.treshermanitos.api.infrastructure.db.springdata.repository.PurchaseProductDboRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final PurchaseProductDboRepository purchaseProductRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PurchaseDtoMapper purchaseDtoMapper;
    private final PurchaseAddDtoMapper purchaseAddDtoMapper;
    @Value("${mercado-pago.access-token}")
    private String mercadoPagoAccesToken;
    @Value("${URL_FRONT}")
    private String urlFront;
    @Value("${URL_BACK}")
    private String urlBack;

    public Page<PurchaseDTO> getAllPurchases(Pageable pageable) {
        return this.purchaseRepository.findAll(pageable)
                .map(this.purchaseDtoMapper::toDto);
    }

    public PurchaseDTO getById(Long id) {
        return this.purchaseDtoMapper
                .toDto(this.purchaseRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("purchase " + id + " not found")));
    }

    public Page<PurchaseDTO> search(Integer dni, String firstName, String lastName, Pageable pageable) {
        return this.purchaseRepository.search(dni, firstName, lastName, pageable)
                .map(this.purchaseDtoMapper::toDto);
    }

    @Transactional
    public PurchaseDTO createOne(PurchaseAddDTO purchase) {
        Purchase purchaseToSave = this.purchaseAddDtoMapper
                .purchaseAddDTOtoDomain(purchase);

        List<PurchaseProduct> products = purchaseToSave.getPurchaseProducts();

        Purchase purchaseSaved = this.purchaseRepository.save(purchaseToSave);

        products.forEach(pp -> pp.setPurchase(purchaseSaved));

        List<PurchaseProduct> productsSaved =
                this.purchaseProductRepository.saveAll(products);

        purchaseSaved.setPurchaseProducts(productsSaved);

        return this.purchaseDtoMapper
                .toDto(this.purchaseRepository.save(purchaseSaved));
    }

    public PurchaseDTO updateById(Long id, PurchaseAddDTO purchaseToUpdate) {

        Purchase purchaseUpdated = this.purchaseAddDtoMapper
                .purchaseAddDTOtoDomainWithPurchaseOptional(purchaseToUpdate);

        Purchase purchase = this.purchaseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("purchase " + id + " not found"));

        if (purchaseUpdated.getPayment() != null) {
            purchase.setPayment(purchaseUpdated.getPayment());
        }
        if (purchaseUpdated.getPurchaseProducts() != null) {
            purchase.setPurchaseProducts(purchaseUpdated.getPurchaseProducts());
        }
        if (purchaseUpdated.getState() != null) {
            purchase.setState(purchaseUpdated.getState());
        }

        return this.purchaseDtoMapper.toDto
                (this.purchaseRepository.save(purchase));
    }


    public SalesStadistics getStadistics() {
        return SalesStadistics.builder()
                .stadisticsProducts(this.productRepository.get5mostSales())
                .stadisticsCategories(this.categoryRepository.get5mostSales())
                .stadisticsLast10days(this.purchaseRepository.getLast10DaysStadistics())
                .build();
    }


    public PurchaseDTO deleteById(long id) {
        Purchase purchase = this.purchaseRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("purchase " + id + " not found"));

        return this.purchaseDtoMapper.toDto
                (this.purchaseRepository.delete(purchase));
    }

    public Object createOrderMp(OrderMpAddDTO body) {
        MercadoPagoConfig.setAccessToken(this.mercadoPagoAccesToken);

        List<PurchaseProduct> products = body.getProducts()
                .stream()
                .map(p -> {
                    Product product = this.productRepository
                            .findByIdAndHasStockIsTrue(p.getProductId())
                            .orElseThrow(() -> new NotFoundException("Product " +
                                    p.getProductId() + " not found"));

                    return PurchaseProduct.builder()
                            .product(product)
                            .quantity(p.getQuantity())
                            .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())))
                            .build();
                }).collect(Collectors.toList());


        PreferenceClient client = new PreferenceClient();

        List<PreferenceItemRequest> items = products
                .stream()
                .map(p -> PreferenceItemRequest.builder()
                        .title(p.getProduct().getName())
                        .description(p.getProduct().getDescription())
                        .pictureUrl(p.getProduct().getImg())
                        .quantity(p.getQuantity())
                        .currencyId("ARS")
                        .unitPrice(p.getProduct().getPrice())
                        .build())
                .collect(Collectors.toList());


        PreferenceRequest request = PreferenceRequest.builder()
                .items(items)
                .externalReference(body.getIdPurchase().toString())
                .backUrls(PreferenceBackUrlsRequest.builder()
                        .success(this.urlFront)
                        .failure(this.urlFront)
                        .pending(this.urlFront)
                        .build())
                .notificationUrl(this.urlBack + "/api/v1/purchases/webhook")
                .build();
        try {
            Preference myPreference = client.create(request);
            return new HashMap<String, String>() {{
                put("urlMercadoPago", client.get(myPreference.getId()).getInitPoint());
            }};
        } catch (MPApiException ex) {
            System.out.printf(
                    "MercadoPago Error. Status: %s, Content: %s%n",
                    ex.getApiResponse().getStatusCode(), ex.getApiResponse().getContent());
            throw new RuntimeException("mp");
        } catch (MPException ex) {
            ex.printStackTrace();
            throw new RuntimeException("mp");
        }
    }

    public Object handleWebhook(String type, Long dataId, Object body) {
        try {
            System.out.println("type " + type);
            System.out.println("dataId " + dataId);
            System.out.println(body);
            if (type.equals("payment")) {
                PaymentClient client = new PaymentClient();
                Payment payment = client.get(dataId);
                Long purchaseId = Long.parseLong(payment.getExternalReference().toString());
                if (payment.getStatus().equals("approved")){

                    Purchase purchase = this.purchaseRepository.findById(purchaseId)
                            .orElseThrow(() -> new NotFoundException("purchase " + purchaseId + " not found"));

                    purchase.setState("paid");

                    this.purchaseRepository.save(purchase);
                    return "Ok";
                }
            }
            return "Webhook handled successfully";
        } catch (MPApiException ex) {
            System.out.printf(
                    "MercadoPago Error. Status: %s, Content: %s%n",
                    ex.getApiResponse().getStatusCode(), ex.getApiResponse().getContent());
            throw new RuntimeException("mp");
        } catch (MPException ex) {
            ex.printStackTrace();
            throw new RuntimeException("mp");
        }
    }
}
