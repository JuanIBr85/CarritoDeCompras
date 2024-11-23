<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
</head>
<body>
    <h2>Bienvenido</h2>

    <%
        models.Usuario usuarioLogueado = (models.Usuario) session.getAttribute("usuarioLogueado");
        models.BilleteraCliente billeteraCliente = (models.BilleteraCliente) session.getAttribute("cuentaCliente");

        
        if (usuarioLogueado != null) {
    %>
        <p>Has iniciado sesión exitosamente.</p>
        <p><strong>Datos del usuario:</strong></p>
        <ul>
            <li><strong>ID:</strong> <%= usuarioLogueado.getIdUsuario() %></li>
            <li><strong>Nombre:</strong> <%= usuarioLogueado.getNombreUsuario() %></li>
            <li><strong>Apellido:</strong> <%= usuarioLogueado.getApellidoUsuario() %></li>
            <li><strong>Rol:</strong> <%= usuarioLogueado.getRolUsuario() %></li>
        </ul>
    <%
        } else {
    %>
        <p><strong>Error:</strong> No se encontró un usuario logueado.</p>
    <%
        }

        if (billeteraCliente != null) {
    %>
        <p><strong>Datos de la billetera:</strong></p>
        <ul>
            <li><strong>DNI de la billetera:</strong> <%= billeteraCliente.getNroCuenta() %></li>
            <li><strong>Saldo:</strong> <%= billeteraCliente.getSaldoCuenta() %></li>
        </ul>
    <%
        } else {
    %>
        <p><strong>Error:</strong> No se encontró la billetera del cliente.</p>
    <%
        }
    %>
<h3>Historial de Compras</h3>
<%
    List<models.Compra> historialCompras = (List<models.Compra>) session.getAttribute("historialCompras");

    if (historialCompras != null && !historialCompras.isEmpty()) {
%>
    <div>
        <% for (models.Compra compra : historialCompras) { %>
            <div style="border: 1px solid #ccc; padding: 10px; margin-bottom: 10px;">
                <p><strong>Cliente DNI:</strong> <%= compra.getClienteDni() %></p>
                <p><strong>Total:</strong> $<%= compra.getTotal() %></p>
                <p><strong>Productos:</strong></p>
                <ul>
                    <% for (models.Producto producto : compra.getProductos()) { %>
                        <li><%= producto.getNombre() %> - $<%= producto.getPrecio() %></li>
                    <% } %>
                </ul>
            </div>
        <% } %>
    </div>
<%
    } else {
%>
    <p>No hay compras registradas.</p>
<%
    }
%>

    <a href="logoutUsuario">Cerrar sesión</a>
</body>
</html>
