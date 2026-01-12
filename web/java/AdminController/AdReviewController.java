package web.java.AdminController;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.ReviewDAO;
import web.java.dao.ProductDAO;
import web.java.dao.UserDAO;

@WebServlet("/admin/reviewUser")
public class AdReviewController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AdReviewController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        request.setAttribute("users", new UserDAO().getAllUsers());
        request.setAttribute("products", new ProductDAO().getAllProducts());
        request.setAttribute("reviews", new ReviewDAO().getAllReviews());

        request.getRequestDispatcher(
                "/Admin/template/statistical/ReviewStatistical.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
