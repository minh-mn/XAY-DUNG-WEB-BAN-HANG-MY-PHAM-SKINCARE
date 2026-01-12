package web.java.controller;

import java.io.IOException;

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
import web.java.model.Category;

@WebServlet("/category")
public class CategoryGroupServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        /* ===== 1. LẤY & VALIDATE categoryId ===== */
        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendRedirect("home");
            return;
        }

        int categoryId;
        try {
            categoryId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("home");
            return;
        }

        CategoryDAO categoryDAO = new CategoryDAO();

        /* ===== 2. LẤY CATEGORY ===== */
        Category category = categoryDAO.getCategoryById(categoryId);
        if (category == null) {
            response.sendRedirect("home");
            return;
        }

        request.setAttribute("category", category);

        /* ===== 3. DANH SÁCH SẢN PHẨM THEO CATEGORY ===== */
        request.setAttribute("products",
                new ProductDAO().getByCategoryId(categoryId));

        /* ===== 4. CATEGORY CÙNG COLLECTION (CATEGORY LIÊN QUAN) ===== */
        if (category.getCollectionId() != null) {
            request.setAttribute("categorySames",
                    categoryDAO.getByCollectionId(category.getCollectionId()));
        }

        /* ===== 5. TOP CATEGORY (nếu cần hiển thị) ===== */
        request.setAttribute("topCategories",
                categoryDAO.getTop6());

        /* ===== 6. DỮ LIỆU CHUNG HEADER / SIDEBAR ===== */
        request.setAttribute("brands",
                new BrandDAO().getAllBrands());

        request.setAttribute("categories",
                categoryDAO.getAllCategories());

        request.setAttribute("collections",
                new ProductCollectionDAO().getAllCollections());

        request.setAttribute("events",
                new EventDAO().getAllEvents());

        /*
         * Makeup categories
         * Giả định Makeup = collection_id = 2
         */
        request.setAttribute("saleMakeups",
                categoryDAO.getByCollectionId(2));

        /* ===== 7. FORWARD ===== */
        request.getRequestDispatcher("/Page/CategoryProduct.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
