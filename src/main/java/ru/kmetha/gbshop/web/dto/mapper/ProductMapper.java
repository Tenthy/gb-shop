package ru.kmetha.gbshop.web.dto.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kmetha.gbapi.product.dto.ProductDto;
import ru.kmetha.gbshop.dao.CategoryDao;
import ru.kmetha.gbshop.dao.ManufacturerDao;
import ru.kmetha.gbshop.entity.Manufacturer;
import ru.kmetha.gbshop.entity.Product;

import java.util.NoSuchElementException;

@Mapper(uses = ManufacturerMapper.class)
public interface ProductMapper {
    Product toProduct(ProductDto productDto, @Context ManufacturerDao manufacturerDao, @Context CategoryDao categoryDao);

    ProductDto toProductDto(Product product);

//    @Mapping(source = "manufacturer", target = "manufacturerDto")
//    ProductManufacturerDto toProductManufacturerDto(Product product);

    default Manufacturer getManufacturer(String manufacturer, @Context ManufacturerDao manufacturerDao) {
        return manufacturerDao.findByName(manufacturer).orElseThrow(
                () -> new NoSuchElementException("There isn't manufacturer with name " + manufacturer)
        );
    }

    default String getManufacturer(Manufacturer manufacturer) {
        return manufacturer.getName();
    }
}
