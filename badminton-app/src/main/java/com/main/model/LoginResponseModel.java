package com.main.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseModel {
	String jwtToken;
}
