package org.example.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteRequest {

    private String nombre;

    private String apellido;

    private String formaPago;

    private LocalDate fechaCreacion;
}
