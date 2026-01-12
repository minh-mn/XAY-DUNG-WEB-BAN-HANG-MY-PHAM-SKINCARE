package web.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.java.model.Coupon;
import web.java.utils.ConnectDB;

public class CouponDAO {

    /* ================= GET ALL ACTIVE COUPONS ================= */
    public List<Coupon> getAllCoupons() {
        List<Coupon> list = new ArrayList<>();
        String sql = """
            SELECT id, code, discount_percent, quantity, used,
                   start_date, end_date, status, created_at
            FROM coupons
            WHERE status = 1
            ORDER BY created_at DESC
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(mapCoupon(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= GET COUPON BY CODE ================= */
    public Coupon getCouponByCode(String code) {
        String sql = """
            SELECT id, code, discount_percent, quantity, used,
                   start_date, end_date, status, created_at
            FROM coupons
            WHERE code = ? AND status = 1
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapCoupon(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* ================= ADD COUPON (ADMIN) ================= */
    public boolean addCoupon(Coupon c) {
        String sql = """
            INSERT INTO coupons
            (code, discount_percent, quantity, used,
             start_date, end_date, status)
            VALUES (?, ?, ?, 0, ?, ?, 1)
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, c.getCode());
            ps.setInt(2, c.getDiscountPercent());
            ps.setInt(3, c.getQuantity());
            ps.setTimestamp(4, c.getStartDate() != null
                    ? java.sql.Timestamp.valueOf(c.getStartDate()) : null);
            ps.setTimestamp(5, c.getEndDate() != null
                    ? java.sql.Timestamp.valueOf(c.getEndDate()) : null);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* =========================================================
       ========== COUPON DÙNG NGOÀI TRANSACTION =================
       ========================================================= */

    public boolean increaseUsed(int couponId) {
        String sql = """
            UPDATE coupons
            SET used = used + 1
            WHERE id = ? AND used < quantity
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, couponId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* =========================================================
       ========== COUPON DÙNG TRONG TRANSACTION =================
       ========================================================= */

    public boolean increaseUsed(Connection conn, int couponId) throws SQLException {
        String sql = """
            UPDATE coupons
            SET used = used + 1
            WHERE id = ? AND used < quantity
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, couponId);
            return ps.executeUpdate() > 0;
        }
    }
    /* ================= DELETE COUPON ================= */
    public boolean deleteCoupon(int id) {
        String sql = "DELETE FROM coupons WHERE id = ?";

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= MAP RESULTSET ================= */
    private Coupon mapCoupon(ResultSet rs) throws SQLException {
        Coupon c = new Coupon();
        c.setId(rs.getInt("id"));
        c.setCode(rs.getString("code"));
        c.setDiscountPercent(rs.getInt("discount_percent"));
        c.setQuantity(rs.getInt("quantity"));
        c.setUsed(rs.getInt("used"));
        c.setStatus(rs.getBoolean("status"));
        c.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

        if (rs.getTimestamp("start_date") != null) {
            c.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
        }
        if (rs.getTimestamp("end_date") != null) {
            c.setEndDate(rs.getTimestamp("end_date").toLocalDateTime());
        }
        return c;
    }
}
