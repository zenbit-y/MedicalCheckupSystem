package com.example.view;

import com.example.entity.Appointment;
import com.example.service.AppointmentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AppointmentListFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private AppointmentService appointmentService = new AppointmentService();

    public AppointmentListFrame() {
        initUI();
        loadData();
    }

    // 初始化界面
    private void initUI() {
        setTitle("全部预约记录");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 表格表头
        String[] headers = {"预约ID", "套餐ID", "预约人姓名", "手机号", "预约日期", "状态"};
        tableModel = new DefaultTableModel(headers, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // 底部按钮：新增 编辑按钮
        JPanel btnPanel = new JPanel();
        JButton btnRefresh = new JButton("刷新数据");
        JButton btnEdit = new JButton("编辑选中预约");
        JButton btnDelete = new JButton("删除选中预约");
        JButton btnBack = new JButton("返回主页");

        btnRefresh.addActionListener(e -> loadData());
        btnEdit.addActionListener(e -> openEditFrame());
        btnDelete.addActionListener(e -> deleteSelectedRow());
        btnBack.addActionListener(e -> this.dispose());

        btnPanel.add(btnRefresh);
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
        btnPanel.add(btnBack);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);
    }

    // 加载数据到表格
    private void loadData() {
        tableModel.setRowCount(0);
        List<Appointment> list = appointmentService.listAllAppointment();
        for (Appointment apt : list) {
            Object[] row = {
                    apt.getAppointmentId(),
                    apt.getPackageId(),
                    apt.getUserName(),
                    apt.getUserPhone(),
                    apt.getAppointDate(),
                    apt.getStatus()
            };
            tableModel.addRow(row);
        }
    }

    // 删除选中记录
    private void deleteSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "请先选中一条记录！");
            return;
        }
        Integer appointId = (Integer) tableModel.getValueAt(selectedRow, 0);
        int choice = JOptionPane.showConfirmDialog(this, "确定删除该预约？", "删除确认", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            boolean res = appointmentService.deleteAppoint(appointId);
            if (res) {
                JOptionPane.showMessageDialog(this, "删除成功");
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "删除失败");
            }
        }
    }

    // 打开编辑窗口
    private void openEditFrame() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "请先选中一条要编辑的记录！");
            return;
        }
        // 获取当前行所有数据
        Integer appointId = (Integer) tableModel.getValueAt(selectedRow, 0);
        Integer pkgId = (Integer) tableModel.getValueAt(selectedRow, 1);
        String name = (String) tableModel.getValueAt(selectedRow, 2);
        String phone = (String) tableModel.getValueAt(selectedRow, 3);
        String date = (String) tableModel.getValueAt(selectedRow, 4);
        String status = (String) tableModel.getValueAt(selectedRow, 5);

        // 打开编辑窗口，并把原有数据传过去回填
        new AppointmentEditFrame(this, appointId, pkgId, name, phone, date, status).setVisible(true);
    }

    // 提供给编辑窗口调用：刷新表格
    public void refreshTable() {
        loadData();
    }
}