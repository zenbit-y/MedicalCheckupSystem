package com.example.service;

import com.example.dao.ReportDAO;
import com.example.entity.Report;
import java.util.List;

public class ReportService {
    // 实例化DAO，消除变量找不到报错
    private final ReportDAO reportDao = new ReportDAO();

    /**
     * 查询所有体检报告
     */
    public List<Report> findAllReport() {
        return reportDao.selectAll();
    }

    /**
     * 根据id查询单条报告
     */
    public Report getReportById(Integer reportId) {
        // 参数校验
        if (reportId == null || reportId <= 0) {
            return null;
        }
        return reportDao.selectById(reportId);
    }

    /**
     * 新增体检报告
     */
    public boolean addReport(Report report) {
        // 简单业务校验
        if (report.getPatientName() == null || report.getPatientName().trim().isEmpty()) {
            return false;
        }
        if (report.getAppointmentId() == null || report.getAppointmentId() <= 0) {
            return false;
        }
        return reportDao.insert(report);
    }

    /**
     * 修改体检报告
     */
    public boolean updateReport(Report report) {
        // ID校验
        if (report.getReportId() == null || report.getReportId() <= 0) {
            return false;
        }
        // 内容校验
        if (report.getPatientName() == null || report.getPatientName().trim().isEmpty()) {
            return false;
        }
        return reportDao.update(report);
    }

    /**
     * 删除报告（补充完整配套方法）
     */
    public boolean deleteReport(Integer reportId) {
        if (reportId == null || reportId <= 0) {
            return false;
        }
        return reportDao.deleteById(reportId);
    }
}