package com.example.service;

import com.example.dao.AppointmentDAO;
import com.example.entity.Appointment;
import java.util.List;

public class AppointmentService {
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    // 新增预约（窗口调用方法名 addAppointment）
    public boolean addAppointment(Appointment apt) {
        if (apt == null) {
            return false;
        }
        // 简单参数校验
        if (apt.getPackageId() == null || apt.getPackageId() <= 0) {
            return false;
        }
        if (apt.getUserName() == null || apt.getUserName().trim().isEmpty()) {
            return false;
        }
        if (apt.getUserPhone() == null || apt.getUserPhone().trim().isEmpty()) {
            return false;
        }
        if (apt.getAppointDate() == null || apt.getAppointDate().trim().isEmpty()) {
            return false;
        }
        return appointmentDAO.add(apt);
    }

    // 修改预约
    public boolean updateAppoint(Appointment apt) {
        if (apt == null || apt.getAppointmentId() == null || apt.getAppointmentId() <= 0) {
            return false;
        }
        if (apt.getPackageId() == null || apt.getPackageId() <= 0) {
            return false;
        }
        if (apt.getUserName() == null || apt.getUserName().trim().isEmpty()) {
            return false;
        }
        if (apt.getUserPhone() == null || apt.getUserPhone().trim().isEmpty()) {
            return false;
        }
        if (apt.getAppointDate() == null || apt.getAppointDate().trim().isEmpty()) {
            return false;
        }
        return appointmentDAO.update(apt);
    }

    // 删除预约
    public boolean deleteAppoint(Integer appointmentId) {
        if (appointmentId == null || appointmentId <= 0) {
            return false;
        }
        return appointmentDAO.deleteById(appointmentId);
    }
    // 查询全部预约
    public List<Appointment> listAllAppointment() {
        return appointmentDAO.listAll();
    }
}