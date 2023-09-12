package com.cajeromasmas.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table()
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

    @Column
    private boolean  bloqueada;

    @Column
    private boolean  sinContacto;

    @Column
    @Size(min = 5, max = 6)
    private String numeroDeTajeta;

    @Column
    @Size(min = 5, max = 6)
    private int CodigoDeTajetas;
}
