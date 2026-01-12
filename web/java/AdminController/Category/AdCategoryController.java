package web.java.AdminController.Category;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.CategoryDAO;
import web.java.dao.ProductCollectionDAO;

@WebServlet("/admin/category")
public class AdCategoryController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AdCategoryController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        CategoryDAO categoryDAO = new CategoryDAO();
        ProductCollectionDAO collectionDAO = new ProductCollectionDAO();

        request.setAttribute("categories", categoryDAO.getAllCategories());
        request.setAttribute("collections", collectionDAO.getAllCollections());

        request.getRequestDispatcher(
                "/Admin/template/management/CategoryManagement.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
