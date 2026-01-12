package web.java.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import web.java.model.User;
import web.java.utils.ConnectDB;

public class UserDAO {

    /* ================= ENCODE PASSWORD ================= */
    private String encodePassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    /* ================= GET ALL USERS ================= */
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(mapUser(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= GET USER BY ID ================= */
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapUser(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* ================= LOGIN ================= */
    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND status = 1";

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, username);
            ps.setString(2, encodePassword(password));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // update last login
                updateLastLogin(rs.getInt("id"));
                return mapUser(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* ================= SIGN UP ================= */
    public boolean signUp(User u) {
        String sql = """
            INSERT INTO users
            (username, password, email, fullname, phone, avatar, role, status)
            VALUES (?, ?, ?, ?, ?, ?, 0, 1)
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, u.getUsername());
            ps.setString(2, encodePassword(u.getPassword()));
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getFullname());
            ps.setString(5, u.getPhone());
            ps.setString(6, u.getAvatar());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= UPDATE USER (NO PASSWORD) ================= */
    public boolean updateUser(User u) {
        String sql = """
            UPDATE users
            SET email=?, fullname=?, phone=?, avatar=?, gender=?, date_of_birth=?, role=?, status=?
            WHERE id=?
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getFullname());
            ps.setString(3, u.getPhone());
            ps.setString(4, u.getAvatar());
            ps.setString(5, u.getGender());
            ps.setDate(6, u.getDateOfBirth());
            ps.setInt(7, u.getRole());
            ps.setBoolean(8, u.isStatus());
            ps.setInt(9, u.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= UPDATE PASSWORD ================= */
    public boolean updatePassword(int userId, String newPassword) {
        String sql = "UPDATE users SET password=? WHERE id=?";

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, encodePassword(newPassword));
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // KHÔNG MK
    public boolean editUserNotPass(
            String id,
            String email,
            String fullname,
            String phone,
            String avatar
    ) {
        String sql = """
            UPDATE users
            SET email = ?, fullname = ?, phone = ?, avatar = ?
            WHERE id = ?
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, email);
            ps.setString(2, fullname);
            ps.setString(3, phone);
            ps.setString(4, avatar);
            ps.setInt(5, Integer.parseInt(id));

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //CÓ MK
    public boolean editUserHavePass(
            String id,
            String password,
            String email,
            String fullname,
            String phone,
            String avatar
    ) {
        String sql = """
            UPDATE users
            SET password = ?, email = ?, fullname = ?, phone = ?, avatar = ?
            WHERE id = ?
        """;

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, password);
            ps.setString(2, email);
            ps.setString(3, fullname);
            ps.setString(4, phone);
            ps.setString(5, avatar);
            ps.setInt(6, Integer.parseInt(id));

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /* ================= DELETE USER ================= */
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
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

    /* ================= UPDATE LAST LOGIN ================= */
    private void updateLastLogin(int userId) {
        String sql = "UPDATE users SET last_login = GETDATE() WHERE id = ?";

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /* ================= GET USER BY USERNAME ================= */
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapUser(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* ================= SIGN UP (STRING PARAMS) ================= */
    public boolean signUp(
            String username,
            String password,
            String email,
            String fullname
    ) {
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        u.setEmail(email);
        u.setFullname(fullname);
        u.setRole(0);      // user
        u.setStatus(true);

        return signUp(u);  // dùng lại logic cũ
    }

    
    /* ================= ADMIN: COUNT USER ================= */
    public int countUser() {

        String sql = "SELECT COUNT(*) FROM users";

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
    /* ================= ADMIN: GET USER BY PAGE ================= */
    public List<User> getAllUserInPage(int page) {

        List<User> list = new ArrayList<>();
        int pageSize = 10;
        int offset = (page - 1) * pageSize;

        String sql = """
            SELECT *
            FROM users
            ORDER BY id DESC
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
                list.add(mapUser(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= MAP RESULTSET ================= */
    private User mapUser(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setEmail(rs.getString("email"));
        u.setFullname(rs.getString("fullname"));
        u.setPhone(rs.getString("phone"));
        u.setAvatar(rs.getString("avatar"));
        u.setRole(rs.getInt("role"));
        u.setGender(rs.getString("gender"));
        u.setDateOfBirth(rs.getDate("date_of_birth"));
        u.setStatus(rs.getBoolean("status"));
        u.setLastLogin(rs.getTimestamp("last_login"));
        return u;
    }
}
