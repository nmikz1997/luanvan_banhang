package com.luanvan.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class Securityhandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
//		CustomUserDetails userDetail = (CustomUserDetails) authentication.getPrincipal();
//        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
//        
//        Date today = new Date();
        
//		if (roles.contains("ROLE_STORE") && !roles.contains("ROLE_ADMIN"))
//        {
//        	long thoiGianConLai = userDetail.getHethan().getTime() - today.getTime(); //milisecond
//        	long soGioConLai = thoiGianConLai/(60*60*1000);
//        	System.out.println("set session timeout "+ thoiGianConLai/1000);
//        	if( soGioConLai < (long) 3){
//        		request.getSession().setMaxInactiveInterval((int)thoiGianConLai/1000);
//        	}
//            
//        }
		response.sendRedirect(request.getContextPath());//redirect trang chủ
	}
	

}
