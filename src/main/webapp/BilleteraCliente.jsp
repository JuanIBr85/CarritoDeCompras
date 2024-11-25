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
            margin-bottom: 40px;
            font-size: 2rem;
            font-weight: bold;
            color: #007bff;
        }
        .card {
            border-radius: 10px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        }
        .card-header {
            background-color: #007bff;
            color: white;
            font-weight: bold;
        }
        .btn-primary, .btn-success, .btn {
            width: 100%;
            font-size: 1rem;
            padding: 10px;
            border-radius: 5px;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .btn-success:hover {
            background-color: #218838;
        }
        .form-container h3 {
            margin-bottom: 20px;
            color: #6c757d;
            font-weight: bold;
        }
        .alert-danger {
            margin-top: 20px;
            border-radius: 5px;
        }
        .gap-4 {
            gap: 1.5rem;
        }
        .btn-sm {
        padding: 5px 10px;
        font-size: 0.875rem;
        width: 9rem;
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
                Información del Cliente
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
                Información de la Billetera
            </div>
            <div class="card-body">
                <ul>
                    <li><strong>Saldo:</strong> $<%= billeteraCliente.getSaldoCuenta() %></li>
                </ul>
            </div>
        </div>
        <% } else { %>
        <div class="alert alert-danger">
            <strong>Error:</strong> No se encontró información de la billetera.
        </div>
        <% } %>

        <div class="d-flex form-container gap-4 justify-content-center">
            <div class="col-md-5 d-flex flex-column card p-4">
                <form action="BilleteraController" method="post" class="h-100 d-flex flex-column">
                    <h3>Ingresar Dinero</h3>
                    <input type="hidden" name="accion" value="deposito">
                    <div class="form-group">
                        <input type="number" name="monto" class="form-control" placeholder="Monto a ingresar" required>
                    </div>
                    <button type="submit" class="btn btn-primary mt-auto">Confirmar Depósito</button>
                </form>
            </div>

            <div class="col-md-5 d-flex flex-column card p-4">
                <form action="BilleteraController" method="post" class="h-100 d-flex flex-column">
                    <h3>Transferencia a Otro Cliente</h3>
                    <input type="hidden" name="accion" value="transferencia">
                    <div class="form-group">
                        <input type="text" name="clienteDestino" class="form-control" placeholder="DNI destinatario" required>
                    </div>
                    <div class="form-group">
                        <input type="number" name="monto" class="form-control" placeholder="Monto a transferir" required>
                    </div>
                    <button type="submit" class="btn btn-success mt-auto">Transferir Monto</button>
                </form>
            </div>
        </div>

       <div class="d-flex justify-content-center my-4 gap-4">
    <a href="ProductoControllers?accion=ListaProductos" class="btn btn-info btn-sm">Ver Productos</a>
    <a href="logout" class="btn btn-danger btn-sm">Cerrar Sesión</a>
</div>

    </div>

<% 
    }
%>

<%
    String mensaje = (String) session.getAttribute("mensaje");
    if (mensaje != null) {
%>
        <div class="container">
            <div class="alert alert-danger text-center">
                <%= mensaje %>
            </div>
        </div>
<%
        session.removeAttribute("mensaje");
    }
%>

</body>
</html>
