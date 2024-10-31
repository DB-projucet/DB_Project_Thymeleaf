
package com.kh.teamproject.vo;

import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderHistoryVO {
    private String memId;
    private String memName;
    private String memAddr;
    private String pType;
    private String pName;
    private String pColor;
    private int pSize;
    private int pPrice;
    private Timestamp ordStartTime;  // Changed to java.sql.Timestamp
    private Date ordEndTime;    // Changed to java.sql.Timestamp
    private int productId;
    private int salesVolume;

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getMemAddr() {
        return memAddr;
    }

    public void setMemAddr(String memAddr) {
        this.memAddr = memAddr;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpColor() {
        return pColor;
    }

    public void setpColor(String pColor) {
        this.pColor = pColor;
    }

    public int getpSize() {
        return pSize;
    }

    public void setpSize(int pSize) {
        this.pSize = pSize;
    }

    public int getpPrice() {
        return pPrice;
    }

    public void setpPrice(int pPrice) {
        this.pPrice = pPrice;
    }

    public Timestamp getOrdStartTime() {
        return ordStartTime;
    }

    public void setOrdStartTime(Timestamp ordStartTime) {
        this.ordStartTime = ordStartTime;
    }

    public Date getOrdEndTime() {
        return ordEndTime;
    }

    public void setOrdEndTime(Date ordEndTime) {
        this.ordEndTime = ordEndTime;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }
}
