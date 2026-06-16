package com.example.view;

import com.example.entity.Appointment;
import com.example.service.AppointmentService;
import com.example.service.PackageService;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List; // 新增：导入List集合

public class UserMainFrame extends JFrame {
    // 页面按钮定义
    private JButton btnPackage;
    private JButton btnAppoint;
    private JButton btnReport;
    private JButton btnExit;
    private JButton btnViewAllAppoint;

    public UserMainFrame() {
        // 1. 窗口基础配置
        setTitle("体检系统-用户主页");
        setSize(900, 650);
        setLocationRelativeTo(null); // 窗口居中
        setResizable(false); // 禁止拉伸窗口
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 2. 创建主面板，设置边距留白
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        Border emptyBorder = BorderFactory.createEmptyBorder(80, 50, 60, 50);
        mainPanel.setBorder(emptyBorder);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(25, 25, 25, 25); // 组件间距

        // 3. 标题
        JLabel titleLabel = new JLabel("欢迎使用体检管理系统");
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 28));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(titleLabel, gbc);
        // 重置网格宽度
        gbc.gridwidth = 1;

        // 第一行按钮
        btnPackage = new JButton("体检套餐管理");
        setBtnStyle(btnPackage);
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(btnPackage, gbc);

        btnAppoint = new JButton("在线预约体检");
        setBtnStyle(btnAppoint);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(btnAppoint, gbc);

        // 第二行按钮
        btnReport = new JButton("查看体检报告");
        setBtnStyle(btnReport);
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(btnReport, gbc);

        btnExit = new JButton("退出登录");
        setBtnStyle(btnExit);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(btnExit, gbc);

        // 新增：第三行 - 查看全部预约按钮
        btnViewAllAppoint = new JButton("查看全部预约");
        setBtnStyle(btnViewAllAppoint);
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(btnViewAllAppoint, gbc);

        // 底部版权文字
        JLabel bottomTip = new JLabel("© 2026 体检管理系统 版权所有");
        bottomTip.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        bottomTip.setForeground(Color.GRAY);
        gbc.gridwidth = 2;
        gbc.gridy = 4;
        mainPanel.add(bottomTip, gbc);

        // 把面板加入窗口
        this.add(mainPanel);
        // 绑定所有按钮点击事件
        bindButtonEvent();
    }

    // 统一按钮样式方法
    private void setBtnStyle(JButton btn) {
        btn.setPreferredSize(new Dimension(190, 55));
        btn.setFont(new Font("微软雅黑", Font.PLAIN, 16));
    }

    // 按钮点击事件
    private void bindButtonEvent() {
        // 1. 体检套餐管理
        btnPackage.addActionListener((ActionEvent e) -> {
            new PackageFrame().setVisible(true);
        });

        // 2. 在线预约体检
        btnAppoint.addActionListener((ActionEvent e) -> {
            new AppointmentFrame().setVisible(true);
        });

        // 3. 查看体检报告
        btnReport.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(this, "报告页面待开发");
        });

        // 4. 退出登录
        btnExit.addActionListener((ActionEvent e) -> {
            this.dispose();
            new LoginFrame().setVisible(true);
        });

        // 5. 查看全部预约：跳转【预约列表窗口】（和我们之前创建的 AppointmentListFrame 对应）
        btnViewAllAppoint.addActionListener((ActionEvent e) -> {
            new AppointmentListFrame().setVisible(true);
        });
    }
}