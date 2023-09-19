package com.treshermanitos.treshermanitos.purchase;

import com.treshermanitos.treshermanitos.customer.CustomerRepository;
import com.treshermanitos.treshermanitos.exceptions.NotFoundException;
import com.treshermanitos.treshermanitos.product.Product;
import com.treshermanitos.treshermanitos.product.ProductRepository;
import com.treshermanitos.treshermanitos.purchase.dto.PurchaseDTO;
import com.treshermanitos.treshermanitos.purchase.dto.PurchaseDTOMapper;
import com.treshermanitos.treshermanitos.purchase.purchasesProducts.PurchaseProduct;
import com.treshermanitos.treshermanitos.purchase.purchasesProducts.PurchasesProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final CustomerRepository customerRepository;
    private final PurchaseDTOMapper purchaseDtoMapper;
    private final ProductRepository productRepository;
    private final PurchasesProductsRepository purchasesProductsRepository;


    public PurchasesPaginatedResponse getAll(Pageable pageable) {


        Page<PurchaseDTO> purchaseDtoPage =
                this.purchaseRepository.findAllPurchasesAsDTOs(pageable)
                        .map(i -> i.addProducts(this.purchaseRepository.findProductsAsProjectionsByPurchaseId(i.getId())));

        return PurchasesPaginatedResponse.builder()
                .purchases(purchaseDtoPage.getContent())
                .totalItems(purchaseDtoPage.getNumberOfElements())
                .totalPages(purchaseDtoPage.getTotalPages())
                .build();
    }

    public PurchasesPaginatedResponse getAllByCustomerDetails(Pageable pageable, String name, Integer dni) {
        Page<PurchaseDTO> purchaseDtoPage =
                this.purchaseRepository.findPurchasesAsDTOsByCustomerDetails(pageable, name, dni)
                        .map(i -> i.addProducts(this.purchaseRepository.findProductsAsProjectionsByPurchaseId(i.getId())));

        return PurchasesPaginatedResponse.builder()
                .purchases(purchaseDtoPage.getContent())
                .totalItems(purchaseDtoPage.getNumberOfElements())
                .totalPages(purchaseDtoPage.getTotalPages())
                .build();
    }


    public PurchaseDTO getById(Long id) {
        return this.purchaseRepository.findPurchaseAsDTOById(id)
                .map(p -> p.addProducts(this.purchaseRepository.findProductsAsProjectionsByPurchaseId(p.getId())))
                .orElseThrow(() -> new NotFoundException("Purchase " + id + " not found"));
    }

    public PurchaseDTO createOne(PurchaseDTO body) {

        Purchase purchase = this.purchaseRepository.save(
                Purchase.builder()
                        .customer(this.customerRepository.findActiveCustomer(body.getCustomerId())
                                .orElseThrow(() -> new NotFoundException("Customer " + body.getCustomerId() + " not found")))
                        .payment(body.getPayment())
                        .state(body.getState())
                        .build());

        List<PurchaseProduct> purchasesProducts = body.getProducts()
                .stream()
                .map(pp -> {
                    Product product = this.productRepository.getByIdAndHasStockIsTrue(pp.getId())
                            .orElseThrow(() -> new NotFoundException("Product " + pp.getId() + " not found"));

                    return PurchaseProduct.builder()
                            .product(product)
                            .purchase(purchase)
                            .quantity(pp.getQuantity())
                            .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(pp.getQuantity())))
                            .build();
                }).collect(Collectors.toList());

        List<PurchaseProduct> purchaseProductsSaves =
                this.purchasesProductsRepository.saveAll(purchasesProducts);

        purchase.setPurchaseProducts(purchaseProductsSaves);
        return this.purchaseDtoMapper.apply(this.purchaseRepository.save(purchase));
    }


    public PurchaseDTO updateById(Long id, PurchaseDTO body) {
        Purchase purchase = this.purchaseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Purchase " + id + " not found"));

        if (body.getPayment() != null) {
            purchase.setPayment(body.getPayment());
        }
        if (body.getState() != null && !body.getState().isEmpty()) {
            purchase.setState(body.getState());
        }
        return this.purchaseDtoMapper.apply(this.purchaseRepository.save(purchase));

    }

}
