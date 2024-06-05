<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 4/6/2024
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Insumo</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            text-align: center;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        input[type="text"], input[type="number"] {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 300px;
            margin-bottom: 10px;
        }

        button {
            padding: 10px 20px;
            font-size: 16px;
            color: white;
            background-color: #007BFF;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        .message {
            margin-top: 20px;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Registrar Insumo</h1>
    <form id="insumoForm" action="SvRegistrarInsumo" method="POST">
        <input type="text" id="nombre" name="nombreInsumo" placeholder="Nombre del Insumo" required>
        <input type="number" id="cantidad" name="cantidadInsumo" placeholder="Cantidad" required>
        <input type="text" id="codigo" name="codigoInsumo" placeholder="Código del Insumo" required>
        <button type="submit">Registrar</button>
    </form>
    <div id="message" class="message"></div>
</div>

</body>
</html>

