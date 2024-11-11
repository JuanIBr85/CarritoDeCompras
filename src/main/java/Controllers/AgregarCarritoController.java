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
        double precioProducto = Double.parseDouble(precioProd);
        String stockProd = request.getParameter("StockProducto");
        int stockProducto = Integer.parseInt(stockProd);

        Producto productoCarrito = new Producto();
        productoCarrito.setCodProducto(codProd);
        productoCarrito.setNombre(nombreProd);
        productoCarrito.setPrecio(precioProducto);
        productoCarrito.setStockProducto(stockProducto);

        HttpSession session = request.getSession();
        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");

        if (carrito == null) {
            carrito = new ArrayList<>();
        }

        carrito.add(productoCarrito);

        session.setAttribute("carrito", carrito);

        response.sendRedirect("productos.jsp");
    }
}
