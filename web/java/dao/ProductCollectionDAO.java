package web.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import web.java.model.ProductCollection;
import web.java.utils.ConnectDB;

public class ProductCollectionDAO {

    /* ================= GET ALL ACTIVE COLLECTIONS ================= */
    public List<ProductCollection> getAllCollections() {
        List<ProductCollection> list = new ArrayList<>();
        String sql = """
            SELECT id, name, description, status, created_at
            FROM product_collections
            WHERE status = 1
            ORDER BY id
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                ProductCollection pc = new ProductCollection(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getBoolean("status"),
                    rs.getTimestamp("created_at").toLocalDateTime()
                );
                list.add(pc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= GET BY ID ================= */
    public ProductCollection getById(int id) {
        String sql = """
            SELECT id, name, description, status, created_at
            FROM product_collections
            WHERE id = ?
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ProductCollection(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getBoolean("status"),
                    rs.getTimestamp("created_at").toLocalDateTime()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* ================= GET NAME BY ID ================= */
    public String getNameById(int id) {
        String sql = "SELECT name FROM product_collections WHERE id = ?";
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
}
