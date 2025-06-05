
package com.javeriana.patrones.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuejaDTO {
    private Long id;
    private String descripcion;
    private String estado;
    private String fechaRegistro;
    private Long usuarioId;
    private Long empresaId;
    private EmpresaDTO empresa;
    private String rutaAsignada;
    private String vencimiento;

}
