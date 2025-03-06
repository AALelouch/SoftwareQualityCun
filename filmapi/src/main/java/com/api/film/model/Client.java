package com.api.film.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Object for get a client")
public class Client {

    @Schema(description = "id of client, It's unique")
    private Long id;

    @Schema(description = "client number")
    private String clientNumber;

    @Schema(description = "The first name of the client")
    private String firstName;

    @Schema(description = "The last name of the client")
    private String lastName;
}
