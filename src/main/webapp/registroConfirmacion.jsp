<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Usuario" %>  
<%@ page import="models.Cliente" %>  <!-- Importar Cliente -->
<%@ page import="models.CuentaCliente" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Clientes y Cuentas de Clientes Registrados</title>
</head>
<body>

<h2>Clientes Registrados</h2>
<%
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
    List<CuentaCliente> cuentasClientes = (List<CuentaCliente>) request.getAttribute("cuentasClientes");
    
    if (usuarios != null) {
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Cliente) { // Es un usuario cliente? uso de instanceof https://stackoverflow.com/questions/7313559/what-is-the-instanceof-operator-used-for-in-java
                Cliente cliente = (Cliente) usuario;
                boolean cuentaEncontrada = false;

                for (CuentaCliente cuenta : cuentasClientes) {
                    if (cuenta.getNroCuenta().equals(cliente.getDniCliente())) { // comparar DNI con nro cuenta, que en nuestro proyecto es lo mismo
                        cuentaEncontrada = true;
%>
                        <p>ID: <%= cliente.getIdUsuario() %>, Nombre: <%= cliente.getNombreUsuario() %> <%= cliente.getApellidoUsuario() %>, DNI: <%= cliente.getDniCliente() %>, Saldo: <%= cuenta.getSaldoCuenta() %></p>
<%
                        break;
                    }
                }

                if (!cuentaEncontrada) {
%>
                    <p>ID: <%= cliente.getIdUsuario() %>, Nombre: <%= cliente.getNombreUsuario() %> <%= cliente.getApellidoUsuario() %>, DNI: <%= cliente.getDniCliente() %>, No tiene cuenta registrada.</p>
<%
                }
            }
        }
    } else {
%>
        <p>No hay clientes registrados.</p>
<%
    }
%>

<h2>Cuentas de Clientes</h2>
<%
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
