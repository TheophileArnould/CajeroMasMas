package com.cajeromasmas.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cajeromasmas.modelos.Tarjeta;

public interface TarjetaDao extends JpaRepository<Tarjeta, Long> {
}
