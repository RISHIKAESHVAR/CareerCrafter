package com.hexaware.app.Dto;

import com.hexaware.app.Enums.UserRole;

public class AuthenticationRequest {
	private String email;
    private String password;
    private UserRole role;
    
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "AuthenticationRequest [email=" + email + ", password=" + password + ", role=" + role + "]";
	}
	
}
