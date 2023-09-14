package com.treshermanitos.treshermanitos.product;


import com.fasterxml.jackson.annotation.JsonView;
import com.treshermanitos.treshermanitos.category.Category;
import com.treshermanitos.treshermanitos.category.CategoryRepository;
import com.treshermanitos.treshermanitos.config.Views;
import com.treshermanitos.treshermanitos.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductoDtoMapper productoDtoMapper;
    private final CategoryRepository categoryRepository;


    public ProductsPaginatedResponse getAll(Pageable pageable) {
        Page<ProductDTO> data = this.productRepository.getAllByHasStockIsTrue(pageable)
                .map(this.productoDtoMapper);

        return ProductsPaginatedResponse.builder()
                .products(data.getContent())
                .totalItems(data.getNumberOfElements())
                .totalPages(data.getTotalPages())
                .build();
    }



    public ProductDTO getById(Long idLong) {
        Product product = this.productRepository.getByIdAndHasStockIsTrue(idLong)
                .orElseThrow(() -> new NotFoundException(" Product " + idLong + " not found"));
        return this.productoDtoMapper.apply(product);
    }

    public ProductDTO createOne(ProductRequest body) {

        Category category = this.categoryRepository.findById(body.getCategory())
                .orElseThrow(() -> new NotFoundException(" Category " + body.getCategory() + " not found"));


        Product product = Product.builder()
                .name(body.getName())
                .description(body.getDescription())
                .price(body.getPrice())
                .category(category)
                .img(body.getImg())
                .stock(body.getStock())
                .fav(body.getFav())
                .build();

        return this.productoDtoMapper.apply(this.productRepository.save(product));
    }

    public ProductDTO updateById(Long id, ProductDTO body) {
        Product customer = this.productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product " + id + " not found "));

        if (body.getName() != null) {
            customer.setName(body.getName());
        }


        if (body.getDescription() != null) {
            customer.setDescription(body.getDescription());
        }


        if (body.getPrice() != null) {
            customer.setPrice(body.getPrice());
        }
        if (body.getImg() != null) {
            customer.setImg(body.getImg());
        }

        if (body.getStock() != null) {
            customer.setStock(body.getStock());
        }

        if (body.getFav() != null) {
            customer.setFav(body.getFav());
        }

        return this.productoDtoMapper
                .apply(this.productRepository.save(customer));

    }

    public void deleteById(Long id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product " + id + " not found "));
        product.setStock(0l);
        this.productRepository.save(product);

    }


}