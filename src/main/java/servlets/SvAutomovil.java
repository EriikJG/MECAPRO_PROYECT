package servlets;

import logica.Controladora;
import logica.Reparacion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SvAutomovil", urlPatterns = {"/SvAutomovil"})
public class SvAutomovil extends HttpServlet {
    Controladora control = new Controladora();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        {
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        crearAutomovil(request);

        response.sendRedirect("servicio.jsp");
    }

    private void crearAutomovil(HttpServletRequest request) {
        List<Reparacion> reparaciones = new ArrayList<>();
        String placa = request.getParameter("placa");
        String marca = request.getParameter("marca");
        String anioFab = request.getParameter("anioFab");


        HttpSession misession = request.getSession(true);
        misession.setAttribute("placa", placa);
        control.crearAutomovil(placa, marca, anioFab, reparaciones);
    }
}