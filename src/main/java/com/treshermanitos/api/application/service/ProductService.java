package com.treshermanitos.api.application.service;

import com.treshermanitos.api.application.repository.UploadFileRepository;
import com.treshermanitos.api.application.dto.ProductAddDTO;
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
    private final UploadFileRepository uploadFileRepository;

    public Page<ProductDTO> getAll(Pageable pageable, int categoryId) {
        return this.productRepository.findAllByHasStockIsTrue(pageable, categoryId)
                .map(this.productDtoMapper::toDto);
    }

    public ProductDTO getById(Long idLong) {
        return this.productDtoMapper.toDto
                (this.productRepository.findByIdAndHasStockIsTrue(idLong)
                        .orElseThrow(() -> new NotFoundException(" Product " + idLong + " not found")));
    }

    public ProductDTO createOne(ProductAddDTO product) {
        Category category = this.categoryRepository
                .findByIdAndStateIsTrue(product.getCategory())
                .orElseThrow
                (() -> new NotFoundException(" Category " + product.getCategory() + " not found"));

        String imgUrl = this.uploadFileRepository.uploadImage(product.getImg());

        var pp = this.productRepository.save
                (Product.builder()
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .img(imgUrl)
                        .category(category)
                        .stock(product.getStock())
                        .fav(product.getFav())
                        .build());

        return this.productDtoMapper.toDto(pp);
    }

    public ProductDTO updateById(Long id, ProductAddDTO productDTO){

        Product product = this.productRepository
                .findByIdAndHasStockIsTrue(id)
                .orElseThrow
                (() -> new NotFoundException(" Product " + id + " not found"));


        if(productDTO.getName() != null && !product.getName().isBlank()){
            product.setName(productDTO.getName());
        }
        if(productDTO.getDescription() != null && !product.getDescription().isBlank()){
            product.setDescription(productDTO.getDescription());
        }
        if(productDTO.getPrice() != null){
            product.setPrice(productDTO.getPrice());
        }
        if(productDTO.getCategory() != null){

            Category category = this.categoryRepository
                    .findByIdAndStateIsTrue(productDTO.getCategory())
                    .orElseThrow
                    (() -> new NotFoundException(" Category " + productDTO.getCategory() + " not found"));

            product.setCategory(category);
        }

        if(productDTO.getImg() != null){
            this.uploadFileRepository.overrideImage(productDTO.getImg(),product.getImg());
        }

        if(productDTO.getStock() != null){
            product.setStock(productDTO.getStock());
        }

        if(productDTO.getFav() != null){
            product.setFav(product.getFav());
        }

        return this.productDtoMapper.toDto
                (this.productRepository.save(product));
    }


    public ProductDTO deleteById(long id) {

        Product product = this.productRepository
                .findByIdAndHasStockIsTrue(id)
                .orElseThrow
                        (() -> new NotFoundException(" Product " + id + " not found"));

        product.setStock(0l);

        return this.productDtoMapper.toDto
                (this.productRepository.save(product));
    }
}
