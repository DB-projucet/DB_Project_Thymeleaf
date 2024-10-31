package com.kh.teamproject.controller;

import com.kh.teamproject.dao.MembersTbDAO;
import com.kh.teamproject.vo.MembersTbVO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/teamproject") // http://localhost:8112/teamproject
public class MembersTbController {
    private final MembersTbDAO membersTbDAO;

    public MembersTbController(MembersTbDAO membersTbDAO) {
        this.membersTbDAO = membersTbDAO;
    }
    @GetMapping({"/", "/home"})
    public String redirectToHomePage() {
        return "MainIndex";
    }

    @GetMapping("/producttb/orderStatus")
    @ResponseBody
    public Map<String, String> getOrderStatus(HttpSession session) {
        Map<String, String> response = new HashMap<>();
        String status = (String) session.getAttribute("orderStatus");
        if (status != null) {
            response.put("orderStatus", status);
            session.removeAttribute("orderStatus"); // 메시지 사용 후 삭제
        }
        return response;
    }

    @GetMapping("/check-login")
    @ResponseBody
    public Map<String, Boolean> checkLogin(HttpSession session) {
        Map<String, Boolean> response = new HashMap<>();
        String loggedInMemId = (String) session.getAttribute("loggedInMemId");
        response.put("isLoggedIn", loggedInMemId != null);
        return response;
    }

    @PostMapping("/signinew")
    public String signIn(@RequestParam String memId, @RequestParam String memPwd, HttpSession session, Model model) {
        // MembersTbDAO에서 ID와 비밀번호로 회원 조회
        MembersTbVO member = membersTbDAO.findByCredentials(memId, memPwd);

        if (member != null) {
            session.setAttribute("loggedInMemId", member.getMemId()); // 로그인 ID를 세션에 저장
            session.setAttribute("loggedIn", true); // 로그인 상태 플래그 저장
            return "redirect:/producttb/all";
        } else {
            model.addAttribute("errorMessage", "로그인 실패: ID 또는 비밀번호가 잘못되었습니다.");
            return "thymeleaf/signin";
        }
    }

    @GetMapping("/signup") // http://localhost:8112/teamproject/signup
    public String signupView(Model model) {
        model.addAttribute("employees", new MembersTbVO());
        return "thymeleaf/signUp";  // html경로
    }
    @PostMapping("/signup")
    public String insertDBMembersTB(@ModelAttribute("employees") MembersTbVO membersTbVO, Model model) {
        // 유효성 검사
        if (!membersTbDAO.validateSignUp(membersTbVO)) {
            model.addAttribute("loginError", "회원가입 정보가 유효하지 않습니다. 다시 확인해주세요.");
            return "thymeleaf/signUp"; // 유효하지 않으면 회원가입 페이지로 돌아감
        }
        boolean isSuccess = membersTbDAO.membersTBSignUp(membersTbVO);
        model.addAttribute("isSuccess", isSuccess);
        return "redirect:/producttb/all";
    }
    @GetMapping("/signin") // http://localhost:8112/teamproject/signin
    public String signinView(Model model) {
        model.addAttribute("employees", new Employees());
        return "thymeleaf/signin";
    }
    @PostMapping("/signin")
    public String signin(@ModelAttribute("employees") MembersTbVO membersTbVO, Model model, HttpSession session)  {
        if (membersTbVO.getMemId() == null || membersTbVO.getMemPwd() == null) {
            model.addAttribute("errorMessage", "ID와 비밀번호를 모두 입력해주세요.");
            return "thymeleaf/signin"; // 실패 시 로그인 페이지로 돌아가기
        }

        boolean isValidUser = membersTbDAO.validateUser(membersTbVO.getMemId(), membersTbVO.getMemPwd());
        if (isValidUser) {
            System.out.println("로그인 성공");
//            model.addAttribute("user", membersTbVO);
            session.setAttribute("loggedInMemId", membersTbVO.getMemId());
            return "redirect:/producttb/all";
        } else {
            System.out.println("로그인 실패");
            model.addAttribute("errorMessage", "아이디 또는 비밀번호가 잘못되었습니다. 다시 입력해 주세요.");
            return "thymeleaf/signin";
        }
    }
}
