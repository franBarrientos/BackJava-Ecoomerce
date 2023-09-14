package com.treshermanitos.treshermanitos.purchase;

import com.treshermanitos.treshermanitos.exceptions.NotFoundException;
import com.treshermanitos.treshermanitos.product.ProductRepository;
import com.treshermanitos.treshermanitos.purchase.projections.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseReporitory purchaseReporitory;
    private final PurchaseDtoMapper purchaseDtoMapper;

    public PurchasesPaginatedResponse getAll(Pageable pageable) {
        Page<PurchaseDTO> dataMapped = this.purchaseReporitory.findAll(pageable)
                .map(this.purchaseDtoMapper);
        return PurchasesPaginatedResponse.builder()
                .purchases(dataMapped.getContent())
                .totalItems(dataMapped.getNumberOfElements())
                .totalPages(dataMapped.getTotalPages())
                .build();
    }

    public PurchasesPaginatedResponse getAllDefinitive(Pageable pageable) {
        Page<PurchaseProjectionClass> dataMapped = this.purchaseReporitory.findAllDefinitive(pageable)
                .map(i -> new PurchaseProjectionClass(
                        i.getId(),
                        i.getPayment(),
                        i.getCustomerId(),
                        i.getDni(),
                        i.getAddres(),
                        i.getUserId(),
                        i.getFirstName(),
                        i.getLastName(),
                        this.purchaseReporitory.findProductsByPurchaseId(i.getId())
                                .stream()
                                .map(p->new ProductProjectionClass(p.getId(), p.getName()))
                                .collect(Collectors.toList())
                ));

        return PurchasesPaginatedResponse.builder()
                .purchases(dataMapped.getContent())
                .totalItems(dataMapped.getNumberOfElements())
                .totalPages(dataMapped.getTotalPages())
                .build();
    }




    public PurchasesPaginatedResponse getAllClosedProjection(Pageable pageable) {
        Page<PurchaseProjection> dataMapped = this.purchaseReporitory.findAllBy(pageable);
        return PurchasesPaginatedResponse.builder()
                .purchases(dataMapped.getContent())
                .totalItems(dataMapped.getNumberOfElements())
                .totalPages(dataMapped.getTotalPages())
                .build();
    }


    public PurchasesPaginatedResponse getAllClosedProjectionRepeat(Pageable pageable) {
        Page<PurchaseProjectionFaster> dataMapped = this.purchaseReporitory.findAllByButRepeat(pageable);
        return PurchasesPaginatedResponse.builder()
                .purchases(dataMapped.getContent())
                .totalItems(dataMapped.getNumberOfElements())
                .totalPages(dataMapped.getTotalPages())
                .build();
    }


    public PurchasesPaginatedResponse getAllClosedProjectionFaster(Pageable pageable) {
        Page<PurchaseProjectionClass> dataMapped =
                this.purchaseReporitory.findAllByClosedProjectionFaster(pageable)
                        .map(i -> new PurchaseProjectionClass(i.getId(), i.getPayment(), i.getCustomerId(),
                                i.getDni(), i.getAddres(), i.getUserId(), i.getFirstName(), i.getLastName(), i.getProducts()));

        return PurchasesPaginatedResponse.builder()
                .purchases(dataMapped.getContent())
                .totalItems(dataMapped.getNumberOfElements())
                .totalPages(dataMapped.getTotalPages())
                .build();
    }


    public List<PurchaseProjectionClass> getAllNamedQuery(Pageable pageable) {
        return this.purchaseReporitory.findAllByNamedQuery();
    }

    public PurchaseDTO getById(Long id) {
        Purchase purchase = this.purchaseReporitory.findById(id).orElseThrow(() -> new NotFoundException("Purchase " + id +
                " " +
                "not " +
                "found"));
        return this.purchaseDtoMapper.apply(purchase);

    }

    public PurchaseDTOCustom getByIdFast(Long id) {
        return this.purchaseReporitory.findByIdFast(id).orElseThrow(() -> new NotFoundException("Purchase " + id +
                " " +
                "not " +
                "found"));
    }

    public PurchaseDTO updateById(Long id, PurchaseDTO body) {
        Purchase purchase = this.purchaseReporitory.findById(id)
                .orElseThrow(() -> new NotFoundException("Purchase " + id + " not found"));

        if (body.getPayment() != null) {
            purchase.setPayment(body.getPayment());
        }
        if (body.getState() != null && !body.getState().isEmpty()) {
            purchase.setState(body.getState());
        }
        return this.purchaseDtoMapper.apply(this.purchaseReporitory.save(purchase));

    }
    /*
    public UserDTO createOne(UserDTO body) {
        throw new UnsupportedOperationException("Unimplemented method 'createOne'");
    }*/
}
