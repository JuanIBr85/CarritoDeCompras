package gestores;

import java.util.ArrayList;
import java.util.List;
import models.CuentaCliente;

public class GestorCuentaCliente {
	// Reglas del singleton
		// 1- Constructor private 
		// 2- Que tenga un atributo estatico que haga referencia a la clase 
		// 3- metodo para obtener la instancia. GetInstance() 
	
    private static GestorCuentaCliente singleton;

    private List<CuentaCliente> cuentas;

    private GestorCuentaCliente() {
        this.cuentas = new ArrayList<>();
    }

    public static synchronized GestorCuentaCliente getInstance() {
        if (singleton == null) {
        	singleton = new GestorCuentaCliente();
        }
        return singleton;
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
            System.out.println("Dinero insuficiente para realizar la operación.");
        }
    }

    public void transferirDinero(String nroCuentaDestino, String nroCuenta, double monto) {
        CuentaCliente cuentaDestino = buscarCuenta(nroCuentaDestino);
        CuentaCliente cuentaOrigen = buscarCuenta(nroCuenta);

        if (cuentaDestino != null && cuentaOrigen != null && cuentaOrigen.getSaldoCuenta() >= monto) {
            restaDineroEnCuenta(nroCuenta, monto);
            sumaDineroEnCuenta(nroCuentaDestino, monto);
        } else {
            System.out.println("No se pudo realizar la transacción.");
        }
    }
}
