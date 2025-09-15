package com.nexgen.layoutmap.dto;

import org.springframework.data.mongodb.core.index.Indexed;

public class AuthRequest {
	
	
    private String password;
    private String email;
    
    @Indexed(unique = true)
    private String username;
    /**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

    // Getters and setters
}

