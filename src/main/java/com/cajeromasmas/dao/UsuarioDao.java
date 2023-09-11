package com.cajeromasmas.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cajeromasmas.modelos.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long>{
}
