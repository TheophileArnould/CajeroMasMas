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
import java.util.List;
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

    @GetMapping("/usuario/Conecetarse/{UsuarioId}") //Conectarse a un usuario especifico
    public ResponseEntity Connectarse(@PathVariable Long UsuarioId) {
        try {
            usuario = usuarioServicio.findById(UsuarioId);
        }
        catch (Exception e){
            return new ResponseEntity<String>("This user does not exists", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Usuario>(usuario,HttpStatus.ACCEPTED);
    }

    @GetMapping("/usuario")// get All usuarios
    public ArrayList<Usuario> getAllUsuario() {
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) usuarioServicio.findAll();
        return usuarios;
    }

    @PostMapping("/usuario")// Create Usuario
    public Usuario creatUsuario(@RequestBody Usuario usuario) {
        return usuarioServicio.save(usuario);
    }

    @GetMapping("/cuentas") // Get YourCuentas
    public ResponseEntity getYourCuentas() {
        if (usuario != null) {
            return new ResponseEntity<ArrayList<Cuenta>>(
                    (ArrayList<Cuenta>) cuentaServicio.findByUsuarioId(usuario.getId()),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<String>("No se conectado a ningun usuario", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/cuentas/CreateCuenta")//Add you a cuenta
    public ResponseEntity addCuenta(@RequestBody Cuenta cuenta) {
        List<String> TipoDeCuenta = List.of("Corriente", "Ahorro", "SuperAhorro");

        if (usuario != null) {
            if( TipoDeCuenta.contains(cuenta.getTipo())){// verify cuenta est de tipo : Corriente, Ahorro, SuperAhorro

                    cuenta.setUsuario(usuario);
                    cuenta.setSaldo(0);
                    return new ResponseEntity<Cuenta>(
                            cuentaServicio.save(cuenta),
                            HttpStatus.CREATED
                    );
                }
            else{
                return new ResponseEntity<String>("tu cuenta no es de un tipo que existe", HttpStatus.EXPECTATION_FAILED);//no esta del bueno tipo
            }
        }
        return new ResponseEntity<String>("No se conectado a ningun usuario", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/tajeta") // Get YourCuentas
    public ResponseEntity getTusTajetas() {

        /**
        if (usuario != null) {
            return new ResponseEntity<ArrayList<Cuenta>>(
                    (ArrayList<Cuenta>) cuentaServicio.findByUsuarioId(usuario.getId()),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<String>("No se conectado a ningun usuario", HttpStatus.UNAUTHORIZED);
         **/
        return new ResponseEntity("no desarollado", HttpStatus.SERVICE_UNAVAILABLE);
    }

    @PostMapping("/tajeta")//Add you a cuenta
    public ResponseEntity addTajetaACuenta(@RequestBody Cuenta cuenta) {
        /**
        List<String> TipoDeCuenta = List.of("Corriente", "Ahorro", "SuperAhorro");

        if (usuario != null) {
            if( TipoDeCuenta.contains(cuenta.getTipo())){// verify cuenta est de tipo : Corriente, Ahorro, SuperAhorro

                cuenta.setUsuario(usuario);
                cuenta.setSaldo(0);
                return new ResponseEntity<Cuenta>(
                        cuentaServicio.save(cuenta),
                        HttpStatus.CREATED
                );
            }
            else{
                return new ResponseEntity<String>("tu cuenta no es de un tipo que existe", HttpStatus.EXPECTATION_FAILED);//no esta del bueno tipo
            }
        }
        return new ResponseEntity<String>("No se conectado a ningun usuario", HttpStatus.UNAUTHORIZED);
         **/
        return new ResponseEntity("no desarollado", HttpStatus.SERVICE_UNAVAILABLE);
    }


}
