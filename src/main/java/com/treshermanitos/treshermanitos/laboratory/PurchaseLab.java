package com.treshermanitos.treshermanitos.laboratory;


import com.treshermanitos.treshermanitos.purchase.PurchaseDto.PurchaseDTO;
import com.treshermanitos.treshermanitos.purchase.PurchaseDto.PurchaseDtoMapper;
import com.treshermanitos.treshermanitos.purchase.PurchaseReporitory;
import com.treshermanitos.treshermanitos.purchase.PurchasesPaginatedResponse;
import com.treshermanitos.treshermanitos.purchase.projections.ProductProjectionClass;
import com.treshermanitos.treshermanitos.purchase.PurchaseProjection.PurchaseProjection;
import com.treshermanitos.treshermanitos.purchase.projections.PurchaseProjectionClass;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PurchaseLab {

    private static final Pageable pageable = PageRequest.of(0, 15);
    private final PurchaseReporitory purchaseReporitory;
    private final PurchaseDtoMapper purchaseDtoMapper;




    public PurchasesPaginatedResponse getAll() {
        long inicio = System.currentTimeMillis();

        Page<PurchaseDTO> dataMapped = this.purchaseReporitory.findAll(pageable)
                .map(this.purchaseDtoMapper);
        PurchasesPaginatedResponse purchasesPaginatedResponse = PurchasesPaginatedResponse.builder()
                .purchases(dataMapped.getContent())
                .totalItems(dataMapped.getNumberOfElements())
                .totalPages(dataMapped.getTotalPages())
                .build();

        long fin = System.currentTimeMillis();
        long tiempoEjecucion = fin - inicio;

        // Puedes registrar el tiempo de ejecución en un registro o base de datos
        registrarTiempoEjecucion("getAll", tiempoEjecucion);

        return purchasesPaginatedResponse; // Devuelve la respuesta del caso de uso
    }


    public PurchasesPaginatedResponse getAllDefinitive() {
        long inicio = System.currentTimeMillis();

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

        PurchasesPaginatedResponse purchasesPaginatedResponse =  PurchasesPaginatedResponse.builder()
                .purchases(dataMapped.getContent())
                .totalItems(dataMapped.getNumberOfElements())
                .totalPages(dataMapped.getTotalPages())
                .build();

        long fin = System.currentTimeMillis();
        long tiempoEjecucion = fin - inicio;

        // Puedes registrar el tiempo de ejecución en un registro o base de datos
        registrarTiempoEjecucion("getAllDefinitive", tiempoEjecucion);

        return purchasesPaginatedResponse; // Devuelve la respuesta del caso de uso
    }

    public PurchasesPaginatedResponse getAllClosedProjection() {
        long inicio = System.currentTimeMillis();

        Page<PurchaseProjection> dataMapped = this.purchaseReporitory.findAllBy(pageable);


        PurchasesPaginatedResponse purchasesPaginatedResponse =  PurchasesPaginatedResponse.builder()
                .purchases(dataMapped.getContent())
                .totalItems(dataMapped.getNumberOfElements())
                .totalPages(dataMapped.getTotalPages())
                .build();

        long fin = System.currentTimeMillis();
        long tiempoEjecucion = fin - inicio;

        // Puedes registrar el tiempo de ejecución en un registro o base de datos
        registrarTiempoEjecucion("getAllClosedProjection", tiempoEjecucion);

        return purchasesPaginatedResponse; // Devuelve la respuesta del caso de uso
    }

    public PurchasesPaginatedResponse getAllClosedProjectionFaster() {
        long inicio = System.currentTimeMillis();

        Page<PurchaseProjectionClass> dataMapped =
                this.purchaseReporitory.findAllByClosedProjectionFaster(pageable)
                        .map(i -> new PurchaseProjectionClass(i.getId(), i.getPayment(), i.getCustomerId(),
                                i.getDni(), i.getAddres(), i.getUserId(), i.getFirstName(), i.getLastName(), i.getProducts()));


        PurchasesPaginatedResponse purchasesPaginatedResponse =  PurchasesPaginatedResponse.builder()
                .purchases(dataMapped.getContent())
                .totalItems(dataMapped.getNumberOfElements())
                .totalPages(dataMapped.getTotalPages())
                .build();

        long fin = System.currentTimeMillis();
        long tiempoEjecucion = fin - inicio;

        // Puedes registrar el tiempo de ejecución en un registro o base de datos
        registrarTiempoEjecucion("getAllClosedProjectionFaster", tiempoEjecucion);

        return purchasesPaginatedResponse; // Devuelve la respuesta del caso de uso
    }






    private void registrarTiempoEjecucion(String name, long tiempoEjecucion) {
        System.out.println(name+" - Time :"+ tiempoEjecucion);
    }
}