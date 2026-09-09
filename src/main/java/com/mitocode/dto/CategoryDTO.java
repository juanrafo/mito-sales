package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {

    private Integer idCategory;

    @NotNull
    //@NotBlank
    //@NotEmpty
    @Size(min = 3, max = 50)
    private String nameofCategory;

    @NotNull
    @Size(min = 3, max = 150)
    private String descriptionCategory;

    @NotNull
    private boolean enabledCategory;

    /*@Max(100)
    @Min(18)
    private int age;

    @Email
    private String email;

    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String password;*/
}
