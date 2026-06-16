package com.example.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    // 完整URL，带时区参数
    private static final String URL = "jdbc:mysql://localhost:3306/medical_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    // 这里改成你自己的MySQL密码！
    private static final String PASSWORD = "R232710";

    // 静态代码块，加载驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("找不到JDBC驱动，请检查lib包是否正确添加");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 测试方法，运行一下看能不能连上
    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                System.out.println("✅ 数据库连接成功！");
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ 数据库连接失败！");
        }
    }
}
