package Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Producto;

@WebServlet("/AgregarCarritoController")
public class AgregarCarritoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codProd = request.getParameter("CodProducto");
        String nombreProd = request.getParameter("NombreProducto");
        String precioProd = request.getParameter("PrecioProducto");
        String cantidadProd = request.getParameter("Cantidad"); 

        double precioProducto = Double.parseDouble(precioProd);
        String stockProd = request.getParameter("StockProducto");
        int stockProducto = Integer.parseInt(stockProd);
        int cantidad = Integer.parseInt(cantidadProd);  

        Producto productoCarrito = new Producto();
        productoCarrito.setCodProducto(codProd);
        productoCarrito.setNombre(nombreProd);
        productoCarrito.setPrecio(precioProducto);
        productoCarrito.setStockProducto(stockProducto);
        productoCarrito.setCantidad(cantidad);

        System.out.println("Producto a agregar:");
        System.out.println("Código: " + productoCarrito.getCodProducto());
        System.out.println("Nombre: " + productoCarrito.getNombre());
        System.out.println("Precio: " + productoCarrito.getPrecio());
        System.out.println("Stock: " + productoCarrito.getStockProducto());
        System.out.println("Cantidad: " + productoCarrito.getCantidad());

        HttpSession session = request.getSession();
        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");

        if (carrito == null) {
            carrito = new ArrayList<>();
            System.out.println("Carrito vacío, creando un nuevo carrito.");
        } else {
            System.out.println("Carrito existente, productos actuales en el carrito:");
            for (Producto p : carrito) {
                System.out.println("Producto en carrito: " + p.getCodProducto() + " - " + p.getNombre());
            }
        }

        carrito.add(productoCarrito);
        System.out.println("Producto agregado al carrito.");

        session.setAttribute("carrito", carrito);

        System.out.println("Redirigiendo a productos.jsp...");
        response.sendRedirect("productos.jsp");
    }
}
