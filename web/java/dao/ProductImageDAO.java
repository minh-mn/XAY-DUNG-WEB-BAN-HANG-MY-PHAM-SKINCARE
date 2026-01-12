package web.java.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import web.java.model.ProductImage;
import web.java.utils.ConnectDB;

public class ProductImageDAO {

    /* ================= GET ALL IMAGES ================= */
    public List<ProductImage> getAllImages() {
        List<ProductImage> list = new ArrayList<>();
        String sql = "SELECT * FROM product_images";

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(mapImage(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= GET IMAGES BY PRODUCT ================= */
    public List<ProductImage> getImagesByProductId(int productId) {
        List<ProductImage> list = new ArrayList<>();
        String sql = """
            SELECT *
            FROM product_images
            WHERE product_id = ?
            ORDER BY is_primary DESC, id ASC
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapImage(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= GET PRIMARY IMAGE ================= */
    public ProductImage getPrimaryImage(int productId) {
        String sql = """
            SELECT TOP 1 *
            FROM product_images
            WHERE product_id = ? AND is_primary = 1
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapImage(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* ================= ADD IMAGE ================= */
    public boolean addImage(int productId, String image, boolean isPrimary) {
        String sql = """
            INSERT INTO product_images(product_id, image, is_primary)
            VALUES (?, ?, ?)
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, productId);
            ps.setString(2, image);
            ps.setBoolean(3, isPrimary);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= SET PRIMARY IMAGE ================= */
    public boolean setPrimaryImage(int imageId, int productId) {
        String resetSql = "UPDATE product_images SET is_primary = 0 WHERE product_id = ?";
        String setSql   = "UPDATE product_images SET is_primary = 1 WHERE id = ?";

        try (Connection conn = ConnectDB.getConnection()) {
            conn.setAutoCommit(false);

            try (
                PreparedStatement ps1 = conn.prepareStatement(resetSql);
                PreparedStatement ps2 = conn.prepareStatement(setSql)
            ) {
                ps1.setInt(1, productId);
                ps1.executeUpdate();

                ps2.setInt(1, imageId);
                ps2.executeUpdate();

                conn.commit();
                return true;
            } catch (Exception ex) {
                conn.rollback();
                throw ex;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= DELETE IMAGE ================= */
    public boolean deleteImage(int id) {
        String sql = "DELETE FROM product_images WHERE id = ?";

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

    /* ================= DELETE ALL IMAGES BY PRODUCT ================= */
    public boolean deleteImagesByProduct(int productId) {
        String sql = "DELETE FROM product_images WHERE product_id = ?";

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, productId);
            return ps.executeUpdate() >= 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= MAP RESULTSET ================= */
    private ProductImage mapImage(ResultSet rs) throws SQLException {
        ProductImage img = new ProductImage();
        img.setId(rs.getInt("id"));
        img.setProductId(rs.getInt("product_id"));
        img.setImage(rs.getString("image"));
        img.setPrimary(rs.getBoolean("is_primary"));

        Timestamp ts = rs.getTimestamp("created_at");
        if (ts != null) {
            img.setCreatedAt(ts.toLocalDateTime());
        }
        return img;
    }
}
