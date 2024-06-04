package servlets;

import logica.Automovil;
import logica.Controladora;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SvEncontrarPlaca", urlPatterns = {"/SvEncontrarPlaca"})
public class SvEncontrarPlaca extends HttpServlet {
    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Automovil autoEncontrado = control.encontrarAuto(request.getParameter("placa"));

        buscarAutomovil(request, response, autoEncontrado);

    }

    private static void buscarAutomovil(HttpServletRequest request, HttpServletResponse response, Automovil autoEncontrado) throws IOException {
        if (null != autoEncontrado) {
            HttpSession misession = request.getSession(true);
            misession.setAttribute("placa", request.getParameter("placa"));
            response.sendRedirect("servicio.jsp");
        } else {
            response.sendRedirect("automovil.jsp");
        }
    }
}
