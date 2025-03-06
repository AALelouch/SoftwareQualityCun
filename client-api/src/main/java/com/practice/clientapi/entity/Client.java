package com.practice.clientapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table( name = "client")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "client associate number",type = "String")
    private String clientNumber;

    @Schema(description = "client first name",type = "String")
    private String firstName;

    @Schema(description = "client last name",type = "String")
    private String lastName;
}
