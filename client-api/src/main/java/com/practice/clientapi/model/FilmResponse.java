package com.practice.clientapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Schema for FilmResponse object")
public class FilmResponse {

    private Long id;

    @Schema(description = "film name",type = "String")
    private String name;

    @Schema(description = "client number",type = "String")
    private String nroSocio;

    @Schema(description = "Rent date of the film")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private LocalDateTime rentDate;
}
