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
import models.Empleado;

@WebServlet("/loginUsuario")
public class LoginController extends HttpServlet {
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
        String tipoUsuario = request.getParameter("tipoUsuario");
        String nroUsuario = request.getParameter("nroUsuario");
        String claveUsuario = request.getParameter("claveUsuario");

        System.out.println("Tipo de Usuario: " + tipoUsuario);
        System.out.println("Numero Usuario: " + nroUsuario);
        System.out.println("Clave del Usuario: " + claveUsuario);

        try {
            if ("cliente".equals(tipoUsuario)) {
                System.out.println("Buscando cliente con DNI: " + nroUsuario);
                Cliente cliente = gestorCuentaUsuario.buscarUsuarioPorDni(nroUsuario);

                if (cliente == null) {
                    System.out.println("Cliente no encontrado.");
                    request.setAttribute("mensaje", "Cliente no encontrado.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                }

                System.out.println("Verificando clave del cliente...");
                if (cliente.getClaveUsuario().equals(claveUsuario)) {
                    System.out.println("Clave correcta, buscando cuenta cliente...");
                    BilleteraCliente cuentaCliente = gestorCuentaCliente.buscarCuenta(cliente.getDniCliente());

                    if (cuentaCliente == null) {
                        System.out.println("Cuenta no encontrada para el cliente.");
                        request.setAttribute("mensaje", "No se encontró una cuenta asociada al cliente.");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                        return;
                    }

                    System.out.println("Cuenta cliente encontrada. Redirigiendo al dashboard.");
                    HttpSession session = request.getSession();
                    session.setAttribute("usuarioLogueado", cliente);
                    session.setAttribute("cuentaCliente", cuentaCliente);

                    response.sendRedirect("dashboard.jsp");
                } else {
                    System.out.println("Clave incorrecta.");
                    request.setAttribute("error", "Clave incorrecta.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else if ("empleado".equals(tipoUsuario)) {
                System.out.println("Buscando empleado con legajo: " + nroUsuario);
                Empleado empleado = gestorCuentaUsuario.buscarEmpleadoPorLegajo(nroUsuario);

                if (empleado == null) {
                    System.out.println("Empleado no encontrado.");
                    request.setAttribute("mensaje", "Empleado no encontrado.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                }

                System.out.println("Verificando clave del empleado...");
                if (empleado.getClaveUsuario().equals(claveUsuario)) {
                    System.out.println("Clave correcta, redirigiendo a acciones de productos.");
                    HttpSession session = request.getSession();
                    session.setAttribute("usuarioLogueado", empleado);

                    response.sendRedirect("AccionesProductos.jsp");
                } else {
                    System.out.println("Clave incorrecta.");
                    request.setAttribute("mensaje", "Clave incorrecta.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                System.out.println("Tipo de usuario no válido.");
                request.setAttribute("mensaje", "Tipo de usuario no válido.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            request.setAttribute("mensaje", "Ocurrió un error inesperado. Por favor, intenta de nuevo.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
