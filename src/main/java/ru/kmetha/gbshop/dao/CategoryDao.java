package ru.kmetha.gbshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kmetha.gbshop.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {

}
