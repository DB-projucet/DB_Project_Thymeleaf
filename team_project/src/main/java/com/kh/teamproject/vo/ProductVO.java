package com.kh.teamproject.vo;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
    private int product_id ;
    private String p_type;
    private String p_name;
    private String p_color;
    private int size;
    private int p_price;
    private int p_cnt;


    public ProductVO(String p_name, int p_price) {
        this.p_name = p_name;
        this.p_price = p_price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getP_type() {
        return p_type;
    }

    public String getP_name() {
        return p_name;
    }

    public String getP_color() {
        return p_color;
    }

    public int getSize() {
        return size;
    }

    public int getP_price() {
        return p_price;
    }

    public int getP_cnt() {
        return p_cnt;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setP_type(String p_type) {
        this.p_type = p_type;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public void setP_color(String p_color) {
        this.p_color = p_color;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setP_price(int p_price) {
        this.p_price = p_price;
    }

    public void setP_cnt(int p_cnt) {
        this.p_cnt = p_cnt;
    }
}
