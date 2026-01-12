package web.java.controller.Ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.EventDAO;
import web.java.dao.ProductImageDAO;
import web.java.model.Product;
import web.java.model.ProductImage;

@WebServlet("/seemore")
public class Seemore extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        /* ===== 1. GET PARAM ===== */
        String startRaw = request.getParameter("start");
        if (startRaw == null || startRaw.isEmpty()) {
            return;
        }

        int eventId;
        try {
            eventId = Integer.parseInt(startRaw);
        } catch (NumberFormatException e) {
            return;
        }

        /* ===== 2. LOAD PRODUCTS ===== */
        List<Product> products = new EventDAO().getProductInEventRan(eventId);
        if (products == null || products.isEmpty()) {
            return;
        }

        ProductImageDAO imageDAO = new ProductImageDAO();

        /* ===== 3. RENDER HTML ===== */
        for (Product product : products) {

            // ===== IMAGE =====
            String imageUrl = "/images/default.png";
            ProductImage img = imageDAO.getPrimaryImage(product.getId());
            if (img != null) {
                imageUrl = img.getImage();
            }

            // ===== PRICE =====
            String finalPrice = product.getFormattedPrice();
            String oldPrice   = String.format("%,.0f", product.getPrice());

            out.print("""
                <div class="col-lg-2 mt-5 product_count_start">
                    <a href="product_detail?id=%d" class="product-sale__item">
                        <div class="product-sale__item-img"
                             style="padding-top:4px;height:220px">
                            <img src="%s" style="width:100%%;height:100%%;object-fit:cover"/>
                        </div>
                        <p class="product-sale__item-name">%s</p>
                        <div class="product-sale__item-price">
                            <span class="product-sale__item-price-curr">%s đ</span>
                            <span class="product-sale__item-price-old">%s đ</span>
                        </div>
                    </a>
                </div>
            """.formatted(
                    product.getId(),
                    imageUrl,
                    product.getName(),
                    finalPrice,
                    oldPrice
            ));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
