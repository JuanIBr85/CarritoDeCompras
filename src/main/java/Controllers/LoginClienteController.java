package Controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import gestores.GestorCuentaUsuario;
import models.Usuario;

@WebServlet("/loginUsuario")
public class LoginClienteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GestorCuentaUsuario gestorCuentaUsuario;

    //https://keepcoding.io/blog/que-es-el-patron-singleton-en-java/#:~:text=%C2%BFQu%C3%A9%20es%20el%20patr%C3%B3n%20Singleton%20en%20Java%3F%20El,te%20devolver%C3%A1%20el%20mismo%20objeto%20que%20ya%20existe.
    @Override
    public void init() throws ServletException {
        super.init();
        gestorCuentaUsuario = GestorCuentaUsuario.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idUsuarioParam = request.getParameter("idUsuario");
        String claveUsuario = request.getParameter("claveUsuario");

        System.out.println("Datos recibidos:");
        System.out.println("ID Usuario: " + idUsuarioParam);
        System.out.println("Clave Usuario: " + claveUsuario);

        List<Usuario> usuarios = gestorCuentaUsuario.obtenerUsuarios();
        System.out.println("Usuarios disponibles para login:");
        for (Usuario u : usuarios) {
            System.out.println("ID: " + u.getIdUsuario() + ", Clave: " + u.getClaveUsuario());
        }

        if (idUsuarioParam == null || claveUsuario == null || idUsuarioParam.isEmpty() || claveUsuario.isEmpty()) {
            System.out.println("Error: Uno o más campos están vacíos.");
            request.setAttribute("error", "Por favor completa todos los campos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        int idUsuario = Integer.parseInt(idUsuarioParam);

        Usuario usuario = gestorCuentaUsuario.buscarUsuarioPorId(idUsuario);

        System.out.println("Usuario encontrado: " + (usuario != null ? usuario.toString() : "null"));

        if (usuario != null && usuario.getClaveUsuario().equals(claveUsuario)) {
            System.out.println("Inicio de sesión exitoso para el usuario con ID: " + idUsuario);
            request.getSession().setAttribute("usuarioLogueado", usuario);
            response.sendRedirect("dashboard.jsp"); 
        } else {
            System.out.println("Error: Usuario o clave incorrectos.");
            request.setAttribute("error", "Usuario o clave incorrectos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }


}
