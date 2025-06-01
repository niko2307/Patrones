
package com.javeriana.patrones.strategy;

import org.springframework.stereotype.Component;

import com.javeriana.patrones.model.Queja;

@Component
public class AlcantarilladoStrategy implements QuejaStrategy {
    @Override
    public void enrutar(Queja queja) {
        System.out.println(" Enrutando queja al área de Alcantarillado: " + queja.getDescripcion());
    }
}


