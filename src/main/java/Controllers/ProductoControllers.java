package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gestores.GestorProducto;
import models.Producto;


@WebServlet("/ProductoControllers")
public class ProductoControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GestorProducto ProductoGestor;
		
    public ProductoControllers() {
    	this.ProductoGestor = GestorProducto.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String accion = request.getParameter("accion");
        accion = Optional.ofNullable(accion).orElse("ListaProductos");

        switch (accion) {
            case "ProductosAMB" -> getProductosABM(request, response);
            case "ListaProductos"->getListaProductos(request,response);
            default -> response.sendError(404, "No existe la acción.");
        }
	}


	private Object getProductosABM(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("AccionesProductos.jsp");
		return null;
	}

	private void getListaProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    List<Producto> productos = ProductoGestor.obtenerListaProductos();

	    HttpSession session = request.getSession();

	    session.setAttribute("productos", productos);

	    request.getRequestDispatcher("productos.jsp").forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String accion = request.getParameter("accion");
        accion = Optional.ofNullable(accion).orElse("ListaProductos");

        switch (accion) {
            case "Alta" -> getAltaProducto(request, response);
            case "Modificacion"->getModificacionProducto(request,response);
            case "Baja"->getBajaProducto(request,response);
            default -> response.sendError(404, "No existe la acción.");
        }	
	}

	private void getAltaProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		try {
		String codProd = request.getParameter("CodProducto");
		String nombreProd = request.getParameter("NombreProducto");
		String uMProd = request.getParameter("UnidadMedidaProducto");
		String precioProd = request.getParameter("PrecioProducto");
		double precioProducto = Double.parseDouble(precioProd);
		String stockProd = request.getParameter("StockProducto");
		int stockProducto = Integer.parseInt(stockProd);
		
		Producto nuevoProducto = new Producto();
		
		nuevoProducto.setCodProducto(codProd);
		nuevoProducto.setNombre(nombreProd);
		nuevoProducto.setUnidadMedidaProducto(uMProd);
		nuevoProducto.setPrecio(precioProducto);
		nuevoProducto.setStockProducto(stockProducto);
		
        this.ProductoGestor.agregarProducto(nuevoProducto);
        
        response.sendRedirect("AccionesProductos.jsp?mensaje=Producto agregado con exito");
        
		}
		catch (Exception e) 
		{
			response.sendRedirect("AccionesProductos.jsp?error=Error al agregar producto: " + e.getMessage());
		}
        
	}
			

	private void getModificacionProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("AccionesProductos.jsp");
		return;
	}

	private void getBajaProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("AccionesProductos.jsp");
		return;
	}

}
