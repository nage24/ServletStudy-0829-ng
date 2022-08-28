package com.servlet.study.web.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data 
@Builder
public class SignupRequestDto {
	private String userId;
	private String userPassword;
	private String userName;
	private String userEmail;
}
