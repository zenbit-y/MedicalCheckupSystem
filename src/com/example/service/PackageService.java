package com.example.service;

import com.example.dao.PackageDAO;
import com.example.entity.Package;
import java.math.BigDecimal;
import java.util.List;

public class PackageService {
    // 加final消除黄色警告：字段可能为final
    private final PackageDAO packageDao = new PackageDAO();

    // 添加套餐
    public boolean addPackage(Package pkg) {
        // 校验名称非空
        if (pkg.getName() == null || pkg.getName().trim().isEmpty()) {
            return false;
        }
        // 价格不能为负/空
        if (pkg.getPrice() == null || pkg.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }
        // 调用DAO新增
        return packageDao.addPackage(pkg);
    }

    // 修改套餐（修复第28行报错，这里用方案2，不改实体也能跑）
    public boolean updatePackage(Package pkg) {
        // 去掉 == null，只判断数值
        if (pkg.getPackageId() <= 0) {
            return false;
        }
        if (pkg.getName() == null || pkg.getName().trim().isEmpty()) {
            return false;
        }
        if (pkg.getPrice() == null || pkg.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }
        return packageDao.updatePackage(pkg);
    }

    // 删除套餐
    public boolean deletePackage(Integer packageId) {
        if (packageId == null || packageId <= 0) {
            return false;
        }
        return packageDao.deletePackageById(packageId);
    }

    // 查询全部套餐
    public List<Package> findAllPackage() {
        return packageDao.getAllPackages();
    }


    /**
     * 根据套餐ID查询单个套餐
     */
    public Package getPackageById(Integer packageId) {
        // ID校验
        if(packageId == null || packageId <= 0){
            return null;
        }
        // 调用DAO查询
        return packageDao.getPackageById(packageId);
    }
}
