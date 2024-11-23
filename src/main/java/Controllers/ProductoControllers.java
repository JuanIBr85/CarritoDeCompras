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
            case "Alta" -> postAltaProducto(request, response);
            case "Modificacion"->postModificacionProducto(request,response);
            case "Baja"->postBajaProducto(request,response);
            default -> response.sendError(404, "No existe la acción.");
        }	
	}


	private void postAltaProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
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
			

	private void postModificacionProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {

		 String accion = request.getParameter("accion");

		    if ("Modificacion".equals(accion)) {
		        String codProd = request.getParameter("CodProducto");
		        String nombreProd = request.getParameter("NombreProducto");
		        String unidadMedidaProd = request.getParameter("UnidadMedidaProducto");
		        double precioProd = Double.parseDouble(request.getParameter("PrecioProducto"));
		        int stockProd = Integer.parseInt(request.getParameter("StockProducto"));
		        
		        Producto productoModificado = new Producto();
		        productoModificado.setNombre(nombreProd);
		        productoModificado.setUnidadMedidaProducto(unidadMedidaProd);
		        productoModificado.setPrecio(precioProd);
		        productoModificado.setStockProducto(stockProd);
		        
		        boolean resultado = GestorProducto.getInstance().modificarProducto(codProd, productoModificado);
		        
		        if (resultado) {
		            response.sendRedirect("AccionesProductos.jsp?mensaje=Producto actualizado con éxito");
		        } else {
		            response.sendRedirect("AccionesProductos.jsp?error=No se pudo actualizar el producto");
		        }
		    }
	}
	
	private void postBajaProducto(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return;
	}


}
