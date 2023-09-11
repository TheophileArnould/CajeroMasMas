package com.cajeromasmas.servicios;
import com.cajeromasmas.modelos.Usuario;

import java.util.List;

public interface IUsuarioServicio {
    public Usuario findById(Long id);
    public Usuario save(Usuario usuario);
    public void delete(Long id);
    public List<Usuario> findAll();

}
