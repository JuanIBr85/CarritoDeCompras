<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Dinero del Cliente</title>
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
        .form-control {
            margin-bottom: 15px;
        }
        .btn-primary {
            background-color: #28a745;
            border-color: #28a745;
        }
        .btn-primary:hover {
            background-color: #218838;
        }
        .error-msg {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <% 
            HttpSession sesion = request.getSession(false);
            if (sesion == null || sesion.getAttribute("usuarioLogueado") == null) {
                response.sendRedirect("login.jsp");
            } else {
                models.Cliente clienteLogueado = (models.Cliente) sesion.getAttribute("usuarioLogueado");
                models.BilleteraCliente billeteraCliente = (models.BilleteraCliente) sesion.getAttribute("cuentaCliente");
        %>
        <div class="text-center mb-4">
            <h2>Gestión de Dinero del Cliente</h2>
        </div>

        <div class="card mb-4">
            <div class="card-header">Información del Usuario</div>
            <div class="card-body">
                <ul class="list-group">
                    <li class="list-group-item"><strong>DNI:</strong> <%= clienteLogueado.getDniCliente() %></li>
                    <li class="list-group-item"><strong>Nombre:</strong> <%= clienteLogueado.getNombreUsuario() %></li>
                    <li class="list-group-item"><strong>Apellido:</strong> <%= clienteLogueado.getApellidoUsuario() %></li>
                </ul>
            </div>
        </div>

        <% if (billeteraCliente != null) { %>
        <div class="card mb-4">
            <div class="card-header">Información de la Billetera</div>
            <div class="card-body">
                <ul class="list-group">
                    <li class="list-group-item"><strong>Saldo:</strong> $<%= billeteraCliente.getSaldoCuenta() %></li>
                </ul>
            </div>
        </div>
        <% } else { %>
        <div class="alert alert-danger">
            <strong>Error:</strong> No se encontró información de la billetera.
        </div>
        <% } %>

        <form action="BilleteraController" method="post" class="mb-4">
            <div class="card">
                <div class="card-header">Ingresar Dinero</div>
                <div class="card-body">
                    <input type="hidden" name="accion" value="deposito">
                    <input type="number" name="monto" placeholder="Monto a ingresar" class="form-control" required>
                    <button type="submit" class="btn btn-primary">Confirmar Depósito</button>
                </div>
            </div>
        </form>

        <form action="BilleteraController" method="post" class="mb-4">
            <div class="card">
                <div class="card-header">Transferencia a Otro Cliente</div>
                <div class="card-body">
                    <input type="hidden" name="accion" value="transferencia">
                    <input type="text" name="clienteDestino" placeholder="DNI destinatario" class="form-control" required>
                    <input type="number" name="monto" placeholder="Monto a transferir" class="form-control" required>
                    <button type="submit" class="btn btn-primary">Transferir monto</button>
                </div>
            </div>
        </form>

        <% 
            String mensaje = (String) session.getAttribute("mensaje");
            if (mensaje != null) {
        %>
        <div class="alert alert-warning">
            <%= mensaje %>
        </div>
        <%
            session.removeAttribute("mensaje");
            }
        %>

        <% } %>
    </div>

</body>
</html>
