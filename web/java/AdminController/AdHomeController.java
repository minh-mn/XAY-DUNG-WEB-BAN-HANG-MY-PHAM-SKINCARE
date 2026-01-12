package web.java.AdminController;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import web.java.model.User;

@WebServlet("/admin/home")
public class AdHomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        /* ===== 1. CHƯA LOGIN ===== */
        if (session == null || session.getAttribute("loginSession") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("loginSession");

        /* ===== 2. KHÔNG PHẢI ADMIN ===== */
        if (user.getRole() != 1) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        /* ===== 3. VÀO TRANG ADMIN ===== */
        request.getRequestDispatcher("/Admin/template/index.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
