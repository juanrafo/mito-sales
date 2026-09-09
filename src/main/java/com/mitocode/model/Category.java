package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
//@Table(name = "tbl_category", schema = "sistemas")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idCategory; //camelCase | lowerCamelCase UpperCamelCase | snake_case

    @Column(length = 50, nullable = false)//, name = "xxxx")
    private String name;

    @Column(length = 150)
    private String description;

    @Column(nullable = false)
    private boolean enabled;

    public Category(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }
}
