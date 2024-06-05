<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>MECAPRO - Página Principal</title>
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
      margin-bottom: 30px;
      font-size: 2.5em;
    }
    .container {
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: 20px;
    }
    a {
      display: inline-block;
      text-decoration: none;
      padding: 15px 25px;
      font-size: 18px;
      color: white;
      background-color: #007BFF;
      border: none;
      border-radius: 8px;
      transition: background-color 0.3s ease, transform 0.3s ease;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }
    a:hover {
      background-color: #0056b3;
      transform: translateY(-3px);
    }
  </style>
</head>
<body>
<h1>MECAPRO</h1>
<div class="container">
  <a href="buscarPlaca.jsp">Agregar Servicio</a>
  <a href="SvServicio">Ver Servicios</a>
  <a href="AgregarInsumos.jsp">Agregar Insumo al inventario</a>
  <a href="VerInsumos.jsp">Ver Insumos</a>
</div>
</body>
</html>


