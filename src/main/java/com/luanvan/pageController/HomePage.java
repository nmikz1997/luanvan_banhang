package com.luanvan.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomePage {
	
	@GetMapping
	public String home() {
		return "homepage/home";
	}
	
	@GetMapping("/search")
	public String search(
			@RequestParam(value = "name",required = false) String name,
			@RequestParam(value = "sort",required = false) String sort,
			@RequestParam(value = "page") Integer page,
			@RequestParam(value = "ratting",required = false) String ratting,
			@RequestParam(value = "min-price",required = false) Integer minPrice,
			@RequestParam(value = "max-price",required = false) Integer maxPrice,
			Model model) {
		model.addAttribute("name", name);
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
}