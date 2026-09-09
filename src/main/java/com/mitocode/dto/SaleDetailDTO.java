package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDetailDTO {

    private Integer idSaleDetail;

    @NotNull
    private ProductDTO product;

    @NotNull
    @Min(1)
    private Short quantity;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal salePrice;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal discount;
}
