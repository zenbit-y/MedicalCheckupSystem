package com.example.dao;

import com.example.entity.Report;
import com.example.utils.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    /**
     * 查询全部报告
     */
    public List<Report> selectAll() {
        List<Report> reportList = new ArrayList<>();
        String sql = "SELECT * FROM report";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Report report = new Report();
                report.setReportId(rs.getInt("report_id"));
                report.setAppointmentId(rs.getInt("appointment_id"));
                report.setPatientName(rs.getString("patient_name"));
                report.setResultContent(rs.getString("result_content"));
                report.setCreateTime(rs.getDate("create_time"));
                reportList.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportList;
    }

    /**
     * 根据ID单查报告
     */
    public Report selectById(Integer reportId) {
        Report report = null;
        String sql = "SELECT * FROM report WHERE report_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reportId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                report = new Report();
                report.setReportId(rs.getInt("report_id"));
                report.setAppointmentId(rs.getInt("appointment_id"));
                report.setPatientName(rs.getString("patient_name"));
                report.setResultContent(rs.getString("result_content"));
                report.setCreateTime(rs.getDate("create_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    }

    /**
     * 新增报告
     */
    public boolean insert(Report report) {
        String sql = "INSERT INTO report(appointment_id, patient_name, result_content, create_time) VALUES (?,?,?,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, report.getAppointmentId());
            pstmt.setString(2, report.getPatientName());
            pstmt.setString(3, report.getResultContent());
            pstmt.setDate(4, new Date(System.currentTimeMillis()));
            int row = pstmt.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改报告
     */
    public boolean update(Report report) {
        String sql = "UPDATE report SET appointment_id=?, patient_name=?, result_content=? WHERE report_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, report.getAppointmentId());
            pstmt.setString(2, report.getPatientName());
            pstmt.setString(3, report.getResultContent());
            pstmt.setInt(4, report.getReportId());
            int row = pstmt.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据ID删除报告
     */
    public boolean deleteById(Integer reportId) {
        String sql = "DELETE FROM report WHERE report_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reportId);
            int row = pstmt.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
