
package models;


public class Producto {
	
	 private String codProducto;
	 private String nombreProducto;
	 private String unidadMedidaProductoString;
	 private double precioProducto;
	 private int stockProducto;
	 
	 
	public Producto(String codProducto, String nombreProducto, String unidadMedidaProducto, double precioProducto, int stockProducto) {
		super();
		this.codProducto = codProducto;
		this.nombreProducto = nombreProducto;
		this.unidadMedidaProductoString = unidadMedidaProducto;
		this.precioProducto = precioProducto;
		this.stockProducto = stockProducto;
	}

	public String getUnidadMedidaProductoString() {	
		return unidadMedidaProductoString;
	}

	public void setUnidadMedidaProductoString(String unidadMedidaProductoString) {
		this.unidadMedidaProductoString = unidadMedidaProductoString;
	}

	public Producto() {
		super();
	}


	public String getCodProducto() {
		return codProducto;
	}


	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}


	public String getNombreProducto() {
		return nombreProducto;
	}


	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}


	public double getPrecioProducto() {
		return precioProducto;
	}


	public void setPrecioProducto(double precioProducto) {
		this.precioProducto = precioProducto;
	}

		

	public int getStockProducto() {
		return stockProducto;
	}


	public void setStockProducto(int stockProducto) {
		this.stockProducto = stockProducto;
	}

	@Override
	public String toString() {
		return "Producto [Codigo= " + codProducto + ", Nombre= " + nombreProducto
				+ ", UM= " + unidadMedidaProductoString + ", Precio= " + precioProducto
				+ ", Stock= " + stockProducto + "]";
	}





	 
	
}
