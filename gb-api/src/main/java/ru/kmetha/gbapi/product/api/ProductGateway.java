package ru.kmetha.gbapi.product.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kmetha.gbapi.product.dto.ProductDto;

import java.util.List;

public interface ProductGateway {

    @GetMapping
    List<ProductDto> getProductList();

    @GetMapping("/{productId}")
    ResponseEntity<ProductDto> getProduct(@PathVariable("productId") Long id);

    @PostMapping
    ResponseEntity<ProductDto> handlePost(@Validated @RequestBody ProductDto productDto);

    @PutMapping("/{productId}")
    ResponseEntity<ProductDto> handleUpdate(@PathVariable("productId") Long id,
                                   @Validated @RequestBody ProductDto productDto);

    @DeleteMapping("/{productId}")
    void deleteById(@PathVariable("productId") Long id);
}
