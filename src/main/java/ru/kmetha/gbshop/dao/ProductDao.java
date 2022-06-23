package ru.kmetha.gbshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kmetha.gbshop.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao extends JpaRepository<Product, Long> {

    Optional<Product> findByTitle(String title);

    List<Product> findAllByTitleContaining(String title);
}
