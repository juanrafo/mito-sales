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
public class Ingress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idIngress;

    @ManyToOne
    @JoinColumn(name = "id_provider", nullable = false, foreignKey = @ForeignKey(name = "fk_ingress_provider"))
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK_INGRESS_USER"))
    private User user;

    @Column(nullable = false, columnDefinition = "decimal(6,2)")
    private BigDecimal total;

    @Column(nullable = false, columnDefinition = "decimal(6,2)")
    private BigDecimal tax;

    @Column(nullable = false)
    private boolean enabled;
}
