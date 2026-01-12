package web.java.AdminController.Statistical;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.BrandDAO;
import web.java.dao.CategoryDAO;
import web.java.dao.ProductDAO;
import web.java.dao.ProductImageDAO;
import web.java.model.Product;

@WebServlet("/admin/stcalProduct")
public class StatisticalProduct extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public StatisticalProduct() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        ProductDAO productDAO = new ProductDAO();

        List<Product> bestSellers = productDAO.getTop20BestSeller();

        request.setAttribute("images", new ProductImageDAO().getAllImages());
        request.setAttribute("products", bestSellers);
        request.setAttribute("categories", new CategoryDAO().getAllCategories());
        request.setAttribute("brands", new BrandDAO().getAllBrands());

        request.getRequestDispatcher(
                "/Admin/template/statistical/ProductStatistical.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
