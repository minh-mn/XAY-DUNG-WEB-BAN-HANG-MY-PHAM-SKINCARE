package web.java.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import web.java.model.Brand;
import web.java.model.Product;
import web.java.utils.ConnectDB;

public class BrandDAO {

    /* ================= GET ALL ACTIVE BRANDS ================= */
    public List<Brand> getAllBrands() {
        List<Brand> list = new ArrayList<>();
        String sql = """
            SELECT id, name
            FROM brands
            WHERE status = 1
            ORDER BY name
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(mapBrand(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= GET BRAND BY ID ================= */
    public Brand getBrandById(int id) {
        String sql = """
            SELECT id, name
            FROM brands
            WHERE id = ? AND status = 1
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapBrand(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* ================= GET BRAND NAME ================= */
    public String getBrandNameById(int id) {
        String sql = "SELECT name FROM brands WHERE id = ? AND status = 1";
        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /* ================= TOP 5 PRODUCTS BY BRAND ================= */
    public List<Product> getTop5ProductsByBrand(int brandId) {
        List<Product> list = new ArrayList<>();
        String sql = """
            SELECT TOP 5
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

    /* ================= MAP BRAND ================= */
    private Brand mapBrand(ResultSet rs) throws SQLException {
        Brand b = new Brand();
        b.setId(rs.getInt("id"));
        b.setName(rs.getString("name"));
        return b;
    }

    /* ================= MAP PRODUCT (rút gọn) ================= */
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
