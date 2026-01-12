package web.java.AdminController.ProductController;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.ProductDAO;
import web.java.dao.ProductImageDAO;

@WebServlet("/admin/deleteImage")
public class DeleteImageAdmin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public DeleteImageAdmin() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String imageIdParam = request.getParameter("imageId");
        String productIdParam = request.getParameter("productId");

        if (imageIdParam != null && productIdParam != null) {
            int imageId = Integer.parseInt(imageIdParam);
            int productId = Integer.parseInt(productIdParam);

            ProductImageDAO imageDAO = new ProductImageDAO();
            imageDAO.deleteImage(imageId);

            request.setAttribute(
                    "images",
                    imageDAO.getImagesByProductId(productId)
            );
            request.setAttribute(
                    "product",
                    new ProductDAO().getProductById(productId)
            );
        }

        request.getRequestDispatcher(
                "/Admin/template/management/AddImageAdmin.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
