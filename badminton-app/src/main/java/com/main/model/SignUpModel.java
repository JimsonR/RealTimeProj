package com.main.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class SignUpModel {
	private String userName;
	private String emailId;
	private String password; 
}
