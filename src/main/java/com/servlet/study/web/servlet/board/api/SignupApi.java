package com.servlet.study.web.servlet.board.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/signup")
public class SignupApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public SignupApi() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("id: ");
		System.out.print(request.getParameter("user_id"));
		System.out.print("password: ");
		System.out.print(request.getParameter("user_password"));
		System.out.print("name: ");
		System.out.print(request.getParameter("user_name"));
		System.out.print("name: ");
		System.out.print(request.getParameter("user_email"));
	}

}
