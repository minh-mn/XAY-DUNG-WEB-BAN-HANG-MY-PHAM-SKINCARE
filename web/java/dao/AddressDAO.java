package web.java.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import web.java.model.Address;
import web.java.utils.ConnectDB;

public class AddressDAO {

    /* ================= ADD ADDRESS ================= */
    public boolean addAddress(Address a) {
        String sql = """
            INSERT INTO addresses(user_id, address, is_default)
            VALUES (?, ?, ?)
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, a.getUserId());
            ps.setString(2, a.getAddress());
            ps.setBoolean(3, a.isDefault());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= GET ADDRESSES BY USER ================= */
    public List<Address> getAddressesByUser(int userId) {
        List<Address> list = new ArrayList<>();
        String sql = """
            SELECT *
            FROM addresses
            WHERE user_id = ?
            ORDER BY is_default DESC, created_at DESC
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapAddress(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= GET DEFAULT ADDRESS ================= */
    public Address getDefaultAddress(int userId) {
        String sql = """
            SELECT TOP 1 *
            FROM addresses
            WHERE user_id = ? AND is_default = 1
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapAddress(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* ================= SET DEFAULT ADDRESS ================= */
    public boolean setDefaultAddress(int addressId, int userId) {
        String resetSql = "UPDATE addresses SET is_default = 0 WHERE user_id = ?";
        String setSql   = "UPDATE addresses SET is_default = 1 WHERE id = ?";

        try (Connection conn = ConnectDB.getConnection()) {
            conn.setAutoCommit(false);

            try (
                PreparedStatement ps1 = conn.prepareStatement(resetSql);
                PreparedStatement ps2 = conn.prepareStatement(setSql)
            ) {
                ps1.setInt(1, userId);
                ps1.executeUpdate();

                ps2.setInt(1, addressId);
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

    /* ================= UPDATE ADDRESS ================= */
    public boolean updateAddress(Address a) {
        String sql = "UPDATE addresses SET address = ? WHERE id = ?";

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, a.getAddress());
            ps.setInt(2, a.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= DELETE ADDRESS ================= */
    public boolean deleteAddress(int id) {
        String sql = "DELETE FROM addresses WHERE id = ?";

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
    private Address mapAddress(ResultSet rs) throws SQLException {
        Address a = new Address();
        a.setId(rs.getInt("id"));
        a.setUserId(rs.getInt("user_id"));
        a.setAddress(rs.getString("address"));
        a.setDefault(rs.getBoolean("is_default"));

        Timestamp ts = rs.getTimestamp("created_at");
        if (ts != null) {
            a.setCreatedAt(ts.toLocalDateTime());
        }
        return a;
    }
}
