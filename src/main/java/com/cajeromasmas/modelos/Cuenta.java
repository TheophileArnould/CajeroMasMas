package com.cajeromasmas.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table()
public class Cuenta {

    public enum TipoDeCuenta {
        Corriente,
        Ahorro

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column
    @Min(0)
    private float  saldo;

    @Column
    private TipoDeCuenta tipo;
}
