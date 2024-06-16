package servlets;

import logica.Controladora;
import logica.Insumo;
import logica.Inventario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "SvInsumo", urlPatterns = {"/SvInsumo"})
public class SvInsumo extends HttpServlet {


    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Insumo> listaInsumos = new ArrayList<>();

        listaInsumos = control.getInsumos();

        HttpSession misession = request.getSession();
        misession.setAttribute("listaInsumos", listaInsumos);


        response.sendRedirect("VerInsumos.jsp");

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        crearInventario();
        String nombreInsumo = request.getParameter("nombreInsumo");
        int cantidadInsumo =  Integer.parseInt(request.getParameter("cantidadInsumo"));
        String codigoInsumo = request.getParameter("codigoInsumo");
       Inventario inventario = control.encontrarInventario(1);
        control.crearInsumo(nombreInsumo, cantidadInsumo, codigoInsumo,inventario);

        response.sendRedirect("index.jsp");
    }

    private void crearInventario() {
        String nombre = "Inventario";
        Date fechaCreacion = new Date();
        Date fechaActualizacion = new Date();
        List<Insumo> insumos = new ArrayList<>();
        control.crearInventario(nombre, fechaCreacion, fechaActualizacion, insumos);
    }
}
