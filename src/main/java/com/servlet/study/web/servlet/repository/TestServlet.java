package com.servlet.study.web.servlet.repository;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/repository/test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("--------------------------------------");
		
		ServletContext context = getServletContext();
		System.out.println("application repository: " + context.getAttribute("appData"));
		System.out.println();
		
		// 얘는 그럼 어따 써먹음ㅡㅡ? -> 필터에서 씀! 
		// 얘는 요청때만 살아있기 때문에 계속 null이 뜨는 것임. test 요청이랑 다르기 때문에 ... 
		// System.out.println("request repository: " + request.getAttribute("reqData"));
		System.out.println("request repository: " + request.getAttribute("frontFilter"));
		request.setAttribute("backFilter", "후처리 데이터");
		System.out.println();
		
		// 쿠키 개념. Cookie JSESSIONID=EA02EB175D4C2C722C38ECA55845A86C 유효기간이 있다. 
		HttpSession session = request.getSession();
		System.out.println("session repository: " + session.getAttribute("sessionData"));
		
	}

}
