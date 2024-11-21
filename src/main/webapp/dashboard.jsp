<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h2>Bienvenido</h2>

    <%
        models.Usuario usuarioLogueado = (models.Usuario) session.getAttribute("usuarioLogueado");

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
        <p>Error: No se encontró un usuario logueado.</p>
    <%
        }
    %>

    <a href="logoutUsuario">Cerrar sesión</a>
</body>
</html>
