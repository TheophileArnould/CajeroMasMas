package com.cajeromasmas.controlador;

import com.cajeromasmas.modelos.Cuenta;
import com.cajeromasmas.modelos.Usuario;
import com.cajeromasmas.servicios.ICuentaServicio;
import com.cajeromasmas.servicios.ITarjetaServicio;
import com.cajeromasmas.servicios.IUsuarioServicio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class Controlador {
    @Autowired
    private ICuentaServicio cuentaServicio;

    @Autowired
    private ITarjetaServicio tarjetaServicio;

    @Autowired
    private IUsuarioServicio usuarioServicio;

    private Usuario usuario = null;

    @GetMapping("/usuario/{UsuarioId}")
    public Usuario getUsuario(@PathVariable Long UsuarioId){
        return usuarioServicio.findById(UsuarioId);
        //return usuario;
    }

    @GetMapping("/usuario")
    public ArrayList<Usuario> getUsuario(){
        ArrayList<Usuario> usuarios  = (ArrayList<Usuario>) usuarioServicio.findAll();
        return usuarios;
    }

    @PostMapping("/usuario")
    public Usuario creatUsuario(@RequestBody Usuario usuario){
        return usuarioServicio.save(usuario);
    }



}
