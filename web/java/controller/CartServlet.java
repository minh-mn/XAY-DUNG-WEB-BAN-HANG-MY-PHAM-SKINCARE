package web.java.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import web.java.dao.ProductDAO;
import web.java.model.Cart;
import web.java.model.CartItem;
import web.java.model.Product;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /* ================= VIEW CART ================= */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Cart cart = getCart(session);

        request.setAttribute("cart", cart);
        request.setAttribute("cartItems", cart.getCartItems());
        request.setAttribute("total", cart.getTotal());

        request.getRequestDispatcher("/Page/cart.jsp")
               .forward(request, response);
    }

    /* ================= CART ACTIONS ================= */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect("cart");
            return;
        }

        switch (action) {
            case "add":
                addToCart(request);
                break;
            case "update":
                updateCart(request);
                break;
            case "remove":
                removeFromCart(request);
                break;
        }

        response.sendRedirect("cart");
    }

    /* ================= ADD TO CART ================= */
    private void addToCart(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Cart cart = getCart(session);

        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity  = Integer.parseInt(request.getParameter("quantity"));

        if (quantity <= 0) return;

        Product product = new ProductDAO().getProductById(productId);
        if (product == null) return;

        CartItem item = new CartItem();
        item.setProduct(product);

        cart.addItem(item, quantity);
    }

    /* ================= UPDATE QUANTITY ================= */
    private void updateCart(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Cart cart = getCart(session);

        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity  = Integer.parseInt(request.getParameter("quantity"));

        if (quantity < 0) return;

        for (CartItem item : cart.getCartItems()) {
            if (item.getProduct().getId() == productId) {
                if (quantity == 0) {
                    cart.removeItemByProductId(productId);
                } else {
                    item.setQuantity(quantity);
                }
                break;
            }
        }
    }

    /* ================= REMOVE ITEM ================= */
    private void removeFromCart(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Cart cart = getCart(session);

        int productId = Integer.parseInt(request.getParameter("productId"));
        cart.removeItemByProductId(productId);
    }

    /* ================= GET CART FROM SESSION ================= */
    private Cart getCart(HttpSession session) {

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
}
