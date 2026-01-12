package web.java.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import web.java.model.Product;
import web.java.utils.ConnectDB;

public class ProductDAO {

    /* ================= GET ALL ACTIVE PRODUCTS ================= */
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = """
            SELECT
                id, name, description, price, discount, stock,
                sold, rating, category_id, brand_id,
                collection_id, event_id
            FROM products
            WHERE status = 1
            ORDER BY created_at DESC
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(mapProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= GET BY ID ================= */
    public Product getProductById(int id) {
        String sql = """
            SELECT
                id, name, description, price, discount, stock,
                sold, rating, category_id, brand_id,
                collection_id, event_id
            FROM products
            WHERE id = ? AND status = 1
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapProduct(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* ================= BY CATEGORY ================= */
    public List<Product> getByCategoryId(int categoryId) {
        List<Product> list = new ArrayList<>();
        String sql = """
            SELECT
                id, name, description, price, discount, stock,
                sold, rating, category_id, brand_id,
                collection_id, event_id
            FROM products
            WHERE category_id = ? AND status = 1
            ORDER BY created_at DESC
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= BY BRAND ================= */
    public List<Product> getByBrandId(int brandId) {
        List<Product> list = new ArrayList<>();
        String sql = """
            SELECT
                id, name, description, price, discount, stock,
                sold, rating, category_id, brand_id,
                collection_id, event_id
            FROM products
            WHERE brand_id = ? AND status = 1
            ORDER BY sold DESC
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, brandId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= BY COLLECTION ================= */
    public List<Product> getByCollectionId(int collectionId) {
        List<Product> list = new ArrayList<>();
        String sql = """
            SELECT
                id, name, description, price, discount, stock,
                sold, rating, category_id, brand_id,
                collection_id, event_id
            FROM products
            WHERE collection_id = ? AND status = 1
            ORDER BY created_at DESC
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, collectionId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= TOP 20 BEST SELLER ================= */
    public List<Product> getTop20BestSeller() {
        List<Product> list = new ArrayList<>();
        String sql = """
            SELECT TOP 20
                id, name, description, price, discount, stock,
                sold, rating, category_id, brand_id,
                collection_id, event_id
            FROM products
            WHERE status = 1
            ORDER BY sold DESC
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(mapProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= ADMIN: INSERT ================= */
    public boolean addProduct(Product p) {
        String sql = """
            INSERT INTO products
            (name, description, price, discount, stock,
             category_id, brand_id, collection_id, event_id, status)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 1)
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setBigDecimal(3, p.getPrice());
            ps.setInt(4, p.getDiscount());
            ps.setInt(5, p.getStock());
            ps.setInt(6, p.getCategoryId());
            ps.setInt(7, p.getBrandId());
            ps.setInt(8, p.getCollectionId());
            ps.setInt(9, p.getEventId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= ADMIN: UPDATE ================= */
    public boolean updateProduct(Product p) {
        String sql = """
            UPDATE products
            SET name=?, description=?, price=?, discount=?, stock=?,
                category_id=?, brand_id=?, collection_id=?, event_id=?
            WHERE id=?
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setBigDecimal(3, p.getPrice());
            ps.setInt(4, p.getDiscount());
            ps.setInt(5, p.getStock());
            ps.setInt(6, p.getCategoryId());
            ps.setInt(7, p.getBrandId());
            ps.setInt(8, p.getCollectionId());
            ps.setInt(9, p.getEventId());
            ps.setInt(10, p.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= ADMIN: DELETE (SOFT) ================= */
    public boolean deleteProduct(int id) {
        String sql = "UPDATE products SET status = 0 WHERE id = ?";

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

    /* =========================================================
       ========== CHỈ 2 HÀM DÙNG CHO TRANSACTION ================
       ========================================================= */

    /* ===== GET STOCK (TRANSACTION) ===== */
    public int getStockById(Connection conn, int productId) throws SQLException {
        String sql = "SELECT stock FROM products WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("stock");
            }
        }
        return 0;
    }

    /* ===== UPDATE STOCK & SOLD (TRANSACTION) ===== */
    public boolean updateStockAndSold(Connection conn, int productId, int quantity) throws SQLException {
        String sql = """
            UPDATE products
            SET stock = stock - ?,
                sold = sold + ?
            WHERE id = ? AND stock >= ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, quantity);
            ps.setInt(3, productId);
            ps.setInt(4, quantity);
            return ps.executeUpdate() > 0;
        }
    }

    //
    public List<Product> getProductSkinCare() {
        String sql = """
            SELECT id, name, description, price, discount,
                   stock, sold, rating,
                   category_id, brand_id,
                   collection_id, event_id
            FROM products
            WHERE category_id = 1 AND status = 1
            ORDER BY created_at DESC
        """;

        List<Product> list = new ArrayList<>();

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(mapProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
//
    public List<Product> getProductMakeUp() {
        String sql = """
            SELECT id, name, description, price, discount,
                   stock, sold, rating,
                   category_id, brand_id,
                   collection_id, event_id
            FROM products
            WHERE category_id = 2 AND status = 1
            ORDER BY created_at DESC
        """;

        List<Product> list = new ArrayList<>();

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(mapProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /* ================= ADMIN: GET PRODUCT BY PAGE (HAVE QTT) ================= */
    public List<Product> getAllProductInPageHaveQtt(int page) {

        List<Product> list = new ArrayList<>();
        int pageSize = 10;
        int offset = (page - 1) * pageSize;

        String sql = """
            SELECT
                id, name, description, price, discount, stock,
                sold, rating, category_id, brand_id,
                collection_id, event_id
            FROM products
            WHERE status = 1
            ORDER BY created_at DESC
            OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, offset);
            ps.setInt(2, pageSize);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    /* ================= ADMIN: UPDATE QUANTITY ================= */
    public boolean updateQtt(String productId, String qtt) {

        String sql = "UPDATE products SET stock = stock + ? WHERE id = ?";

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, Integer.parseInt(qtt));
            ps.setInt(2, Integer.parseInt(productId));
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /* ================= ADMIN: COUNT PRODUCT ================= */
    public int countProduct() {

        String sql = "SELECT COUNT(*) FROM products WHERE status = 1";

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /* ================= MAP PRODUCT ================= */
    private Product mapProduct(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setDescription(rs.getString("description"));
        p.setPrice(rs.getBigDecimal("price"));
        p.setDiscount(rs.getInt("discount"));
        p.setStock(rs.getInt("stock"));
        p.setSold(rs.getInt("sold"));
        p.setRating(rs.getDouble("rating"));
        p.setCategoryId(rs.getInt("category_id"));
        p.setBrandId(rs.getInt("brand_id"));
        p.setCollectionId(rs.getInt("collection_id"));
        p.setEventId(rs.getInt("event_id"));
        return p;
    }
}
