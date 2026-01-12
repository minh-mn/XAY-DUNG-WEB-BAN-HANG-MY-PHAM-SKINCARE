package web.java.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.OrderDAO;

@WebServlet("/recieveOrder")
public class RecieveOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        /* ===== 1. CHECK LOGIN ===== */
        int loginId = -1;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("loginId".equals(c.getName())) {
                    loginId = Integer.parseInt(c.getValue());
                    break;
                }
            }
        }

        if (loginId <= 0) {
            response.sendRedirect("login");
            return;
        }

        /* ===== 2. GET ORDER ID ===== */
        String orderIdRaw = request.getParameter("recieve");
        if (orderIdRaw == null) {
            response.sendRedirect("history");
            return;
        }

        int orderId;
        try {
            orderId = Integer.parseInt(orderIdRaw);
        } catch (NumberFormatException e) {
            response.sendRedirect("history");
            return;
        }

        /* ===== 3. UPDATE ORDER STATUS ===== */
        OrderDAO orderDAO = new OrderDAO();
        // status = 3 → DONE / RECEIVED (theo enum bạn dùng)
        orderDAO.updateOrderStatus(orderId, 3);

        /* ===== 4. REDIRECT ===== */
        response.sendRedirect("history");
    }
}
