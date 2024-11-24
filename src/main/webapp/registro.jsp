<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .register-container {
            width: 100%;
            max-width: 500px;
            padding: 20px;
            background-color: white;
            border: 1px solid #dee2e6;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .form-control {
            margin-bottom: 15px;
        }
        .btn-primary {
            width: 100%;
        }
        .message {
            margin-top: 15px;
            text-align: center;
            color: red;
        }
    </style>
</head>
<body>
    <div class="register-container">
        <h2 class="text-center mb-4">Registrar Usuario</h2>
        <form action="registrarUsuario" method="post">
            <div class="mb-3">
                <label for="dniCliente" class="form-label">DNI Cliente</label>
                <input type="text" id="dniCliente" name="dniCliente" class="form-control" placeholder="Ingrese el DNI del cliente" required>
            </div>
            <div class="mb-3">
                <label for="claveUsuario" class="form-label">Clave</label>
                <input type="password" id="claveUsuario" name="claveUsuario" class="form-control" placeholder="Ingrese una clave segura" required>
            </div>
            <div class="mb-3">
                <label for="nombreUsuario" class="form-label">Nombre</label>
                <input type="text" id="nombreUsuario" name="nombreUsuario" class="form-control" placeholder="Ingrese el nombre" required>
            </div>
            <div class="mb-3">
                <label for="apellidoUsuario" class="form-label">Apellido</label>
                <input type="text" id="apellidoUsuario" name="apellidoUsuario" class="form-control" placeholder="Ingrese el apellido" required>
            </div>
            <!--  
            <div class="mb-3">
                <label for="saldoInicial" class="form-label">Saldo Inicial</label>
                <input type="number" id="saldoInicial" name="saldoInicial" class="form-control" value="0.0" readonly>
            </div>
            -->
            <button type="submit" class="btn btn-primary">Registrar</button>
        </form>
         <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null && !mensaje.isEmpty()) {
    %>
        <%= mensaje %>
    <%
        }
    %>
    </div>
</body>
</html>
