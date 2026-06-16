package com.example.entity;

import java.util.Date;

public class Report {
    private Integer reportId;
    private Integer appointmentId;
    private String patientName;
    private String resultContent;
    private Date createTime;

    // 无参构造
    public Report() {}

    // getter & setter
    public Integer getReportId() {
        return reportId;
    }
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getResultContent() {
        return resultContent;
    }
    public void setResultContent(String resultContent) {
        this.resultContent = resultContent;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
