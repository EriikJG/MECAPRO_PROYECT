<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar Automóvil</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            text-align: center;
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
            font-size: 2.5em;
        }
        p {
            color: #666;
            margin-bottom: 30px;
            font-size: 1.2em;
        }
        form {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 300px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .form-control {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .btn {
            width: 100%;
            padding: 10px;
            font-size: 1em;
            color: white;
            background-color: #007BFF;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .agregarUsuario {
            display: inline-block;
            margin-top: 20px;
            font-size: 1em;
            color: #007BFF;
            text-decoration: none;
            transition: color 0.3s ease;
        }
        .agregarUsuario:hover {
            color: #0056b3;
        }
    </style>
</head>
<body>
<h1>Agregar Automóvil</h1>
<p>Este es el apartado para añadir el automóvil al sistema</p>
<form class="user" action="SvAutomovil" method="POST">
    <input type="text" class="form-control form-control-user" id="placa" name="placa" placeholder="Placa">
    <input type="text" class="form-control form-control-user" id="marca" name="marca" placeholder="Marca">
    <input type="text" class="form-control form-control-user" id="anioFab" name="anioFab" placeholder="Año de Fabricación">
    <button class="btn btn-primary btn-user btn-block" type="submit">Agregar Automóvil</button>
</form>
<a href="index.jsp" class="agregarUsuario">Cancelar</a>
</body>
</html>
