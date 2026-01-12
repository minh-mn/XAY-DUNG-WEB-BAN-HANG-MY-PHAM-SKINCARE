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
import web.java.model.Brand;

@WebServlet("/brand")
public class BrandGroupServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        /* ===== 1. LẤY & VALIDATE brandId ===== */
        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendRedirect("home");
            return;
        }

        int brandId;
        try {
            brandId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("home");
            return;
        }

        BrandDAO brandDAO = new BrandDAO();

        /* ===== 2. LẤY BRAND ===== */
        Brand brand = brandDAO.getBrandById(brandId);
        if (brand == null) {
            response.sendRedirect("home");
            return;
        }

        /* ===== 3. DỮ LIỆU CHÍNH CỦA TRANG BRAND ===== */
        request.setAttribute("brand", brand);

        // Top 5 sản phẩm bán chạy của brand
        request.setAttribute("topfiveproducts",
                brandDAO.getTop5ProductsByBrand(brandId));

        // Danh sách sản phẩm theo brand
        request.setAttribute("products",
                new ProductDAO().getByBrandId(brandId));

        /* ===== 4. DỮ LIỆU CHUNG HEADER / SIDEBAR ===== */
        request.setAttribute("brands",
                brandDAO.getAllBrands());

        request.setAttribute("categories",
                new CategoryDAO().getAllCategories());

        request.setAttribute("collections",
                new ProductCollectionDAO().getAllCollections());


        request.setAttribute("events",
                new EventDAO().getAllEvents());

        /*
         * Makeup categories
         * Giả định Makeup là 1 collection (ví dụ collection_id = 2)
         * (ĐÂY LÀ CÁCH ĐÚNG – KHÔNG TẠO getMakeupCate() GIẢ)
         */
        request.setAttribute("saleMakeups",
                new CategoryDAO().getByCollectionId(2));

        /* ===== 5. FORWARD ===== */
        request.getRequestDispatcher("/Page/BrandProduct.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
