package web.java.AdminController.UserController;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.UserDAO;
import web.java.model.User;

@WebServlet("/admin/editUser")
public class EditUserAdmin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public EditUserAdmin() {
        super();
    }

    /* ================= SHOW EDIT FORM ================= */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        UserDAO userDAO = new UserDAO();

        String idParam = request.getParameter("id");
        if (idParam != null) {
            int userId = Integer.parseInt(idParam);
            User userEdit = userDAO.getUserById(userId);
            request.setAttribute("userEdit", userEdit);
        }

        request.getRequestDispatcher(
                "/Admin/template/management/EditUserAdmin.jsp"
        ).forward(request, response);
    }

    /* ================= UPDATE USER ================= */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        UserDAO userDAO = new UserDAO();

        String id = request.getParameter("id");
        String avatar = request.getParameter("avatar");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");

        /* ===== UPDATE LOGIC ===== */
        if (password == null || password.isEmpty()) {
            // KHÔNG đổi mật khẩu
            userDAO.editUserNotPass(id, null, fullname, null, avatar);
        } else {
            // CÓ đổi mật khẩu
            userDAO.editUserHavePass(id, password, null, fullname, null, avatar);
        }

        /* ===== PAGINATION ===== */
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<User> users = userDAO.getAllUserInPage(page);

        int numberOfUsers = userDAO.countUser();
        int numberOfPage = (int) Math.ceil(numberOfUsers / 10.0);

        request.setAttribute("currentPage", page);
        request.setAttribute("users", users);
        request.setAttribute("numberOfPage", numberOfPage);

        request.getRequestDispatcher(
                "/Admin/template/management/UserManagement.jsp"
        ).forward(request, response);
    }
}
