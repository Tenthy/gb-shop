package ru.kmetha.gbshop.web.dto.mapper;

import org.mapstruct.Mapper;
import ru.kmetha.gbapi.manufacturer.dto.ManufacturerDto;
import ru.kmetha.gbshop.entity.Manufacturer;

@Mapper
public interface ManufacturerMapper {
    Manufacturer toManufacturer(ManufacturerDto manufacturerDto);

    ManufacturerDto toManufacturerDto(Manufacturer manufacturer);
}
