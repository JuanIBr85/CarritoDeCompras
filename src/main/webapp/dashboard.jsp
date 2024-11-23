<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 30px;
        }
        .card-header {
            background-color: #007bff;
            color: white;
        }
        .card {
            margin-bottom: 20px;
        }
        .logout-btn {
            background-color: #dc3545;
            color: white;
        }
        .logout-btn:hover {
            background-color: #c82333;
                        color: white;
            
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="text-center mb-4">
            <h2>Bienvenido</h2>
        </div>

        <%
            models.Usuario usuarioLogueado = (models.Usuario) session.getAttribute("usuarioLogueado");
            models.BilleteraCliente billeteraCliente = (models.BilleteraCliente) session.getAttribute("cuentaCliente");

            if (usuarioLogueado != null) {
        %>
        <div class="card">
            <div class="card-header">
                Datos del Usuario
            </div>
            <div class="card-body">
                <ul class="list-group">
                    <li class="list-group-item"><strong>ID:</strong> <%= usuarioLogueado.getIdUsuario() %></li>
                    <li class="list-group-item"><strong>Nombre:</strong> <%= usuarioLogueado.getNombreUsuario() %></li>
                    <li class="list-group-item"><strong>Apellido:</strong> <%= usuarioLogueado.getApellidoUsuario() %></li>
                    <li class="list-group-item"><strong>Rol:</strong> <%= usuarioLogueado.getRolUsuario() %></li>
                </ul>
            </div>
        </div>
        <%
            } else {
        %>
        <div class="alert alert-danger">
            <strong>Error:</strong> No se encontró un usuario logueado.
        </div>
        <%
            }

            if (billeteraCliente != null) {
        %>
        <div class="card">
            <div class="card-header">
                Datos de la Billetera
            </div>
            <div class="card-body">
                <ul class="list-group">
                    <li class="list-group-item"><strong>DNI de la billetera:</strong> <%= billeteraCliente.getNroCuenta() %></li>
                    <li class="list-group-item"><strong>Saldo:</strong> $<%= billeteraCliente.getSaldoCuenta() %></li>
                </ul>
            </div>
        </div>
        <%
            } else {
        %>
        <div class="alert alert-danger">
            <strong>Error:</strong> No se encontró la billetera del cliente.
        </div>
        <%
            }
        %>

        <h3 class="mt-4">Historial de Compras</h3>
        <%
            List<models.Compra> historialCompras = (List<models.Compra>) session.getAttribute("historialCompras");

            if (historialCompras != null && !historialCompras.isEmpty()) {
        %>
        <div class="row">
            <% for (models.Compra compra : historialCompras) { %>
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        Compra de Cliente DNI: <%= compra.getClienteDni() %>
                    </div>
                    <div class="card-body">
                        <p><strong>Total:</strong> $<%= compra.getTotal() %></p>
                        <p><strong>Productos:</strong></p>
                        <ul class="list-group">
                            <% for (models.Producto producto : compra.getProductos()) { %>
                            <li class="list-group-item">
                                <%= producto.getNombre() %> - $<%= producto.getPrecio() %>
                            </li>
                            <% } %>
                        </ul>
                    </div>
                </div>
            </div>
            <% } %>
        </div>
        <%
            } else {
        %>
        <div class="alert alert-warning">
            No hay compras registradas.
        </div>
        <%
            }
        %>

        <div class="text-center mt-4">
            <a href="logoutUsuario" class="btn logout-btn">Cerrar sesión</a>
        </div>
    </div>

</body>
</html>
