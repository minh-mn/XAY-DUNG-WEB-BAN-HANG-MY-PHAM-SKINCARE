package web.java.AdminController.ProductController;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.ProductDAO;
import web.java.dao.ProductImageDAO;

@WebServlet("/admin/addImageProduct")
public class AddImageAdmin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AddImageAdmin() {
        super();
    }

    /* ================= SHOW IMAGE LIST ================= */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        if (idParam != null) {
            int productId = Integer.parseInt(idParam);

            ProductImageDAO imageDAO = new ProductImageDAO();
            ProductDAO productDAO = new ProductDAO();

            request.setAttribute(
                    "images",
                    imageDAO.getImagesByProductId(productId)
            );
            request.setAttribute(
                    "product",
                    productDAO.getProductById(productId)
            );
        }

        request.getRequestDispatcher(
                "/Admin/template/management/AddImageAdmin.jsp"
        ).forward(request, response);
    }

    /* ================= ADD IMAGE ================= */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        String image = request.getParameter("image");

        if (idParam != null && image != null && !image.isEmpty()) {
            int productId = Integer.parseInt(idParam);

            ProductImageDAO imageDAO = new ProductImageDAO();
            imageDAO.addImage(productId, image, false); // mặc định không phải ảnh chính
        }

        // reload lại trang
        doGet(request, response);
    }
}
