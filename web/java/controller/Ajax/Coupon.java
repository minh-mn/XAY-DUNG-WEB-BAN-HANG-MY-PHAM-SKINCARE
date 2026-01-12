package web.java.controller.Ajax;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import web.java.dao.CouponDAO;
import web.java.dao.TransportDAO;
import web.java.model.*;


@WebServlet("/Coupon")
public class Coupon extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        /* ===== 1. PARAM ===== */
        String transportRaw = request.getParameter("transport");
        String couponCode  = request.getParameter("magiamgia");

        /* ===== 2. SESSION & CART ===== */
        HttpSession session = request.getSession(false);
        if (session == null) {
            out.print("0");
            return;
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getCartItems().isEmpty()) {
            out.print("0");
            return;
        }

        /* ===== 3. SHIPPING FEE ===== */
        double shippingFee = 0;
        try {
            if (transportRaw != null && !transportRaw.isEmpty()) {
                int transportId = Integer.parseInt(transportRaw);
                shippingFee = new TransportDAO().getTransportFeeById(transportId);
            }
        } catch (Exception ignored) {}

        /* ===== 4. COUPON ===== */
        double discountPercent = 0;
        if (couponCode != null && !couponCode.isEmpty()) {
        	web.java.model.Coupon coupon =
        	        new CouponDAO().getCouponByCode(couponCode.trim());

            if (coupon != null) {
                discountPercent = coupon.getDiscountPercent();
            }
        }

        /* ===== 5. CALCULATE ===== */
        double finalTotal =
                cart.getTotalDiscount(shippingFee, discountPercent);

        /* ===== 6. OUTPUT ===== */
        out.print(String.format("%,.0f", finalTotal));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}