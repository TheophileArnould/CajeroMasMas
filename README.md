# CajeroMasMas

## foncionalidades
- una cuenta usuario
  - con differentes cuentas (corriente, ahoro...)
- puedes pedir un prestamo de dinero
- hacer transferencias entre sus cuentas y a cuentas de otros usuarios
- puedes ver la differentes tajetas
  - bloquear o activar una
  - bloquear o activar sin contacto
  - ver los datos

## modelos

- Usuario
  - Long : id (primary)
  - String : nombre
  - int : numero de cedula

- Cuentas
  - Long : id (primary)
  - Long : usuarioId ForeignKey(cuentaUsario.Id)
  - float : saldo
  - String : tipo

- Tajetas
  - Long : id (primary)
  - Long : CuentaID ForeignKey(cuentasDeBanco.Id)
  - Bool : bloqueada
  - Bool : sinContacto
  - String : numeroDeTajeta
  - int : CodigoDeTajetas

## Clases


