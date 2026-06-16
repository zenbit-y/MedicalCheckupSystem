package com.example.dao;

import com.example.entity.Appointment;
import com.example.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class AppointmentDAO {

    // 新增预约
    public boolean add(Appointment appoint) {
        String sql = "INSERT INTO appointment(package_id,user_name,user_phone,appoint_date,status) VALUES (?,?,?,?,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, appoint.getPackageId());
            pstmt.setString(2, appoint.getUserName());
            pstmt.setString(3, appoint.getUserPhone());
            pstmt.setString(4, appoint.getAppointDate());
            pstmt.setString(5, appoint.getStatus());
            int rows = pstmt.executeUpdate();
            System.out.println("插入行数：" + rows);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 修改预约
    public boolean update(Appointment apt) {
        String sql = "UPDATE appointment SET package_id=?,user_name=?,user_phone=?,appoint_date=?,status=? WHERE appointment_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, apt.getPackageId());
            pstmt.setString(2, apt.getUserName());
            pstmt.setString(3, apt.getUserPhone());
            pstmt.setString(4, apt.getAppointDate());
            pstmt.setString(5, apt.getStatus());
            pstmt.setInt(6, apt.getAppointmentId());
            int row = pstmt.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 根据ID删除预约
    public boolean deleteById(Integer appointId) {
        String sql = "DELETE FROM appointment WHERE appointment_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, appointId);
            int row = pstmt.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // 查询所有预约记录
    public List<Appointment> listAll() {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointment";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Appointment apt = new Appointment();
                apt.setAppointmentId(rs.getInt("appointment_id"));
                apt.setPackageId(rs.getInt("package_id"));
                apt.setUserName(rs.getString("user_name"));
                apt.setUserPhone(rs.getString("user_phone"));
                apt.setAppointDate(rs.getString("appoint_date"));
                apt.setStatus(rs.getString("status"));
                list.add(apt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}