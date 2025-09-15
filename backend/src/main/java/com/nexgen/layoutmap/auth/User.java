package com.nexgen.layoutmap.auth;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.HashSet;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private String email;
    private String password;

    private Set<String> roles = new HashSet<>();

	/**
	 * @return the roles
	 */
	public Set<String> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
		
	}

	public void setEmail(String email) {
		this.email = email;
		
	}

	public void setPassword(String encode) {
		this.password = encode;
		
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	

	
}