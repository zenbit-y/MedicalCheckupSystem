package com.example.service;

import com.example.dao.DoctorDAO;
import com.example.entity.Doctor;
import java.util.List;

public class DoctorService {
    // 缺失的关键：先new出DAO对象，下面才能调用
    private DoctorDAO doctorDao = new DoctorDAO();

    /**
     * 医生登录
     */
    public Doctor login(String username, String password) {
        // 调用DAO查询数据库
        return doctorDao.login(username, password);
    }

    /**
     * 查询所有医生（管理员后台使用）
     */
    public List<Doctor> findAllDoctor() {
        return doctorDao.selectAll();
    }

    /**
     * 添加医生账号（管理员）
     */
    public boolean addDoctor(Doctor doctor) {
        // 简单校验：科室、账号不为空
        if(doctor.getUsername().trim().length()<=3){
            return false;
        }
        return doctorDao.insert(doctor);
    }

    /**
     * 删除医生（管理员）
     */
    public boolean deleteDoctor(Integer doctorId) {
        if(doctorId == null || doctorId <= 0){
            return false;
        }
        return doctorDao.deleteById(doctorId);
    }
}