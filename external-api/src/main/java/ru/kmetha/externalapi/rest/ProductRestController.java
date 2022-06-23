package ru.kmetha.externalapi.rest;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kmetha.gbapi.category.api.CategoryGateway;
import ru.kmetha.gbapi.manufacturer.api.ManufacturerGateway;
import ru.kmetha.gbapi.product.api.ProductGateway;
import ru.kmetha.gbapi.product.dto.ProductDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductRestController {

    private final ProductGateway productGateway;
    private final ManufacturerGateway manufacturerGateway;

    @GetMapping
    public List<ProductDto> getProductList() {
        return productGateway.getProductList();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable("productId") Long id) {
        return productGateway.getProduct(id);
    }

    @PostMapping
    public ResponseEntity<?> handlePost(@Validated @RequestBody ProductDto productDto) {
        if (productDto.getManufacturer() != null) {
            return productGateway.handlePost(productDto);
        }
        return  null;
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> handleUpdate(@PathVariable("productId") Long id,
                                          @Validated @RequestBody ProductDto productDto) {
        return productGateway.handleUpdate(id, productDto);
    }

    @DeleteMapping("/{productId}")
    public void deleteById(@PathVariable("productId") Long id) {
        productGateway.deleteById(id);
    }
}
