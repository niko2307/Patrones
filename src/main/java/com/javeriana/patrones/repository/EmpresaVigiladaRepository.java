
package com.javeriana.patrones.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javeriana.patrones.model.EmpresaVigilada;
import com.javeriana.patrones.model.TipoServicio;

public interface EmpresaVigiladaRepository extends JpaRepository<EmpresaVigilada, Long>{
    Optional<EmpresaVigilada> findByTipoServicio(TipoServicio tipo);
}
