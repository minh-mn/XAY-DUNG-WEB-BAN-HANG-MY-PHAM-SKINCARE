package web.java.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import web.java.model.Category;
import web.java.utils.ConnectDB;

public class CategoryDAO {

    /* ================= GET ALL ACTIVE ================= */
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        String sql = """
            SELECT id, name, parent_id, collection_id
            FROM categories
            WHERE status = 1
            ORDER BY name
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(mapCategory(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= GET BY ID ================= */
    public Category getCategoryById(int id) {
        String sql = """
            SELECT id, name, parent_id, collection_id
            FROM categories
            WHERE id = ? AND status = 1
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapCategory(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* ================= GET NAME BY ID ================= */
    public String getCategoryNameById(int id) {
        String sql = "SELECT name FROM categories WHERE id = ? AND status = 1";
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

    /* ================= GET BY PARENT ================= */
    public List<Category> getByParentId(int parentId) {
        List<Category> list = new ArrayList<>();
        String sql = """
            SELECT id, name, parent_id, collection_id
            FROM categories
            WHERE parent_id = ? AND status = 1
            ORDER BY name
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, parentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapCategory(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= GET BY COLLECTION ================= */
    public List<Category> getByCollectionId(int collectionId) {
        List<Category> list = new ArrayList<>();
        String sql = """
            SELECT id, name, parent_id, collection_id
            FROM categories
            WHERE collection_id = ? AND status = 1
            ORDER BY name
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, collectionId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapCategory(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= TOP 6 CATEGORY ================= */
    public List<Category> getTop6() {
        List<Category> list = new ArrayList<>();
        String sql = """
            SELECT TOP 6 id, name, parent_id, collection_id
            FROM categories
            WHERE status = 1
            ORDER BY created_at DESC
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(mapCategory(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= GET MAKEUP CATEGORY ================= */
    public List<Category> getMakeupCate() {
        List<Category> list = new ArrayList<>();

        String sql = """
            SELECT id, name, parent_id, collection_id
            FROM categories
            WHERE status = 1
              AND name LIKE '%Makeup%'
            ORDER BY name
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(mapCategory(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= MAP RESULTSET ================= */
    private Category mapCategory(ResultSet rs) throws SQLException {
        Category c = new Category();
        c.setId(rs.getInt("id"));
        c.setName(rs.getString("name"));
        c.setParentId(rs.getInt("parent_id"));
        c.setCollectionId(rs.getInt("collection_id"));
        return c;
    }
}
