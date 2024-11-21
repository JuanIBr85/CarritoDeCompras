package Controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestores.GestorCuentaCliente;
import gestores.GestorUsuarios;
import models.Cliente;
import models.Usuario;
import models.CuentaCliente;

@WebServlet("/registrarUsuario")
public class RegistroClienteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GestorUsuarios gestorCuentaUsuario;
    private GestorCuentaCliente gestorCuentaCliente;

    
    //https://keepcoding.io/blog/que-es-el-patron-singleton-en-java/#:~:text=%C2%BFQu%C3%A9%20es%20el%20patr%C3%B3n%20Singleton%20en%20Java%3F%20El,te%20devolver%C3%A1%20el%20mismo%20objeto%20que%20ya%20existe.
    @Override
    public void init() throws ServletException {
        super.init();
        gestorCuentaUsuario = GestorUsuarios.getInstance();
        gestorCuentaCliente = GestorCuentaCliente.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        String claveUsuario = request.getParameter("claveUsuario");
        String nombreUsuario = request.getParameter("nombreUsuario");
        String apellidoUsuario = request.getParameter("apellidoUsuario");
        String rolUsuario = "Cliente"; 
        String dniCliente = request.getParameter("dniCliente");

        double saldoInicial = Double.parseDouble(request.getParameter("saldoInicial"));

        Cliente cliente = new Cliente(idUsuario, claveUsuario, nombreUsuario, apellidoUsuario, rolUsuario, dniCliente);

        CuentaCliente cuentaCliente = new CuentaCliente(cliente, saldoInicial);  

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
