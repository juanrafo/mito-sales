package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IngressDTO {

    private Integer idIngress;

    @NotNull
    private ProviderDTO provider;

    @NotNull
    private UserDTO user;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal total;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal tax;

    @NotNull
    private boolean enabled;
}
