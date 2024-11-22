<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="loginUsuario" method="post">
        <label for="dniCliente">DNI Usuario:</label>
        <input type="number" id="dniCliente" name="dniCliente" required><br>

        <label for="claveUsuario">Clave:</label>
        <input type="password" id="claveUsuario" name="claveUsuario" required><br>

        <button type="submit">Login</button>
    </form>
</body>
</html>
