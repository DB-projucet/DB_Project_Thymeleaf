package com.kh.teamproject.controller;

import com.kh.teamproject.dao.MyPageDAO;
import com.kh.teamproject.vo.MyPageVO;
import com.kh.teamproject.vo.MembersTbVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/teamproject")
public class MyPageController {
    private final MyPageDAO myPageDAO;

    public MyPageController(MyPageDAO myPageDAO) {
        this.myPageDAO = myPageDAO;
    }

    @GetMapping("/welcome")
    public String welcomePage(@RequestParam("loggedInMemId") String memId, Model model) {
        MembersTbVO member = myPageDAO.getMemberInfo(memId);
        model.addAttribute("user", member); // 사용자 정보 추가
        return "thymeleaf/welcome";
    }

    // 마이페이지 (내 정보)
    @GetMapping("/mypage")
    public String getMyInfo(HttpSession session, Model model) {
        String memId = (String) session.getAttribute("loggedInMemId");

        if (memId == null) {
            return "redirect:/teamproject/signin";
        }

        MembersTbVO memberInfo = myPageDAO.getMemberInfo(memId);

        if (memberInfo == null) {
            model.addAttribute("errorMessage", "회원 정보를 찾을 수 없습니다.");
            return "thymeleaf/errorPage";
        }

        model.addAttribute("user", memberInfo);
        return "thymeleaf/mypage";
    }

    @PostMapping("/mypage")
    public String updateMyInfo(@ModelAttribute MembersTbVO memberInfo, HttpSession session, Model model)  {
        String memId = (String) session.getAttribute("loggedInMemId");

        if (memId == null) {
            model.addAttribute("errorMessage", "로그인되어 있지 않습니다.");
            return "thymeleaf/errorPage";
        }

        memberInfo.setMemId(memId);

        try {
            myPageDAO.updateMemberInfo(memberInfo, memId);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "회원 정보 수정 중 오류가 발생했습니다.");
            return "thymeleaf/errorPage";
        }
        return "redirect:/teamproject/mypage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/teamproject/signin";
    }

    @GetMapping("/cancelaccount")
    public String cancelAccount(HttpSession session, Model model) {
        String memId = (String) session.getAttribute("loggedInMemId");

        if (memId == null) {
            System.out.println("null"+memId);
            return "redirect:/teamproject/signin";
        }

        MembersTbVO memberInfo = myPageDAO.getMemberInfo(memId);
        if (memberInfo == null) {
            System.out.println("Info null"+memId);
            model.addAttribute("errorMessage", "회원 정보를 찾을 수 없습니다.");
            return "thymeleaf/errorPage"; // 회원 정보가 없을 경우 에러 페이지로 이동
        }

        model.addAttribute("user", memberInfo);
        return "thymeleaf/cancelaccount";
    }

    @PostMapping("/cancelaccount")
    private String processCancelAccount(@RequestParam("memPwd") String memPwd, HttpSession session, Model model) {
        String memId = (String) session.getAttribute("loggedInMemId");

        // 로그아웃 상태일때
        if (memId == null) {
            System.out.println("null 로그아웃"+memId);
            model.addAttribute("errorMessage", "로그인되어 있지 않습니다.");
            return "thymeleaf/errorPage";
        }

        // 회원정보가 없을때
        MembersTbVO memberInfo = myPageDAO.getMemberInfo(memId);
        if (memberInfo == null) {
            System.out.println("null 회원정보"+memId);
            model.addAttribute("errorMessage", "회원 정보를 찾을 수 없습니다.");
            return "thymeleaf/errorPage"; // 회원 정보가 없을 경우 에러 페이지로 이동
        }

        // 비밀번호 불일치일때
        if (!myPageDAO.checkPw(memId, memPwd)) {
            System.out.println("불일치"+memId);
            model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
            return "thymeleaf/cancelaccount"; // 비밀번호 불일치 시 다시 돌아감
        }

        try {
            //System.out.println(memId);
            myPageDAO.processCancelAccount(memId, memPwd); // 계정 삭제 메서드 호출
            session.invalidate(); // 세션 무효화
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "회원 탈퇴 중 오류가 발생했습니다.");
            return "thymeleaf/errorPage";
        }
        return "redirect:/teamproject/signup"; // 로그인 페이지로 리다이렉트
    }
}
