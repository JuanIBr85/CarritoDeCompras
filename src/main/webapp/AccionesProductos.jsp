<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.Producto" %>
<%@ page import="models.Cliente" %>
<%@ page import="models.BilleteraCliente" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestor de Productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .container {
            margin-top: 30px;
        }

        .form-container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .btn-custom {
            background-color: #007bff;
            color: white;
            font-size: 1.1rem;
            border-radius: 0.3rem;
        }

        .btn-custom:hover {
            background-color: #0056b3;
            transition: background-color 0.3s ease;
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

<%
    models.Usuario usuarioLogueado = (models.Usuario) session.getAttribute("usuarioLogueado");

    if (usuarioLogueado == null || !"Empleado".equals(usuarioLogueado.getRolUsuario())) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<div class="container mt-5">
    <h1 class="text-center text-primary mb-4">Gestor de Productos</h1>
    <% if (request.getParameter("mensaje") != null) { %>
    <div class="alert alert-success" role="alert">
        <%=request.getParameter("mensaje")%>
    </div>
    <% } else if (request.getParameter("error") != null) { %>
    <div class="alert alert-danger" role="alert">
        <%=request.getParameter("error")%>
    </div>
    <% } %>

<%
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
%>

<div class="row">
    <div class="col-6">
        <div class="form-container p-4 shadow rounded">
            <h2 class="text-center mb-4">Alta Producto</h2>
            <form action="ProductoControllers" method="post">
                <input type="hidden" name="accion" value="Alta">
                <div class="row">
                    <div class="col-12 mb-3">
                        <label for="CodProducto" class="form-label">Código:</label>
                        <input type="text" id="CodProducto" name="CodProducto" class="form-control" required />
                    </div>
                    <div class="col-12 mb-3">
                        <label for="NombreProducto" class="form-label">Nombre:</label>
                        <input type="text" id="NombreProducto" name="NombreProducto" class="form-control" required />
                    </div>
                    <div class="col-12 mb-3">
                        <label for="DescripcionProducto" class="form-label">Descripcion:</label>
                        <input type="text" id="DescripcionProducto" name="DescripcionProducto" class="form-control" required />
                    </div>
                    <div class="col-12 mb-3">
                        <label for="UnidadMedidaProducto" class="form-label">Unidad de Medida:</label>
                        <input type="text" id="UnidadMedidaProducto" name="UnidadMedidaProducto" class="form-control" required />
                    </div>
                    <div class="col-12 mb-3">
                        <label for="PrecioProducto" class="form-label">Precio:</label>
                        <input type="number" id="PrecioProducto" name="PrecioProducto" step="0.01" class="form-control" required />
                    </div>
                    <div class="col-12 mb-3">
                        <label for="StockProducto" class="form-label">Stock Inicial:</label>
                        <input type="number" id="StockProducto" name="StockProducto" class="form-control" required />
                    </div>
                </div>
                <button type="submit" class="btn btn-custom w-100">Dar de alta</button>
            </form>
        </div>
    </div>

    <div class="col-6">
        <div class="form-container p-4 shadow rounded">
            <h2 class="text-center mb-4">Modificar Producto</h2>
            <form action="ProductoControllers" method="POST">
                <input type="hidden" name="accion" value="Modificacion">
                <div class="mb-3">
                    <label for="CodProducto" class="form-label">Código:</label>
                    <input type="text" id="CodProducto" name="CodProducto" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="NombreProducto" class="form-label">Nombre:</label>
                    <input type="text" id="NombreProducto" name="NombreProducto" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="DescripcionProducto" class="form-label">Descripcion:</label>
                    <input type="text" id="DescripcionProducto" name="DescripcionProducto" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="UnidadMedidaProducto" class="form-label">Unidad de medida:</label>
                    <input type="text" id="UnidadMedidaProducto" name="UnidadMedidaProducto" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="PrecioProducto" class="form-label">Precio:</label>
                    <input type="number" id="PrecioProducto" name="PrecioProducto" step="0.01" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="StockProducto" class="form-label">Stock:</label>
                    <input type="number" id="StockProducto" name="StockProducto" class="form-control">
                </div>
                <button type="submit" class="btn btn-custom w-100">Modificar Producto</button>
            </form>
        </div>
    </div>
</div>

<div class="d-flex justify-content-center mt-4 gap-2">
    <a href="logout" class="btn logout-btn">Cerrar sesión</a>
</div>

</div>

</body>
</html>
