package com.practice.clientapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Schema for RegisterClient object")
@Valid
public class RegisterClient {


    @Schema(description = "client first name",type = "String")
    @NotBlank(message = "Client first name cannot be null")
    private String firstName;

    @Schema(description = "client last name",type = "String")
    @NotBlank(message = "Client last name cannot be null")
    private String lastName;

}
