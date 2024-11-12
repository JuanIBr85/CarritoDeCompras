<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Usuario" %>  
<%@ page import="models.CuentaCliente" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Usuarios y Cuentas de Clientes Registrados</title>
</head>
<body>

<h2>Usuarios Registrados</h2>
<%
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
    if (usuarios != null) {
        for (Usuario usuario : usuarios) {
%>
            <p>ID: <%= usuario.getIdUsuario() %>, Nombre: <%= usuario.getNombreUsuario() %> <%= usuario.getApellidoUsuario() %></p>
<%
        }
    } else {
%>
        <p>No hay usuarios registrados.</p>
<%
    }
%>

<h2>Cuentas de Clientes</h2>
<%
    List<CuentaCliente> cuentasClientes = (List<CuentaCliente>) request.getAttribute("cuentasClientes");
    if (cuentasClientes != null) {
        for (CuentaCliente cuenta : cuentasClientes) {
%>
            <p>Número de Cuenta: <%= cuenta.getNroCuenta() %>, Saldo: <%= cuenta.getSaldoCuenta() %></p>
<%
        }
    } else {
%>
        <p>No hay cuentas de clientes registradas.</p>
<%
    }
%>

<p><%= request.getAttribute("mensaje") %></p>

</body>
</html>
