package web.java.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.BrandDAO;
import web.java.dao.CategoryDAO;
import web.java.dao.EventDAO;
import web.java.dao.OrderDAO;
import web.java.dao.ProductCollectionDAO;

@WebServlet("/history")
public class HistoryOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        /* ===== 1. LẤY LOGIN ID TỪ COOKIE ===== */
        int loginId = -1;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("loginId".equals(c.getName())) {
                    try {
                        loginId = Integer.parseInt(c.getValue());
                    } catch (NumberFormatException e) {
                        loginId = -1;
                    }
                    break;
                }
            }
        }

        /* ===== 2. CHƯA LOGIN → REDIRECT ===== */
        if (loginId <= 0) {
            response.sendRedirect("login");
            return;
        }

        OrderDAO orderDAO = new OrderDAO();

        /* ===== 3. LOAD DATA ===== */
        request.setAttribute("orders", orderDAO.getOrdersByUser(loginId));

        request.setAttribute("events", new EventDAO().getAllEvents());
        request.setAttribute("brands", new BrandDAO().getAllBrands());
        request.setAttribute("categories", new CategoryDAO().getAllCategories());
        request.setAttribute("collections", new ProductCollectionDAO().getAllCollections());

        /* ===== 4. FORWARD ===== */
        request.getRequestDispatcher("/Page/historyOrder.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
