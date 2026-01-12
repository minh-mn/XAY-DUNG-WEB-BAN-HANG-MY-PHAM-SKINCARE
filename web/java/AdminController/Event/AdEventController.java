package web.java.AdminController.Event;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.EventDAO;
import web.java.dao.ProductDAO;

@WebServlet("/admin/event")
public class AdEventController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AdEventController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        EventDAO eventDAO = new EventDAO();
        ProductDAO productDAO = new ProductDAO();

        request.setAttribute("events", eventDAO.getAllEvents());
        request.setAttribute("products", productDAO.getAllProducts());

        request.getRequestDispatcher(
                "/Admin/template/management/EventManagement.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
