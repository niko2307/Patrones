
package com.javeriana.patrones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javeriana.patrones.model.HistorialQueja;

public interface HistorialQuejaRepository extends JpaRepository<HistorialQueja, Long> {
    List<HistorialQueja> findByQuejaId(Long quejaId);
}

