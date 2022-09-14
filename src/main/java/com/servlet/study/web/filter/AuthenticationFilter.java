package com.servlet.study.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlet.study.web.servlet.dto.PrincipalUser;

@WebFilter("/*")
public class AuthenticationFilter extends HttpFilter implements Filter {
       
    public AuthenticationFilter() {
        super();
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// ServletRequest -> 세션을 가져올 수 없다 .. HttpServletRequest 가 ServletRequest를 상속함. HttpServletRequest 요청이 업캐스팅 되어 들어오는것임. 
		// System.out.println("test");
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession(); // http인 상태에서만 세션을 쓸 수 이씀
		
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		
		String uri = httpServletRequest.getRequestURI();
		
		if(!uri.contains("static") && !uri.contains("index") && !uri.contains("auth/signin") && !uri.contains("auth/signup")) { // 얘네는 필터 안 거친다. 
			
			PrincipalUser principalUser = (PrincipalUser) session.getAttribute("principal");
			
			if(principalUser == null) {
				if(!uri.contains("auth/signin")) {
					session.setAttribute("preuri", uri);
				}
				
				
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("<html>");
				stringBuilder.append("<body>");
				stringBuilder.append("<script>");
				stringBuilder.append("alert(\"로그인 후 접근 할 수 있습니다.\");");
				stringBuilder.append("location.replace(\"/auth/signin\");");
				stringBuilder.append("</script>");
				stringBuilder.append("</body>");
				stringBuilder.append("</html>");
				
				
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().print(stringBuilder.toString());
				return;
				
			}else if(uri.contains("auth")){
				String preUri = (String) session.getAttribute("preuri");
				if(preUri == null) {
					httpServletResponse.sendRedirect("/index");
				}else {
					httpServletResponse.sendRedirect(preUri);
				}
				session.setAttribute("preuri", null);
				return;
				
				// user management에 들어가면 바로 signin 페이지로 보내줌. 그럼 로그인 했을 때 이전페이지(user management)로 보내주고, 
				// uri를 쳐서 signin 페이지로 들어가면 이전페이지가 존재하지 않기 때문에  /index로 보내주는 것임. 
				// 나중에 이전 페이지가 없는 경우에는 default uri로 보낼 수 있게 할 거임. 
			}
			
		}
		
		chain.doFilter(request, response);
		
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
