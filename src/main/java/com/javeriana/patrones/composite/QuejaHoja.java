package com.javeriana.patrones.composite;

import java.util.List;

import com.javeriana.patrones.Dtos.QuejaDTO;

public class QuejaHoja implements ComponenteQueja {
    private final QuejaDTO queja;

    public QuejaHoja(QuejaDTO queja) {
        this.queja = queja;
    }

    @Override
    public String obtenerDescripcion() {
        return "Queja #" + queja.getId() + ": " + queja.getDescripcion();
    }

    @Override
    public List<QuejaDTO> obtenerQuejas() {
        return List.of(queja);
    }
}