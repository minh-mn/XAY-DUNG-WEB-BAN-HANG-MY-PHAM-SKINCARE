package web.java.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import jakarta.mail.*;
import jakarta.mail.Transport;
import jakarta.mail.internet.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import web.java.dao.*;
import web.java.model.*;
import web.java.service.OrderService;

@WebServlet("/confirmOrder")
public class ConfirmOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /* ===== EMAIL CONFIG ===== */
    private static final String MAIL_USER = "a@gmail.com";
    private static final String MAIL_PASS = "a123";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        /* ===== 1. LẤY PARAM ===== */
        String orderAddress = request.getParameter("orderAddress");
        String orderPhone   = request.getParameter("orderPhone");
        String transportId  = request.getParameter("hidden_transport");
        String couponCode   = request.getParameter("hidden_coupon");
        String orderNote    = request.getParameter("orderNote");
        String totalStr     = request.getParameter("hidden_total");
        String userIdStr    = request.getParameter("userLogin");

        /* ===== 2. VALIDATE ===== */
        if (orderAddress == null || orderAddress.isEmpty()
                || orderPhone == null || orderPhone.isEmpty()
                || userIdStr == null) {
            response.sendRedirect("previewOrder");
            return;
        }

        HttpSession session = request.getSession(false);
        Cart cart = (session != null) ? (Cart) session.getAttribute("cart") : null;
        if (cart == null || cart.getCartItems().isEmpty()) {
            response.sendRedirect("home");
            return;
        }

        int userId = Integer.parseInt(userIdStr);
        int transportIdInt = Integer.parseInt(transportId);

        /* ===== 3. TÍNH TIỀN (BIGDECIMAL CHUẨN) ===== */
        BigDecimal totalDeal = new BigDecimal(totalStr);

        BigDecimal transportFee = BigDecimal.valueOf(
                new TransportDAO().getTransportFeeById(transportIdInt)
        );

        int discountPercent = 0;
        Integer couponId = null;

        if (couponCode != null && !couponCode.isEmpty()) {
            Coupon coupon = new CouponDAO().getCouponByCode(couponCode);
            if (coupon != null) {
                discountPercent = coupon.getDiscountPercent();
                couponId = coupon.getId();
            }
        }

        /* ===== 4. TẠO ORDER MODEL ===== */
        Order order = new Order();
        order.setUserId(userId);
        order.setPhone(orderPhone);
        order.setNote(orderNote);
        order.setTotal(totalDeal);
        order.setDiscountPercent(discountPercent);
        order.setShippingId(transportIdInt);
        order.setShippingFee(transportFee);
        order.setStatus(0); // Pending

        /* ===== 5. LƯU ORDER (TRANSACTION) ===== */
        try {
            OrderService orderService = new OrderService();
            orderService.placeOrder(order, cart, couponId);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("previewOrder?error=order_failed");
            return;
        }

        /* ===== 6. GỬI EMAIL ===== */
        try {
            User user = new UserDAO().getUserById(userId);
            BigDecimal finalTotal = order.getFinalTotal();

            sendEmail(
                user.getEmail(),
                cart.getCartItems(),
                orderAddress,
                orderPhone,
                finalTotal
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* ===== 7. CLEAR CART ===== */
        session.removeAttribute("cart");

        response.sendRedirect("history");
    }

    /* ================= SEND EMAIL ================= */
    private void sendEmail(
            String to,
            List<CartItem> cartItems,
            String address,
            String phone,
            BigDecimal total
    ) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session mailSession = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(MAIL_USER, MAIL_PASS);
                    }
                });

        StringBuilder productHtml = new StringBuilder();
        for (CartItem item : cartItems) {
            productHtml.append("<p>")
                    .append(item.getProduct().getName())
                    .append(" x ")
                    .append(item.getQuantity())
                    .append("</p>");
        }

        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(MAIL_USER));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("Order Confirmation");
        message.setContent(
                "<h2>Thank you for your order</h2>" +
                "<p>Address: " + address + "</p>" +
                "<p>Phone: " + phone + "</p>" +
                productHtml +
                "<h3>Total: " + String.format("%,.0f", total) + " VND</h3>",
                "text/html; charset=UTF-8"
        );

        Transport.send(message);
    }
}
