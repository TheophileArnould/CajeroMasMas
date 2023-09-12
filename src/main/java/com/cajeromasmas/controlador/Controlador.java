package com.cajeromasmas.controlador;

import com.cajeromasmas.modelos.Cuenta;
import com.cajeromasmas.modelos.Tarjeta;
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
import java.util.*;
import java.util.List;

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
        } catch (Exception e) {
            return new ResponseEntity<String>("This user does not exists", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Usuario>(usuario, HttpStatus.ACCEPTED);
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
            if (TipoDeCuenta.contains(cuenta.getTipo())) {// verify cuenta est de tipo : Corriente, Ahorro, SuperAhorro

                cuenta.setUsuario(usuario);
                cuenta.setSaldo(0);
                return new ResponseEntity<Cuenta>(
                        cuentaServicio.save(cuenta),
                        HttpStatus.CREATED
                );
            } else {
                return new ResponseEntity<String>("tu cuenta no es de un tipo que existe", HttpStatus.EXPECTATION_FAILED);//no esta del bueno tipo
            }
        }
        return new ResponseEntity<String>("No se ha conectado a ningun usuario", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/tajeta/{cuentaId}") // Get Tarjetas
    public ResponseEntity getTusTajetas(@PathVariable Long cuentaId) {
        if (usuario != null) {
            Cuenta cuenta = cuentaServicio.findByUsuarioIdAndId(usuario.getId(), cuentaId);
            if (cuenta != null) {
                return new ResponseEntity<ArrayList>(
                        (ArrayList) tarjetaServicio.findByCuentaId(cuentaId),
                        HttpStatus.OK
                );
            } else {
                return new ResponseEntity<String>("No se encontro la cuenta", HttpStatus.NOT_FOUND);
            }

        }
        return new ResponseEntity<String>("No se ha conectado a ningun usuario", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/tarjeta/{cuentaId}")//Add a tarjeta
    public ResponseEntity addTajetaACuenta(@PathVariable Long cuentaId) {
        if (usuario != null) {
            Cuenta cuenta = cuentaServicio.findById(cuentaId);
            if (cuenta != null) {
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.setCuenta(cuenta);
                tarjeta.setBloqueada(false);
                tarjeta.setSinContacto(false);
                tarjeta.setNumeroDeTajeta(String.valueOf(10000 + new Random().nextInt(90000)));
                tarjeta.setCodigoDeTajetas(100 + new Random().nextInt(900));
                return new ResponseEntity<Tarjeta>(
                        tarjetaServicio.save(tarjeta),
                        HttpStatus.CREATED
                );
            } else {
                return new ResponseEntity<String>("No existe la cuenta", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<String>("No se conectado a ningun usuario", HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/transferencia")
    public ResponseEntity transferir(@RequestBody Map<String, Object> payload) {
        if (usuario != null) {
            Long cuentaId = Long.parseLong(payload.get("cuentaId").toString());
            Double monto = Double.parseDouble(payload.get("monto").toString());
            Long cuentaIdDestino = Long.parseLong(payload.get("cuentaIdDestino").toString());
            Cuenta cuenta = cuentaServicio.findById(cuentaId);
            Cuenta cuentaDestino = cuentaServicio.findById(cuentaIdDestino);
            if (cuenta != null && cuentaDestino != null) {
                if (!cuenta.getUsuario().getId().equals(usuario.getId())) {
                    return new ResponseEntity<String>("No tienes permiso para acceder a esta cuenta", HttpStatus.UNAUTHORIZED);
                }
                if (cuenta.getSaldo() >= monto) {
                    cuenta.setSaldo((float) (cuenta.getSaldo() - monto));
                    cuentaDestino.setSaldo((float) (cuentaDestino.getSaldo() + monto));

                    cuentaServicio.save(cuenta);
                    cuentaServicio.save(cuentaDestino);
                    return new ResponseEntity<String>("Transferencia exitosa", HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("No tienes suficiente saldo", HttpStatus.EXPECTATION_FAILED);
                }
            } else {
                return new ResponseEntity<String>("No se encontro la cuenta", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<String>("No se ha conectado a ningun usuario", HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/tarjeta/{tarjetaId}/bloqueo")
    public ResponseEntity bloquearTarjeta(@PathVariable Long tarjetaId) {
        if (usuario != null) {
            Tarjeta tarjeta = tarjetaServicio.findById(tarjetaId);
            if (tarjeta != null) {
                if (!tarjeta.getCuenta().getUsuario().getId().equals(usuario.getId())) {
                    return new ResponseEntity<String>("No tienes permiso para acceder a esta tarjeta", HttpStatus.UNAUTHORIZED);
                }
                tarjeta.setBloqueada(!tarjeta.isBloqueada());
                tarjetaServicio.save(tarjeta);
                return new ResponseEntity<String>("Tarjeta bloqueada", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("No se encontro la tarjeta", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<String>("No se ha conectado a ningun usuario", HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/tarjeta/{tarjetaId}/sincontacto")
    public ResponseEntity sinContacto(@PathVariable Long tarjetaId) {
        if (usuario != null) {
            Tarjeta tarjeta = tarjetaServicio.findById(tarjetaId);
            if (tarjeta != null) {
                if (!tarjeta.getCuenta().getUsuario().getId().equals(usuario.getId())) {
                    return new ResponseEntity<String>("No tienes permiso para acceder a esta tarjeta", HttpStatus.UNAUTHORIZED);
                }
                tarjeta.setSinContacto(!tarjeta.isSinContacto());
                tarjetaServicio.save(tarjeta);
                return new ResponseEntity<String>("Tarjeta sin contacto", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("No se encontro la tarjeta", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<String>("No se ha conectado a ningun usuario", HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/pedirPrestamo")
    public ResponseEntity prestamo(@RequestBody Map<String, Object> payload) {
        if (usuario != null) {
            Long cuentaId = Long.parseLong(payload.get("cuentaId").toString());
            Double monto = Double.parseDouble(payload.get("monto").toString());
            Cuenta cuenta = cuentaServicio.findById(cuentaId);
            if (cuenta != null) {
                if (!cuenta.getUsuario().getId().equals(usuario.getId())) {
                    return new ResponseEntity<String>("No tienes permiso para acceder a esta cuenta", HttpStatus.UNAUTHORIZED);
                }
                cuenta.setSaldo((float) (cuenta.getSaldo() + monto));
                cuentaServicio.save(cuenta);
                return new ResponseEntity<String>("Prestamo exitoso", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("No se encontro la cuenta", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<String>("No se ha conectado a ningun usuario", HttpStatus.UNAUTHORIZED);
    }
}
