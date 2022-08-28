package com.servlet.study.web.servlet.repository;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/repository/test2")
public class TestServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("--------------------------------------");
		
		ServletContext context = getServletContext();
		System.out.println("application repository: " + context.getAttribute("appData"));
		System.out.println();
		
		// 요청 때만 살아있기 때문에 계속 null이 뜨게 됨. test 요청이랑 다르기 때문에 ... 
		System.out.println("request repository: " + request.getAttribute("reqData"));
  		System.out.println();
		
		HttpSession session = request.getSession();
		System.out.println("session repository: " + session.getAttribute("sessionData"));	
	}
}
