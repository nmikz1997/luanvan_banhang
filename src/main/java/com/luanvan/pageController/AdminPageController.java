package com.luanvan.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminPageController {
	
	@GetMapping("/")
	public String index() {
		return "admin/index";
	}
	
	
	@GetMapping("category")
	public String origin() {
		return "admin/category";
	}
	
//	
//	@GetMapping("origin")
//	public String origin() {
//		return "admin/origin";
//	}
//	
//	@GetMapping("store")
//	public String store() {
//		return "admin/store";
//	}
//	
//	@GetMapping("member-type")
//	public String memberType() {
//		return "admin/memberType";
//	}
	
}
