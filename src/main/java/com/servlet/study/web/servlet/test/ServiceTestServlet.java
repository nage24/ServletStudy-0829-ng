package com.servlet.study.web.servlet.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/service/test")
public class ServiceTestServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("첫 요청 때 한번 호출");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
		System.out.println("요청 들어오면 서비스 실행");
		System.out.println("요청 메소드: " + req.getMethod());
		
		getServletContext().setAttribute("servletApplicationData","우리가 저장한 데이터");
		
		// if(req.getMethod().equalsIgnoreCase("get")) //-> GenericServlet
		doGet(req, resp); // -> 500 에러가 날거임 . 메소드를 지원한다는 의미임. 
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Get 요청");
		System.out.println(getServletContext().getAttribute("servletApplicationData"));
		
	}
	
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("서블릿 소멸");
	}
}
