<%@ page import="models.Producto" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4 text-primary">Lista de Productos</h1>

        <div class="row">
            <%
                List<Producto> productos = (List<Producto>) session.getAttribute("productos");

                if (productos != null && !productos.isEmpty()) {
                    for (Producto producto : productos) {
            %>
            <div class="col-md-4 mb-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"><%= producto.getNombre() %></h5>
                        <p class="card-text"><strong>Código:</strong> <%= producto.getCodProducto() %></p>
                        <p class="card-text"><strong>Descripción:</strong> <%= producto.getDescripcion() != null ? producto.getDescripcion() : "No disponible" %></p>
                        <p class="card-text"><strong>Stock:</strong> <%= producto.getStockProducto() %></p>
                        <p class="card-text"><strong>Unidad de Medida:</strong> <%= producto.getUnidadMedidaProducto() %></p>
                        <p class="card-text"><strong>Precio:</strong> $<%= producto.getPrecio() %></p>
                    </div>
                </div>
            </div>
            <%
                    }
                } else {
            %>
            <div class="col-12">
                <div class="alert alert-warning text-center" role="alert">
                    No hay productos disponibles.
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>
