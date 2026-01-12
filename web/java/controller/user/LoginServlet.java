package web.java.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.java.dao.UserDAO;
import web.java.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("Page/web/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null ||
            username.isEmpty() || password.isEmpty()) {

            request.setAttribute("mess", "Username and password must not be empty");
            request.getRequestDispatcher("Page/web/login.jsp").forward(request, response);
            return;
        }

        UserDAO userDAO = new UserDAO();
        User user = userDAO.login(username, password);

        if (user == null) {
            request.setAttribute("mess", "Wrong username or password");
            request.getRequestDispatcher("Page/web/login.jsp").forward(request, response);
            return;
        }

        // COOKIE
        Cookie loginId = new Cookie("loginId", String.valueOf(user.getId()));
        Cookie loginName = new Cookie("loginName", user.getUsername());

        loginId.setMaxAge(60 * 60 * 24);
        loginName.setMaxAge(60 * 60 * 24);

        response.addCookie(loginId);
        response.addCookie(loginName);

        // SESSION
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);

        response.sendRedirect("home");
    }
}