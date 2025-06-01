
package com.javeriana.patrones.services;

import java.util.List;

import com.javeriana.patrones.Dtos.HistorialQuejaDTO;

public interface HistorialQuejaService {
    HistorialQuejaDTO registrarHistorial(HistorialQuejaDTO dto);
    List<HistorialQuejaDTO> listarPorQueja(Long quejaId);
}
