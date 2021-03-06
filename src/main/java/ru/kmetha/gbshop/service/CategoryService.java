package ru.kmetha.gbshop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kmetha.gbapi.category.dto.CategoryDto;
import ru.kmetha.gbshop.dao.CategoryDao;
import ru.kmetha.gbshop.entity.Category;
import ru.kmetha.gbshop.web.dto.mapper.CategoryMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryDao categoryDao;
    private final CategoryMapper categoryMapper;

    public CategoryDto save(CategoryDto categoryDto) {
        Category category = categoryMapper.toCategory(categoryDto);
        if (category.getId() != null) {
            categoryDao.findById(categoryDto.getId()).ifPresent(
                    (p) -> category.setTitle(p.getTitle())
            );
        }
        return categoryMapper.toCategoryDto(categoryDao.save(category));
    }

    public List<CategoryDto> findAll() {
        return categoryDao.findAll().stream()
                .map(categoryMapper::toCategoryDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDto findById(Long id) {
        return categoryMapper.toCategoryDto(categoryDao.findById(id).orElse(null));
    }

    public void deleteById(Long id) {
        try {
            categoryDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
        }
    }
}
