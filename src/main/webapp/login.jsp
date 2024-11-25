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
        .btn-secondary {
            width: 100%;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <% if (request.getParameter("mensaje") != null) { %>
    <div class="alert alert-success" role="alert">
        <%=request.getParameter("mensaje")%>
    </div>
    <% } %>
    	
    <div class="login-container">
    <h2 class="text-center mb-4">Inicio de Sesión</h2>
    <form action="loginUsuario" method="post">
        <div class="mb-3">
            <label for="tipoUsuario" class="form-label">Tipo de Usuario</label>
            <select id="tipoUsuario" name="tipoUsuario" class="form-control" required>
                <option value="cliente">Cliente</option>
                <option value="empleado">Empleado</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="nroUsuario" class="form-label">DNI/Legajo Usuario</label>
            <input type="number" id="nroUsuario" name="nroUsuario" class="form-control" placeholder="Ingrese su DNI" required>
        </div>
        <div class="mb-3">
            <label for="claveUsuario" class="form-label">Clave</label>
            <input type="password" id="claveUsuario" name="claveUsuario" class="form-control" placeholder="Ingrese su clave" required>
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
    <div class="d-flex ">
    <a href="registro.jsp" class="btn btn-secondary mt-2 mx-1">Quiero Registrarme (Cliente)</a>
    <a href="registro_empleado.jsp" class="btn btn-secondary mt-2 mx-1">Quiero Registrarme (Empleado)</a>
    
    </div>
   
    
</div>

</body>
</html>