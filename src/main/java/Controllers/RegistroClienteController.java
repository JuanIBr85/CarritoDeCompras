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
import models.Usuario;
import models.BilleteraCliente;

@WebServlet("/registrarUsuario")
public class RegistroClienteController extends HttpServlet {
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
        String claveUsuario = request.getParameter("claveUsuario");
        String nombreUsuario = request.getParameter("nombreUsuario");
        String apellidoUsuario = request.getParameter("apellidoUsuario");
        String rolUsuario = "Cliente"; 
        String dniCliente = request.getParameter("dniCliente");

        double saldoInicial = Double.parseDouble(request.getParameter("saldoInicial"));

        Cliente cliente = new Cliente(0, claveUsuario, nombreUsuario, apellidoUsuario, rolUsuario, dniCliente);
        BilleteraCliente cuentaCliente = new BilleteraCliente(cliente, saldoInicial);

        boolean registrado = gestorCuentaUsuario.registrarUsuario(cliente);

        gestorCuentaCliente.agregaCuenta(cuentaCliente);

        if (registrado) {
            request.getSession().setAttribute("usuarioLogueado", cliente);
            request.getSession().setAttribute("cuentaCliente", cuentaCliente);

            List<Usuario> usuarios = gestorCuentaUsuario.obtenerUsuarios();
            if (usuarios != null && !usuarios.isEmpty()) {
                System.out.println("Usuarios registrados:");
                for (Usuario usuario : usuarios) {
                    System.out.println("ID: " + usuario.getIdUsuario() + " - Nombre: " + usuario.getNombreUsuario() + " - Apellido: " + usuario.getApellidoUsuario());
                }
            } else {
                System.out.println("No hay usuarios registrados.");
            }

            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("mensaje", "Error: el usuario ya existe.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }

    }
}


