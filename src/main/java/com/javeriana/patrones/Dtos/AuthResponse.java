
package com.javeriana.patrones.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
      private String token;
    private Long userId;
    private String rol;

   
}
