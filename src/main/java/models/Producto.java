package models;

public class Producto {
    private int idProducto;
    private String codProducto;
    private String nombre;
    private String descripcion;
    private int stockProducto;
    private String unidadMedidaProducto;
    private double precio;
    private String imagen;  

    public Producto(int idProducto, String codProducto ,String nombre, String descripcion, double precio, int stockProducto, String unidadMedidaProducto, String imagen) { 
        this.idProducto = idProducto;
        this.codProducto = codProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidadMedidaProducto=unidadMedidaProducto;
        this.stockProducto=stockProducto;
        this.imagen = imagen; 
    }
    
    
    public Producto() {
		super();
	}

	public int getIdProducto() {
        return idProducto;
    }
	
    public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public String getUnidadMedidaProducto() {
		return unidadMedidaProducto;
	}


	public void setUnidadMedidaProducto(String unidadMedidaProducto) {
		this.unidadMedidaProducto = unidadMedidaProducto;
	}


	public String getDescripcion() { 
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public String getImagen() { 
        return imagen;  
    }
       
	public String getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}

	public int getStockProducto() {
		return stockProducto;
	}

	public void setStockProducto(int stockProducto) {
		this.stockProducto = stockProducto;
	}


	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", codProducto=" + codProducto + ", nombre=" + nombre + ", descripcion="
				+ descripcion + ", stockProducto=" + stockProducto + ", unidadMedidaProducto=" + unidadMedidaProducto
				+ ", precio=" + precio + "]";
	}



}
