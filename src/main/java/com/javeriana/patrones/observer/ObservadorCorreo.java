
package com.javeriana.patrones.observer;

import org.springframework.stereotype.Component;

@Component
public class ObservadorCorreo implements NotificacionObserver {
    @Override
    public void recibirNotificacion(EventoNotificacion evento) {
        System.out.println("📧 [SIMULACIÓN DE ENVÍO DE CORREO]");
        System.out.println("📬 Para     : " + evento.getDestinatario());
        System.out.println("📝 Asunto   : Notificación automática");
        System.out.println("📄 Contenido: " + evento.getMensaje());
        System.out.println("───────────────────────────────────────────────");
    }
}
