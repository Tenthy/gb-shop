package ru.kmetha.externalapi.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kmetha.gbapi.category.api.CategoryGateway;
import ru.kmetha.gbapi.category.dto.CategoryDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryRestController {

    private final CategoryGateway categoryGateway;

    @GetMapping
    public List<CategoryDto> findAll() {
        return categoryGateway.findAll();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable("categoryId") Long id) {
        return categoryGateway.getCategory(id);
    }

    @PostMapping
    public ResponseEntity<?> handlePost(@Validated @RequestBody CategoryDto categoryDto) {
        return categoryGateway.handlePost(categoryDto);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> handleUpdate(@PathVariable("categoryId") Long id,
                                          @Validated @RequestBody CategoryDto categoryDto) {
        return categoryGateway.handleUpdate(id, categoryDto);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteById(@PathVariable("categoryId") Long id) {
        categoryGateway.deleteById(id);
    }
}
