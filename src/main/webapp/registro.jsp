<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro de Cliente</title>
</head>
<body>
    <h2>Registro de Cliente</h2>
    <form action="RegistroClienteController" method="POST">
        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dniCliente" required><br>
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombreUsuario" required><br>
        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellidoUsuario" required><br>
        <label for="clave">Clave:</label>
        <input type="password" id="clave" name="claveUsuario" required><br>
        <input type="submit" value="Registrar">
    </form>
</body>
</html>
