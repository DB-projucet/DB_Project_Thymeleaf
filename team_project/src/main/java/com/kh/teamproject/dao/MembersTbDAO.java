package com.kh.teamproject.dao;

import com.kh.teamproject.vo.MembersTbVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MembersTbDAO {
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public MembersTbDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }


    public MembersTbVO findById(String memId) {
        String sql = "SELECT * FROM MembersTB WHERE mem_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{memId}, new BeanPropertyRowMapper<>(MembersTbVO.class));
        } catch (EmptyResultDataAccessException e) {
            return null; // 해당 ID가 없는 경우 null 반환
        }
    }

    public Map<String, Object> findMemberInfoByMemId(String memId) throws SQLException {
        Map<String, Object> memberInfo = new HashMap<>();
        String query = "SELECT mem_id, mem_name, mem_addr FROM MembersTB WHERE mem_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, memId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                memberInfo.put("mem_id", rs.getString("mem_id"));
                memberInfo.put("mem_name", rs.getString("mem_name"));
                memberInfo.put("mem_addr", rs.getString("mem_addr"));
            }
        }
        return memberInfo;
    }

    //    public List<MembersTbVO> MembersTB
    public boolean membersTBSignUp(MembersTbVO vo) {
        int result = 0;
        String sql = "INSERT INTO MEMBERSTB (MEMBER_id, mem_id, mem_pwd, mem_name, mem_email, mem_addr, mem_birth, mem_phone, mem_shsize) VALUES (MEMBER_ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            result = jdbcTemplate.update(sql, vo.getMemId(), vo.getMemPwd(), vo.getMemName(), vo.getMemEmail(), vo.getMemAddr(), vo.getMemBirth(), vo.getMemPhone(), vo.getMemShsize());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result > 0;
    }

    public boolean validateSignUp(MembersTbVO vo) {
        // ID 유효성 검사
        if (vo.getMemId().length() < 5 || vo.getMemId().length() > 10 || !vo.getMemId().matches("^[a-z0-9!@#\\$%^&*()_+=-]+$")) {
            return false; // 유효성 불합격
        }
        // 비밀번호 유효성 검사
        if (vo.getMemPwd().length() < 8 || vo.getMemPwd().length() > 16 || !vo.getMemPwd().matches("^[a-zA-Z0-9!@#\\$%^&*()_+=-]+$")) {
            return false; // 유효성 불합격
        }
        // 이메일 유효성 검사
        if (!vo.getMemEmail().matches("^[a-z0-9]+@[a-z0-9]+\\.[a-z]+$")) {
            return false; // 유효성 불합격
        }
        // 이름 유효성 검사
        if (!vo.getMemName().matches("^[a-zA-Z가-힣]+$")) {
            return false; // 유효성 불합격
        }
        // 생년월일 유효성 검사
        if (!vo.getMemBirth().toString().matches("^(\\d{4})-(\\d{2})-(\\d{2})$")) {
            return false; // 유효성 불합격
        }
        // 전화번호 유효성 검사
        if (!vo.getMemPhone().matches("^0(\\d{2})-(\\d{4})-(\\d{4})$")) {
            return false; // 유효성 불합격
        }
        // 발 사이즈 유효성 검사
        if (vo.getMemShsize() < 230 || vo.getMemShsize() > 290 || (vo.getMemShsize() % 10 == 5)) {
            return false; // 유효성 불합격
        }
        return true; // 모든 유효성 검사 통과
    }

    public MembersTbVO findByCredentials(String memId, String memPwd) {
        String sql = "SELECT * FROM MembersTB WHERE mem_id = ? AND mem_pwd = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{memId, memPwd}, new BeanPropertyRowMapper<>(MembersTbVO.class));
        } catch (EmptyResultDataAccessException e) {
            return null; // ID와 비밀번호가 일치하는 사용자가 없는 경우 null 반환
        }
    }

    public boolean validateUser(String memId, String memPwd) {
        String query = "SELECT COUNT(*) FROM MEMBERSTB WHERE mem_id = ? AND mem_pwd = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setString(1, memId);
            psmt.setString(2, memPwd);
            System.out.println("입력된 ID: " + memId + ", 입력된 비밀번호: " + memPwd); // 추가된 로그
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 예외가 발생했을 때, 로그에 남기기
            System.out.println("SQL 오류: " + e.getMessage());
        }
        return false;
    }

}
