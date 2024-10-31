package com.kh.teamproject.controller;

import com.kh.teamproject.dao.OrderHistoryDAO;
import com.kh.teamproject.vo.OrderHistoryVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/teamproject")
public class OrderHistoryController {
    private final OrderHistoryDAO orderHistoryDAO;

    public OrderHistoryController(OrderHistoryDAO orderHistoryDAO) {
        this.orderHistoryDAO = orderHistoryDAO;
    }

    // 내 주문 내역 페이지
    @GetMapping("/mypage/orderhistory")
    public String getOrderHistory(HttpSession session, Model model) {
        String memId = (String) session.getAttribute("loggedInMemId");

        if (memId == null) {
            return "redirect:/teamproject/signin";
        }

        System.out.println("Logged in member ID: " + memId);

        List<OrderHistoryVO> orderHistoryList = orderHistoryDAO.getOrderHistory(memId);

        if (orderHistoryList.isEmpty()) {
            // Handle no orders found
            model.addAttribute("message", "주문 내역이 없습니다."); // 사용자에게 보여줄 메시지
        } else {
            model.addAttribute("orderHistory", orderHistoryList);
        }

        return "thymeleaf/orderHistory";
    }
}
