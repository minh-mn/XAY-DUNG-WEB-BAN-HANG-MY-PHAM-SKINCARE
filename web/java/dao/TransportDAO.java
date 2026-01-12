package web.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.java.model.Transport;
import web.java.utils.ConnectDB;

public class TransportDAO {

    /* ================= GET ALL ACTIVE TRANSPORTS ================= */
    public List<Transport> getAllTransports() {
        List<Transport> list = new ArrayList<>();

        String sql = """
            SELECT id, name, fee, status, created_at
            FROM transports
            WHERE status = 1
            ORDER BY id
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(mapTransport(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= GET TRANSPORT BY ID ================= */
    public Transport getTransportById(int id) {
        String sql = """
            SELECT id, name, fee, status, created_at
            FROM transports
            WHERE id = ?
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapTransport(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* ================= GET TRANSPORT NAME ================= */
    public String getTransportNameById(int id) {
        String sql = "SELECT name FROM transports WHERE id = ?";

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /* ================= GET TRANSPORT FEE ================= */
    public double getTransportFeeById(int id) {
        String sql = "SELECT fee FROM transports WHERE id = ?";

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("fee");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /* ================= GET DEFAULT TRANSPORT ================= */
    public Transport getDefaultTransport() {
        String sql = """
            SELECT TOP 1 id, name, fee, status, created_at
            FROM transports
            WHERE status = 1
            ORDER BY id
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) {
                return mapTransport(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* ================= MAP RESULTSET ================= */
    private Transport mapTransport(ResultSet rs) throws SQLException {
        Transport t = new Transport();
        t.setId(rs.getInt("id"));
        t.setName(rs.getString("name"));
        t.setFee(rs.getBigDecimal("fee"));
        t.setStatus(rs.getBoolean("status"));

        if (rs.getTimestamp("created_at") != null) {
            t.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        }
        return t;
    }
}
