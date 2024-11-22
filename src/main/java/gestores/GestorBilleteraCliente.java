package gestores;

import java.util.ArrayList;
import java.util.List;
import models.BilleteraCliente;

public class GestorBilleteraCliente {
	// Reglas del singleton
		// 1- Constructor private 
		// 2- Que tenga un atributo estatico que haga referencia a la clase 
		// 3- metodo para obtener la instancia. GetInstance() 
	
    private static GestorBilleteraCliente singleton;

    private List<BilleteraCliente> cuentas;

    private GestorBilleteraCliente() {
        this.cuentas = new ArrayList<>();
    }

    public static synchronized GestorBilleteraCliente getInstance() {
        if (singleton == null) {
        	singleton = new GestorBilleteraCliente();
        }
        return singleton;
    }

    public void agregaCuenta(BilleteraCliente nCuenta) {
        boolean existeCta = cuentas.stream().anyMatch(p -> p.getNroCuenta().equals(nCuenta.getNroCuenta()));
        if (existeCta) return;
        cuentas.add(nCuenta);
    }

    public List<BilleteraCliente> obtenerCuentas() {
        return new ArrayList<>(cuentas);
    }

    public BilleteraCliente buscarCuenta(String nroCuenta) {
        return cuentas.stream()
                .filter(ct -> ct.getNroCuenta().equals(nroCuenta))
                .findFirst()
                .orElse(null);
    }

    public void sumaDineroEnCuenta(String nroCuenta, double monto) {
        BilleteraCliente cuenta = buscarCuenta(nroCuenta);
        if (cuenta != null) cuenta.setSaldoCuenta(cuenta.getSaldoCuenta() + monto);
    }

    public void restaDineroEnCuenta(String nroCuenta, double monto) {
        BilleteraCliente cuenta = buscarCuenta(nroCuenta);
        if (cuenta != null && cuenta.getSaldoCuenta() >= monto) {
            cuenta.setSaldoCuenta(cuenta.getSaldoCuenta() - monto);
        } else {
            System.out.println("Dinero insuficiente para realizar la operación.");
        }
    }

    public void transferirDinero(String nroCuentaDestino, String nroCuenta, double monto) {
        BilleteraCliente cuentaDestino = buscarCuenta(nroCuentaDestino);
        BilleteraCliente cuentaOrigen = buscarCuenta(nroCuenta);

        System.out.println("Cuenta Destino: " + cuentaDestino);
        System.out.println("Cuenta Origen: " + cuentaOrigen);
        
        if (cuentaDestino != null && cuentaOrigen != null && cuentaOrigen.getSaldoCuenta() >= monto) {
            restaDineroEnCuenta(nroCuenta, monto);
            sumaDineroEnCuenta(nroCuentaDestino, monto);
        } else {
            System.out.println("No se pudo realizar la transacción.");
        }
    }
}
