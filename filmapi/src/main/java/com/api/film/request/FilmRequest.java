package com.api.film.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Object for create a film")
public class FilmRequest {

    @Schema(description = "name of the film")
    @NotBlank(message = "Name can't be empty or null")
    private String name;


}
