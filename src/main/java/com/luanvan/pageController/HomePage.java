package com.luanvan.pageController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luanvan.dto.response.ProductDTO;
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
	public String search() {
		return "homepage/search";
	}
	
	@GetMapping("/query")
	@ResponseBody
	public Page<ProductDTO> query(
			@RequestParam(value = "name",		required = false, defaultValue = "%%") String name,
			@RequestParam(value = "cate",		required = false, defaultValue = "") Long cate,
			@RequestParam(value = "material",	required = false, defaultValue = "%%") String material,
			@RequestParam(value = "origin",		required = false, defaultValue = "%%") String origin,
			@RequestParam(value = "producer",	required = false, defaultValue = "%%") String producer,
			@RequestParam(value = "store",		required = false, defaultValue = "%%") String store,
			@RequestParam(value = "page",		required = false, defaultValue = "1") int page,
			@RequestParam(value = "ratting",	required = false, defaultValue = "0") int ratting,
			@RequestParam(value = "filter",		required = false, defaultValue = "0") String filter,
			Model model) {
		
		page -= 1;
		
		return productService.findbyPlugName(name,cate,material,origin,producer,store,ratting,filter,page);
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
