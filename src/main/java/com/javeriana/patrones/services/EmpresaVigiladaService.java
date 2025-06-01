
package com.javeriana.patrones.services;

import java.util.List;

import com.javeriana.patrones.Dtos.EmpresaDTO;

public interface  EmpresaVigiladaService {
       EmpresaDTO registrarEmpresa(EmpresaDTO empresaDTO);
       EmpresaDTO obtenerPorId(Long id);
       List<EmpresaDTO> listarEmpresas();
}
