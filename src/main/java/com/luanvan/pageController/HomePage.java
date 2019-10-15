package com.luanvan.pageController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luanvan.service.ProductService;
import com.luanvan.service.UserService;

@Controller
@RequestMapping("/")
public class HomePage {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public String home() {
		return "homepage/home";
	}
	
	@GetMapping("/dang-nhap")
	public String login() {
		return "homepage/dangnhap";
	}
	
	@GetMapping("/dang-ky")
	public String register() {
		return "homepage/dangky";
	}
	
	@GetMapping("/quan-ly-tai-khoan")
	public String userInfo(Model model, Authentication auth, UserService user) {
		model.addAttribute("thongtin", auth.getName());
		return "homepage/quanlytaikhoan";
	}
	
	@GetMapping("/tro-thanh-nha-ban-hang")
	public String registerStore() {
		return "homepage/dangkybanhang";
	}
	
	@GetMapping("/store/dang-ky-thanh-vien")
	public String BuyMember() {
		return "homepage/thethanhvien";
	}
	
	@GetMapping("/tim-kiem")
	public String search(
			@RequestParam(value = "name",		required = false, defaultValue = "") String name,
			@RequestParam(value = "cate",		required = false, defaultValue = "") String cate,
			@RequestParam(value = "material",	required = false, defaultValue = "") String material,
			@RequestParam(value = "origin",		required = false, defaultValue = "") String origin,
			@RequestParam(value = "producer",	required = false, defaultValue = "") String producer,
			@RequestParam(value = "page",		required = false, defaultValue = "1") int page,
			@RequestParam(value = "ratting",	required = false, defaultValue = "0") int ratting,
			@RequestParam(value = "min-price",	required = false, defaultValue = "0") int minPrice,
			@RequestParam(value = "max-price",	required = false, defaultValue = "0") int maxPrice,
			Model model) {
		
		page -= 1;
		
		model.addAttribute("result",productService.findbyPlugName(
				name,
				cate,
				material,
				origin,
				producer,
				ratting,
				minPrice,
				maxPrice,
				page)
				);
		
		return "homepage/search";
	}
	
	@GetMapping("/chi-tiet-san-pham/{id}")
	public String search(@PathVariable Long id) {
		return "homepage/chitietsanpham";
	}
	
	@GetMapping("/gio-hang")
	public String shoppingCart() {
		return "homepage/giohang";
	}
	
	@GetMapping("/quan-ly-don-hang")
	public String quanlydonhang() {
		return "homepage/quanlydonhang";
	}
	
	@GetMapping("/chi-tiet-don-hang/{id}")
	public String chitietdonhang() {
		return "homepage/chitietdonhang";
	}
	
}
