
package com.javeriana.patrones.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegistroDTO {
    private String nombre;
    private String email;
    private String password;
    private String rol;
}

