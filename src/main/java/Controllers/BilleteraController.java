package Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintStream;

import models.Producto;
import gestores.GestorBilleteraCliente;
import gestores.GestorPago;
import models.Cliente;  
import models.Compra;
import models.Producto;

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
            case "Billetera" -> getBilletera(request, response);
            default -> response.sendError(404, "No existe la acción.");
        }
    }

    private void getBilletera(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("BilleteraCliente.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        
        Cliente clienteLogueado = (Cliente) session.getAttribute("usuarioLogueado");
        
        if (clienteLogueado == null) {
            response.sendError(404, "Usuario no autenticado.");
            return;
        }

        String nroCuenta = clienteLogueado.getDniCliente();  
        
        String accion = request.getParameter("accion");
        accion = Optional.ofNullable(accion).orElse("-");

        switch (accion) {
            case "deposito" -> postDeposito(request, response, nroCuenta);
            case "transferencia" -> postTransferencia(request, response, nroCuenta);
            case "pago" -> postPago(request, response, nroCuenta);
            default -> response.sendError(404, "No existe la acción.");
        }
    }

    private void postDeposito(HttpServletRequest request, HttpServletResponse response, String nroCuenta) throws IOException {
        double monto = Double.parseDouble(request.getParameter("monto"));  
        gestorBilleteraCliente.sumaDineroEnCuenta(nroCuenta, monto);

        response.sendRedirect("BilleteraCliente.jsp?saldo=" + gestorBilleteraCliente.buscarCuenta(nroCuenta).getSaldoCuenta());
    }

    private void postTransferencia(HttpServletRequest request, HttpServletResponse response, String nroCuenta) throws IOException {
        String nroCuentaDestino = request.getParameter("clienteDestino");
        double monto = Double.parseDouble(request.getParameter("monto"));

        String mensaje = gestorBilleteraCliente.transferirDinero(nroCuentaDestino, nroCuenta, monto);

        HttpSession session = request.getSession();
        session.setAttribute("mensaje", mensaje);

        response.sendRedirect("BilleteraCliente.jsp?saldo=" + gestorBilleteraCliente.buscarCuenta(nroCuenta).getSaldoCuenta());
    }

    private void postPago(HttpServletRequest request, HttpServletResponse response, String nroCuenta) throws IOException {
        System.out.println("Iniciando postPago...");

        double monto = Double.parseDouble(request.getParameter("monto"));
        String clienteDni = request.getParameter("clienteDni");
        System.out.println("Monto: " + monto);
        System.out.println("Cliente DNI: " + clienteDni);

        GestorPago gestorPago = new GestorPago(gestorBilleteraCliente);

        boolean pagoExitoso = gestorPago.procesarPago(nroCuenta, monto, clienteDni, request.getSession());

        if (pagoExitoso) {
            double saldoActual = gestorPago.obtenerSaldoActual(nroCuenta);
            System.out.println("Saldo actualizado: " + saldoActual);
            response.sendRedirect("dashboard.jsp");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("mensaje", "No se pudo procesar la compra. El carrito está vacío.");
            response.sendRedirect("dashboard.jsp");
        }
    }
}
