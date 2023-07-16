package ar.com.codoacodo;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ar.com.codoacodo.dao.impl.DAO;
import ar.com.codoacodo.dao.impl.MySQLDAOImpl;
import ar.com.codoacodo.oop.Articulo;
import ar.com.codoacodo.oop.Libro;

@WebServlet("/AltaArticuloController")
public class AltaArticuloController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AltaArticuloController");
        String titulo = req.getParameter("nombre");
        double precio = Double.parseDouble(req.getParameter("precio"));
        String autor  = req.getParameter("autor");
        String codigo  = req.getParameter("codigo");
        String isbn  = "123465465456";
        LocalDateTime fechaCreacion = LocalDateTime.now();
        
        Articulo articulo = new Libro(titulo, codigo, autor, precio, false, isbn,codigo,fechaCreacion);
        
        DAO dao = new MySQLDAOImpl();
        
        try {
            dao.create(articulo);
            resp.sendRedirect(req.getContextPath() + "/ListadoArticuloController");
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/nuevo.jsp").forward(req, resp);
            e.printStackTrace();
        } 

    }
}
