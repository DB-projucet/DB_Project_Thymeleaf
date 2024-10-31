package com.kh.teamproject;

import com.kh.teamproject.dao.ProductDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class TeamprojectApplication implements CommandLineRunner {
	private final ProductDAO productDAO; // 변수 이름 변경
	public TeamprojectApplication(ProductDAO productDAO) {
		this.productDAO = productDAO; // 변경
	}

	public static void main(String[] args) {
		SpringApplication.run(TeamprojectApplication.class, args);

		// Open browser after the application starts
		//openHomePage();
	}

	// 애플리케이션 시작 후 브라우저 열기
	@EventListener(ApplicationReadyEvent.class)
	public void openBrowser() {
		String url = "http://localhost:8112/producttb/home";
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(new URI(url));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}


	@RestController
	public class HomeController {
		@GetMapping("/")
		public String home() {
			return "redirect:/review"; // 기본 경로 리다이렉트
		}
	}


	@Override
	public void run(String... args) throws Exception {
		sortOptionSelect();
	}
	public void sortOptionSelect() {
	}

}
