package Controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import gestores.GestorUsuarios;
import models.Cliente;
import models.Usuario;

@WebServlet("/loginUsuario")
public class LoginClienteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GestorUsuarios gestorCuentaUsuario;

    //https://keepcoding.io/blog/que-es-el-patron-singleton-en-java/#:~:text=%C2%BFQu%C3%A9%20es%20el%20patr%C3%B3n%20Singleton%20en%20Java%3F%20El,te%20devolver%C3%A1%20el%20mismo%20objeto%20que%20ya%20existe.
    @Override
    public void init() throws ServletException {
        super.init();
        gestorCuentaUsuario = GestorUsuarios.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dniCliente = request.getParameter("dniCliente");
        String claveUsuario = request.getParameter("claveUsuario");

        if (dniCliente == null || claveUsuario == null || dniCliente.isEmpty() || claveUsuario.isEmpty()) {
            request.setAttribute("error", "Por favor completa todos los campos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        Cliente cliente = gestorCuentaUsuario.buscarUsuarioPorDni(dniCliente);

        if (cliente != null && cliente.getClaveUsuario().equals(claveUsuario)) {
            request.getSession().setAttribute("usuarioLogueado", cliente);
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("error", "Cliente o clave incorrectos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }



}
