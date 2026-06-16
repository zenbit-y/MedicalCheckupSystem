package com.example.view;

import com.example.dao.PackageDAO;
import com.example.entity.Package;
import com.example.service.PackageService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;
import java.awt.event.ActionEvent;

public class PackageFrame extends JFrame {
    private PackageService packageService;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnUpdate, btnDelete, btnRefresh;

    public PackageFrame() {
        packageService = new PackageService();
        // 窗口基础配置
        setTitle("体检套餐管理");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 顶部按钮面板
        JPanel btnPanel = new JPanel();
        btnAdd = new JButton("新增套餐");
        btnUpdate = new JButton("修改套餐");
        btnDelete = new JButton("删除套餐");
        btnRefresh = new JButton("刷新列表");
        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnRefresh);

        // 表格
        String[] columnNames = {"套餐ID", "套餐名称", "价格", "套餐描述"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 表格单元格不可直接编辑
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // 整体布局
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(btnPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        this.add(mainPanel);

        // 加载套餐数据
        loadPackageData();
        // 绑定按钮事件
        bindEvent();
        refreshTable();
    }

    // 从数据库加载套餐到表格
    private void loadPackageData() {
        // 清空旧数据
        tableModel.setRowCount(0);
        List<Package> list = packageService.findAllPackage();
        for (Package pkg : list) {
            Object[] row = {
                    pkg.getPackageId(),
                    pkg.getName(),
                    pkg.getPrice(),
                    pkg.getDescription()
            };
            tableModel.addRow(row);
        }
    }
    private void refreshTable() {
        // 清空表格旧数据
        tableModel.setRowCount(0);
        // 查询全部套餐
        List<Package> list = packageService.findAllPackage();
        // 循环添加到表格
        for(Package p : list){
            Object[] row = {
                    p.getPackageId(),
                    p.getName(),
                    p.getPrice(),
                    p.getDescription()
            };
            tableModel.addRow(row);
        }
    }
    private void openAddDialog() {
        JTextField txtName = new JTextField();
        JTextField txtPrice = new JTextField();
        JTextField txtDesc = new JTextField();
        Object[] msg = {
                "套餐名称：", txtName,
                "套餐价格：", txtPrice,
                "套餐描述：", txtDesc
        };
        int opt = JOptionPane.showConfirmDialog(this, msg, "新增套餐", JOptionPane.OK_CANCEL_OPTION);
        if(opt == JOptionPane.OK_OPTION){
            String name = txtName.getText().trim();
            String priceText = txtPrice.getText().trim();
            String desc = txtDesc.getText().trim();
            // 非空校验
            if(name.isEmpty() || priceText.isEmpty()){
                JOptionPane.showMessageDialog(this, "名称和价格不能为空！");
                return;
            }
            Package pkg = new Package();
            pkg.setName(name);
            pkg.setPrice(new BigDecimal(priceText));
            pkg.setDescription(desc);
            boolean res = packageService.addPackage(pkg);
            if(res){
                JOptionPane.showMessageDialog(this, "新增成功");
                refreshTable();
            }else{
                JOptionPane.showMessageDialog(this, "新增失败");
            }
        }
    }
    private void openUpdateDialog(Package oldPkg) {
        JTextField txtName = new JTextField(oldPkg.getName());
        JTextField txtPrice = new JTextField(oldPkg.getPrice().toString());
        JTextField txtDesc = new JTextField(oldPkg.getDescription());
        Object[] msg = {
                "套餐名称：", txtName,
                "套餐价格：", txtPrice,
                "套餐描述：", txtDesc
        };
        int opt = JOptionPane.showConfirmDialog(this, msg, "修改套餐", JOptionPane.OK_CANCEL_OPTION);
        if(opt == JOptionPane.OK_OPTION){
            String name = txtName.getText().trim();
            String priceText = txtPrice.getText().trim();
            String desc = txtDesc.getText().trim();
            if(name.isEmpty() || priceText.isEmpty()){
                JOptionPane.showMessageDialog(this, "名称和价格不能为空！");
                return;
            }
            oldPkg.setName(name);
            oldPkg.setPrice(new BigDecimal(priceText));
            oldPkg.setDescription(desc);
            boolean res = packageService.updatePackage(oldPkg);
            if(res){
                JOptionPane.showMessageDialog(this, "修改成功");
                refreshTable();
            }else{
                JOptionPane.showMessageDialog(this, "修改失败");
            }
        }
    }


    // 绑定按钮点击事件
    private void bindEvent() {
        // 刷新
        btnRefresh.addActionListener(e -> loadPackageData());

        // 新增套餐
        btnAdd.addActionListener(e -> openAddDialog());

        // 修改套餐
        btnUpdate.addActionListener(e -> {
            int selectRow = table.getSelectedRow();
            if (selectRow == -1) {
                JOptionPane.showMessageDialog(this, "请先选中一行套餐数据！");
                return;
            }
            Integer id = (Integer) tableModel.getValueAt(selectRow, 0);
            Package pkg = packageService.getPackageById(id);
            openUpdateDialog(pkg);
        });

        btnDelete.addActionListener((ActionEvent e) -> {
            int selectRow = table.getSelectedRow();
            if (selectRow == -1) {
                JOptionPane.showMessageDialog(this, "请先选中一行套餐数据！");
                return;
            }
            Integer id = (Integer) tableModel.getValueAt(selectRow, 0);
            int opt = JOptionPane.showConfirmDialog(this, "确定要删除该套餐吗？", "提示", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                boolean del = packageService.deletePackage(id);
                if(del){
                    JOptionPane.showMessageDialog(this, "删除成功");
                    refreshTable();
                }else{
                    JOptionPane.showMessageDialog(this, "删除失败");
                }
            }
        });
        // 刷新列表按钮
        btnRefresh.addActionListener((ActionEvent e) -> {
            refreshTable();
        });
    }


}