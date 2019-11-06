package com.luanvan.model;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class CustomUserDetails implements UserDetails{

	private static final long serialVersionUID = 8484954795676190459L;

	private User user;
	private Set<GrantedAuthority> grantedAuthorities;
	private Long storeId = null;
	private Long customerId = null;
	private Date hethan;
	
	public CustomUserDetails(User user, Set<GrantedAuthority> grantedAuthorities, Long storeId, Long customerId, Date hethan) {
		this.user = user;
		this.grantedAuthorities = grantedAuthorities;
		this.storeId = storeId;
		this.customerId = customerId;
		this.hethan = hethan;
	}
	
	
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
	public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
