
package com.javeriana.patrones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javeriana.patrones.model.EstadoQueja;
import com.javeriana.patrones.model.Queja;

public interface QuejaRepository extends JpaRepository<Queja, Long> {
    List<Queja> findByEstado(EstadoQueja estado);
    List<Queja> findByUsuarioId(Long usuarioId);
}
