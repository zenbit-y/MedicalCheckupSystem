package com.example.entity;

public class Appointment {
    private Integer appointmentId;
    private Integer packageId;
    private String userName;
    private String userPhone;
    private String appointDate;
    private String status;

    public Appointment(){}

    public Integer getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }
    public Integer getPackageId() {
        return packageId;
    }
    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPhone() {
        return userPhone;
    }
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    public String getAppointDate() {
        return appointDate;
    }
    public void setAppointDate(String appointDate) {
        this.appointDate = appointDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}