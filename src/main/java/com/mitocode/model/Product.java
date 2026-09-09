package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity//(name = "ENTITY_PRODUCT")
public class Product {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduct;

    @ManyToOne //FK
    @JoinColumn(name = "id_category", nullable = false, foreignKey = @ForeignKey(name = "fk_product_category"))
    private Category category; //JPQL

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, columnDefinition = "DECIMAL(6,2)") //PRECISION, SCALE //9999.99
    private BigDecimal price;

    @Column(nullable = false, length = 150)
    private String description;

    @Column(nullable = false)
    private boolean enabled;
}
