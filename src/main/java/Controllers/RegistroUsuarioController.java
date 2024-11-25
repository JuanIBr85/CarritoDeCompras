package Controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestores.GestorBilleteraCliente;
import gestores.GestorUsuarios;
import models.Cliente;
import models.Empleado;
import models.Usuario;
import models.BilleteraCliente;

@WebServlet("/registrarUsuario")
public class RegistroUsuarioController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GestorUsuarios gestorCuentaUsuario;
    private GestorBilleteraCliente gestorCuentaCliente;

    @Override
    public void init() throws ServletException {
        super.init();
        gestorCuentaUsuario = GestorUsuarios.getInstance();
        gestorCuentaCliente = GestorBilleteraCliente.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipoUsuario = request.getParameter("tipoUsuario"); 

        switch (tipoUsuario) {
            case "cliente" -> postCliente(request, response);
            case "empleado" -> postEmpleado(request, response);
            default -> response.sendError(404, "Tipo de usuario no válido.");
        }
    }

    private void postCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String claveUsuario = request.getParameter("claveUsuario");
        String nombreUsuario = request.getParameter("nombreUsuario");
        String apellidoUsuario = request.getParameter("apellidoUsuario");
        String dni = request.getParameter("dniCliente");
        double saldoInicial = Double.parseDouble(request.getParameter("saldoInicial"));

        Cliente cliente = new Cliente(0, claveUsuario, nombreUsuario, apellidoUsuario, "Cliente", dni);
        BilleteraCliente cuentaCliente = new BilleteraCliente(cliente, saldoInicial);

        boolean registrado = gestorCuentaUsuario.registrarUsuario(cliente);
        if (registrado) {
            gestorCuentaCliente.agregaCuenta(cuentaCliente);
            request.getSession().setAttribute("usuarioLogueado", cliente);
            request.getSession().setAttribute("cuentaCliente", cuentaCliente);
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("mensaje", "Error: el cliente ya existe.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
    }

    private void postEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String claveUsuario = request.getParameter("claveUsuario");
        String nombreUsuario = request.getParameter("nombreUsuario");
        String apellidoUsuario = request.getParameter("apellidoUsuario");
        String legajoEmpleado = request.getParameter("legajoEmpleado");

        Empleado empleado = new Empleado(0, claveUsuario, nombreUsuario, apellidoUsuario, "Empleado");
        empleado.setLegajoEmpleado(legajoEmpleado)	;

        boolean registrado = gestorCuentaUsuario.registrarUsuario(empleado);
        if (registrado) {
            request.getSession().setAttribute("usuarioLogueado", empleado);
            response.sendRedirect("AccionesProductos.jsp"); 
        } else {
            request.setAttribute("mensaje", "Error: el empleado ya existe.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
    }

}
