package com.servlet.study.web.servlet.user.api;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet.study.web.domain.user.User;
import com.servlet.study.web.service.UserService;

@WebServlet("/api/v1/user/update")
public class UserUpdateApi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService;
	
    public UserUpdateApi() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	userService = (UserService) config.getServletContext();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userCode = request.getParameter("userCode"); // JSON 통신 Request Parameter -> GET POST 만
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		User user = User.builder()
					.user_code(Integer.parseInt(userCode))
					.user_phone(phone)
					.user_address(address)
					.build();
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(userService.updateUser(user));
		
		System.out.println("userCode: " + userCode);
		System.out.println("phone: " + phone);
		System.out.println("address: " + address);
	}

}
