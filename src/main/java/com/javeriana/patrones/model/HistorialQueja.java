
package com.javeriana.patrones.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "historial_quejas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialQueja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Queja queja;

    @Enumerated(EnumType.STRING)
    private EstadoQueja estadoAnterior;

    @Enumerated(EnumType.STRING)
    private EstadoQueja estadoNuevo;

    private LocalDateTime fechaCambio;
}
