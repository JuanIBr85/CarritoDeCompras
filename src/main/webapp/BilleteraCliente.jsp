<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión de Dinero del Cliente</title>
</head>
<body>

    <h2>Gestión de Dinero del Cliente</h2>
    
    <div>
        <h3>Saldo Actual:</h3>
        <p>${saldo}
    </div>

    <form action="BilleteraController" method="post">
        <h3>Ingresar Dinero</h3>
        <input type="hidden" name="accion" value="deposito">
        <input type="number" name="monto" placeholder="Monto a ingresar" required>
        <button type="submit">Confirmar Deposito</button>
    </form>

    <form action="BilleteraController" method="post">
        <h3>Transferencia a Otro Cliente</h3>
        <input type="hidden" name="accion" value="transferencia">
        <input type="text" name="clienteDestino" placeholder="DNI destinatario" required>
        <input type="number" name="monto" placeholder="Monto a transferir" required>
        <button type="submit">Transferir monto</button>
    </form>

    <form action="BilleteraController" method="post">
        <h3>Realizar pago</h3>
        <input type="hidden" name="accion" value="pago">
        <input type="number" name="monto" placeholder="Monto a pagar" required>
        <button type="submit">Realizar Pago</button>
    </form>
</body>
</html>