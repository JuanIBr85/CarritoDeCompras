<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión de Dinero del Cliente</title>
</head>
<body>

<% 
    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("usuarioLogueado") == null) {
        response.sendRedirect("login.jsp"); 
    } else {
        models.Cliente clienteLogueado = (models.Cliente) sesion.getAttribute("usuarioLogueado");
        models.BilleteraCliente billeteraCliente = (models.BilleteraCliente) sesion.getAttribute("cuentaCliente");
%>

        <h2>Gestión de Dinero del Cliente</h2>
        
        <div>
            <h3>Información del Usuario</h3>
            <ul>
                <li><strong>DNI:</strong> <%= clienteLogueado.getDniCliente() %></li>
                <li><strong>Nombre:</strong> <%= clienteLogueado.getNombreUsuario() %></li>
                <li><strong>Apellido:</strong> <%= clienteLogueado.getApellidoUsuario() %></li>
            </ul>
        </div>

        <% if (billeteraCliente != null) { %>
        <div>
            <h3>Información de la Billetera</h3>
            <ul>
                <li><strong>Saldo:</strong> <%= billeteraCliente.getSaldoCuenta() %></li>
            </ul>
        </div>
        <% } else { %>
        <div>
            <p><strong>Error:</strong> No se encontró información de la billetera.</p>
        </div>
        <% } %>

        <form action="BilleteraController" method="post">
            <h3>Ingresar Dinero</h3>
            <input type="hidden" name="accion" value="deposito">
            <input type="number" name="monto" placeholder="Monto a ingresar" required>
            <button type="submit">Confirmar Depósito</button>
        </form>

        <form action="BilleteraController" method="post">
            <h3>Transferencia a Otro Cliente</h3>
            <input type="hidden" name="accion" value="transferencia">
            <input type="text" name="clienteDestino" placeholder="DNI destinatario" required>
            <input type="number" name="monto" placeholder="Monto a transferir" required>
            <button type="submit">Transferir monto</button>
        </form>

        <form action="BilleteraController" method="post">
            <h3>Realizar Pago</h3>
            <input type="hidden" name="accion" value="pago">
            <input type="number" name="monto" placeholder="Monto a pagar" required>
            <button type="submit">Realizar Pago</button>
        </form>

<% 
    }
%>
</body>
</html>
