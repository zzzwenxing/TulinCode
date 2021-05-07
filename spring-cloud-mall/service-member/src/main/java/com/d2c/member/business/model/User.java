package com.d2c.member.business.model;

import java.io.Serializable;

import lombok.NonNull;

public class User implements Serializable {

	Long id;
    @NonNull
    String username;
    @NonNull
    String password;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
}
