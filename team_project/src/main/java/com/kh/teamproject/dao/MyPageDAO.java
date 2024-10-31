package com.kh.teamproject.dao;

import com.kh.teamproject.vo.MembersTbVO;
import com.kh.teamproject.vo.MyPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MyPageDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MyPageDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 내 정보 조회
    public MembersTbVO getMemberInfo(String memId) {
        String sql = "SELECT * FROM MEMBERSTB WHERE mem_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{memId}, (rs, rowNum) -> {
                MembersTbVO member = new MembersTbVO();
                member.setMemId(rs.getString("mem_id"));
                member.setMemPwd(rs.getString("mem_pwd"));  // 241030_추가
                member.setMemName(rs.getString("mem_name"));
                member.setMemEmail(rs.getString("mem_email"));
                member.setMemAddr(rs.getString("mem_addr"));
                member.setMemBirth(rs.getDate("mem_birth"));
                member.setMemPhone(rs.getString("mem_phone"));
                member.setMemShsize(rs.getInt("mem_shsize"));
                return member;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 회원 정보 수정
    public void updateMemberInfo(MembersTbVO memberInfo, String memId) {
        String sql = "UPDATE MEMBERSTB SET mem_pwd = ?, mem_name = ?, mem_email = ?, mem_phone = ?, mem_addr = ?, mem_birth = ?, mem_shsize = ? WHERE mem_id = ?";
        // 기존
/*        jdbcTemplate.update(sql,
                memberInfo.getMemPwd(),
                memberInfo.getMemName(),
                memberInfo.getMemEmail(),
                memberInfo.getMemPhone(),
                memberInfo.getMemAddr(),
                memberInfo.getMemBirth(),
                memberInfo.getMemShsize(),
                memberInfo.getMemId());*/
        memberInfo.setMemId(memId);

//        System.out.println("Updating member info: " + memberInfo);

        try {
            jdbcTemplate.update(sql,
                    memberInfo.getMemPwd(),
                    memberInfo.getMemName(),
                    memberInfo.getMemEmail(),
                    memberInfo.getMemPhone(),
                    memberInfo.getMemAddr(),
                    memberInfo.getMemBirth(),
                    memberInfo.getMemShsize(),
                    memberInfo.getMemId()
            );
            System.out.println("회원 정보가 성공적으로 수정되었습니다.");
        } catch (DataAccessException e) {
            System.out.println("회원 정보 수정 중 오류가 발생했습니다 : " + e.getMessage());
        }
    }

    // 회원 탈퇴
/*    public void deleteMember(String memId) {
        String sql = "DELETE FROM MEMBERSTB WHERE mem_id = ? AND mem_pwd = ?";
        jdbcTemplate.update(sql, memId);
    }*/

    // 비밀번호 체크 (탈퇴시)
    public boolean checkPw(String memId, String memPwd) {
        String sql = "SELECT COUNT(*) FROM MEMBERSTB WHERE mem_id = ? AND mem_pwd = ?";
        try {
            Integer count = jdbcTemplate.queryForObject(sql, new Object[]{memId, memPwd}, Integer.class);
            return count != null && count > 0;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 회원 탈퇴
    public void processCancelAccount(String memId, String memPwd) {
        String sql = "DELETE FROM MEMBERSTB WHERE mem_id = ? AND mem_pwd = ?";
        try {
            jdbcTemplate.update(sql, memId, memPwd);
            System.out.println("회원이 성공적으로 탈퇴되었습니다.");
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("회원 탈퇴 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

}
