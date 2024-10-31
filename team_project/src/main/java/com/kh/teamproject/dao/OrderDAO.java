package com.kh.teamproject.dao;


import com.kh.teamproject.vo.OrderVO;
import com.kh.teamproject.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Slf4j
public class OrderDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public OrderDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<OrderVO> OrderManySelect(String c) {
        String query = " SELECT P_NAME , P_PRICE FROM OrderTB WHERE P_TYPE = ? ORDER BY COUNT(P_NAME) DESC";
        return jdbcTemplate.query(query, new Object[]{c}, new EmpRowMapper());
    }

    public ProductVO findProduct(String pName, String pColor, int pSize) {
        String sql = "SELECT product_id, p_type, p_price FROM ProductTB WHERE p_name = ? AND p_color = ? AND p_size = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{pName, pColor, pSize},
                (rs, rowNum) -> {
                    ProductVO product = new ProductVO();
                    product.setProduct_id(rs.getInt("product_id"));
                    product.setP_type(rs.getString("p_type"));
                    product.setP_price(rs.getInt("p_price"));
                    return product;
                });
    }

    @Autowired
    private DataSource dataSource;

    public void save(OrderVO order) {
        String sql = "INSERT INTO OrderTB (order_id, mem_id, mem_name, mem_addr, p_type, p_name, p_color, p_size, p_price, ord_d_start, ord_d_end, sales_volume, product_id) " +
                "VALUES (ORDER_ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                order.getMemId(),
                order.getMemName(),
                order.getMemAddr(),
                order.getPType(),
                order.getPName(),
                order.getPColor(),
                order.getPSize(),
                order.getPPrice(),
                order.getOrd_d_start(),
                order.getOrd_d_end(),
                order.getSalesVolume(),
                order.getProductId()
        );
    }

    public void saveOrder(OrderVO order) throws SQLException {
        String query = "INSERT INTO OrderTB (mem_id, mem_name, mem_addr, p_name, p_price, ord_d_start) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, order.getMemId());
            pstmt.setString(2, order.getMemName());
            pstmt.setString(3, order.getMemAddr());
            pstmt.setString(4, order.getPName());
            pstmt.setInt(5, order.getPPrice());
            pstmt.executeUpdate();
        }
    }

    private static class EmpRowMapper implements RowMapper<OrderVO> {
        @Override
        public OrderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new OrderVO(
                    rs.getInt("p_price"),
                    rs.getString("p_name")
            );
        }
    }
}
