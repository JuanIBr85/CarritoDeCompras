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


	private void getProductosABM(HttpServletRequest request, HttpServletResponse response) throws  ServletException,IOException {
	    List<Producto> productos = ProductoGestor.obtenerListaProductos();
	    HttpSession session = request.getSession();
	    session.setAttribute("productos", productos);

	    request.getRequestDispatcher("AccionesProductos.jsp").forward(request, response);
		return;
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
		String descripcionProd = request.getParameter("DescripcionProducto");
		String uMProd = request.getParameter("UnidadMedidaProducto");
		String precioProd = request.getParameter("PrecioProducto");
		double precioProducto = Double.parseDouble(precioProd);
		String stockProd = request.getParameter("StockProducto");
		int stockProducto = Integer.parseInt(stockProd);
		
		Producto nuevoProducto = new Producto();
		
		nuevoProducto.setCodProducto(codProd);
		nuevoProducto.setNombre(nombreProd);
		nuevoProducto.setDescripcion(descripcionProd);
		nuevoProducto.setUnidadMedidaProducto(uMProd);
		nuevoProducto.setPrecio(precioProducto);
		nuevoProducto.setStockProducto(stockProducto);
		
        this.ProductoGestor.agregarProducto(nuevoProducto);
        
        response.sendRedirect("ProductoControllers?accion=ProductosAMB");
        
		}
		catch (Exception e) 
		{
			response.sendRedirect("AccionesProductos.jsp?mensaje=Error al agregar producto: " + e.getMessage());
		}
        
	}
			

	private void postModificacionProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    try {
	        String codProd = request.getParameter("CodProducto");

	        if (codProd == null || codProd.isEmpty()) {
	            response.sendRedirect("AccionesProductos.jsp?mensaje=El código del producto es obligatorio.");
	            return;
	        }

	        String nombreProd = request.getParameter("NombreProducto");
	        String descripcionProd = request.getParameter("DescripcionProducto");
	        String uMProd = request.getParameter("UnidadMedidaProducto");
	        String precioProd = request.getParameter("PrecioProducto");
	        String stockProd = request.getParameter("StockProducto");
	        
	        
	        Producto productoModificado = new Producto();

	        if (nombreProd != null && !nombreProd.isEmpty()) {
		        System.out.println("Nuevo Nombre del Producto: " + nombreProd);

	            productoModificado.setNombre(nombreProd);
	        }
	        
	        if (descripcionProd !=null && !descripcionProd.isEmpty()) {
		        System.out.println("Nuevo Descripción del Producto: " + descripcionProd);

				productoModificado.setDescripcion(descripcionProd);
			}

	        if (uMProd != null && !uMProd.isEmpty()) {
		        System.out.println("Nuevo Unidad de Medida del Producto: " + uMProd);

	            productoModificado.setUnidadMedidaProducto(uMProd);
	        }

	        if (precioProd != null && !precioProd.isEmpty()) {
		        System.out.println("Nuevo Precio del Producto: " + precioProd);

	            try {
	                productoModificado.setPrecio(Double.parseDouble(precioProd));
	            } catch (NumberFormatException e) {
	                response.sendRedirect("AccionesProductos.jsp?mensaje=El precio debe ser un número válido.");
	                return;
	            }
	        }

	        if (stockProd != null && !stockProd.isEmpty()) {
		        System.out.println("Nuevo Stock del Producto: " + stockProd);

	            try {
	                productoModificado.setStockProducto(Integer.parseInt(stockProd));
	            } catch (NumberFormatException e) {
	                response.sendRedirect("AccionesProductos.jsp?mensaje=El stock debe ser un número válido.");
	                return;
	            }
	        }

	        boolean modificado = ProductoGestor.modificarProducto(codProd, productoModificado);

	        if (modificado) {
	            response.sendRedirect("ProductoControllers?accion=ProductosAMB&mensaje=Producto modificado exitosamente.");
	        } else {
	            response.sendRedirect("AccionesProductos.jsp?mensaje=No se encontró un producto con el código especificado.");
	        }

	    } catch (Exception e) {
	        response.sendRedirect("AccionesProductos.jsp?mensaje=Error al modificar producto: " + e.getMessage());
	    }
	}

	
	private void postBajaProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    try {
	        String codProd = request.getParameter("CodProducto");

	        if (codProd == null || codProd.isEmpty()) {
	            response.sendRedirect("AccionesProductos.jsp?mensaje=El código del producto es obligatorio para borrar.");
	            return;
	        }

	        boolean eliminado = ProductoGestor.eliminarProducto(codProd);

	        if (eliminado) {
	            response.sendRedirect("ProductoControllers?accion=ProductosAMB&mensaje=Producto eliminado con éxito.");
	        } else {
	            response.sendRedirect("AccionesProductos.jsp?mensaje=No se encontró un producto con el código especificado.");
	        }
	    } catch (Exception e) {
	        response.sendRedirect("AccionesProductos.jsp?mensaje=Error al eliminar producto: " + e.getMessage());
	    }
	}



}
