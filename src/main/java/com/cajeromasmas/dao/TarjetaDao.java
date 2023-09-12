package com.cajeromasmas.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cajeromasmas.modelos.Tarjeta;

import java.util.List;

public interface TarjetaDao extends JpaRepository<Tarjeta, Long> {
    List<Tarjeta> findByCuentaId(Long cuentaId);
}
