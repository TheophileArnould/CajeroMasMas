package com.cajeromasmas.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @JoinColumn(name = "Cuenta_id")
    private Long CuentaID;

    @Column
    private boolean  bloqueada;

    @Column
    private boolean  sinContacto;

    @Column
    private String numeroDeTajeta;

    @Column
    private int CodigoDeTajetas;
}
