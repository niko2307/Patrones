
package com.javeriana.patrones.observer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoNotificacion {
    private String mensaje;
    private String destinatario;
    private String tipo; // puede ser INFO, ALERTA, etc.
}
