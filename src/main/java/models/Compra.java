package models;

import java.util.List;
import java.util.ArrayList;

public class Compra {
    private String clienteDni;
    private List<Producto> productos;
    private double total;
    private static List<Compra> historialCompras = new ArrayList<>();

    
    public Compra(String clienteDni, List<Producto> productos, double total) {
        this.clienteDni = clienteDni;
        this.productos = productos;
        this.total = total;
    }

    public String getClienteDni() {
        return clienteDni;
    }

    public void setClienteDni(String clienteDni) {
        this.clienteDni = clienteDni;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public static void agregarCompra(Compra compra) {
        historialCompras.add(compra);
    }
    
    public static List<Compra> obtenerHistorialCompras() {
        return new ArrayList<>(historialCompras);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Compra [Cliente DNI: ").append(clienteDni).append(", total: $").append(total).append(", Productos: ");
        
        for (Producto producto : productos) {
            sb.append("{Nombre: ").append(producto.getNombre()).append(", Precio: $").append(producto.getPrecio()).append("}, ");
        }
        
        if (!productos.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        
        sb.append("]");
        return sb.toString();
    }
}
