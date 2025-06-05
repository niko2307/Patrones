
package com.javeriana.patrones.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quejas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Queja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    private EstadoQueja estado;

    private LocalDateTime fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresaVigilada empresaVigilada;


    @Column(name = "ruta_asignada")
     private String rutaAsignada;

     @Column(name = "vencimiento")
     private LocalDateTime vencimiento;

}
