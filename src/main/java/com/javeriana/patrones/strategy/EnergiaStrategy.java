
package com.javeriana.patrones.strategy;


import org.springframework.stereotype.Service;

import com.javeriana.patrones.model.Queja;

@Service
public class EnergiaStrategy implements QuejaStrategy {
    @Override
    public void enrutar(Queja queja) {
        // Establece prioridad y vencimiento de 24h
        queja.setRutaAsignada("Ruta rápida - ENERGÍA");
        queja.setVencimiento(queja.getFechaRegistro().plusHours(24));
        System.out.println("⚡ Enrutada por energía con prioridad alta.");
    }
}

