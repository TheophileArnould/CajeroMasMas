package com.cajeromasmas.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cajeromasmas.modelos.Cuenta;


public interface CuentaDao extends JpaRepository<Cuenta, Long>{
}
