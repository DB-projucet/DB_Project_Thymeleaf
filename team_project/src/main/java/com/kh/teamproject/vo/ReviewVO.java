package com.kh.teamproject.vo;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReviewVO {
    private int review_id;
    private String mem_id;
    private int order_id;
    private Timestamp ord_d_start;
    private String p_name;
    private String p_color;
    private String r_content;
    private int star_rate;

    public int getReview_id() {
        return review_id;
    }

    public String getMem_id() {
        return mem_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public Timestamp getOrd_d_start() {
        return ord_d_start;
    }

    public String getP_name() {
        return p_name;
    }

    public String getP_color() {
        return p_color;
    }

    public String getR_content() {
        return r_content;
    }

    public int getStar_rate() {
        return star_rate;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setOrd_d_start(Timestamp ord_d_start) {
        this.ord_d_start = ord_d_start;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public void setP_color(String p_color) {
        this.p_color = p_color;
    }

    public void setR_content(String r_content) {
        this.r_content = r_content;
    }

    public void setStar_rate(int star_rate) {
        this.star_rate = star_rate;
    }
}