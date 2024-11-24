<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
    crossorigin="anonymous">
<style>
    body {
        background-color: #f8f9fa;
        font-family: Arial, sans-serif;
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
</style>
</head>
<body>
    <div class="container">
        <h1 class="text-center text-primary mb-4">Gestor de Productos</h1>
        <%
        if (request.getParameter("mensaje") != null) {
        %>
        <div class="alert alert-success" role="alert">
            <%=request.getParameter("mensaje")%>
        </div>
        <%
        } else if (request.getParameter("error") != null) {
        %>
        <div class="alert alert-danger" role="alert">
            <%=request.getParameter("error")%>
        </div>
        <%
        }
        %>

        <div class="form-container">
            <h2 class="text-center mb-4">Alta Producto</h2>
            <form action="ProductoControllers" method="post">
                <input type="hidden" name="accion" value="Alta">
                <div class="row">
                    <div class="col-6 mb-3">
                        <label for="CodProducto" class="form-label">Código del Producto:</label>
                        <input type="text" id="CodProducto" name="CodProducto" class="form-control" required />
                    </div>
                    <div class="col-6 mb-3">
                        <label for="NombreProducto" class="form-label">Nombre del Producto:</label>
                        <input type="text" id="NombreProducto" name="NombreProducto" class="form-control" required />
                    </div>
                    <div class="col-6 mb-3">
                        <label for="UnidadMedidaProducto" class="form-label">Unidad de Medida:</label>
                        <input type="text" id="UnidadMedidaProducto" name="UnidadMedidaProducto" class="form-control" required />
                    </div>
                    <div class="col-6 mb-3">
                        <label for="PrecioProducto" class="form-label">Precio del Producto:</label>
                        <input type="number" id="PrecioProducto" name="PrecioProducto" step="0.01" class="form-control" required />
                    </div>
                    <div class="col-6 mb-3">
                        <label for="StockProducto" class="form-label">Stock Inicial:</label>
                        <input type="number" id="StockProducto" name="StockProducto" class="form-control" required />
                    </div>
                </div>
                <button type="submit" class="btn btn-custom w-100">Dar de alta</button>
            </form>
        </div>

        <div class="form-container mt-5">
            <h2 class="text-center mb-4">Modificar Producto</h2>
            <form action="ProductoControllers" method="post">
                <input type="hidden" name="accion" value="Modificacion">
                <div class="row">
                    <div class="col-6 mb-3">
                        <label for="CodProducto" class="form-label">Código del Producto:</label>
                        <input type="text" id="CodProducto" name="CodProducto" class="form-control" required />
                    </div>
                    <div class="col-6 mb-3">
                        <label for="NombreProducto" class="form-label">Nombre del Producto:</label>
                        <input type="text" id="NombreProducto" name="NombreProducto" class="form-control" />
                    </div>
                    <div class="col-6 mb-3">
                        <label for="UnidadMedidaProducto" class="form-label">Unidad de Medida:</label>
                        <input type="text" id="UnidadMedidaProducto" name="UnidadMedidaProducto" class="form-control" />
                    </div>
                    <div class="col-6 mb-3">
                        <label for="PrecioProducto" class="form-label">Precio del Producto:</label>
                        <input type="number" id="PrecioProducto" name="PrecioProducto" step="0.01" class="form-control" />
                    </div>
                </div>
                <button type="submit" class="btn btn-custom w-100">Actualizar Producto</button>
            </form>
        </div>

        <% 
            List<Producto> productos = (List<Producto>) session.getAttribute("productos");
        %>

        <% if (productos != null && !productos.isEmpty()) { %>
        <div class="table-responsive mt-5">
            <table class="table table-bordered">
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
                    <tr>
                        <td><%= producto.getIdProducto() %></td>
                        <td><%= producto.getCodProducto() %></td>
                        <td><%= producto.getNombre() %></td>
                        <td><%= producto.getStockProducto() %></td>
                        <td>$<%= producto.getPrecio() %></td>
                        <td>
                            <a href="ProductoControllers?accion=Modificar&idProducto=<%= producto.getIdProducto() %>" class="btn btn-warning btn-sm">Editar</a>
                            <% if (producto.getStockProducto() > 0) { %>
                               
                            <% } else { %>
                                <span class="badge bg-danger">Sin Stock</span>
                            <% } %>
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

        <div class="my-2 text-center">
            <a href="ProductoControllers?accion=ListaProductos" class="btn btn-outline-primary">Listado de Productos</a>
        </div>
    </div>
</body>
</html>
