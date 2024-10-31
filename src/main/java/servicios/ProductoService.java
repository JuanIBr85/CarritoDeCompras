 package servicios;

import java.util.ArrayList;
import java.util.List;

 
import models.Producto;

public class ProductoService {
	
	/* Reglas del singleton
	 *  1- Constructor en privado.
	 *  2-
	 *  3-
	 */
	
	private List<Producto> productos = new ArrayList<Producto>();
	
	public void altaProducto (Producto producto) {
		
		
		
		
		productos.add(producto);
	}
	
	public List<Producto> obtenerListaProductos(){
		return new ArrayList<>(productos);
	}
	
	/**
	 * 
	 * @param codigo codigo del producto a buscar
	 * @return 
	 */
	public Producto buscaProductoPorCod(String codigo) {
		
		return productos.stream()
						.filter(p -> p.getCodProducto().equals(codigo))
						.findFirst()
						.orElse(null);
	}
	
	public void darIngresoAlStock(String codigo , int cantidad) {
		
		Producto prod = buscaProductoPorCod(codigo);
		if (prod != null) {
			prod.setStockProducto(prod.getStockProducto()+cantidad);
		}
	}
	
	public void darBajaAlStock(String codigo , int cantidad) {
		
		Producto prod = buscaProductoPorCod(codigo);
		if (prod != null && prod.getStockProducto()>= cantidad ) {
			prod.setStockProducto(prod.getStockProducto()-cantidad);
		} else {
			System.out.println("El stock no es suficiente.");
		}
	}
	
}
