package com.javeriana.patrones.adapter;

public class EmpresaExternaAdapter implements ProveedorEmpresaExterno {
    private final ApiEmpresaExternaSimulada apiSimulada;

    public EmpresaExternaAdapter() {
        this.apiSimulada = new ApiEmpresaExternaSimulada();
    }

    @Override
    public boolean empresaEstaActiva(Long empresaId) {
        return apiSimulada.verificarEmpresaActiva(empresaId);
    }
}