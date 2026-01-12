package web.java.AdminController.ProductController;

import java.io.IOException;
import java.math.BigDecimal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.BrandDAO;
import web.java.dao.CategoryDAO;
import web.java.dao.ProductCollectionDAO;
import web.java.dao.ProductDAO;
import web.java.model.Product;

@WebServlet("/admin/addProduct")
public class AddProductAdmin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AddProductAdmin() {
        super();
    }

    /* ================= SHOW FORM ================= */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        request.setAttribute("categories", new CategoryDAO().getAllCategories());
        request.setAttribute("collections", new ProductCollectionDAO().getAllCollections());
        request.setAttribute("brands", new BrandDAO().getAllBrands());

        request.getRequestDispatcher(
                "/Admin/template/management/AddProductAdmin.jsp"
        ).forward(request, response);
    }

    /* ================= ADD PRODUCT ================= */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");
        String discountStr = request.getParameter("discount");
        String categoryIdStr = request.getParameter("category");
        String brandIdStr = request.getParameter("brand");
        String collectionIdStr = request.getParameter("collection");

        /* ===== VALIDATE ===== */
        if (title == null || title.isEmpty()
                || description == null || description.isEmpty()
                || priceStr == null || priceStr.isEmpty()) {

            request.setAttribute("mess", "Please fill all fields!");
            doGet(request, response);
            return;
        }

        if (!priceStr.matches("\\d+(\\.\\d+)?")) {
            request.setAttribute("mess", "Price must be a number!");
            doGet(request, response);
            return;
        }

        /* ===== MAP PRODUCT ===== */
        Product p = new Product();
        p.setName(title);
        p.setDescription(description);
        p.setPrice(new BigDecimal(priceStr));
        p.setDiscount(discountStr == null ? 0 : Integer.parseInt(discountStr));
        p.setStock(0);
        p.setCategoryId(Integer.parseInt(categoryIdStr));
        p.setBrandId(Integer.parseInt(brandIdStr));
        p.setCollectionId(Integer.parseInt(collectionIdStr));
        p.setEventId(0);

        /* ===== SAVE ===== */
        ProductDAO productDAO = new ProductDAO();
        productDAO.addProduct(p);

        request.setAttribute("mess", "Add product successfully!");
        doGet(request, response);
    }
}
