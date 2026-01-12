package web.java.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.BrandDAO;
import web.java.dao.CategoryDAO;
import web.java.dao.EventDAO;
import web.java.dao.ProductDAO;
import web.java.dao.ProductCollectionDAO;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BrandDAO brandDAO;
    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;
    private EventDAO eventDAO;
    private ProductCollectionDAO collectionDAO;

    @Override
    public void init() throws ServletException {
        brandDAO = new BrandDAO();
        categoryDAO = new CategoryDAO();
        productDAO = new ProductDAO();
        eventDAO = new EventDAO();
        collectionDAO = new ProductCollectionDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        /* ================= LOAD DATA ================= */

        // Danh mục
        request.setAttribute("categories", categoryDAO.getAllCategories());

        // Thương hiệu
        request.setAttribute("brands", brandDAO.getAllBrands());

        // Bộ sưu tập
        request.setAttribute("collections", collectionDAO.getAllCollections());

        // Sản phẩm nổi bật
        request.setAttribute("bestSellers", productDAO.getTop20BestSeller());

        // Skincare & Makeup (theo collection_id)
        request.setAttribute("skincareProducts", productDAO.getByCollectionId(1));
        request.setAttribute("makeupProducts", productDAO.getByCollectionId(2));

        // Event / Flash sale
        request.setAttribute("events", eventDAO.getAllEvents());
        request.setAttribute("flashSaleProducts",
                eventDAO.getProductsInActiveEvents(8));

        /* ================= FORWARD ================= */
        request.getRequestDispatcher("/Page/index.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
