package gestores;

import java.util.List;

import javax.servlet.http.HttpSession;

import models.Compra;
import models.Producto;

public class GestorPago {
    private GestorBilleteraCliente gestorBilleteraCliente;
    private GestorProducto gestorProducto;

    public GestorPago(GestorBilleteraCliente gestorBilleteraCliente) {
        this.gestorBilleteraCliente = gestorBilleteraCliente;
        this.gestorProducto = GestorProducto.getInstance();
    }

    public boolean procesarPago(String nroCuenta, double monto, String clienteDni, HttpSession session) {
        double saldoActual = gestorBilleteraCliente.buscarCuenta(nroCuenta).getSaldoCuenta();
        System.out.println("Saldo actual: " + saldoActual);
        System.out.println("Monto a pagar: " + monto);

        if (saldoActual < monto) {
            System.out.println("Saldo insuficiente para realizar el pago.");
            return false;
        }

        gestorBilleteraCliente.restaDineroEnCuenta(nroCuenta, monto);
        System.out.println("Dinero restado de la cuenta: " + nroCuenta);

        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");

        if (carrito != null && !carrito.isEmpty()) {
            System.out.println("Carrito: Productos cargados");
            System.out.println("Cantidad de productos en el carrito: " + carrito.size());

            for (Producto producto : carrito) {
                if (producto != null) {
                    System.out.println("Producto: " + producto.getNombre() + ", Cantidad: " + producto.getCantidad());

                    gestorProducto.darBajaAlStock(producto.getCodProducto(), producto.getCantidad());
                } else {
                    System.out.println("Producto vacío en el carrito");
                }
            }

            Compra nuevaCompra = new Compra(clienteDni, carrito, monto);
            Compra.agregarCompra(nuevaCompra);
            System.out.println("Nueva compra agregada: " + nuevaCompra);

            //session.removeAttribute("carrito");
           
            //algunos de estos 3 tiene que funcionar
            session.setAttribute("carrito", null);
            carrito = null;
            session.removeAttribute("carrito");
            
            List<Compra> historialCompras = Compra.obtenerHistorialCompras();
            session.setAttribute("historialCompras", historialCompras);
            System.out.println("Historial de compras guardado en la sesión.");

            return true; 
        } else {
            System.out.println("Carrito vacío o no encontrado.");
            return false; 
        }
    }

    public double obtenerSaldoActual(String nroCuenta) {
        return gestorBilleteraCliente.buscarCuenta(nroCuenta).getSaldoCuenta();
    }
}
