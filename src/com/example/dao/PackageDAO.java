package com.example.dao;

import com.example.entity.Package;
import com.example.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class PackageDAO {

    // 1. 查询所有套餐
    public List<Package> getAllPackages() {
        List<Package> packageList = new ArrayList<>();
        String sql = "SELECT * FROM package";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Package pkg = new Package();
                pkg.setPackageId(rs.getInt("package_id"));
                pkg.setName(rs.getString("name"));
                pkg.setPrice(rs.getBigDecimal("price"));
                pkg.setDescription(rs.getString("description"));
                packageList.add(pkg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packageList;
    }

    // 2. 根据ID查询单个套餐
    public Package getPackageById(int packageId) {
        String sql = "SELECT * FROM package WHERE package_id = ?";
        Package pkg = null;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, packageId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                pkg = new Package();
                pkg.setPackageId(rs.getInt("package_id"));
                pkg.setName(rs.getString("name"));
                pkg.setPrice(rs.getBigDecimal("price"));
                pkg.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pkg;
    }

    // 3. 新增套餐
    public boolean addPackage(Package pkg) {
        String sql = "INSERT INTO package(name, price, description) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pkg.getName());
            pstmt.setBigDecimal(2, pkg.getPrice());
            pstmt.setString(3, pkg.getDescription());
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. 修改套餐信息
    public boolean updatePackage(Package pkg) {
        String sql = "UPDATE package SET name=?, price=?, description=? WHERE package_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pkg.getName());
            pstmt.setBigDecimal(2, pkg.getPrice());
            pstmt.setString(3, pkg.getDescription());
            pstmt.setInt(4, pkg.getPackageId());
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 5. 根据ID删除套餐
    public boolean deletePackageById(Integer packageId) {
        if (packageId == null || packageId <= 0) {
            return false;
        }
        String sql = "DELETE FROM package WHERE package_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, packageId);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}