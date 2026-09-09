package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO {

    private Integer idClient;

    @NotNull
    @Size(min = 1, max = 25)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 25)
    private String lastName;

    @NotNull
    @Size(min = 1, max = 10)
    private String cardId;

    @NotNull
    @Email
    @Size(max = 50)
    private String email;

    @NotNull
    @Size(min = 1, max = 10)
    private String phoneNumber;

    @NotNull
    @Size(min = 1, max = 150)
    private String address;

    @NotNull
    @Size(min = 1, max = 35)
    private String country;
}
