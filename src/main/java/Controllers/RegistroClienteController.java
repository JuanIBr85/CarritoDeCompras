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

@WebServlet("/registrarUsuario")
public class RegistroClienteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GestorCuentaUsuario gestorCuentaUsuario;

    @Override
    public void init() throws ServletException {
        super.init();
        gestorCuentaUsuario = new GestorCuentaUsuario(); 
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        String claveUsuario = request.getParameter("claveUsuario");
        String nombreUsuario = request.getParameter("nombreUsuario");
        String apellidoUsuario = request.getParameter("apellidoUsuario");
        String rolUsuario = "Cliente"; 
        String dniCliente = request.getParameter("dniCliente");

        Cliente cliente = new Cliente(idUsuario, claveUsuario, nombreUsuario, apellidoUsuario, rolUsuario);
        cliente.setDniCliente(dniCliente);

        boolean registrado = gestorCuentaUsuario.registrarUsuario(cliente);

        if (registrado) {
            request.setAttribute("mensaje", "Usuario registrado exitosamente.");
        } else {
            request.setAttribute("mensaje", "Error: el usuario ya existe.");
        }

        List<Usuario> usuarios = gestorCuentaUsuario.obtenerUsuarios();
        
        request.setAttribute("usuarios", usuarios);
        
        request.getRequestDispatcher("registroConfirmacion.jsp").forward(request, response);    }
}
