package web.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import web.java.model.Review;
import web.java.utils.ConnectDB;

public class ReviewDAO {

    /* ================= GET ALL REVIEWS ================= */
    public List<Review> getAllReviews() {
        List<Review> list = new ArrayList<>();
        String sql = """
            SELECT id, user_id, product_id, order_id,
                   rating, comment, image,
                   status, created_at
            FROM reviews
            ORDER BY created_at DESC
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(mapReview(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= GET REVIEWS BY PRODUCT ================= */
    public List<Review> getReviewsByProductId(int productId) {
        List<Review> list = new ArrayList<>();
        String sql = """
            SELECT id, user_id, product_id, order_id,
                   rating, comment, image,
                   status, created_at
            FROM reviews
            WHERE product_id = ? AND status = 1
            ORDER BY created_at DESC
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapReview(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= ADD REVIEW ================= */
    public boolean addReview(Review r) {
        String sql = """
            INSERT INTO reviews
            (user_id, product_id, order_id, rating, comment, image, status)
            VALUES (?, ?, ?, ?, ?, ?, 1)
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, r.getUserId());
            ps.setInt(2, r.getProductId());
            ps.setInt(3, r.getOrderId());
            ps.setInt(4, r.getRating());
            ps.setString(5, r.getComment());
            ps.setString(6, r.getImage());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= DISABLE REVIEW (ADMIN) ================= */
    public boolean disableReview(int id) {
        String sql = "UPDATE reviews SET status = 0 WHERE id = ?";
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
    private Review mapReview(ResultSet rs) throws Exception {
        return new Review(
            rs.getInt("id"),
            rs.getInt("user_id"),
            rs.getInt("product_id"),
            rs.getInt("order_id"),
            rs.getInt("rating"),
            rs.getString("comment"),
            rs.getString("image"),
            rs.getBoolean("status"),
            rs.getTimestamp("created_at").toLocalDateTime()
        );
    }
}
