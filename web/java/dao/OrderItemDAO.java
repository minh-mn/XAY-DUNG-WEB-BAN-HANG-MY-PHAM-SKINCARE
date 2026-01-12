package web.java.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import web.java.model.OrderItem;

public class OrderItemDAO {

    /* ================= ADD ORDER ITEM (TRANSACTION) ================= */
    public boolean addOrderItem(Connection conn, OrderItem item) throws SQLException {
        String sql = """
            INSERT INTO order_items(order_id, product_id, price, quantity)
            VALUES (?, ?, ?, ?)
        """;

        try (
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getProductId());
            ps.setBigDecimal(3, item.getPrice());
            ps.setInt(4, item.getQuantity());
            return ps.executeUpdate() > 0;
        }
    }

    /* ================= GET ITEMS BY ORDER ================= */
    public List<OrderItem> getItemsByOrderId(Connection conn, int orderId) throws SQLException {
        List<OrderItem> list = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE order_id = ?";

        try (
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapOrderItem(rs));
                }
            }
        }
        return list;
    }

    /* ================= GET ALL ORDER ITEMS ================= */
    public List<OrderItem> getAllOrderItems(Connection conn) throws SQLException {
        List<OrderItem> list = new ArrayList<>();
        String sql = "SELECT * FROM order_items";

        try (
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(mapOrderItem(rs));
            }
        }
        return list;
    }

    /* ================= DELETE ITEMS BY ORDER ================= */
    public boolean deleteItemsByOrder(Connection conn, int orderId) throws SQLException {
        String sql = "DELETE FROM order_items WHERE order_id = ?";

        try (
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, orderId);
            return ps.executeUpdate() >= 0;
        }
    }

    /* ================= MAP RESULTSET ================= */
    private OrderItem mapOrderItem(ResultSet rs) throws SQLException {
        OrderItem item = new OrderItem();
        item.setId(rs.getInt("id"));
        item.setOrderId(rs.getInt("order_id"));
        item.setProductId(rs.getInt("product_id"));
        item.setPrice(rs.getBigDecimal("price"));
        item.setQuantity(rs.getInt("quantity"));
        return item;
    }
}
