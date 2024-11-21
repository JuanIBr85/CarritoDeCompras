<!DOCTYPE html>
<html>
<head>
    <title>Registro de Usuario</title>
</head>
<body>
    <h2>Registrar Usuario</h2>
	<form action="registrarUsuario" method="post">
        <label for="idUsuario">ID Usuario:</label>
        <input type="number" id="idUsuario" name="idUsuario" required><br>

        <label for="claveUsuario">Clave:</label>
        <input type="password" id="claveUsuario" name="claveUsuario" required><br>

        <label for="nombreUsuario">Nombre:</label>
        <input type="text" id="nombreUsuario" name="nombreUsuario" required><br>

        <label for="apellidoUsuario">Apellido:</label>
        <input type="text" id="apellidoUsuario" name="apellidoUsuario" required><br>

        <label for="dniCliente">DNI Cliente:</label>
        <input type="text" id="dniCliente" name="dniCliente" required><br>

        <label for="saldoInicial">Saldo Inicial:</label>
        <input type="number" id="saldoInicial" name="saldoInicial" required><br>

        <button type="submit">Registrar</button>
    </form>
</body>
</html>
