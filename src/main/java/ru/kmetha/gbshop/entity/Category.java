package ru.kmetha.gbshop.entity;

import lombok.*;
import ru.kmetha.gbshop.entity.common.BaseEntity;
import ru.kmetha.gbshop.entity.common.InfoEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category extends InfoEntity {

    @Column(name = "title")
    private String title;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

    @Builder
    public Category(int version, String createdBy, LocalDateTime createdDate, String lastModifiedBy,
                    LocalDateTime lastModifiedDate, String title, Set<Product> products) {
        super(version, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
        this.title = title;
        this.products = products;
    }
}
