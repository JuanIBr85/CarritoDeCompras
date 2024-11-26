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
       
    	String idProd = request.getParameter("IdProducto");
    	String codProd = request.getParameter("CodProducto");
        String nombreProd = request.getParameter("NombreProducto");
        String precioProd = request.getParameter("PrecioProducto");
        String cantidadProd = request.getParameter("Cantidad");
        String stockProd = request.getParameter("StockProducto");
        
        
        double precioProducto = Double.parseDouble(precioProd);
        int stockProducto = Integer.parseInt(stockProd);
        int cantidad = Integer.parseInt(cantidadProd);
        int idProducto = Integer.parseInt(idProd);

        Producto productoCarrito = new Producto();
        
        productoCarrito.setIdProducto(idProducto);
        productoCarrito.setCodProducto(codProd);
        productoCarrito.setNombre(nombreProd);
        productoCarrito.setPrecio(precioProducto);
        productoCarrito.setStockProducto(stockProducto);
        productoCarrito.setCantidad(cantidad);

        HttpSession session = request.getSession();
        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");

        if (carrito == null) {
            carrito = new ArrayList<>();
        }

        boolean productoExiste = false;
        for (Producto p : carrito) {
            if (p.getCodProducto().equals(codProd)) {
                productoExiste = true;
                int nuevaCantidad = p.getCantidad() + cantidad;

                if (nuevaCantidad > stockProducto) {
                    request.setAttribute("mensaje", "La cantidad supera el stock disponible.");
                    request.getRequestDispatcher("productos.jsp").forward(request, response);
                    return;
                } else {
                    p.setCantidad(nuevaCantidad); 
                    break;
                }
            }
        }

        if (!productoExiste) {
            if (cantidad > stockProducto) {
                request.setAttribute("mensaje", "La cantidad supera el stock disponible.");
                request.getRequestDispatcher("productos.jsp").forward(request, response);
                return;
            }
            carrito.add(productoCarrito);
        }

        session.setAttribute("carrito", carrito);
        response.sendRedirect("productos.jsp");
    }
}

