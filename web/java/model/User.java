package web.java.model;

import java.sql.Date;
import java.sql.Timestamp;

public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private String fullname;
    private String phone;
    private String avatar;

    private String gender;
    private Date dateOfBirth;

    private int role;        // 0: USER, 1: ADMIN
    private boolean status;  // true: active | false: locked
    private Timestamp lastLogin;
    private Timestamp createdAt;

    public User() {
    }

    /* ===== Constructor cơ bản ===== */
    public User(int id, String username, String email, String fullname, int role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullname = fullname;
        this.role = role;
    }

    /* ===== Constructor đầy đủ ===== */
    public User(int id, String username, String password, String email,
                String fullname, String phone, String avatar,
                String gender, Date dateOfBirth,
                int role, boolean status,
                Timestamp lastLogin, Timestamp createdAt) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.avatar = avatar;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.status = status;
        this.lastLogin = lastLogin;
        this.createdAt = createdAt;
    }

    /* ===== Getter / Setter ===== */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}
