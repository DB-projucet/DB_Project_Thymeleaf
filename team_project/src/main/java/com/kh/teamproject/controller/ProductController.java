package com.kh.teamproject.controller;

import com.kh.teamproject.dao.ProductDAO;
import com.kh.teamproject.vo.ProductVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

@RequestMapping("/producttb")
public class ProductController {
    private final ProductDAO productDAO;

    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    @GetMapping({"/home"})
    public String redirectToHome() {
        return "redirect:/producttb/all";
    }


//    @GetMapping("/all")
//    public String redirectToAll(Model model) {
//        String type = "daily";
//        String sortOption = "high";
//        List<ProductVO> products = productDAO.product__Select(type, sortOption);
//        model.addAttribute("products", products);
//        return "thymeleaf/all";
//    }

    @GetMapping("/all")
    public String redirectToAll(Model model) {
        List<ProductVO> products = productDAO.selectAllProducts();
        model.addAttribute("products", products);
        return "thymeleaf/all";  // 적절한 템플릿 이름으로 변경
    }


    @GetMapping("/daily")
    public String redirectToDaily(Model model) {
        String type = "DAILY";
        String sortOption = "high";
        List<ProductVO> products = productDAO.product_Select(type, sortOption);
        model.addAttribute("products", products);
        return "thymeleaf/daily";
    }

    @GetMapping("/running")
    public String redirectToRunning(Model model) {
        String type = "RUNNING";
        String sortOption = "high";
        List<ProductVO> products = productDAO.product_Select(type, sortOption);
        model.addAttribute("products", products);
        return "thymeleaf/running";
    }

    @GetMapping("/football")
    public String redirectToFootball(Model model) {
        String type = "FOOTBALL";
        String sortOption = "high";
        List<ProductVO> products = productDAO.product_Select(type, sortOption);
        model.addAttribute("products", products);
        return "thymeleaf/football";
    }

    @GetMapping("/basketball")
    public String redirectToBasketball(Model model) {
        String type = "BASKETBALL";
        String sortOption = "high";
        List<ProductVO> products = productDAO.product_Select(type, sortOption);
        model.addAttribute("products", products);
        return "thymeleaf/basketball";
    }

    @GetMapping("/sandal")
    public String redirectToSandal(Model model) {
        String type = "SANDAL";
        String sortOption = "high";
        List<ProductVO> products = productDAO.product_Select(type, sortOption);
        model.addAttribute("products", products);
        return "thymeleaf/sandal";
    }

    @GetMapping("/products")
    public String getProducts(@RequestParam(required = false) String type,
                              @RequestParam(required = false) String sortOption,
                              Model model) {
        if (type == null) {
            // 기본값 설정 (예: "defaultType" 또는 다른 유효한 값)
            type = "defaultType"; // 이 값을 실제 사용하고자 하는 기본 타입으로 변경
        }

        if (sortOption == null) {
            sortOption = "popular"; // 기본값 설정
        }

        List<ProductVO> products = productDAO.productSelect(type, sortOption);
        model.addAttribute("products", products);
        return "thymeleaf/products"; // 타임리프 템플릿 이름
    }

}

