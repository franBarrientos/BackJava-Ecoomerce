package com.treshermanitos.api.application.service;

import com.treshermanitos.api.application.exceptions.NotFoundException;
import com.treshermanitos.api.application.repository.CategoryRepository;
import com.treshermanitos.api.application.repository.ProductRepository;
import com.treshermanitos.api.domain.Category;
import com.treshermanitos.api.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Page<Product> getAll(Pageable pageable) {
        return this.productRepository.findAllByHasStockIsTrue(pageable);
    }

    public Product getById(Long idLong) {
        return this.productRepository.findByIdAndHasStockIsTrue(idLong)
                .orElseThrow(() -> new NotFoundException(" Product " + idLong + " not found"));
    }

    public Product createOne(Product product){
        Category category = this.categoryRepository.findByIdAndStateIsTrue(product.getCategory().getId())
                .orElseThrow(() -> new NotFoundException(" Category " + product.getCategory() + " not found"));
        return this.productRepository.save(product);
    }

}
