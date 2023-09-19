package com.treshermanitos.category;

import com.treshermanitos.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDtoMapper categoryDtoMapper;


    public CategoryPaginatedResponse getAll(Pageable pageable) {
        Page<Category> data = this.categoryRepository.findAllByStateIsTrue(pageable);

        List<CategoryDTO> categoriesDto = data.getContent()
                .stream().map(this.categoryDtoMapper).collect(Collectors.toList());

        return CategoryPaginatedResponse.builder()
                .categoriesDto(categoriesDto)
                .totalItems(data.getNumberOfElements())
                .totalPages(data.getTotalPages())
                .build();
    }


    public CategoryDTO getById(Long idLong) {
        Category customer = this.categoryRepository.findByIdAndStateIsTrue(idLong)
                .orElseThrow(() -> new NotFoundException(" category " + idLong + " not found"));
        return this.categoryDtoMapper.apply(customer);
    }

    public CategoryDTO createOne(CategoryDTO body) {
        Category category = Category.builder()
                .name(body.getName())
                .img(body.getImg())
                .build();

        return this.categoryDtoMapper.apply(this.categoryRepository.save(category));
    }

    public CategoryDTO updateById(Long id, CategoryDTO body) {
        Category category = this.categoryRepository.findByIdAndStateIsTrue(id)
                .orElseThrow(() -> new NotFoundException("category " + id + " not found "));

        if (body.getImg() != null) {
            category.setImg(body.getImg());
        }


        if (body.getName() != null) {
            category.setName(body.getName());
        }


        return this.categoryDtoMapper
                .apply(this.categoryRepository.save(category));

    }


    public void deleteById(Long id){
        Category category = this.categoryRepository.findByIdAndStateIsTrue(id)
                .orElseThrow(() -> new NotFoundException("category " + id + " not found "));
        category.setState(false);
        this.categoryRepository.save(category);
    }

}


