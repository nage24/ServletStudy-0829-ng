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

@WebFilter("/*") // * ; 전체, 모든 요청에 대해 이 필터를 거치겠다는 말임. 
public class CharacterEncodingFilter extends HttpFilter implements Filter {
      
	private static final long serialVersionUID = 1L;

	public CharacterEncodingFilter() {
        super();
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		request.setCharacterEncoding("utf-8");
		
		chain.doFilter(request, response); //chain 전 후가 있음 전에 적음 전처리, 후에 적음 후처리임. 
		
		response.setCharacterEncoding("utf-8");
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
