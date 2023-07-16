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

@WebServlet("/EditarController")
public class EditarController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        DAO dao = new MySQLDAOImpl();

        try {
            Articulo articulo = dao.getById(Long.parseLong(id));

            req.setAttribute("producto", articulo);
            req.setAttribute("success", "Se edito correctamente el articulo" );
        } catch (Exception e) {
            req.setAttribute("error", "No se pudo encontrar el articulo");
            getServletContext().getRequestDispatcher("/ListadoArticuloController").forward(req, resp);
        }

        getServletContext().getRequestDispatcher("/editar.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        DAO dao = new MySQLDAOImpl();
        try {
            Articulo articuloDB = dao.getById(Long.parseLong(id));

            String titulo = req.getParameter("nombre");
            double precio = Double.parseDouble(req.getParameter("precio"));
            String autor = req.getParameter("autor");
            String codigo = req.getParameter("codigo");
            String isbn = "123465465456";
            LocalDateTime fechaCreacion = LocalDateTime.now();

            Articulo articuloNuevo = new Libro(titulo, codigo, autor, precio, false, isbn, codigo, fechaCreacion);
            articuloNuevo.setId(articuloDB.getId());

            dao.update(articuloNuevo);

            resp.sendRedirect(req.getContextPath() + "/ListadoArticuloController");
            req.setAttribute("success", "Se edito correctamente el articulo" );
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "No se pudo editar el articulo" );
            getServletContext().getRequestDispatcher("/editar.jsp").forward(req, resp);
        }
    }
}
