package com.kh.teamproject.vo;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyPageVO {
    private String memPwd;
    private String memEmail;
    private Date memBirth;
    private String memPhone;
    private Integer memShsize;

    // 회원 & 주문 공통
    private String memId;
    private String memName;
    private String memAddr;

    // 주문
    private Integer orderId;
    private String pType;
    private String pName;
    private String pColor;
    private Integer pSize;
    private Integer pPrice;
    private Date ordDStart;
    private Date ordDEnd;

    public String getMemPwd() {
        return memPwd;
    }

    public void setMemPwd(String memPwd) {
        this.memPwd = memPwd;
    }

    public String getMemEmail() {
        return memEmail;
    }

    public void setMemEmail(String memEmail) {
        this.memEmail = memEmail;
    }

    public Date getMemBirth() {
        return memBirth;
    }

    public void setMemBirth(Date memBirth) {
        this.memBirth = memBirth;
    }

    public String getMemPhone() {
        return memPhone;
    }

    public void setMemPhone(String memPhone) {
        this.memPhone = memPhone;
    }

    public Integer getMemShsize() {
        return memShsize;
    }

    public void setMemShsize(Integer memShsize) {
        this.memShsize = memShsize;
    }

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Integer getpSize() {
        return pSize;
    }

    public void setpSize(Integer pSize) {
        this.pSize = pSize;
    }

    public Integer getpPrice() {
        return pPrice;
    }

    public void setpPrice(Integer pPrice) {
        this.pPrice = pPrice;
    }

    public Date getOrdDStart() {
        return ordDStart;
    }

    public void setOrdDStart(Date ordDStart) {
        this.ordDStart = ordDStart;
    }

    public Date getOrdDEnd() {
        return ordDEnd;
    }

    public void setOrdDEnd(Date ordDEnd) {
        this.ordDEnd = ordDEnd;
    }
}
