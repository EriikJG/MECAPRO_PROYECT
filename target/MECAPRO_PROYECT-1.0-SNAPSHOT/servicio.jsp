<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 19/5/2024
  Time: 1:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Reparación</title>
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
        }

        form {
            margin-bottom: 20px;
        }

        input[type="text"],
        select {
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

        .cancelar {
            display: block;
            color: #007BFF;
            text-decoration: none;
            font-size: 16px;
        }

        .cancelar:hover {
            color: #0056b3;
        }

    </style>
</head>
<body>
<div class="container">
    <h1>Agrega un Servicio</h1>
    <form class="user" action="SvServicio" method="POST">
        <input type="text" class="form-control form-control-user" id="descripcion" name="descripcion" placeholder="Descripción" required>
        <input type="text" class="form-control form-control-user" id="costo" name="costo" placeholder="Costo" required>
        <select name="tipo" class="form-control form-control-user">
            <option value="mantenimiento">Mantenimiento</option>
            <option value="reparacion">Reparación</option>
        </select>
        <button class="btn btn-primary btn-user btn-block" type="submit">Crear Registro</button>
    </form>
    <a href="index.jsp" class="cancelar">Cancelar</a>
</div>
</body>
</html>
