package com.kh.teamproject.controller;

import com.kh.teamproject.dao.ReviewDAO;
import com.kh.teamproject.vo.ReviewVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {
    private final ReviewDAO reviewDAO;

    public ReviewController(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    @GetMapping
    public String showReviewPage() {
        return "thymeleaf/review"; // review.html 파일을 반환
    }

    @GetMapping("/list")
    public String getReviewList(@RequestParam(value = "productName", required = false) String productName, Model model) {
        List<ReviewVO> reviewList = reviewDAO.getReviewByProduct(productName);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("pName", productName != null ? productName : "");

        // 고유한 상품 이름 목록 추가
        List<String> uniqueProductNames = reviewDAO.getUniqueProductNames();
        model.addAttribute("uniqueProductNames", uniqueProductNames);

        return "thymeleaf/list"; // list.html을 반환
    }

    @GetMapping("/add")
    public String showAddReviewForm() {
        return "thymeleaf/add";  // 리뷰 추가 폼 페이지
    }

    @PostMapping("/add")
    public String addReview(@RequestParam("mem_id") String memId,
                            @RequestParam("order_id") int orderId,
                            @RequestParam("ord_d_start") Timestamp ordDStart,
                            @RequestParam("p_name") String pName,
                            @RequestParam("p_color") String pColor,
                            @RequestParam("r_content") String rContent,
                            @RequestParam("star_rate") int starRate,
                            Model model) {

        ReviewVO newReview = new ReviewVO();
        newReview.setMem_id(memId);
        newReview.setOrder_id(orderId);
        newReview.setOrd_d_start(ordDStart);
        newReview.setP_name(pName);
        newReview.setP_color(pColor);
        newReview.setR_content(rContent);
        newReview.setStar_rate(starRate);

        reviewDAO.insertReview(newReview);
        model.addAttribute("message", "Review added successfully!");
        return "redirect:/review/list?productName=" + pName;  // 리뷰 목록 페이지로 리다이렉트
    }

    @GetMapping("/{reviewId}")
    public String viewReview(@PathVariable int reviewId, Model model) {
        ReviewVO review = reviewDAO.getReview(reviewId);
        model.addAttribute("review", review);
        model.addAttribute("pName", review.getP_name()); // 리뷰의 상품 이름 추가
        List<ReviewVO> reviewList = reviewDAO.getReviewByProduct(review.getP_name()); // 상품의 모든 리뷰 가져오기
        model.addAttribute("reviewList", reviewList);
        return "thymeleaf/list"; // list.html로 리턴
    }

    @GetMapping("/{reviewId}/edit")
    public String showEditReviewForm(@PathVariable int reviewId, Model model) {
        ReviewVO review = reviewDAO.getReview(reviewId);
        model.addAttribute("review", review);
        return "thymeleaf/edit"; // 수정 폼 페이지
    }

    @PostMapping("/{reviewId}/edit")
    public String editReview(@PathVariable int reviewId, @ModelAttribute ReviewVO review) {
        review.setReview_id(reviewId);
        reviewDAO.updateReview(review);
        return "redirect:/review/list?productName=" + review.getP_name(); // 상품 이름으로 리다이렉트
    }

    @PostMapping("/{reviewId}/delete")
    public String deleteReview(@PathVariable int reviewId) {
        ReviewVO review = reviewDAO.getReview(reviewId);
        reviewDAO.deleteReview(reviewId);
        return "redirect:/review/list?productName=" + review.getP_name(); // 상품 이름으로 리다이렉트
    }

    @GetMapping("/{reviewId}/change")
    public String showChangeReviewForm(@PathVariable int reviewId, Model model) {
        ReviewVO review = reviewDAO.getReview(reviewId);
        model.addAttribute("review", review);
        return "thymeleaf/change"; // change.html 파일 경로
    }
}
