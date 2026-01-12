package web.java.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.BrandDAO;
import web.java.dao.CategoryDAO;
import web.java.dao.EventDAO;
import web.java.dao.ProductCollectionDAO;

@WebServlet("/event")
public class DealGroupServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        /* ===== 1. LẤY & VALIDATE EVENT ID ===== */
        String idRaw = request.getParameter("id");
        int eventId;

        try {
            eventId = Integer.parseInt(idRaw);
        } catch (Exception e) {
            response.sendRedirect("home");
            return;
        }

        EventDAO eventDAO = new EventDAO();

        /* ===== 2. LOAD DATA ===== */
        request.setAttribute("event", eventDAO.getEventById(eventId));
        request.setAttribute("products", eventDAO.getProductsByEventId(eventId));

        request.setAttribute("events", eventDAO.getAllEvents());
        request.setAttribute("brands", new BrandDAO().getAllBrands());
        request.setAttribute("categories", new CategoryDAO().getAllCategories());
        request.setAttribute("collections", new ProductCollectionDAO().getAllCollections());

        /* ===== 3. FORWARD VIEW ===== */
        request.getRequestDispatcher("/Page/DealProduct.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
