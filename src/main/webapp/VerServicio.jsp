<%@page import="java.util.ArrayList"%>
<%@page import="logica.Reparacion"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.stream.Collectors" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ver Reparaciones</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            height: 100vh;
            padding-top: 20px;
        }

        .container-fluid {
            width: 80%;
            margin: 20px auto;
        }

        h1 {
            color: #333;
            text-align: center;
        }

        p {
            text-align: center;
            color: #666;
        }

        .card {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }

        .card-header {
            padding-bottom: 10px;
            border-bottom: 1px solid #ddd;
        }

        .card-header h6 {
            color: #007BFF;
            font-weight: bold;
        }

        .table-responsive {
            margin-top: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #f8f8f8;
        }

        .btn {
            padding: 10px;
            font-size: 14px;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .btn-primary {
            background-color: #007BFF;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .btn-danger {
            background-color: red;
        }

        .btn-danger:hover {
            background-color: darkred;
        }

        .btn-group {
            display: flex;
            justify-content: center;
        }

        .btn-group form {
            margin: 0 5px;
        }

        .search-container {
            text-align: center;
            margin-bottom: 20px;
        }

        input[type="text"] {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 300px;
            margin-right: 10px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <h1>Ver Reparaciones</h1>
    <p>A continuación podrá visualizar la lista completa de Reparaciones</p>

    <div class="search-container">
        <form action="VerServicio.jsp" method="GET">
            <input type="text" id="placa" name="placa" placeholder="Ingrese la placa del vehículo">
            <button type="submit" class="btn btn-primary">Buscar</button>
        </form>
    </div>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Reparaciones</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Costo</th>
                        <th>Descripción</th>
                        <th>Tipo</th>
                        <th>Placa</th>
                        <th style="width: 210px">Acción</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>Id</th>
                        <th>Costo</th>
                        <th>Descripción</th>
                        <th>Tipo</th>
                        <th>Placa</th>
                        <th style="width: 210px">Acción</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <%
                        String placa = request.getParameter("placa");
                        List<Reparacion> listaReparaciones = new ArrayList<>();

                        if (placa != null && !placa.isEmpty()) {
                            // Filtrar por placa
                            listaReparaciones = (List<Reparacion>) request.getSession().getAttribute("listaReparaciones");
                            listaReparaciones = listaReparaciones.stream()
                                    .filter(repa -> repa.getAutomovil().getPlaca().equalsIgnoreCase(placa))
                                    .collect(Collectors.toList());
                        } else {
                            // Mostrar todas las reparaciones
                            listaReparaciones = (List<Reparacion>) request.getSession().getAttribute("listaReparaciones");
                        }

                        for (Reparacion repa : listaReparaciones) {
                    %>
                    <tr>
                        <td><%= repa.getId() %></td>
                        <td><%= repa.getCosto() %></td>
                        <td><%= repa.getDescripcion() %></td>
                        <td><%= repa.getTipo() %></td>
                        <td><%= repa.getAutomovil().getPlaca() %></td>
                        <td class="btn-group">
                            <form name="eliminar" action="SvEliminarReparacion" method="POST">
                                <input type="hidden" name="id" value="<%= repa.getId() %>">
                                <button type="submit" class="btn btn-danger">Eliminar</button>
                            </form>
                            <form name="editar" action="SvEditarReparacion" method="GET">
                                <input type="hidden" name="id" value="<%= repa.getId() %>">
                                <button type="submit" class="btn btn-primary">Editar</button>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
