package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDTO {

    private Integer idSale;

    @NotNull
    private ClientDTO client;

    @NotNull
    private UserDTO user;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal total;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal tax;

    @NotNull
    private boolean enabled;

    @NotEmpty
    @Valid
    private List<SaleDetailDTO> details;
}
