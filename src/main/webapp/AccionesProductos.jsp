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
            color: white;
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

	//intento de proteger Acciones Productos.jsp de quien no sea empleado
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
            Datos del Empleado
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
        <div class="card ">
           <div class="card-header">
            Alta de Producto
           </div>
         <div class="card-body">
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
    </div>

    <div class="col-6">
        <div class="card ">
             <div class="card-header">
           Modificar Producto
           </div>
             <div class="card-body">
           
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
                    <label for="AjusteStockProducto" class="form-label">Stock:</label>
                    <input type="number" id="AjusteStockProducto" name="AjusteStockProducto" class="form-control">
                </div>
                <button type="submit" class="btn btn-custom w-100">Modificar Producto</button>
            </form>
               </div>
        </div>
    </div>
    
        <h2 class="text-center text-secondary mb-4">Lista de Productos</h2>
    
<%@ page import="models.Producto" %>
<%@ page import="java.util.List" %>

<%
    List<Producto> productos = (List<Producto>) session.getAttribute("productos");
%>

<% if (productos != null && !productos.isEmpty()) { %>
    <div>
        <table class="table table-bordered table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Código</th>
                    <th>Nombre</th>
                    <th>Stock</th>
                    <th>Precio</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% for (Producto producto : productos) { %>
                <tr class="<%= producto.getStockProducto() <= 0 ? "table-danger" : "" %>">
                    <td><%= producto.getIdProducto() %></td>
                    <td><%= producto.getCodProducto() %></td>
                    <td><%= producto.getNombre() %></td>
                    <td>
                        <%= producto.getStockProducto() %>
                        <% if (producto.getStockProducto() <= 0) { %>
                        <span class="badge bg-danger">Sin Stock</span>
                        <% } %>
                    </td>
                    <td>$<%= producto.getPrecio() %></td>
                    <td>
                        <form action="ProductoControllers" method="post" class="d-inline">
                            <input type="hidden" name="accion" value="Baja">
                            <input type="hidden" name="CodProducto" value="<%= producto.getCodProducto() %>">
                            <button type="submit" class="btn btn-danger btn-sm">
                                <i class="bi bi-trash"></i> Eliminar
                            </button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
<% } else { %>
    <div class="alert alert-warning text-center" role="alert">
        No hay productos en stock.
    </div>
<% } %>

</div>

<div class="d-flex justify-content-center m-4 gap-2">
    <a href="logout" class="btn logout-btn">Cerrar sesión</a>
</div>

</div>

</body>
</html>
