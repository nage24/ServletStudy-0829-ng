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

@WebServlet("/api/v1/user")
public class UserApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserService userService;

    public UserApi() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	userService = (UserService) config.getServletContext().getAttribute("userService");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		String userName = request.getParameter("username");
		String userEmail = request.getParameter("userEmail");
		
		User user = User.builder()
					.user_id(userId)
					.user_password(userPassword)
					.user_name(userName)
					.user_email(userEmail)
					.build();
		
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(userService.addUser(user));
	}
	
//	@Override // override 는 있어도 없어도 됨. 
//	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String userCode = request.getParameter("userCode"); // JSON 통신 Request Parameter -> GET POST 만
//		String phone = request.getParameter("phone");
//		String address = request.getParameter("address");
//		
//		System.out.println("userCode: " + userCode);
//		System.out.println("phone: " + phone);
//		System.out.println("address: " + address);
//	}
	
}
