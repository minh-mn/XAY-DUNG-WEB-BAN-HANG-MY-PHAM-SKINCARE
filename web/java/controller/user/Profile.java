package web.java.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import web.java.dao.*;
import web.java.model.User;

@WebServlet("/profile")
public class Profile extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /* ================= LOAD COMMON DATA ================= */
    private void loadCommonData(HttpServletRequest request) {
        request.setAttribute("saleMakeups", new CategoryDAO().getMakeupCate());
        request.setAttribute("events", new EventDAO().getAllEvents());
        request.setAttribute("brands", new BrandDAO().getAllBrands());
        request.setAttribute("categories", new CategoryDAO().getAllCategories());
        request.setAttribute("collections", new ProductCollectionDAO().getAllCollections());

        request.setAttribute("flashdeal1s", new EventDAO().getProductInEventRan(3));
        request.setAttribute("flashdeal2s", new EventDAO().getProductInEventRan(5));

        request.setAttribute("SkincareProducts", new ProductDAO().getProductSkinCare());
        request.setAttribute("MakeupProducts", new ProductDAO().getProductMakeUp());
    }

    /* ================= GET ================= */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        loadCommonData(request);
        request.getRequestDispatcher("/Page/Profile.jsp").forward(request, response);
    }

    /* ================= POST (UPDATE PROFILE) ================= */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        String password = request.getParameter("password");
        String email    = request.getParameter("email");
        String fullname = request.getParameter("fullname");
        String phone    = request.getParameter("phone");
        String avatar   = request.getParameter("avatar");

        UserDAO userDAO = new UserDAO();

        /* ===== UPDATE USER ===== */
        if (password == null || password.trim().isEmpty()) {
            // không đổi password
            userDAO.editUserNotPass(
                String.valueOf(user.getId()),
                email, fullname, phone, avatar
            );
        } else {
            // có đổi password
            userDAO.editUserHavePass(
                String.valueOf(user.getId()),
                password, email, fullname, phone, avatar
            );
        }

        session.setAttribute("message", "Cập nhật thông tin thành công!");
        response.sendRedirect("profile");
    }
}
