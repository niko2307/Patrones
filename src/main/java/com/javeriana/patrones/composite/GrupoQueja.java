package com.javeriana.patrones.composite;

import java.util.ArrayList;
import java.util.List;

import com.javeriana.patrones.Dtos.QuejaDTO;

public class GrupoQueja implements ComponenteQueja {
    private String nombreGrupo;
    private List<ComponenteQueja> componentes = new ArrayList<>();

    public GrupoQueja(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public void agregar(ComponenteQueja componente) {
        componentes.add(componente);
    }

    @Override
    public String obtenerDescripcion() {
        return "Grupo: " + nombreGrupo;
    }

    @Override
    public List<QuejaDTO> obtenerQuejas() {
        List<QuejaDTO> todas = new ArrayList<>();
        for (ComponenteQueja c : componentes) {
            todas.addAll(c.obtenerQuejas());
        }
        return todas;
    }
}
