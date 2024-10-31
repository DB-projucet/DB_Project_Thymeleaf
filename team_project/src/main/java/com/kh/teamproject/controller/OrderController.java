package com.kh.teamproject.controller;

import com.kh.teamproject.dao.MembersTbDAO;
import com.kh.teamproject.dao.OrderDAO;
import com.kh.teamproject.vo.MembersTbVO;
import com.kh.teamproject.vo.OrderVO;
import com.kh.teamproject.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

@Controller
@RequestMapping("/buy")
public class OrderController {

    @Autowired
    private MembersTbDAO membersTbDAO;
    @Autowired
    private OrderDAO orderDAO;

    @GetMapping("/order")
    public String buyProduct(HttpSession session, Model model) {
        String loggedInMemId = (String) session.getAttribute("loggedInMemId");

        if (loggedInMemId == null) {
            return "redirect:/teamproject/signin"; // 로그인되지 않은 경우 로그인 페이지로 리디렉션
        }

        // 로그인된 사용자 정보를 MembersTbDAO를 통해 조회
        MembersTbVO member = membersTbDAO.findById(loggedInMemId);
        if (member != null) {
            model.addAttribute("memId", member.getMemId());
            model.addAttribute("memName", member.getMemName());
            model.addAttribute("memAddr", member.getMemAddr());
        }

        return "thymeleaf/order"; // order.html로 이동
    }

    @PostMapping("/submitOrder")
    public String submitOrder(@ModelAttribute OrderVO order, HttpSession session) {
        String loggedInMemId = (String) session.getAttribute("loggedInMemId");

        // 로그인된 사용자 정보 조회
        if (loggedInMemId != null) {
            MembersTbVO member = membersTbDAO.findById(loggedInMemId);
            order.setMemId(member.getMemId());
            order.setMemName(member.getMemName());
            order.setMemAddr(member.getMemAddr());
        } else {
            return "redirect:/teamproject/signin"; // 로그인되지 않은 경우 로그인 페이지로 리디렉션
        }

        // ProductTB에서 제품 정보 조회 및 설정
        ProductVO product = orderDAO.findProduct(order.getPName(), order.getPColor(), order.getPSize());
        if (product != null) {
            order.setProductId(product.getProduct_id());
            order.setPType(product.getP_type());
            order.setPPrice(product.getP_price());
        } else {
            return "redirect:/producttb/all?status=failure"; // 제품이 없을 경우 실패
        }

        // 주문 시작일 및 종료일 설정
        order.setOrd_d_start(new Timestamp(System.currentTimeMillis()));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 3);
        order.setOrd_d_end(new Timestamp(calendar.getTimeInMillis()));

        // sales_volume을 1로 설정
        order.setSalesVolume(1);

        // 주문 저장 처리
        try {
            orderDAO.save(order);
            session.setAttribute("orderStatus", "success"); // 성공 상태 설정
            return "redirect:/producttb/all";
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("orderStatus", "failure"); // 실패 상태 설정
            return "redirect:/producttb/all";
        }
    }
}
