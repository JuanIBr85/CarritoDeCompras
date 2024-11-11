<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestor de Productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
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
        .alert-warning {
            font-size: 1.2rem;
            padding: 1rem;
            background-color: #cce5ff;
            color: #004085;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="text-center text-primary mb-4">Gestor de Productos</h1>

        <div class="form-container">
            <h2 class="text-center mb-4">Alta Producto</h2>
            <form action="ProductoControllers" method="post">
                <div class="mb-3">
                    <label for="CodProducto" class="form-label">Código del Producto:</label>
                    <input type="text" id="CodProducto" name="CodProducto" class="form-control" required />
                </div>
                <div class="mb-3">
                    <label for="NombreProducto" class="form-label">Nombre del Producto:</label>
                    <input type="text" id="NombreProducto" name="NombreProducto" class="form-control" required />
                </div>
                <div class="mb-3">
                    <label for="UnidadMedidaProducto" class="form-label">Unidad de Medida:</label>
                    <input type="text" id="UnidadMedidaProducto" name="UnidadMedidaProducto" class="form-control" required />
                </div>
                <div class="mb-3">
                    <label for="PrecioProducto" class="form-label">Precio del Producto:</label>
                    <input type="number" id="PrecioProducto" name="PrecioProducto" step="0.01" class="form-control" required />
                </div>
                <div class="mb-3">
                    <label for="StockProducto" class="form-label">Stock Inicial:</label>
                    <input type="number" id="StockProducto" name="StockProducto" class="form-control" required />
                </div>
                <button type="submit" class="btn btn-custom w-100">Dar de alta</button>
            </form>
        </div>

        <div class="my-2 text-center">
            <a href="ProductoControllers?action=listar" class="btn btn-outline-primary">Listado de Productos</a>
        </div>
    </div>

</body>
</html>
