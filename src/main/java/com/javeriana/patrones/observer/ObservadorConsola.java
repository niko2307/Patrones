
package com.javeriana.patrones.observer;

import org.springframework.stereotype.Component;

@Component
public class ObservadorConsola  implements NotificacionObserver {
    @Override
    public void recibirNotificacion(EventoNotificacion evento) {
        System.out.println("🔔 [CONSOLA - Notificación]");
        System.out.println("📨 Para     : " + evento.getDestinatario());
        System.out.println("📋 Mensaje  : " + evento.getMensaje());
        System.out.println("📎 Tipo     : " + evento.getTipo());
        System.out.println("───────────────────────────────────────────────");
    }
    }

