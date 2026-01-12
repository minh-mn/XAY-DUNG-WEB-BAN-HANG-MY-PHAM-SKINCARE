package web.java.AdminController;

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

@WebServlet("/admin/updateQtt")
public class AdAddQuantityServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AdAddQuantityServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        ProductDAO productDAO = new ProductDAO();

        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Product> products =
                productDAO.getAllProductInPageHaveQtt(page);

        int numberOfProduct = productDAO.countProduct();
        int numberOfPage = (int) Math.ceil(numberOfProduct / 10.0);

        request.setAttribute("images", new ProductImageDAO().getAllImages());
        request.setAttribute("currentPage", page);
        request.setAttribute("products", products);
        request.setAttribute("numberOfPage", numberOfPage);
        request.setAttribute("categories", new CategoryDAO().getAllCategories());
        request.setAttribute("brands", new BrandDAO().getAllBrands());

        request.getRequestDispatcher(
                "/Admin/template/statistical/AddQtt.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        ProductDAO productDAO = new ProductDAO();

        String productId = request.getParameter("product");
        String qtt = request.getParameter("qtt");

        if (productId != null && qtt != null) {
            productDAO.updateQtt(productId, qtt);
        }

        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int numberOfProduct = productDAO.countProduct();
        int numberOfPage = (int) Math.ceil(numberOfProduct / 10.0);

        request.setAttribute("images", new ProductImageDAO().getAllImages());
        request.setAttribute("currentPage", page);
        request.setAttribute(
                "products",
                productDAO.getAllProductInPageHaveQtt(page)
        );
        request.setAttribute("numberOfPage", numberOfPage);
        request.setAttribute("categories", new CategoryDAO().getAllCategories());
        request.setAttribute("brands", new BrandDAO().getAllBrands());

        request.getRequestDispatcher(
                "/Admin/template/statistical/AddQtt.jsp"
        ).forward(request, response);
    }
}
