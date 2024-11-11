<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Usuario" %>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Usuarios Registrados</title>
</head>
<body>
    <h1>Lista de Usuarios Registrados</h1>
    
    <% 
        List<Usuario> usuarios = null;
        Object usuariosObject = request.getAttribute("usuarios");
        if (usuariosObject instanceof List<?>) {
            usuarios = (List<Usuario>) usuariosObject;
        }
        
        if (usuarios != null && !usuarios.isEmpty()) {
            for (Usuario usuario : usuarios) {
    %>
                <p>ID: <%= usuario.getIdUsuario() %> | Nombre: <%= usuario.getNombreUsuario() %> | ID: <%= usuario.getIdUsuario() %></p>
    <% 
            }
        } else {
    %>
            <p>No hay usuarios registrados.</p>
    <% 
        }
    %>
</body>
</html>
