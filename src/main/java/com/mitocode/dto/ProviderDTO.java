package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderDTO {

    private Integer idProvider;

    @NotNull
    @Size(min = 3, max = 30)
    private String name;

    @NotNull
    @Size(min = 3, max = 150)
    private String address;

    @NotNull
    private boolean enabled;
}
