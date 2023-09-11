package com.cajeromasmas.servicios;
import com.cajeromasmas.modelos.Cuenta;

import java.util.List;

public interface ICuentaServicio {
    public List<Cuenta> findAll();
    public Cuenta findById(Long id);
    public Cuenta save(Cuenta cuenta);
    public void delete(Long id);
}
