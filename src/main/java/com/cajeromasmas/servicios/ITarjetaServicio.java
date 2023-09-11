package com.cajeromasmas.servicios;
import com.cajeromasmas.modelos.Tarjeta;

import java.util.List;

public interface ITarjetaServicio {
    public List<Tarjeta> findAll();
    public Tarjeta findById(Long id);
    public Tarjeta save(Tarjeta tarjeta);
    public void delete(Long id);
}
