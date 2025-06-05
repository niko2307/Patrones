package com.javeriana.patrones.composite;

import java.util.List;

import com.javeriana.patrones.Dtos.QuejaDTO;

public interface ComponenteQueja {
    String obtenerDescripcion();
    List<QuejaDTO> obtenerQuejas();
}
