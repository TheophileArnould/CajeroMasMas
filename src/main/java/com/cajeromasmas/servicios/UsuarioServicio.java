package com.cajeromasmas.servicios;

import com.cajeromasmas.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cajeromasmas.dao.UsuarioDao;
import java.util.List;

@Service
public class UsuarioServicio implements IUsuarioServicio{
    @Autowired
    private UsuarioDao usuarioDao;
    @Override
    public Usuario findById(Long id) {
        return usuarioDao.findById(id).orElse(null);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    @Override
    public void delete(Long id) {
        usuarioDao.deleteById(id);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioDao.findAll();
    }
}
