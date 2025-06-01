
package com.javeriana.patrones.observer;

import org.springframework.stereotype.Component;

@Component
public class ObservadorEmpresa implements NotificacionObserver {
    @Override
    public void recibirNotificacion(EventoNotificacion evento) {
        System.out.println("🏢 [NOTIFICACIÓN A EMPRESA VIGILADA]");
        System.out.println("📬 Empresa    : " + evento.getDestinatario());
        System.out.println("📄 Mensaje    : " + evento.getMensaje());
        System.out.println("🔔 Tipo Alerta: " + evento.getTipo());
        System.out.println("───────────────────────────────────────────────");
    }
}
