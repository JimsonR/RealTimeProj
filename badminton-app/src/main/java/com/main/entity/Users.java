package com.main.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Users implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	private int userCode;
	private String userName;
	private String emailId; 
	private String password;
	private String jwtToken;
	private boolean isJwtTokenValid;
	private boolean isVerified;
	private String verificationToken;
	private String resetPasswordToken;
	private Long resetPasswordotp;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return emailId;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isVerified;
	}
}
