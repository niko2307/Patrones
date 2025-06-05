
package com.javeriana.patrones.strategy;


import org.springframework.stereotype.Service;

import com.javeriana.patrones.model.Queja;

@Service
public class AguaStrategy implements QuejaStrategy{
     @Override
    public void enrutar(Queja queja) {
        queja.setRutaAsignada("Ruta normal - AGUA");
        queja.setVencimiento(queja.getFechaRegistro().plusDays(2));
        System.out.println("🧹 Enrutada por agua, vencimiento en 48h.");
    }
}
