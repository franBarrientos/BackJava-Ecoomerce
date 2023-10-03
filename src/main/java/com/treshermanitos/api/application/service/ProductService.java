package com.treshermanitos.api.application.service;

import com.treshermanitos.api.application.dto.ProductDTO;
import com.treshermanitos.api.application.exceptions.NotFoundException;
import com.treshermanitos.api.application.mapper.ProductDtoMapper;
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
    private final ProductDtoMapper productDtoMapper;

    public Page<ProductDTO> getAll(Pageable pageable, int categoryId) {
        return this.productRepository.findAllByHasStockIsTrue(pageable, categoryId)
                .map(this.productDtoMapper::toDto);
    }

    public ProductDTO getById(Long idLong) {
        return this.productDtoMapper.toDto
                (this.productRepository.findByIdAndHasStockIsTrue(idLong)
                .orElseThrow(() -> new NotFoundException(" Product " + idLong + " not found")));
    }

    public ProductDTO createOne(ProductDTO product){
        Category category = this.categoryRepository.findByIdAndStateIsTrue(product.getCategory().getId())
                .orElseThrow(() -> new NotFoundException(" Category " + product.getCategory() + " not found"));

        return this.productDtoMapper.toDto
                (this.productRepository.save(this.productDtoMapper.toDomain(product)));
    }

}
