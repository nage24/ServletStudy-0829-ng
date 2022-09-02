package com.servlet.study.web.service;

import com.servlet.study.web.domain.user.User;

public interface UserService {
	public String getUserList();
	public String checkUserId(String userId);
	public String addUser(User user);
	
	// 서비스에서 기능을 모두 ! 구현하고 api(controller) 에서 구현해서 사용한다 는 개념...
}
