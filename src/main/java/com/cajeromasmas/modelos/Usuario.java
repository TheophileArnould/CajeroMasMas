package com.cajeromasmas.modelos;

import jakarta.persistence.*;

@Table
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private float  numeroCedula;

    @Column
    private String nombre;
}
