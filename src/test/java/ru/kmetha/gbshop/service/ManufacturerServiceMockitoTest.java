package ru.kmetha.gbshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kmetha.gbshopmay.dao.ManufacturerDao;
import ru.kmetha.gbshopmay.entity.Manufacturer;
import ru.kmetha.gbshopmay.web.dto.ManufacturerDto;
import ru.kmetha.gbshopmay.web.dto.mapper.ManufacturerMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ManufacturerServiceMockitoTest {

    public static final String APPLE_COMPANY_NAME = "Apple";
    public static final String MICROSOFT_COMPANY_NAME = "Microsoft";

    @Mock
    ManufacturerDao manufacturerDao;

    @Mock
    ManufacturerMapper manufacturerMapper;

    @InjectMocks
    ManufacturerService manufacturerService;

    List<Manufacturer> manufacturers;

    @BeforeEach
    void setUp() {
        manufacturers = new ArrayList<>();
        manufacturers.add(Manufacturer.builder()
                .id(1L)
                .name(APPLE_COMPANY_NAME).build());
        manufacturers.add(Manufacturer.builder()
                .id(2L)
                .name(MICROSOFT_COMPANY_NAME).build());
    }

    @Test
    void findAllTest() {
        //given
        given(manufacturerDao.findAll()).willReturn(manufacturers);
        given(manufacturerMapper.toManufacturerDto(any())).will(
                (invocation) -> {
                    Manufacturer manufacturer = (Manufacturer) invocation.getArgument(0);
                    if (manufacturer == null) {
                        return null;
                    }
                    return ManufacturerDto.builder()
                            .id(manufacturer.getId())
                            .name(manufacturer.getName())
                            .build();
                });
        final int manufactirersSize = manufacturers.size();

        //when
        List<ManufacturerDto> manufaturerList = manufacturerService.findAll();

        //then
        then(manufacturerDao).should().findAll();

        assertAll(
                () -> assertEquals(manufactirersSize, manufaturerList.size(), "Size must be equals: " + manufactirersSize),
                () -> assertEquals(APPLE_COMPANY_NAME, manufaturerList.get(0).getName())
        );
    }

    @Test
    void saveTest() {
        ManufacturerDto savedManufacturer = new ManufacturerDto(1L, "Google Inc.");

        //given
        given(manufacturerMapper.toManufacturer(any())).will(
                (invocation) -> {
                    ManufacturerDto manufacturerDto = (ManufacturerDto) invocation.getArgument(0);
                    if (manufacturerDto == null) {
                        return null;
                    }
                    return Manufacturer.builder()
                            .id(manufacturerDto.getId())
                            .name(manufacturerDto.getName())
                            .build();
                });
        given(manufacturerMapper.toManufacturerDto(any())).will(
                (invocation) -> {
                    Manufacturer manufacturer = (Manufacturer) invocation.getArgument(0);
                    if (manufacturer == null) {
                        return null;
                    }
                    return ManufacturerDto.builder()
                            .id(manufacturer.getId())
                            .name(manufacturer.getName())
                            .build();
                });

        //when
        manufacturerService.save(savedManufacturer);

        //then
        then(manufacturerDao).should().save(any());
    }

    @Test
    void deleteByIdTest() {
        //when
        manufacturerService.deleteById(1L);

        //then
        then(manufacturerDao).should().deleteById(1L);
    }
}