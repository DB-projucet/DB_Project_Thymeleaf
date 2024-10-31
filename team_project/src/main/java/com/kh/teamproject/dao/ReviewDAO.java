package com.kh.teamproject.dao;

import com.kh.teamproject.vo.ReviewVO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class ReviewDAO {
    private final JdbcTemplate jdbcTemplate;

    public ReviewDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ReviewVO> getReviewByProduct(String productName) {
        String sql;
        if (productName == null || productName.isEmpty()) {
            sql = "SELECT * FROM ReviewTB"; // 모든 리뷰 가져오기
            return jdbcTemplate.query(sql, new ReviewRowMapper());
        } else {
            sql = "SELECT * FROM ReviewTB WHERE p_name = ?"; // 특정 상품 리뷰 가져오기
            return jdbcTemplate.query(sql, new Object[]{productName}, new ReviewRowMapper());
        }
    }


    public List<String> getUniqueProductNames() {
        String sql = "SELECT DISTINCT p_name FROM ReviewTB"; // 리뷰 테이블에서 고유한 상품 이름 가져오기
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public class Database {
        private static final String URL = "jdbc:oracle:thin:@localhost:1521/xe";
        private static final String USER = "SCOTT";
        private static final String PASSWORD = "TIGER";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }

    public void insertReview(ReviewVO review) {
        String sql = "INSERT INTO ReviewTB (review_id, mem_id, order_id, ord_d_start, p_name, p_color, r_content, star_rate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, review.getReview_id(), review.getMem_id(), review.getOrder_id(), review.getOrd_d_start(),
                review.getP_name(), review.getP_color(), review.getR_content(), review.getStar_rate());
    }

    public List<ReviewVO> getReviews() {
        String sql = "SELECT * FROM ReviewTB";
        return jdbcTemplate.query(sql, new ReviewRowMapper());
    }

    public ReviewVO getReview(int reviewId) {
        String sql = "SELECT * FROM ReviewTB WHERE review_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{reviewId}, new ReviewRowMapper());
    }

    public void updateReview(ReviewVO review) {
        String sql = "UPDATE ReviewTB SET r_content = ?, star_rate = ? WHERE review_id = ?";
        jdbcTemplate.update(sql, review.getR_content(), review.getStar_rate(), review.getReview_id());
    }

    public void deleteReview(int reviewId) {
        String sql = "DELETE FROM ReviewTB WHERE review_id = ?";
        jdbcTemplate.update(sql, reviewId);
    }

    private static class ReviewRowMapper implements RowMapper<ReviewVO> {
        @Override
        public ReviewVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ReviewVO(
                    rs.getInt("review_id"),
                    rs.getString("mem_id"),
                    rs.getInt("order_id"),
                    rs.getTimestamp("ord_d_start"),
                    rs.getString("p_name"),
                    rs.getString("p_color"),
                    rs.getString("r_content"),
                    rs.getInt("star_rate")
            );
        }
    }
}
