package com.kh.teamproject;

import com.kh.teamproject.dao.MembersTbDAO;
import com.kh.teamproject.vo.MembersTbVO;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberService {
    @Autowired
    private MembersTbDAO membersTbDAO;

    // mem_id로 멤버 정보를 조회하는 메서드
    public MembersTbVO findById(String memId) {
        return membersTbDAO.findById(memId);
    }
}
