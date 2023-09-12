package com.cajeromasmas.controlador;

import com.cajeromasmas.modelos.Cuenta;
import com.cajeromasmas.modelos.Usuario;
import com.cajeromasmas.servicios.ICuentaServicio;
import com.cajeromasmas.servicios.ITarjetaServicio;
import com.cajeromasmas.servicios.IUsuarioServicio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/usuario/{UsuarioId}") //Conectarse a un usuario especifico
    public Usuario getUsuario(@PathVariable Long UsuarioId) {
        usuario = usuarioServicio.findById(UsuarioId);
        return usuario;
    }

    @GetMapping("/usuario")
    public ArrayList<Usuario> getUsuario() {
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) usuarioServicio.findAll();
        return usuarios;
    }

    @PostMapping("/usuario")
    public Usuario creatUsuario(@RequestBody Usuario usuario) {
        return usuarioServicio.save(usuario);
    }

    @GetMapping("/cuentas")
    public ResponseEntity getCuentas() {
        if (usuario != null) {
            return new ResponseEntity<ArrayList<Cuenta>>(
                    (ArrayList<Cuenta>) cuentaServicio.findByUsuarioId(usuario.getId()),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<String>("No se conectado a ningun usuario", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/cuenta")
    public ResponseEntity addCuenta(@RequestBody Cuenta cuenta) {
        if (usuario != null) {
            cuenta.setUsuarioId(usuario.getId());
            return new ResponseEntity<Cuenta>(
                    cuentaServicio.save(cuenta),
                    HttpStatus.CREATED
            );
        }
        return new ResponseEntity<String>("No se conectado a ningun usuario", HttpStatus.UNAUTHORIZED);
    }


}
