package com.example.dao;

import com.example.entity.User;
import com.example.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // 用户登录查询方法
    public User login(String username, String password) {
        User user = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 1. 获取数据库连接
            conn = DBUtil.getConnection();
            // 2. 编写SQL语句，? 是占位符，防止SQL注入
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            // 3. 执行查询
            rs = pstmt.executeQuery();

            // 4. 如果查到结果，封装成User对象
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. 关闭资源，避免占用
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return user;
    }
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        // 用你之前插入的 test 用户测试
        User user = dao.login("test", "123456");
        if (user != null) {
            System.out.println("登录成功：" + user.getUsername() + "，角色：" + user.getRole());
        } else {
            System.out.println("用户名或密码错误");
        }
    }
}