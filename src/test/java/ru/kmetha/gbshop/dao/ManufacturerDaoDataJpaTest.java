package ru.kmetha.gbshop.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.kmetha.gbshopmay.config.ShopConfig;
import ru.kmetha.gbshopmay.entity.Manufacturer;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(ShopConfig.class)
class ManufacturerDaoDataJpaTest {

    public static final String APPLE_COMPANY_NAME = "Apple";
    public static final String MICROSOFT_COMPANY_NAME = "Microsoft";
    public static final String GOOGLE_COMPANY_NAME = "Google";

    @Autowired
    ManufacturerDao manufacturerDao;

    @Test
    public void saveTest() {
        Manufacturer savedManufacturer = manufacturerDao.save(Manufacturer.builder()
                .name(APPLE_COMPANY_NAME)
                .build());

        assertAll(
                () -> assertEquals(1L, savedManufacturer.getId()),
                () -> assertEquals(APPLE_COMPANY_NAME, savedManufacturer.getName()),
                () -> assertEquals(0, savedManufacturer.getVersion()),
                () -> assertEquals("User", savedManufacturer.getCreatedBy()),
                () -> assertNotNull(savedManufacturer.getCreatedDate()),
                () -> assertEquals("User", savedManufacturer.getLastModifiedBy()),
                () -> assertNotNull(savedManufacturer.getLastModifiedDate())
        );
    }

    @Test
    public void deleteTest() {
        Manufacturer google = manufacturerDao.save(Manufacturer.builder()
                .id(1L)
                .name(GOOGLE_COMPANY_NAME)
                .build());

        manufacturerDao.delete(google);

        Optional<Manufacturer> byId = manufacturerDao.findById(1L);

        assertAll(
                () -> assertEquals (Optional.empty() , byId)
        );
    }

    @Test
    public void findAllTest() {
        manufacturerDao.save(Manufacturer.builder()
                .name(APPLE_COMPANY_NAME)
                .build());
        manufacturerDao.save(Manufacturer.builder()
                .name(MICROSOFT_COMPANY_NAME)
                .build());

        List<Manufacturer> manufacturerList = manufacturerDao.findAll();

        assertAll(
                () -> assertEquals(1L, manufacturerList.get(0).getId()),
                () -> assertEquals(2L, manufacturerList.get(1).getId()),

                () -> assertEquals(APPLE_COMPANY_NAME, manufacturerList.get(0).getName()),
                () -> assertEquals(MICROSOFT_COMPANY_NAME, manufacturerList.get(1).getName()),

                () -> assertEquals(0, manufacturerList.get(0).getVersion()),
                () -> assertEquals(0, manufacturerList.get(1).getVersion()),

                () -> assertEquals("User", manufacturerList.get(0).getCreatedBy()),
                () -> assertEquals("User", manufacturerList.get(1).getCreatedBy()),

                () -> assertNotNull(manufacturerList.get(0).getCreatedDate()),
                () -> assertNotNull(manufacturerList.get(1).getCreatedDate())
        );
    }
}