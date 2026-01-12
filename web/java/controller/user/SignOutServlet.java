package web.java.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/signout")
public class SignOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        /* ===== 1. CLEAR COOKIES ===== */
        Cookie loginId = new Cookie("loginId", "");
        Cookie loginName = new Cookie("loginName", "");
        loginId.setMaxAge(0);
        loginName.setMaxAge(0);
        loginId.setPath("/");
        loginName.setPath("/");

        response.addCookie(loginId);
        response.addCookie(loginName);

        /* ===== 2. INVALIDATE SESSION ===== */
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        /* ===== 3. REDIRECT ===== */
        response.sendRedirect("home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
