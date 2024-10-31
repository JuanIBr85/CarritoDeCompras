package Controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestores.GestorProducto;
import models.Producto;
import servicios.ProductoService;


@WebServlet("/ProductoControllers")
public class ProductoControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GestorProducto ProductoGestor;
		
    public ProductoControllers() {
    	this.ProductoGestor = GestorProducto.getInstance();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.addHeader("Content-type", "text/plain");
		PrintWriter escritor = response.getWriter();
		
		for (Producto producto : ProductoGestor.obtenerListaProductos()) {
			escritor.append(producto.toString() + "\n");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String codProd = request.getParameter("CodProducto");
		String nombreProd = request.getParameter("NombreProducto");
		String uMProdString = request.getParameter("UnidadMedidaProducto");
		String precioProd = request.getParameter("PrecioProducto");
		double precioProducto = Double.parseDouble(precioProd);
		String stockProd = request.getParameter("StockProducto");
		int stockProducto = Integer.parseInt(stockProd);
		
		Producto nuevoProducto = new Producto();
		
		nuevoProducto.setCodProducto(codProd);
		nuevoProducto.setNombreProducto(nombreProd);
		nuevoProducto.setUnidadMedidaProductoString(uMProdString);
		nuevoProducto.setPrecioProducto(precioProducto);
		nuevoProducto.setStockProducto(stockProducto);
		
		this.ProductoGestor.agregarProducto(nuevoProducto);
		
	}

}
