package com.example.view;

import com.example.entity.Appointment;
import com.example.entity.Package;
import com.example.service.AppointmentService;
import com.example.service.PackageService;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointmentFrame extends JFrame {
    // 组件定义
    private JComboBox<Package> cbxPackage; // 下拉选套餐
    private JTextField txtName;
    private JTextField txtPhone;
    private JTextField txtDate;
    private JButton btnSubmit;
    private JButton btnBack;

    // 业务层
    private PackageService packageService;
    private AppointmentService appointmentService;

    public AppointmentFrame() {
        // 初始化服务
        packageService = new PackageService();
        appointmentService = new AppointmentService();

        // 窗口基础设置
        setTitle("在线预约体检");
        setSize(600, 420);
        setLocationRelativeTo(null); // 窗口居中
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 组装界面
        initPanel();
        // 绑定按钮点击事件
        bindEvent();
        // 下拉框加载所有套餐
        loadPackageBox();
    }

    // 搭建页面布局
    private void initPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2, 15, 18));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        // 1 套餐下拉
        mainPanel.add(new JLabel("选择体检套餐："));
        cbxPackage = new JComboBox<>();
        mainPanel.add(cbxPackage);

        // 2 姓名
        mainPanel.add(new JLabel("预约人姓名："));
        txtName = new JTextField();
        mainPanel.add(txtName);

        // 3 手机号
        mainPanel.add(new JLabel("联系手机号："));
        txtPhone = new JTextField();
        mainPanel.add(txtPhone);

        // 4 预约日期（格式：2026-06-15）
        mainPanel.add(new JLabel("预约日期(yyyy-MM-dd)："));
        txtDate = new JTextField();
        mainPanel.add(txtDate);

        // 按钮行
        btnSubmit = new JButton("提交预约");
        btnBack = new JButton("返回主页");
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
        btnPanel.add(btnSubmit);
        btnPanel.add(btnBack);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);
    }

    // 下拉框加载数据库全部套餐
    private void loadPackageBox() {
        List<Package> pkgList = packageService.findAllPackage();
        for(Package p : pkgList){
            cbxPackage.addItem(p);
        }
    }

    // 绑定按钮事件
    private void bindEvent() {
        // 提交预约
        btnSubmit.addActionListener(e -> submitAppoint());

        // 返回主页
        btnBack.addActionListener(e -> {
            this.dispose(); // 关闭预约窗口
        });
    }

    // 提交预约逻辑
    private void submitAppoint() {
        Package selectPkg = (Package) cbxPackage.getSelectedItem();
        String name = txtName.getText().trim();
        String phone = txtPhone.getText().trim();
        String date = txtDate.getText().trim();

        if(name.isEmpty() || phone.isEmpty() || date.isEmpty()){
            JOptionPane.showMessageDialog(this, "姓名、手机号、预约日期不能为空！");
            return;
        }

        Appointment appoint = new Appointment();
        appoint.setPackageId(selectPkg.getPackageId());
        appoint.setUserName(name);
        appoint.setUserPhone(phone);
        appoint.setAppointDate(date);
        appoint.setStatus("待体检");

        boolean result = appointmentService.addAppointment(appoint);
        if(result){
            JOptionPane.showMessageDialog(this, "预约提交成功！");
            txtName.setText("");
            txtPhone.setText("");
            txtDate.setText("");
        }else{
            JOptionPane.showMessageDialog(this, "预约失败，请检查信息");
        }
    }
}