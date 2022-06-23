package ru.kmetha.gbshop.web.dto.mapper;

import org.mapstruct.Mapper;
import ru.kmetha.gbshop.entity.Category;
import ru.kmetha.gbapi.category.dto.CategoryDto;

@Mapper
public interface CategoryMapper {

    Category toCategory(CategoryDto categoryDto);

    CategoryDto toCategoryDto(Category category);
}
