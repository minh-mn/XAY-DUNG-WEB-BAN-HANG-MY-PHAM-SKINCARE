package web.java.service;

import java.sql.Connection;
import java.sql.SQLException;

import web.java.dao.*;
import web.java.model.*;
import web.java.utils.ConnectDB;

public class OrderService {

    private final OrderDAO orderDAO = new OrderDAO();
    private final OrderItemDAO orderItemDAO = new OrderItemDAO();
    private final ProductDAO productDAO = new ProductDAO();
    private final CouponDAO couponDAO = new CouponDAO();

    /**
     * ================= PLACE ORDER (TRANSACTION CHUẨN)
     */
    public int placeOrder(
            Order order,
            Cart cart,
            Integer couponId
    ) throws SQLException {

        Connection conn = null;

        try {
            conn = ConnectDB.getConnection();
            conn.setAutoCommit(false); // 🚨 START TRANSACTION

            /* ================= 1. CREATE ORDER ================= */
            int orderId = orderDAO.createOrder(conn, order, couponId);
            if (orderId <= 0) {
                throw new SQLException("Create order failed");
            }

            /* ================= 2. CREATE ORDER ITEMS ================= */
            for (CartItem ci : cart.getCartItems()) {

                int productId = ci.getProduct().getId();
                int quantity = ci.getQuantity();

                // 2.1 CHECK STOCK
                int stock = productDAO.getStockById(conn, productId);
                if (stock < quantity) {
                    throw new SQLException(
                        "Not enough stock for product ID: " + productId
                    );
                }

                // 2.2 SNAPSHOT PRICE (GIÁ TẠI THỜI ĐIỂM MUA)
                OrderItem item = new OrderItem(
                    0,
                    orderId,
                    productId,
                    ci.getProduct().getPrice(), // ✅ KHÔNG dùng getFinalPrice
                    quantity
                );

                if (!orderItemDAO.addOrderItem(conn, item)) {
                    throw new SQLException("Insert order item failed");
                }

                // 2.3 UPDATE STOCK & SOLD
                if (!productDAO.updateStockAndSold(conn, productId, quantity)) {
                    throw new SQLException("Update stock failed");
                }
            }

            /* ================= 3. UPDATE COUPON ================= */
            if (couponId != null) {
                if (!couponDAO.increaseUsed(conn, couponId)) {
                    throw new SQLException("Update coupon failed");
                }
            }

            conn.commit(); // ✅ COMMIT THÀNH CÔNG
            return orderId;

        } catch (Exception e) {
            if (conn != null) {
                conn.rollback(); // ❌ ROLLBACK TOÀN BỘ
            }
            throw new SQLException("Place order failed – rollback", e);

        } finally {
            if (conn != null) {
                conn.close(); // 🔚 ĐÓNG CONNECTION
            }
        }
    }
}
