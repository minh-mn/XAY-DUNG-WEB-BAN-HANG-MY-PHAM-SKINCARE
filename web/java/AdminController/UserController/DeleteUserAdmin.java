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

@WebServlet("/admin/deleteUser")
public class DeleteUserAdmin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public DeleteUserAdmin() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        UserDAO userDAO = new UserDAO();

        /* ===== DELETE USER ===== */
        String idParam = request.getParameter("id");
        if (idParam != null) {
            int userId = Integer.parseInt(idParam);
            userDAO.deleteUser(userId);   // ✔ đúng method trong DAO
        }

        /* ===== PAGING ===== */
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
