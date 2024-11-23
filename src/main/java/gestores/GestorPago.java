package gestores;

import java.util.List;

import javax.servlet.http.HttpSession;

import models.Compra;
import models.Producto;

public class GestorPago {
    private GestorBilleteraCliente gestorBilleteraCliente;
    
    public GestorPago(GestorBilleteraCliente gestorBilleteraCliente) {
        this.gestorBilleteraCliente = gestorBilleteraCliente;
    }

    public boolean procesarPago(String nroCuenta, double monto, String clienteDni, HttpSession session) {
        gestorBilleteraCliente.restaDineroEnCuenta(nroCuenta, monto);
        System.out.println("Dinero restado de la cuenta: " + nroCuenta);

        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");

        if (carrito != null && !carrito.isEmpty()) {
            System.out.println("Carrito: Productos cargados");
            System.out.println("Cantidad de productos en el carrito: " + carrito.size());

            for (Producto producto : carrito) {
                if (producto != null) {
                    System.out.println("Producto: " + producto.getNombre() + ", Precio: " + producto.getPrecio());
                } else {
                    System.out.println("Producto vacío en el carrito");
                }
            }

            Compra nuevaCompra = new Compra(clienteDni, carrito, monto);
            Compra.agregarCompra(nuevaCompra);
            System.out.println("Nueva compra agregada: " + nuevaCompra);

            session.removeAttribute("carrito");
            System.out.println("Carrito vaciado después de la compra.");

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
