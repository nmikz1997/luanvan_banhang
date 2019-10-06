package com.luanvan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/*").permitAll()
                .antMatchers("/admin/*").hasRole("ADMIN")
                .antMatchers("/store/*").hasAnyRole("ADMIN","STORE")
                .antMatchers(HttpMethod.POST,"orders").authenticated()
                .and()
            .formLogin()
            	.loginProcessingUrl("/dang-nhap")
            	.loginPage("/dang-nhap")
            	.usernameParameter("email")
            	.passwordParameter("password")
            	.defaultSuccessUrl("/")
            	.failureUrl("/dang-nhap?error=true")
            	.and()
            .logout()
            	.logoutSuccessUrl("/")
            	.and()
        	.exceptionHandling()
    			.accessDeniedPage("/403");
        http
        	.csrf().disable();
    }
}
