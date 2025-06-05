
package com.javeriana.patrones.strategy;


import org.springframework.stereotype.Service;

import com.javeriana.patrones.model.Queja;

@Service
public class AseoStrategy implements QuejaStrategy {
    @Override
    public void enrutar(Queja queja) {
        queja.setRutaAsignada("Ruta normal - ASEO");
        queja.setVencimiento(queja.getFechaRegistro().plusDays(2));
        System.out.println("🧹 Enrutada por aseo, vencimiento en 48h.");
    }
}
