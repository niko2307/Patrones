package com.javeriana.patrones.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialQuejaDTO {
    private Long quejaId;
    private String estadoAnterior;
    private String estadoNuevo;
    private String fechaCambio;
}
