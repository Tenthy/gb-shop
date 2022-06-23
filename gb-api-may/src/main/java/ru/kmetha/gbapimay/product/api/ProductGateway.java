package ru.kmetha.gbapimay.product.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kmetha.gbapimay.product.dto.ProductDto;

import java.util.List;

public interface ProductGateway {

    @GetMapping
    List<ProductDto> getProductList();

    @GetMapping("/{productId}")
    default ResponseEntity<ProductDto> getProduct(@PathVariable("productId") Long id) {
        return null;
    }

    @PostMapping
    ResponseEntity<ProductDto> handlePost(@Validated @RequestBody ProductDto productDto);

    @PutMapping("/{productId}")
    ResponseEntity<ProductDto> handleUpdate(@PathVariable("productId") Long id,
                                   @Validated @RequestBody ProductDto productDto);

    @DeleteMapping("/{productId}")
    void deleteById(@PathVariable("productId") Long id);
}
