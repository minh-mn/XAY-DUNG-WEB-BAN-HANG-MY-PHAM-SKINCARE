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
import web.java.model.Product;
import web.java.model.Event;

@WebServlet("/admin/eventDelete")
public class DeleteEventAdmin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public DeleteEventAdmin() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String eventIdStr   = request.getParameter("event");
        String productIdStr = request.getParameter("product");

        if (eventIdStr != null && productIdStr != null) {
            int eventId   = Integer.parseInt(eventIdStr);
            int productId = Integer.parseInt(productIdStr);

            EventDAO eventDAO = new EventDAO();
            eventDAO.removeProductFromEvent(eventId, productId);

            Event event = eventDAO.getEventById(eventId);
            List<Product> productsInEvent = eventDAO.getProductsByEventId(eventId);
            List<Product> allProducts     = new ProductDAO().getAllProducts();

            request.setAttribute("event", event);
            request.setAttribute("productsInEvent", productsInEvent);
            request.setAttribute("products", allProducts);
        }

        request.getRequestDispatcher(
                "/Admin/template/management/EditEventAdmin.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
