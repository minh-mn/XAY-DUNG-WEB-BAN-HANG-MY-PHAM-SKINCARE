package web.java.controller.product;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.BrandDAO;
import web.java.dao.CategoryDAO;
import web.java.dao.ProductCollectionDAO;
import web.java.dao.EventDAO;
import web.java.dao.ProductDAO;
import web.java.dao.ProductImageDAO;
import web.java.model.Product;
import web.java.model.ProductImage;

@WebServlet("/product_detail")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProductServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        /* ===== 1. GET PRODUCT ID ===== */
        String idRaw = request.getParameter("id");
        if (idRaw == null || idRaw.isEmpty()) {
            response.sendRedirect("home");
            return;
        }

        int productId;
        try {
            productId = Integer.parseInt(idRaw);
        } catch (NumberFormatException e) {
            response.sendRedirect("home");
            return;
        }

        /* ===== 2. LOAD PRODUCT ===== */
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(productId);

        if (product == null) {
            response.sendRedirect("home");
            return;
        }

        /* ===== 3. LOAD PRODUCT IMAGES ===== */
        ProductImageDAO imageDAO = new ProductImageDAO();
        List<ProductImage> images = imageDAO.getImagesByProductId(productId);

        /* ===== 4. LOAD COMMON DATA ===== */
        request.setAttribute("events", new EventDAO().getAllEvents());
        request.setAttribute("brands", new BrandDAO().getAllBrands());
        request.setAttribute("categories", new CategoryDAO().getAllCategories());
        request.setAttribute("collections", new ProductCollectionDAO().getAllCollections());

        /* ===== 5. SET DATA FOR VIEW ===== */
        request.setAttribute("productDetail", product);
        request.setAttribute("listImages", images);

        /* ===== 6. FORWARD ===== */
        request.getRequestDispatcher("/Page/productDetail.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
