package com.cajeromasmas.modelos;

import jakarta.persistence.*;

@Table
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @JoinColumn(name = "Usuario_id")
    private Long usuarioId;

    @Column
    private float  saldo;

    @Column
    private String tipo;
}
