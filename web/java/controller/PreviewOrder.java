package web.java.controller;

import java.io.IOException;
import java.math.BigDecimal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import web.java.dao.BrandDAO;
import web.java.dao.CategoryDAO;
import web.java.dao.ProductCollectionDAO;
import web.java.dao.EventDAO;
import web.java.dao.TransportDAO;
import web.java.model.Cart;

@WebServlet("/preview")
public class PreviewOrder extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        /* ===== 1. GET CART ===== */
        HttpSession session = request.getSession(false);
        Cart cart = (session != null) ? (Cart) session.getAttribute("cart") : null;

        if (cart == null || cart.getCartItems().isEmpty()) {
            response.sendRedirect("home");
            return;
        }

        /* ===== 2. TRANSPORT FEE ===== */
        TransportDAO transportDAO = new TransportDAO();
        BigDecimal transportFee = transportDAO.getDefaultTransport() != null
                ? transportDAO.getDefaultTransport().getFee()
                : BigDecimal.ZERO;

        String transportParam = request.getParameter("transport");
        if (transportParam != null) {
            try {
                int transportId = Integer.parseInt(transportParam);
                transportFee = BigDecimal.valueOf(
                        transportDAO.getTransportFeeById(transportId)
                );
            } catch (NumberFormatException ignored) {
            }
        }

        /* ===== 3. TOTAL ===== */
        BigDecimal cartTotal = cart.getTotal(); // BigDecimal
        BigDecimal finalTotal = cartTotal.add(transportFee);

        /* ===== 4. SET ATTRIBUTES ===== */
        request.setAttribute("cart", cart);
        request.setAttribute("cartTotal", cartTotal);
        request.setAttribute("transportFee", transportFee);
        request.setAttribute("finalTotal", finalTotal);
        request.setAttribute("finalTotalFormatted",
                String.format("%,.0f", finalTotal));

        request.setAttribute("saleMakeups",
                new CategoryDAO().getMakeupCate());

        request.setAttribute("saleProducts",
                new EventDAO().getProductsInActiveEvents(8));

        request.setAttribute("events",
                new EventDAO().getAllEvents());

        request.setAttribute("brands",
                new BrandDAO().getAllBrands());

        request.setAttribute("categories",
                new CategoryDAO().getAllCategories());

        request.setAttribute("collections",
                new ProductCollectionDAO().getAllCollections());

        request.setAttribute("transports",
                transportDAO.getAllTransports());

        /* ===== 5. FORWARD ===== */
        request.getRequestDispatcher("/Page/previewOrder.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
