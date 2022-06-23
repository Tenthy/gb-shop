package ru.kmetha.gbapimay.category.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kmetha.gbapimay.category.dto.CategoryDto;

import java.util.List;

public interface CategoryGateway {

    @GetMapping
    public List<CategoryDto> findAll();

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable("categoryId") Long id);

    @PostMapping
    public ResponseEntity<?> handlePost(@Validated @RequestBody CategoryDto categoryDto);

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> handleUpdate(@PathVariable("categoryId") Long id,
                                          @Validated @RequestBody CategoryDto categoryDto);

    @DeleteMapping("/{categoryId}")
    public void deleteById(@PathVariable("categoryId") Long id);
}
