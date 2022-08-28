package com.servlet.study.web.servlet.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/content/type")
public class ContentTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("요청 URL: " + request.getRequestURL());
		System.out.println("요청 Method: " + request.getMethod());
		System.out.println("요청 param(id):" + request.getParameter("id"));
		System.out.println("요청 param(pw):" + request.getParameter("password"));
		System.out.println("요청 param(email):" + request.getParameter("email"));
		
		request.getRequestDispatcher("/WEB-INF/dispatcher-test.html").forward(request, response);
		// 이 서블릿 파일은 서버 내이기 때문에 web-inf 안에 있는 html 파일을 열어볼 수 있는 것임. 
		
		
		//response.setContentType("application/json; charset=utf-8");
		
		//response.getWriter().print("{\"name\":\"junil\"}");
		
//		response.getWriter().print("<html>");
//		response.getWriter().print("<head>");
//		response.getWriter().print("<title>contentType</title>");
//		response.getWriter().print("</head>");
//		response.getWriter().print("<bodt>");
//		response.getWriter().print("<h1>ContentType Test!</h1>");
//		response.getWriter().print("</body>");
//		response.getWriter().print("</html>");
	}
}
