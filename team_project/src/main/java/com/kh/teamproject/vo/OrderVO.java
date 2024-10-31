package com.kh.teamproject.vo;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {
   private int orderId;
   private String memId;
   private String memName;
   private String memAddr;
   private String p_type;
   private String p_name;
   private String p_color;
   private int p_size;
   private int p_price;
   @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
   private Date ord_d_start; // Changed to java.sql.Timestamp
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private Date ord_d_end;    // Changed to java.sql.Timestamp
   private int product_id;
   private int sales_volume;

   public OrderVO(int p_price, String p_name  ) {
      this.p_name = p_name;
      this.p_price = p_price;
   }

   public int getOrderId() {
      return orderId;
   }

   public String getMemId() {
      return memId;
   }

   public String getMemName() {
      return memName;
   }

   public String getMemAddr() {
      return memAddr;
   }

   public String getPType() {
      return p_type;
   }

   public String getPName() {
      return p_name;
   }

   public String getPColor() {
      return p_color;
   }

   public int getPSize() {
      return p_size;
   }

   public int getPPrice() {
      return p_price;
   }

   public Date getOrd_d_start() {
      return ord_d_start;
   }

   public Date getOrdEndTime() {
      return ord_d_end;
   }

   public int getProductId() {
      return product_id;
   }

   public int getSalesVolume() {
      return sales_volume;
   }

   public void setOrderId(int orderId) {
      this.orderId = orderId;
   }

   public void setMemId(String memId) {
      this.memId = memId;
   }

   public void setMemName(String memName) {
      this.memName = memName;
   }

   public void setMemAddr(String memAddr) {
      this.memAddr = memAddr;
   }

   public void setPType(String pType) {
      this.p_type = pType;
   }

   public void setPName(String pName) {
      this.p_name = pName;
   }

   public void setPColor(String pColor) {
      this.p_color = pColor;
   }

   public void setPSize(int pSize) {
      this.p_size = pSize;
   }

   public void setPPrice(int pPrice) {
      this.p_price = pPrice;
   }

   public void getOrd_d_start(Date ord_d_start) {
      this.ord_d_start = ord_d_start;
   }

   public void setOrdEndTime(Date ordEndTime) {
      this.ord_d_end = ordEndTime;
   }

   public void setProductId(int productId) {
      this.product_id = productId;
   }

   public void setSalesVolume(int salesVolume) {
      this.sales_volume = salesVolume;
   }
}
