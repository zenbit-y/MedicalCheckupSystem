package com.example.entity;

public class User {
    // 对应user表的字段
    private int userId;
    private String username;
    private String password;
    private String phone;
    private String role;

    // 无参构造方法
    public User() {
    }

    // 带参构造方法（可选，方便创建对象）
    public User(int userId, String username, String password, String phone, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    // Getter和Setter方法
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // toString方法（可选，调试用）
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

