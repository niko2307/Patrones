
package com.javeriana.patrones.strategy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javeriana.patrones.model.Queja;
import com.javeriana.patrones.model.TipoServicio;

@Component
public class StrategyContext {
    private final Map<TipoServicio, QuejaStrategy> estrategias = new HashMap<>();

    public StrategyContext(EnergiaStrategy energia, AguaStrategy agua, AseoStrategy aseo, AlcantarilladoStrategy alcantarillado) {
        estrategias.put(TipoServicio.ENERGIA, energia);
        estrategias.put(TipoServicio.AGUA, agua);
        estrategias.put(TipoServicio.ASEO, aseo);
        estrategias.put(TipoServicio.ALCANTARILLADO, alcantarillado);
    }

    public void enrutar(Queja queja) {
        TipoServicio tipo = queja.getEmpresaVigilada().getTipoServicio();
        QuejaStrategy estrategia = estrategias.get(tipo);
        if (estrategia != null) {
            estrategia.enrutar(queja);
        } else {
            System.out.println("❌ No hay estrategia para: " + tipo);
        }
    }
}


