
package com.javeriana.patrones.observer;

public interface SujetoObservable {
    void agregarObservador(NotificacionObserver observer);
    void eliminarObservador(NotificacionObserver observer);
    void notificarObservadores(EventoNotificacion evento);
}
