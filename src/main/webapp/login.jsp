<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
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
        .login-container {
            width: 100%;
            max-width: 400px;
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
    </style>
</head>
<body>
    <div class="login-container">
        <h2 class="text-center mb-4">Inicio de Sesión</h2>
        <form action="loginUsuario" method="post">
            <div class="mb-3">
                <label for="dniCliente" class="form-label">DNI Usuario</label>
                <input type="number" id="dniCliente" name="dniCliente" class="form-control" placeholder="Ingrese su DNI" required>
            </div>
            <div class="mb-3">
                <label for="claveUsuario" class="form-label">Clave</label>
                <input type="password" id="claveUsuario" name="claveUsuario" class="form-control" placeholder="Ingrese su clave" required>
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
    </div>
 
</body>
</html>
