package com.cajeromasmas.servicios;

import com.cajeromasmas.modelos.Tarjeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cajeromasmas.dao.TarjetaDao;
import java.util.List;

@Service
public class TarjetaServicio implements ITarjetaServicio{

    @Autowired
    private TarjetaDao tarjetaDao;
    @Override
    public List<Tarjeta> findAll() {
        return tarjetaDao.findAll();
    }

    @Override
    public Tarjeta findById(Long id) {
        return tarjetaDao.findById(id).orElse(null);
    }

    @Override
    public Tarjeta save(Tarjeta tarjeta) {
        return tarjetaDao.save(tarjeta);
    }

    @Override
    public void delete(Long id) {
        tarjetaDao.deleteById(id);
    }
}
