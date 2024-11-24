package gestores;

import java.nio.channels.NonReadableChannelException;
import java.util.ArrayList;
import java.util.List;

import models.Producto;

public class GestorProducto {

	// Reglas del singleton
	// 1- Constructor private 
	// 2- Que tenga un atributo estatico que haga referencia a la clase 
	// 3- metodo para obtener la instancia. GetInstance() 
		
	private static GestorProducto singleton; // Atributo singleton
	private int ultimoIdProd = 0;
	
	
	public static GestorProducto getInstance() { //metodo para obtener la instancia. GetInstance() 
		 if (singleton == null) {
			singleton = new GestorProducto();
		}
		return singleton;
	}
	
	
	private List<Producto> productos;  
	
	private GestorProducto () { // constructor private
		
		this.productos = new ArrayList<Producto>();
	}


	public void agregarProducto(Producto nProducto) {
		
		boolean existeProd = productos.stream().anyMatch(p -> p.getCodProducto().equals(nProducto.getCodProducto()));

		if (existeProd) { 
			System.out.println("El producto ya existe en la lista.");
		return;
		}

		nProducto.setIdProducto(++ultimoIdProd);
		productos.add(nProducto);
		
		System.out.println("Alta producto exitosa. ID: " + ultimoIdProd);

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
	
	public boolean modificarProducto(String codigo, Producto ProductoModificado) {
		
		Producto productoCreado = buscaProductoPorCod(codigo);
		
		if (productoCreado!=null) {
			
			if (ProductoModificado.getNombre()!=null && !ProductoModificado.getNombre().isEmpty()) {
				productoCreado.setNombre(ProductoModificado.getNombre());
			}
			
			if (ProductoModificado.getDescripcion()!=null && !ProductoModificado.getDescripcion().isEmpty()) {
				productoCreado.setDescripcion(ProductoModificado.getDescripcion());
			}
			
			if (ProductoModificado.getUnidadMedidaProducto()!=null && !ProductoModificado.getUnidadMedidaProducto().isEmpty()) {
				productoCreado.setUnidadMedidaProducto(ProductoModificado.getUnidadMedidaProducto());
			}
			
			if (ProductoModificado.getPrecio()>0) {
				productoCreado.setPrecio(ProductoModificado.getPrecio());
			}
			
			if (ProductoModificado.getStockProducto() > 0) {
				productoCreado.setStockProducto(ProductoModificado.getStockProducto());
			}
			
		 System.out.println("Producto actualizado con éxito.");
		    return true;

		}else {
	        System.out.println("El producto con el código especificado no existe.");
	        return false;
		}
		

	}
	public boolean eliminarProducto(String codigo) {
	    Producto producto = buscaProductoPorCod(codigo);

	    if (producto != null) {
	        productos.remove(producto);
	        System.out.println("Producto con código " + codigo + " eliminado exitosamente.");
	        return true;
	    } else {
	        System.out.println("No se encontró el producto con código " + codigo + ".");
	        return false;
	    }
	}

}
