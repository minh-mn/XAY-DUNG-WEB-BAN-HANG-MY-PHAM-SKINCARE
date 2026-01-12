package web.java.test;

import java.sql.Connection;
import web.java.utils.ConnectDB;

public class TestDB {
    public static void main(String[] args) {
        Connection conn = ConnectDB.getConnection();
        if (conn != null) {
            System.out.println("CONNECT SQL SERVER SUCCESS");
        } else {
            System.out.println("CONNECT FAILED");
        }
    }
}
