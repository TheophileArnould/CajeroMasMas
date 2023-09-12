package com.cajeromasmas.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cajeromasmas.modelos.Cuenta;

import java.util.List;


public interface CuentaDao extends JpaRepository<Cuenta, Long>{
    List<Cuenta> findByUsuarioId(Long usuarioId);
    Cuenta findByUsuarioIdAndId(Long usuarioId, Long cuentaId);
}
