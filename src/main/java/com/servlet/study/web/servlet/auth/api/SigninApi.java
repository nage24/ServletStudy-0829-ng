package com.servlet.study.web.servlet.auth.api;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.AbstractDocument.Content;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.servlet.study.web.dto.auth.SignupRequestDto;
import com.servlet.study.web.service.UserService;
import com.servlet.study.web.servlet.dto.PrincipalUser;

@WebServlet("/api/v1/auth/signin") // http://localhost:8000//api/v1/auth/signin <- 로그인 된 정보 확인
public class SigninApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
       
    public SigninApi() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	userService = (UserService) config.getServletContext().getAttribute("userService");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    	
    	HttpSession session = request.getSession();
    	
    	PrincipalUser principalUser = (PrincipalUser) session.getAttribute("principal");
    	
    	response.setContentType("application/json; charset=utf-8");
    	response.getWriter().print(gson.toJson(principalUser));
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userJson = request.getParameter("user");
		
		Gson jsonUser = new GsonBuilder().setPrettyPrinting().create();
		
		Map<String, Object> jsonObject = jsonUser.fromJson(userJson, Map.class);
		
		String userId = jsonObject.get("userId").toString();
		String userPassword = jsonObject.get("userPassword").toString();
		
	
		PrincipalUser principalUser = userService.signin(userId, userPassword);
		
		Gson responseData = new Gson();
		JsonObject data = new JsonObject();
		
		if(principalUser == null) {
			data.addProperty("status", false);
		}else {
			data.addProperty("status", true);
			
			// 세션 저장소에 로그인 정보 10분간 보관
			HttpSession session = request.getSession();
			session.setAttribute("principal", principalUser);
			session.setMaxInactiveInterval(60 * 10);
		}
		
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(responseData.toJson(data));
		
		
		
		
		
		// ServletContext context = request.getServletContext();
		// SignupRequestDto signupRequestDto = (SignupRequestDto) context.getAttribute("userData");
		
		
//		
//		if(signupRequestDto.getUserId().equals(userId)) {
//			if(signupRequestDto.getUserPassword().equals(userPassword)) {
//				System.out.println("로그인 인증 성공");
//				
//				data.addProperty("status", true);
//				
//				response.setContentType("application/json; charset=utf-8");
//				response.getWriter().print(responseData.toJson(data));
//				return;
//			}
//		}else{
//			data.addProperty("status", false);
//			
//			
//			response.setContentType("application/json; charset=utf-8");
//			response.getWriter().print(responseData.toJson(data));
//			return;
//		}
		
		
	}

}






