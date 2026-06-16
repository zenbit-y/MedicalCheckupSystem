package com.example.view;

import com.example.entity.User;
import com.example.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
/**
 * 登录界面
 */
public class LoginFrame extends JFrame {
    // 业务层对象
    private final UserService userService = new UserService();

    // 组件
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrame() {
        // 初始化窗口基础设置
        initFrame();
        // 初始化界面组件
        initComponent();
        // 绑定登录按钮事件
        bindEvent();
    }

    /**
     * 窗口基础配置
     */
    private void initFrame() {
        setTitle("体检系统 - 登录");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 窗口居中
        setResizable(false); // 禁止缩放
    }

    /**
     * 绘制页面输入框、按钮
     */
    private void initComponent() {
        // 主面板，网格布局 3行2列
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // 账号行
        panel.add(new JLabel("账号："));
        txtUsername = new JTextField();
        panel.add(txtUsername);

        // 密码行
        panel.add(new JLabel("密码："));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        // 登录按钮
        panel.add(new JLabel());
        btnLogin = new JButton("登录");
        btnLogin.setPreferredSize(new Dimension(100, 30));
        panel.add(btnLogin);

        this.add(panel);
    }

    /**
     * 绑定登录点击事件
     */
    private void bindEvent() {
        btnLogin.addActionListener((ActionEvent e) -> {
            // 1. 获取输入内容
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            // 2. 简单非空校验
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请输入账号");
                return;
            }
            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请输入密码");
                return;
            }

            // 3. 调用业务层登录
            User loginUser = userService.login(username, password);
            if (loginUser != null) {
                // 登录成功：关闭登录窗口，打开主界面
                JOptionPane.showMessageDialog(this, "登录成功，欢迎：" + loginUser.getUsername());
                this.dispose(); // 这行就是之前让你加的，已经写好了
                new UserMainFrame().setVisible(true);
            } else {
                // 登录失败
                JOptionPane.showMessageDialog(this, "账号或密码错误");
                // 清空输入框
                txtUsername.setText("");
                txtPassword.setText("");
            }
        });
    }

    /**
     * 程序入口，启动登录窗口
     */
    public static void main(String[] args) {
        // Swing规范：UI在线程中启动
        SwingUtilities.invokeLater(() -> {
            LoginFrame login = new LoginFrame();
            login.setVisible(true);
        });
    }
}