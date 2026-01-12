package web.java.AdminController.Event;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.EventDAO;
import web.java.dao.ProductDAO;
import web.java.model.Event;
import web.java.model.Product;

@WebServlet("/admin/eventEdit")
public class EditEventAdmin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public EditEventAdmin() {
        super();
    }

    /* ===================== GET ===================== */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String eventIdStr = request.getParameter("id");

        if (eventIdStr != null) {
            int eventId = Integer.parseInt(eventIdStr);

            EventDAO eventDAO = new EventDAO();
            ProductDAO productDAO = new ProductDAO();

            Event event = eventDAO.getEventById(eventId);
            List<Product> productsInEvent = eventDAO.getProductsByEventId(eventId);
            List<Product> productsNotInEvent = eventDAO.getProductsNotInEvent(eventId);

            request.setAttribute("event", event);
            request.setAttribute("productsInEvent", productsInEvent);
            request.setAttribute("productNotInEvents", productsNotInEvent);
            request.setAttribute("products", productDAO.getAllProducts());
        }

        request.getRequestDispatcher(
                "/Admin/template/management/EditEventAdmin.jsp"
        ).forward(request, response);
    }

    /* ===================== POST ===================== */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String eventIdStr   = request.getParameter("eventId");
        String productIdStr = request.getParameter("productId");

        if (eventIdStr != null && productIdStr != null) {
            int eventId   = Integer.parseInt(eventIdStr);
            int productId = Integer.parseInt(productIdStr);

            EventDAO eventDAO = new EventDAO();
            eventDAO.addProductToEvent(eventId, productId);

            Event event = eventDAO.getEventById(eventId);
            List<Product> productsInEvent = eventDAO.getProductsByEventId(eventId);
            List<Product> productsNotInEvent = eventDAO.getProductsNotInEvent(eventId);

            request.setAttribute("event", event);
            request.setAttribute("productsInEvent", productsInEvent);
            request.setAttribute("productNotInEvents", productsNotInEvent);
            request.setAttribute("products", new ProductDAO().getAllProducts());
        }

        request.getRequestDispatcher(
                "/Admin/template/management/EditEventAdmin.jsp"
        ).forward(request, response);
    }
}
