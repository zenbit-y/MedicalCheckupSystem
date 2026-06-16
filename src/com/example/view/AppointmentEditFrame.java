package com.example.view;

import com.example.entity.Appointment;
import com.example.service.AppointmentService;

import javax.swing.*;
import java.awt.*;

public class AppointmentEditFrame extends JFrame {
    // 组件
    private JTextField txtPackageId, txtUserName, txtPhone, txtDate, txtStatus;
    private AppointmentService service = new AppointmentService();
    private AppointmentListFrame parentFrame;
    private Integer appointId;

    public AppointmentEditFrame(AppointmentListFrame parent, Integer id, Integer pkgId,
                                String name, String phone, String date, String status) {
        this.parentFrame = parent;
        this.appointId = id;
        initUI();
        // 回填原有数据
        txtPackageId.setText(pkgId + "");
        txtUserName.setText(name);
        txtPhone.setText(phone);
        txtDate.setText(date);
        txtStatus.setText(status);
    }

    private void initUI() {
        setTitle("编辑预约信息");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        // 初始化输入框
        add(new JLabel("套餐ID："));
        txtPackageId = new JTextField();
        add(txtPackageId);

        add(new JLabel("预约人姓名："));
        txtUserName = new JTextField();
        add(txtUserName);

        add(new JLabel("手机号："));
        txtPhone = new JTextField();
        add(txtPhone);

        add(new JLabel("预约日期："));
        txtDate = new JTextField();
        add(txtDate);

        add(new JLabel("预约状态："));
        txtStatus = new JTextField();
        add(txtStatus);

        // 按钮
        JButton btnSave = new JButton("保存修改");
        JButton btnCancel = new JButton("取消");
        add(btnSave);
        add(btnCancel);

        // 保存事件
        btnSave.addActionListener(e -> saveData());
        // 取消事件
        btnCancel.addActionListener(e -> this.dispose());
    }

    // 保存修改，更新数据库
    private void saveData() {
        String pkgIdStr = txtPackageId.getText().trim();
        String name = txtUserName.getText().trim();
        String phone = txtPhone.getText().trim();
        String date = txtDate.getText().trim();
        String status = txtStatus.getText().trim();

        // 简单非空校验
        if (pkgIdStr.isEmpty() || name.isEmpty() || phone.isEmpty() || date.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(this, "所有内容不能为空！");
            return;
        }

        Integer packageId;
        try {
            packageId = Integer.parseInt(pkgIdStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "套餐ID必须为数字！");
            return;
        }

        // 封装对象
        Appointment apt = new Appointment();
        apt.setAppointmentId(appointId);
        apt.setPackageId(packageId);
        apt.setUserName(name);
        apt.setUserPhone(phone);
        apt.setAppointDate(date);
        apt.setStatus(status);

        // 调用更新方法
        boolean result = service.updateAppoint(apt);
        if (result) {
            JOptionPane.showMessageDialog(this, "修改成功！");
            // 刷新上级列表窗口
            parentFrame.refreshTable();
            // 关闭当前编辑窗口
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "修改失败！");
        }
    }
}
