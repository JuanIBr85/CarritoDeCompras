package gestores;

import java.util.ArrayList;
import java.util.List;
import models.CuentaCliente;
import models.Usuario;
import models.Cliente;

public class GestorCuentaCliente {
    private List<CuentaCliente> cuentas;
    private List<Usuario> usuarios;

    public GestorCuentaCliente() {
        this.cuentas = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public void agregaCuenta(CuentaCliente nCuenta) {
        boolean existeCta = cuentas.stream().anyMatch(p -> p.getNroCuenta().equals(nCuenta.getNroCuenta()));
        if (existeCta) return;
        cuentas.add(nCuenta);
    }

    public List<CuentaCliente> obtenerCuentas() {
        return new ArrayList<>(cuentas);
    }

    public CuentaCliente buscarCuenta(String nroCuenta) {
        return cuentas.stream()
                .filter(ct -> ct.getNroCuenta().equals(nroCuenta))
                .findFirst()
                .orElse(null);
    }

    public void sumaDineroEnCuenta(String nroCuenta, double monto) {
        CuentaCliente cuenta = buscarCuenta(nroCuenta);
        if (cuenta != null) cuenta.setSaldoCuenta(cuenta.getSaldoCuenta() + monto);
    }

    public void restaDineroEnCuenta(String nroCuenta, double monto) {
        CuentaCliente cuenta = buscarCuenta(nroCuenta);
        if (cuenta != null && cuenta.getSaldoCuenta() >= monto) {
            cuenta.setSaldoCuenta(cuenta.getSaldoCuenta() - monto);
        } else {
            System.out.println("Dinero insuficiente para realizar la operacion.");
        }
    }

    public void transferirDinero(String nroCuentaDestino, String nroCuenta, double monto) {
        CuentaCliente cuentaDestino = buscarCuenta(nroCuentaDestino);
        CuentaCliente cuentaOrigen = buscarCuenta(nroCuenta);

        if (cuentaDestino != null && cuentaOrigen != null && cuentaOrigen.getSaldoCuenta() >= monto) {
            restaDineroEnCuenta(nroCuenta, monto);
            sumaDineroEnCuenta(nroCuentaDestino, monto);
        } else {
            System.out.println("No se pudo realizar la transaccion.");
        }
    }


    public boolean registrarUsuario(Usuario usuario) {
        boolean existeUsuario = usuarios.stream()
                .anyMatch(u -> u.getIdUsuario() == usuario.getIdUsuario() ||
                               u.getClaveUsuario().equals(usuario.getClaveUsuario()));

        if (!existeUsuario) {
            usuarios.add(usuario);
            return true;
        } else {
            System.out.println("El usuario ya existe.");
            return false; 
        }
    }

    public List<Usuario> obtenerUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public Usuario buscarUsuarioPorId(int idUsuario) {
        return usuarios.stream()
                .filter(u -> u.getIdUsuario() == idUsuario)
                .findFirst()
                .orElse(null);
    }
    
    public List<Cliente> obtenerClientes() {
        return clientes; // no quitar 
    }
}
