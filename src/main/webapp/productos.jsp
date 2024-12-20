<%@ page import="models.Producto" %>
<%@ page import="models.Cliente" %>
<%@ page import="models.BilleteraCliente" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .card {
            border: 1px solid #007bff;
            border-radius: 0.5rem;
            transition: transform 0.2s ease-in-out;
        }
        .card:hover {
            transform: scale(1.01);
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }
        .btn-custom {
            background-color: #0069d9;
            color: white;
            font-size: 1.1rem;
            border-radius: 0.3rem;
        }
        .btn-custom:hover {
            background-color: #0056b3;
            color: white;
            transition: background-color 0.3s ease;
        }
        .alert-warning {
            font-size: 1.2rem;
            padding: 1rem;
            background-color: #cce5ff;
            color: #004085;
        }
        .btn-carrito {
            position: fixed;
            bottom: 20px;
            right: 20px;
            z-index: 1000;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 10px 20px;
            font-size: 1.2rem;
            border-radius: 0.5rem;
        }

        .btn-carrito svg {
            margin-right: 10px; 
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <%
        Cliente clienteLogueado = (Cliente) session.getAttribute("usuarioLogueado");
        BilleteraCliente billeteraCliente = (BilleteraCliente) session.getAttribute("cuentaCliente");
        List<Producto> productos = (List<Producto>) session.getAttribute("productos");
        %>

        <div class="text-end mb-4">
            <% if (clienteLogueado != null) { %>
<div class="d-flex justify-content-end align-items-center">
    <p class="mb-0">Bienvenido, <strong><%= clienteLogueado.getNombreUsuario() %></strong></p>
    <a href="dashboard.jsp" class="btn btn-primary ms-2">Mi Perfil</a>
</div>

               
                
            <% } else { %>
                <a href="login.jsp" class="btn btn-custom">Iniciar Sesi�n</a>
                
            <% } %>
        </div>
        
        <h1 class="text-center mb-4 text-primary">Lista de Productos</h1>

       <div class="d-flex flex-wrap justify-content-start gap-4">
    <% if (productos != null && !productos.isEmpty()) { 
        for (Producto producto : productos) { %>
        <div class="card" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title"><%= producto.getNombre() %></h5>
                                <p class="card-text"><strong>ID:</strong> <%= producto.getIdProducto() %></p>
               
                <p class="card-text"><strong>C�digo:</strong> <%= producto.getCodProducto() %></p>
                <p class="card-text"><strong>Descripci�n:</strong> <%= producto.getDescripcion() != null ? producto.getDescripcion() : "No disponible" %></p>
                <p class="card-text"><strong>Stock:</strong> <%= producto.getStockProducto() %></p>
                <p class="card-text"><strong>Unidad de Medida:</strong> <%= producto.getUnidadMedidaProducto() %></p>
                <p class="card-text"><strong>Precio:</strong> $<%= producto.getPrecio() %></p>
                
                <% if (producto.getStockProducto() > 0) { %>
                    <form action="AgregarCarritoController" method="post">
                                            <input type="hidden" name="IdProducto" value="<%= producto.getIdProducto() %>">
             
                        <input type="hidden" name="CodProducto" value="<%= producto.getCodProducto() %>">
                        <input type="hidden" name="NombreProducto" value="<%= producto.getNombre() %>">
                        <input type="hidden" name="PrecioProducto" value="<%= producto.getPrecio() %>">
                        <input type="hidden" name="StockProducto" value="<%= producto.getStockProducto() %>">
                        
                        <div class="mb-3">
                            <label for="cantidad" class="form-label">Cantidad</label>
                            <input type="number" class="form-control" id="cantidad" name="Cantidad" min="1" max="<%= producto.getStockProducto() %>" value="1" required>
                        </div>

                        <button type="submit" class="btn btn-custom w-100">Agregar al carrito</button>
                    </form>
                <% } else { %>
                    <div class="alert alert-danger text-center mt-3">
                        Producto fuera de stock
                    </div>
                <% } %>
            </div>
        </div>
    <% } } else { %>
        <div class="col-12 m-1">
            <div class="alert alert-warning text-center" role="alert">
                No hay productos en stock.
            </div>
        </div>
    <% } %>
</div>

        	
        <% 
    String mensaje = (String) request.getAttribute("mensaje");
    if (mensaje != null) { 
%>
    <div class="alert alert-warning text-center m-3" role="alert">
        <%= mensaje %>
    </div>
<% 
    } 
%>
		
        <a href="Carrito.jsp" class="btn btn-success btn-carrito">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
                <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l1.313 7h8.17l1.313-7zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/>
            </svg>
            Ver Carrito
        </a>    
    </div>
</body>
</html>
