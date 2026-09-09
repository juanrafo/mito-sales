package com.mitocode.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class IngressDetailPK {

    @ManyToOne
    @JoinColumn(name = "id_ingress", nullable = false, foreignKey = @ForeignKey(name = "fk_ingress_detail_ingress"))
    private Ingress ingress;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false, foreignKey = @ForeignKey(name = "fk_ingress_detail_product"))
    private Product product;
}
