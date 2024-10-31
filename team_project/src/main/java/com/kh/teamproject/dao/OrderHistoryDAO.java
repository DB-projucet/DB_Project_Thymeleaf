package com.kh.teamproject.dao;

import com.kh.teamproject.vo.OrderHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderHistoryDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderHistoryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 내 주문 내역 조회
    public List<OrderHistoryVO> getOrderHistory(String memId) {
        String sql = "SELECT mem_id, mem_name, mem_addr, p_type, p_name, p_color, p_size, p_price, ord_d_start, ord_d_end FROM ORDERTB WHERE mem_id = ?";
        return jdbcTemplate.query(sql, new Object[]{memId}, (rs, rowNum) -> {
            OrderHistoryVO order = new OrderHistoryVO();
            order.setMemId(rs.getString("mem_id"));
            order.setMemName(rs.getString("mem_name"));
            order.setMemAddr(rs.getString("mem_addr"));
            order.setpType(rs.getString("p_type"));
            order.setpName(rs.getString("p_name"));
            order.setpColor(rs.getString("p_color"));
            order.setpSize(rs.getInt("p_size"));
            order.setpPrice(rs.getInt("p_price"));
            order.setOrdStartTime(rs.getTimestamp("ord_d_start"));
            order.setOrdEndTime(rs.getTime("ord_d_end"));
            return order;
        });
    }
}
