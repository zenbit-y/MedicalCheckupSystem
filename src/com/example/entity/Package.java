package com.example.entity;

import java.math.BigDecimal;

public class Package {
    // 对应package表的字段
    private int packageId;
    private String name;
    private BigDecimal price;
    private String description;

    // 无参构造方法
    public Package() {
    }

    // 带参构造方法
    public Package(int packageId, String name, BigDecimal price, String description) {
        this.packageId = packageId;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // Getter和Setter方法
    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString方法
    @Override
    public String toString() {
        return "Package{" +
                "packageId=" + packageId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
