package web.java.controller.Ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import web.java.dao.TransportDAO;
import web.java.model.Cart;

@WebServlet("/discount_transport")
public class CartDiscountAndTransport extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        /* ===== 1. GET SESSION & CART ===== */
        HttpSession session = request.getSession(false);
        if (session == null) {
            out.print("0");
            return;
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            out.print("0");
            return;
        }

        /* ===== 2. GET TRANSPORT FEE ===== */
        String transportRaw = request.getParameter("transport");
        BigDecimal shippingFee = BigDecimal.ZERO;

        try {
            if (transportRaw != null && !transportRaw.isEmpty()) {
                int transportId = Integer.parseInt(transportRaw);
                double fee = new TransportDAO().getTransportFeeById(transportId);
                shippingFee = BigDecimal.valueOf(fee);
            }
        } catch (Exception ignored) {}

        /* ===== 3. CALCULATE TOTAL ===== */
        BigDecimal total = cart.getTotal().add(shippingFee);

        /* ===== 4. RESPONSE ===== */
        out.print(String.format("%,.0f", total));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
