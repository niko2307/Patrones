
package com.javeriana.patrones.strategy;


import org.springframework.stereotype.Service;

import com.javeriana.patrones.model.Queja;

@Service
public class AlcantarilladoStrategy implements QuejaStrategy {
     @Override
    public void enrutar(Queja queja) {
        queja.setRutaAsignada("Ruta normal - ALCANTARILLADO");
        queja.setVencimiento(queja.getFechaRegistro().plusDays(2));
        System.out.println("🧹 Enrutada por alcantarillado, vencimiento en 48h.");
    }
}


