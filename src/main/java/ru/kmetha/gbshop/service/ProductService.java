package ru.kmetha.gbshop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kmetha.gbapi.product.dto.ProductDto;
import ru.kmetha.gbshop.dao.ManufacturerDao;
import ru.kmetha.gbshop.dao.ProductDao;
import ru.kmetha.gbshop.entity.Product;
import ru.kmetha.gbshop.entity.enums.Status;
import ru.kmetha.gbshop.web.dto.mapper.ProductMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductDao productDao;
    private final ProductMapper productMapper;
    private final ManufacturerDao manufacturerDao;

    public ProductDto save(ProductDto productDto) {
        Product product = productMapper.toProduct(productDto, manufacturerDao);
        if (product.getId() != null) {
            productDao.findById(productDto.getId()).ifPresent(
                    (p) -> product.setVersion(p.getVersion())
            );
        }
        return productMapper.toProductDto(productDao.save(product));
    }


    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        return productMapper.toProductDto(productDao.findById(id).orElse(null));
    }

    public List<ProductDto> findAll() {
        return productDao.findAll().stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        try {
            productDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
        }
    }

    public void disable(Long id) {
        Optional<Product> product = productDao.findById(id);
        product.ifPresent(p -> {
            p.setStatus(Status.DISABLED);
            productDao.save(p);
        });
    }


//    public List<ProductManufacturerDto> findFullInfo() {
//        return productDao.findAll()
//                .stream()
//                .map(productMapper::toProductManufacturerDto)
//                .collect(Collectors.toList());
//    }
}
