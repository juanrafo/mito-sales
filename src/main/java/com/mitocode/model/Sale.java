package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idSale;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false, foreignKey = @ForeignKey(name = "fk_sale_client"))
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_sale_user"))
    private User user;

    //@Temporal(TemporalType.TIMESTAMP)//Calendar, GregorianCalendar, JodaTime
    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false, columnDefinition = "DECIMAL(6,2)")
    private BigDecimal total;

    @Column(nullable = false, columnDefinition = "DECIMAL(6,2)")
    private BigDecimal tax;

    @Column(nullable = false)
    private boolean enabled;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleDetail> details;
}
