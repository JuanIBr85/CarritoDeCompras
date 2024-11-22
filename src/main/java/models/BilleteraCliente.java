 package models;

public class BilleteraCliente {
	
    private String nroCtaCliente;
    private double saldoCtaCliente;
    
    
	public BilleteraCliente() {
		super();
	}

	public BilleteraCliente(Cliente cliente, double saldoCuenta) {
		this.nroCtaCliente = cliente.getDniCliente();
		this.saldoCtaCliente = saldoCuenta;
	}
    
	public String getNroCuenta() {
		return nroCtaCliente;
	}

	public double getSaldoCuenta() {
		return saldoCtaCliente;
	}
	
	public void setSaldoCuenta(double saldoCuenta) {
		this.saldoCtaCliente = saldoCuenta;
	}

}	

   
    
    




