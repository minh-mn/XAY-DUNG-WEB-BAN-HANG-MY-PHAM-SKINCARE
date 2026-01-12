package web.java.controller.product;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import web.java.dao.ProductDAO;
import web.java.model.Cart;
import web.java.model.CartItem;
import web.java.model.Product;

@WebServlet("/addProductToCart")
public class AddProductCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        /* ===== 1. GET & VALIDATE PARAM ===== */
        String productIdRaw = request.getParameter("productId");
        String quantityRaw  = request.getParameter("quantity");

        if (productIdRaw == null || quantityRaw == null) {
            response.sendRedirect("home");
            return;
        }

        int productId;
        int quantity;

        try {
            productId = Integer.parseInt(productIdRaw);
            quantity  = Integer.parseInt(quantityRaw);
        } catch (NumberFormatException e) {
            response.sendRedirect("home");
            return;
        }

        if (quantity <= 0) {
            response.sendRedirect("product_detail?id=" + productId);
            return;
        }

        /* ===== 2. GET LOGIN ID FROM COOKIE ===== */
        int loginId = 0;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("loginId".equals(c.getName())) {
                    loginId = Integer.parseInt(c.getValue());
                    break;
                }
            }
        }

        /* ===== 3. GET PRODUCT ===== */
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(productId);

        if (product == null) {
            response.sendRedirect("home");
            return;
        }

        /* ===== 4. GET SESSION & CART ===== */
        HttpSession session = request.getSession(true);

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart(loginId);
        }

        /* ===== 5. ADD TO CART ===== */
        CartItem item = new CartItem(product, quantity);
        cart.addItem(item, quantity);

        session.setAttribute("cart", cart);

        /* ===== 6. REDIRECT (TRÁNH F5 ADD LẠI) ===== */
        response.sendRedirect("product_detail?id=" + productId);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("home");
    }
}
