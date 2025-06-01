
package com.javeriana.patrones.observer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GestorNotificaciones implements SujetoObservable {
    private final List<NotificacionObserver> observadores = new ArrayList<>();

    @Override
    public void agregarObservador(NotificacionObserver observer) {
        observadores.add(observer);
    }

    @Override
    public void eliminarObservador(NotificacionObserver observer) {
        observadores.remove(observer);
    }

    @Override
    public void notificarObservadores(EventoNotificacion evento) {
        for (NotificacionObserver observer : observadores) {
            observer.recibirNotificacion(evento);
        }
    }
}
