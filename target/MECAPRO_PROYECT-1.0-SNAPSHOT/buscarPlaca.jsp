<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Búsqueda de Placa</title>
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

        h1 {
            color: #333;
            margin-bottom: 20px;
        }

        form {
            margin-bottom: 20px;
        }

        input[type="text"] {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 300px;
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
    <h1>Búsqueda de Placa</h1>
    <form action="SvEncontrarPlaca" method="POST">
        <input type="text" id="placa" name="placa" placeholder="Introduce la placa" required>
        <button type="submit">Buscar Placa</button>
    </form>
    <a href="index.jsp" class="cancelar">Cancelar</a>
</div>
</body>
</html>
