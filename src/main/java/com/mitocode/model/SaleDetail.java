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
@Entity
public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idSaleDetail;

    @ManyToOne
    @JoinColumn(name = "id_sale", nullable = false, foreignKey = @ForeignKey(name = "fk_sale_detail_sale"))
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false, foreignKey = @ForeignKey(name = "fk_sale_detail_product"))
    private Product product;

    @Column(nullable = false)
    private short quantity;

    @Column(nullable = false, columnDefinition = "DECIMAL(6,2)")
    private BigDecimal salePrice;

    @Column(nullable = false, columnDefinition = "DECIMAL(6,2)")
    private BigDecimal discount;
}
