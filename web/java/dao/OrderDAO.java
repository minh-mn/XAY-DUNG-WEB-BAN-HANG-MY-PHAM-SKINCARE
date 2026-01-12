package web.java.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import web.java.model.Order;
import web.java.utils.ConnectDB;

public class OrderDAO {

    /* ================= CREATE ORDER (TRANSACTION) ================= */
    public int createOrder(Connection conn, Order o, Integer couponId) throws SQLException {
        String sql = """
            INSERT INTO orders
            (user_id, phone, note, total, discount_percent,
             shipping_id, shipping_fee, status, coupon_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (
            PreparedStatement ps = conn.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setInt(1, o.getUserId());
            ps.setString(2, o.getPhone());
            ps.setString(3, o.getNote());
            ps.setBigDecimal(4, o.getTotal());
            ps.setInt(5, o.getDiscountPercent());
            ps.setInt(6, o.getShippingId());
            ps.setBigDecimal(7, o.getShippingFee());
            ps.setInt(8, o.getStatus());

            if (couponId != null) {
                ps.setInt(9, couponId);
            } else {
                ps.setNull(9, Types.INTEGER);
            }

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return -1;
    }

    /* ================= GET ORDER BY ID ================= */
    public Order getOrderById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE id = ?";

        try (
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapOrder(rs);
                }
            }
        }
        return null;
    }

    /* ================= GET ORDERS BY USER ================= */
    public List<Order> getOrdersByUser(Connection conn, int userId) throws SQLException {
        List<Order> list = new ArrayList<>();
        String sql = """
            SELECT *
            FROM orders
            WHERE user_id = ?
            ORDER BY created_at DESC
        """;

        try (
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapOrder(rs));
                }
            }
        }
        return list;
    }

    /* ================= GET ALL ORDERS (ADMIN) ================= */
    public List<Order> getAllOrders(Connection conn) throws SQLException {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM orders ORDER BY created_at DESC";

        try (
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(mapOrder(rs));
            }
        }
        return list;
    }

    /* ================= UPDATE STATUS ================= */
    public boolean updateOrderStatus(Connection conn, int orderId, int status) throws SQLException {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";

        try (
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, status);
            ps.setInt(2, orderId);
            return ps.executeUpdate() > 0;
        }
    }

    /* ================= DELETE ORDER ================= */
    public boolean deleteOrder(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM orders WHERE id = ?";

        try (
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Order> getOrdersByUser(int userId) {
        String sql = """
            SELECT *
            FROM orders
            WHERE user_id = ?
            ORDER BY created_at DESC
        """;

        List<Order> list = new ArrayList<>();

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapOrder(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /* ================= UPDATE STATUS (NON-TRANSACTION) ================= */
    public boolean updateOrderStatus(int orderId, int status) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, status);
            ps.setInt(2, orderId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= MAP RESULTSET ================= */
    private Order mapOrder(ResultSet rs) throws SQLException {
        Order o = new Order();
        o.setId(rs.getInt("id"));
        o.setUserId(rs.getInt("user_id"));
        o.setPhone(rs.getString("phone"));
        o.setNote(rs.getString("note"));
        o.setTotal(rs.getBigDecimal("total"));
        o.setDiscountPercent(rs.getInt("discount_percent"));
        o.setShippingId(rs.getInt("shipping_id"));
        o.setShippingFee(rs.getBigDecimal("shipping_fee"));
        o.setStatus(rs.getInt("status"));

        Timestamp ts = rs.getTimestamp("created_at");
        if (ts != null) {
            o.setCreatedAt(ts.toLocalDateTime());
        }
        return o;
    }
}
