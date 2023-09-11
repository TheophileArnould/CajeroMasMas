package com.cajeromasmas.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
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
