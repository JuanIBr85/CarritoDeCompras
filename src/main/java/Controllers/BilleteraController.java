package Controllers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gestores.GestorBilleteraCliente;


@WebServlet("/BilleteraController")
public class BilleteraController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
private GestorBilleteraCliente gestorBilleteraCliente;


    public BilleteraController() {
        super();
        this.gestorBilleteraCliente = GestorBilleteraCliente.getInstance();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
        String accion = request.getParameter("accion");
        accion = Optional.ofNullable(accion).orElse("Billetera");
        
        switch (accion) {
        case "Billetera"-> getBilletera(request,response);
        default -> response.sendError(404,"No exite la accion.");
        }
       	
 }

	private void getBilletera(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("BilleteraCliente.jsp");
		return;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    HttpSession session = request.getSession();
	    String nroCuenta = (String) session.getAttribute("dniCliente"); // Obtener DNI de la sesión
	    
	    if (nroCuenta == null) {
	        response.sendError(404, "Usuario no autenticado.");
	        return;
	    }
		
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("-");
		
		switch (accion) {
		case "deposito" -> postDeposito(request,response,nroCuenta);
		case "transferencia" -> postTransferencia(request,response,nroCuenta);
		case "pago" ->postPago(request,response,nroCuenta);
		default -> response.sendError(404,"No exite la accion.");
		}

	}


	private void postDeposito(HttpServletRequest request, HttpServletResponse response, String nroCuenta) throws IOException {
		
		double monto = Double.parseDouble("monto");
		gestorBilleteraCliente.sumaDineroEnCuenta(nroCuenta, monto);
		
		response.sendRedirect("BilleteraCliente.jsp?saldo=" + gestorBilleteraCliente.buscarCuenta(nroCuenta).getSaldoCuenta());
	}


	private void postTransferencia(HttpServletRequest request, HttpServletResponse response, String nroCuenta) throws IOException {
		String nroCuentaDestino = request.getParameter("clienteDestino");
	    double monto = Double.parseDouble(request.getParameter("monto"));

	    gestorBilleteraCliente.transferirDinero(nroCuentaDestino, nroCuenta, monto);

	    response.sendRedirect("BilleteraCliente.jsp?saldo=" + gestorBilleteraCliente.buscarCuenta(nroCuenta).getSaldoCuenta());
	}


	private void postPago(HttpServletRequest request, HttpServletResponse response, String nroCuenta) throws IOException {
		double monto = Double.parseDouble(request.getParameter("monto"));

	    gestorBilleteraCliente.restaDineroEnCuenta(nroCuenta, monto);

	    response.sendRedirect("BilleteraCliente.jsp?saldo=" + gestorBilleteraCliente.buscarCuenta(nroCuenta).getSaldoCuenta());
	}

}
