package web.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.java.model.Event;
import web.java.model.Product;
import web.java.utils.ConnectDB;

public class EventDAO {

    /* ================= GET ALL EVENTS ================= */
    public List<Event> getAllEvents() {
        List<Event> list = new ArrayList<>();

        String sql = """
            SELECT id, name, description, icon,
                   start_date, end_date, status, created_at
            FROM events
            ORDER BY created_at DESC
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(mapEvent(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= GET EVENT BY ID ================= */
    public Event getEventById(int eventId) {
        String sql = """
            SELECT id, name, description, icon,
                   start_date, end_date, status, created_at
            FROM events
            WHERE id = ?
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapEvent(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* ================= GET PRODUCTS BY EVENT ================= */
    public List<Product> getProductsByEventId(int eventId) {
        List<Product> list = new ArrayList<>();

        String sql = """
            SELECT
                p.id, p.name, p.description, p.price, p.discount,
                p.stock, p.sold, p.rating,
                p.category_id, p.brand_id,
                p.collection_id, ep.event_id
            FROM products p
            JOIN event_products ep ON p.id = ep.product_id
            WHERE ep.event_id = ?
              AND p.status = 1
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapProduct(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= GET PRODUCTS IN ACTIVE EVENTS ================= */
    public List<Product> getProductsInActiveEvents(int limit) {
        List<Product> list = new ArrayList<>();

        String sql = """
            SELECT DISTINCT
                p.id, p.name, p.description, p.price, p.discount,
                p.stock, p.sold, p.rating,
                p.category_id, p.brand_id,
                p.collection_id, ep.event_id
            FROM products p
            JOIN event_products ep ON p.id = ep.product_id
            JOIN events e ON ep.event_id = e.id
            WHERE e.status = 1
              AND e.start_date <= GETDATE()
              AND e.end_date >= GETDATE()
              AND p.status = 1
            ORDER BY e.start_date DESC
            OFFSET 0 ROWS FETCH NEXT ? ROWS ONLY
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapProduct(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= ADD PRODUCT TO EVENT ================= */
    public boolean addProductToEvent(int eventId, int productId) {
        String sql = "INSERT INTO event_products(event_id, product_id) VALUES (?, ?)";

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, eventId);
            ps.setInt(2, productId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= REMOVE PRODUCT FROM EVENT ================= */
    public boolean removeProductFromEvent(int eventId, int productId) {
        String sql = "DELETE FROM event_products WHERE event_id = ? AND product_id = ?";

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, eventId);
            ps.setInt(2, productId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= MAP EVENT ================= */
    private Event mapEvent(ResultSet rs) throws SQLException {
        return new Event(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getString("icon"),
            rs.getTimestamp("start_date") != null
                ? rs.getTimestamp("start_date").toLocalDateTime()
                : null,
            rs.getTimestamp("end_date") != null
                ? rs.getTimestamp("end_date").toLocalDateTime()
                : null,
            rs.getBoolean("status"),
            rs.getTimestamp("created_at").toLocalDateTime()
        );
    }
//
    public List<Product> getProductInEventRan(int eventId) {
        List<Product> list = new ArrayList<>();

        String sql = """
            SELECT TOP 8
                p.id, p.name, p.description, p.price, p.discount,
                p.stock, p.sold, p.rating,
                p.category_id, p.brand_id,
                p.collection_id, ep.event_id
            FROM products p
            JOIN event_products ep ON p.id = ep.product_id
            WHERE ep.event_id = ?
              AND p.status = 1
            ORDER BY NEWID()
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    //
    public List<Product> getProductsNotInEvent(int eventId) {
        List<Product> list = new ArrayList<>();

        String sql = """
            SELECT *
            FROM products
            WHERE id NOT IN (
                SELECT product_id
                FROM event_products
                WHERE event_id = ?
            )
            AND status = 1
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
