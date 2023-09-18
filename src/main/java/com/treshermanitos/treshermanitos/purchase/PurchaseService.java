package com.treshermanitos.treshermanitos.purchase;

import com.treshermanitos.treshermanitos.exceptions.NotFoundException;
import com.treshermanitos.treshermanitos.purchase.PurchaseDto.ProductProjection;
import com.treshermanitos.treshermanitos.purchase.PurchaseDto.PurchaseDTO;
import com.treshermanitos.treshermanitos.purchase.PurchaseDto.PurchaseDtoMapper;
import com.treshermanitos.treshermanitos.purchase.PurchaseProjection.PurchaseProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

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

        Page<PurchaseDTO> dataMapped = this.purchaseReporitory.findAllDefinitive(pageable)
                .map(i -> {
                    PurchaseDTO pDto = new PurchaseDTO(i);
                    pDto.setProducts(
                            this.purchaseReporitory.findProductsByPurchaseId(i.getId()));
                    return pDto;
                });


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

    public PurchasesPaginatedResponse getAllClosedProjectionFaster(Pageable pageable) {

        Page<PurchaseDTO> dataMapped =
                this.purchaseReporitory.findAllByClosedProjectionFaster(pageable)
                        .map(PurchaseDTO::new);

        return PurchasesPaginatedResponse.builder()
                .purchases(dataMapped.getContent())
                .totalItems(dataMapped.getNumberOfElements())
                .totalPages(dataMapped.getTotalPages())
                .build();
    }
    public PurchasesPaginatedResponse getAllClassInstanced(Pageable pageable) {


        Page<PurchaseDTO> dataMapped =
                this.purchaseReporitory.findAllClassInstanced(pageable)
                        .map(i -> i.addProducts(this.purchaseReporitory.findProductsByPurchaseId(i.getId())));

        return PurchasesPaginatedResponse.builder()
                .purchases(dataMapped.getContent())
                .totalItems(dataMapped.getNumberOfElements())
                .totalPages(dataMapped.getTotalPages())
                .build();
    }


    public PurchaseDTO getById(Long id) {
        Purchase purchase = this.purchaseReporitory.findById(id).orElseThrow(() -> new NotFoundException("Purchase " + id +
                " " +
                "not " +
                "found"));
        return this.purchaseDtoMapper.apply(purchase);

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
