package com.example.service;

// 完整正确导包
import com.example.entity.User;
import com.example.dao.UserDAO;

/**
 * 普通用户业务层
 */
public class UserService {
    // 依赖DAO对象
    private UserDAO userDao = new UserDAO();

    /**
     * 用户登录校验
     * @param username 账号
     * @param password 密码
     * @return 登录成功返回用户对象，失败返回null
     */
    public User login(String username, String password) {
        // 简单业务校验：账号密码非空
        if (username == null || username.trim().length() == 0) {
            return null;
        }
        if (password == null || password.trim().length() == 0) {
            return null;
        }
        // 调用DAO查询数据库
        return userDao.login(username, password);
    }

    /**
     * 用户注册
     * @param username 账号
     * @param password 密码
     * @param phone 手机号
     * @return true注册成功，false失败(账号重复)
     */
    public boolean register(String username, String password, String phone) {
        // 业务校验：账号长度至少3位
        if (username == null || username.length() < 3) {
            return false;
        }
        // 后续注册逻辑
        return false;
    }
}
