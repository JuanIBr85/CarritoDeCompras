<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login de Cliente</title>
</head>
<body>
    <h2>Login de Cliente</h2>
    <form action="LoginClienteController" method="POST">
        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dniCliente" required><br>
        <label for="clave">Clave:</label>
        <input type="password" id="clave" name="claveUsuario" required><br>
        <input type="submit" value="Ingresar">
    </form>
</body>
</html>
