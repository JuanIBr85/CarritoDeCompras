package Controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestores.GestorCuentaCliente;
import gestores.GestorCuentaUsuario;
import models.Cliente;
import models.Usuario;
import models.CuentaCliente;

@WebServlet("/registrarUsuario")
public class RegistroClienteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GestorCuentaUsuario gestorCuentaUsuario;
    private GestorCuentaCliente gestorCuentaCliente;

    @Override
    public void init() throws ServletException {
        super.init();
        gestorCuentaUsuario = new GestorCuentaUsuario(); 
        gestorCuentaCliente = new GestorCuentaCliente(); 
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        String claveUsuario = request.getParameter("claveUsuario");
        String nombreUsuario = request.getParameter("nombreUsuario");
        String apellidoUsuario = request.getParameter("apellidoUsuario");
        String rolUsuario = "Cliente"; 
        String dniCliente = request.getParameter("dniCliente");

        Cliente cliente = new Cliente(idUsuario, claveUsuario, nombreUsuario, apellidoUsuario, rolUsuario, dniCliente);

        CuentaCliente cuentaCliente = new CuentaCliente(cliente, 0);  

        boolean registrado = gestorCuentaUsuario.registrarUsuario(cliente);

        gestorCuentaCliente.agregaCuenta(cuentaCliente);

        if (registrado) {
            request.setAttribute("mensaje", "Usuario registrado exitosamente.");
        } else {
            request.setAttribute("mensaje", "Error: el usuario ya existe.");
        }

        List<Usuario> usuarios = gestorCuentaUsuario.obtenerUsuarios();
        List<CuentaCliente> cuentasClientes = gestorCuentaCliente.obtenerCuentas();  

        request.setAttribute("usuarios", usuarios);
        
        if (cuentasClientes != null && !cuentasClientes.isEmpty()) {
            System.out.println("Cuentas de Clientes:");
            for (CuentaCliente cuenta : cuentasClientes) {
                System.out.println("Número de Cuenta: " + cuenta.getNroCuenta() + ", Saldo: " + cuenta.getSaldoCuenta());
            }
        } else {
            System.out.println("No hay cuentas de clientes.");
        }

        request.setAttribute("cuentasClientes", cuentasClientes); 
        
        request.getRequestDispatcher("registroConfirmacion.jsp").forward(request, response);
    }
}

