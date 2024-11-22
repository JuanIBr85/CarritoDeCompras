package Controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import gestores.GestorBilleteraCliente;
import gestores.GestorUsuarios;
import models.Cliente;
import models.BilleteraCliente;

@WebServlet("/loginUsuario")
public class LoginClienteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GestorUsuarios gestorCuentaUsuario;
    private GestorBilleteraCliente gestorCuentaCliente;

    @Override
    public void init() throws ServletException {
        super.init();
        gestorCuentaUsuario = GestorUsuarios.getInstance();
        gestorCuentaCliente = GestorBilleteraCliente.getInstance(); 
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

        try {
            Cliente cliente = gestorCuentaUsuario.buscarUsuarioPorDni(dniCliente);

            if (cliente == null) {
                System.out.println("No se encontró un usuario con DNI: " + dniCliente);
                request.setAttribute("error", "Cliente no encontrado.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            System.out.println("Usuario encontrado:");
            System.out.println("ID: " + cliente.getIdUsuario() + " - Nombre: " + cliente.getNombreUsuario() + " - Apellido: " + cliente.getApellidoUsuario() + " - DNI: " + cliente.getDniCliente());

            if (cliente.getClaveUsuario().equals(claveUsuario)) {
                BilleteraCliente cuentaCliente = gestorCuentaCliente.buscarCuenta(cliente.getDniCliente());

                if (cuentaCliente == null) {
                    System.out.println("Billetera no encontrada para el cliente con DNI: " + cliente.getDniCliente());
                    request.setAttribute("error", "No se encontró una cuenta asociada al cliente.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                }

                HttpSession session = request.getSession();
                session.setAttribute("usuarioLogueado", cliente);
                session.setAttribute("cuentaCliente", cuentaCliente);

                response.sendRedirect("dashboard.jsp");
            } else {
                request.setAttribute("error", "Clave incorrecta.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error inesperado. Por favor, intenta de nuevo.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
