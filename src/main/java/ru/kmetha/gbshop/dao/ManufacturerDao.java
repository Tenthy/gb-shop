package ru.kmetha.gbshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kmetha.gbshop.entity.Manufacturer;

import java.util.Optional;

public interface ManufacturerDao extends JpaRepository<Manufacturer, Long> {

    Manufacturer findByNameLike(String name);

    Optional<Manufacturer> findByName(String name);
}
