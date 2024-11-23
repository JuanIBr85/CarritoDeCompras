<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Dinero del Cliente</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .container {
            margin-top: 50px;
        }
        .section-title {
            margin-bottom: 20px;
        }
        .alert {
            margin-top: 20px;
        }
        .form-container {
            margin-top: 30px;
        }
        h3 {
            margin-bottom: 15px;
        }
    </style>
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

    <div class="container">
        <h2 class="text-center section-title">Gestión de Dinero del Cliente</h2>

        <div class="card mb-4">
            <div class="card-header">
                <h3>Información del Usuario</h3>
            </div>
            <div class="card-body">
                <ul>
                    <li><strong>DNI:</strong> <%= clienteLogueado.getDniCliente() %></li>
                    <li><strong>Nombre:</strong> <%= clienteLogueado.getNombreUsuario() %></li>
                    <li><strong>Apellido:</strong> <%= clienteLogueado.getApellidoUsuario() %></li>
                </ul>
            </div>
        </div>

        <% if (billeteraCliente != null) { %>
        <div class="card mb-4">
            <div class="card-header">
                <h3>Información de la Billetera</h3>
            </div>
            <div class="card-body">
                <ul>
                    <li><strong>Saldo:</strong> <%= billeteraCliente.getSaldoCuenta() %></li>
                </ul>
            </div>
        </div>
        <% } else { %>
        <div class="alert alert-danger">
            <strong>Error:</strong> No se encontró información de la billetera.
        </div>
        <% } %>

        <div class="form-container">
            <form action="BilleteraController" method="post" class="mb-4">
                <h3>Ingresar Dinero</h3>
                <input type="hidden" name="accion" value="deposito">
                <div class="form-group">
                    <input type="number" name="monto" class="form-control" placeholder="Monto a ingresar" required>
                </div>
                <button type="submit" class="btn btn-primary">Confirmar Depósito</button>
            </form>

            <form action="BilleteraController" method="post" class="mb-4">
                <h3>Transferencia a Otro Cliente</h3>
                <input type="hidden" name="accion" value="transferencia">
                <div class="form-group">
                    <input type="text" name="clienteDestino" class="form-control" placeholder="DNI destinatario" required>
                </div>
                <div class="form-group">
                    <input type="number" name="monto" class="form-control" placeholder="Monto a transferir" required>
                </div>
                <button type="submit" class="btn btn-success">Transferir Monto</button>
            </form>

            <!-- 
            <form action="BilleteraController" method="post">
                <h3>Realizar Pago</h3>
                <input type="hidden" name="accion" value="pago">
                <div class="form-group">
                    <input type="number" name="monto" class="form-control" placeholder="Monto a pagar" required>
                </div>
                <button type="submit" class="btn btn-danger">Realizar Pago</button>
            </form>
            -->
        </div>

    </div>

<% 
    }
%>

<%
    String mensaje = (String) session.getAttribute("mensaje");
    if (mensaje != null) {
%>
        <div class="alert alert-danger text-center">
            <%= mensaje %>
        </div>
<%
        session.removeAttribute("mensaje");
    }
%>


</body>
</html>
