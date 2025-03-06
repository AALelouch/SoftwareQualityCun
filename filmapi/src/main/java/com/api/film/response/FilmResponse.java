package com.api.film.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@Schema(description = "Object for get a film in whatever query")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FilmResponse {

    @Schema(description = "Id of the film")
    private Long id;

    @Schema(description = "Name of the film")
    private String name;

    @Schema(description = "The client number assigned to the film or unassigned if it is available")
    private String clientNumber;

    @Schema(description = "Rent date of the film")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private LocalDateTime rentDate;
}
