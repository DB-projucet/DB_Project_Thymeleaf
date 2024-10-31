package com.kh.teamproject.dao;

import com.kh.teamproject.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.kh.teamproject.dao.ReviewDAO.Database.getConnection;


@Repository
@Slf4j
public class ProductDAO {
    private final JdbcTemplate jdbcTemplate;

    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    public List<ProductVO> productSelect(String type, String sortOption) {
        if (sortOption == null) {
            throw new IllegalArgumentException("sortOption cannot be null");
        }

        String query;
        while (true ){
        switch (sortOption) {
            case "popular":
                query = "SELECT P.P_name, P.P_price FROM ProductTB P " +
                        "JOIN OrderTB O ON P.product_id = O.product_id " +
                        "WHERE P.P_type = ? " +
                        "GROUP BY P.P_name, P.P_price " +
                        "ORDER BY SUM(O.sales_volume) DESC";
                break;

            case "high":
                query = "SELECT DISTINCT P_name, P_price FROM ProductTB WHERE P_type = ? ORDER BY P_price DESC";
                break;
            case "low":
                query = "SELECT DISTINCT P_name, P_price FROM ProductTB WHERE P_type = ? ORDER BY P_price ASC";
                break;
            default:
                throw new IllegalArgumentException("Invalid sort option: " + sortOption);
        }

        return jdbcTemplate.query(query, new Object[]{type}, new ProductRowMapper());
        }
    }

    public List<ProductVO> product_Select(String type, String sortOption) {
        //String sql = "SELECT * FROM ProductTB WHERE p_type = ? ORDER BY p_name ASC";
        String sql = "SELECT DISTINCT p_name, p_price FROM ProductTB WHERE p_type = ? ORDER BY p_name ASC";
        List<ProductVO> productList = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, type);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductVO product = new ProductVO();
                product.setP_name(rs.getString("p_name"));
                product.setP_price(rs.getInt("p_price"));
                // other fields as necessary
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public List<ProductVO> product__Select(String type, String sortOption) {
        //String sql = "SELECT * FROM ProductTB WHERE p_type = ? ORDER BY p_name ASC";
        String sql = "SELECT DISTINCT p_name, p_price, p_type FROM ProductTB ORDER BY p_type, p_name ASC";
        List<ProductVO> productList = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, type);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductVO product = new ProductVO();
                product.setP_name(rs.getString("p_name"));
                product.setP_price(rs.getInt("p_price"));
                // other fields as necessary
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public List<ProductVO> selectAllProducts() {
        String sql = "SELECT DISTINCT p_name, p_price, p_type FROM ProductTB ORDER BY p_type, p_name ASC";
        List<ProductVO> productList = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ProductVO product = new ProductVO();
                product.setP_name(rs.getString("p_name"));
                product.setP_price(rs.getInt("p_price"));
                product.setP_type(rs.getString("p_type"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    private static class ProductRowMapper implements RowMapper<ProductVO> {
        @Override
        public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ProductVO(
                    rs.getString("p_name"),
                    rs.getInt("p_price")

            );
        }
    }

   }
