package com.javeriana.patrones.adapter;

import java.util.Set;

public class ApiEmpresaExternaSimulada {
    private static final Set<Long> empresasActivas = Set.of(1L, 2L, 3L, 4L, 5L, 6L);
 // simuladas

    public boolean verificarEmpresaActiva(Long id) {
        return empresasActivas.contains(id);
    }
}
