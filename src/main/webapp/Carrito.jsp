<%@ page import="models.Producto" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrito de Compras</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            background-color: #f0f4f8;
        }
        .card-header {
            background-color: #007bff;
            color: white;
            font-size: 1.5rem;
        }
        .table th, .table td {
            vertical-align: middle;
        }
        .table thead {
            background-color: #0069d9;
            color: white;
        }
        .btn-custom {
            background-color: #0069d9;
            color: white;
            font-size: 1.1rem;
            border: none;
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
        .card {
            border: 1px solid #007bff;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-header text-center">
                <h1 class="mb-0">Carrito de Compras</h1>
            </div>
            <div class="card-body">
                <%
                models.Cliente clienteLogueado = (models.Cliente) session.getAttribute("usuarioLogueado");
                List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");

                if (carrito != null && !carrito.isEmpty()) {
                %>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Codigo</th>
                            <th scope="col">Producto</th>
                            <th scope="col">Precio</th>
                            <th scope="col">Cantidad</th>
                            <th scope="col">Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            double total = 0;
                            for (Producto producto : carrito) {
                                double precio = producto.getPrecio();
                                int cantidad = producto.getCantidad(); 
                                double subtotal = precio * cantidad;
                                total += subtotal;
                        %>
                        <tr>
                         <td><%= producto.getIdProducto() %></td>
                            <td><%= producto.getCodProducto() %></td>
                        
                            <td><%= producto.getNombre() %></td>
                            <td>$<%= producto.getPrecio() %></td>
                            <td><%= cantidad %></td>
                            <td>$<%= subtotal %></td>
                        </tr>
                        <%
                            }
                        %>
                        <tr>
                            <td colspan="3" class="text-end"><strong>Total</strong></td>
                            <td><strong>$<%= total %></strong></td>
                        </tr>
                    </tbody>
                </table>

                <div class="text-center">
                    <%
                    if (clienteLogueado == null) {
                    %>
                    <div class="alert alert-warning text-center" role="alert">
                        Debes iniciar sesión para proceder al pago.
                    </div>
                    <a href="login.jsp" class="btn btn-custom">Iniciar Sesión</a>
                    <%
                    } else {
                    %>
                    <form action="BilleteraController" method="post">
                        <h3>Realizar Pago</h3>
                        <input type="hidden" name="accion" value="pago">
                        <input type="text" name="clienteDni" value="<%= clienteLogueado.getDniCliente() %>" readonly>
                        <input type="text" name="id" value="<%= clienteLogueado.getIdUsuario() %>" readonly>
                       
                        <input type="number" name="monto" value="<%= total %>" readonly>
                        <button type="submit" class="btn btn-custom">Realizar Pago</button>
                    </form>
                    <%
                    }
                    %>
                </div>
                <%
                } else {
                %>
                <div class="alert alert-warning text-center" role="alert">
                    No hay productos en tu carrito.
                </div>
                <%
                }
                %>
            </div>         	
        </div>
        <div class="d-flex justify-content-center mt-4 gap-2">
   					 <a href="BilleteraCliente.jsp" class="btn btn-custom">Ir a Billetera</a>
   					 <a href="productos.jsp" class="btn btn-custom">Ver Productos</a>
				</div>
    </div>

</body>
</html>
