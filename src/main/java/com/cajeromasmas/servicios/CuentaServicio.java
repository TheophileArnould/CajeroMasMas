package com.cajeromasmas.servicios;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.cajeromasmas.modelos.Cuenta;
import com.cajeromasmas.dao.CuentaDao;

import java.util.List;

@Service
public class CuentaServicio implements ICuentaServicio{
    @Autowired
    private CuentaDao cuentaDao;

    @Override
    public List<Cuenta> findAll() {
        return cuentaDao.findAll();
    }

    @Override
    public Cuenta findById(Long id) {
        return cuentaDao.findById(id).orElse(null);
    }

    @Override
    public Cuenta save(Cuenta cuenta) {
        return cuentaDao.save(cuenta);
    }

    @Override
    public void delete(Long id) {
        cuentaDao.deleteById(id);
    }
    @Override
    public List<Cuenta> findByUsuarioId(Long user_id) {
        return cuentaDao.findByUsuarioId(user_id);
    }
}
