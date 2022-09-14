package com.servlet.study.web.servlet.cookie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@WebServlet("/cookie/test")
public class CookieTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CookieTestServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String flag = request.getParameter("flag");
		
		
		if(flag != null) {
			//api 요청
			Cookie[] cookies = request.getCookies();
			Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
			
			Map<String, Object> jsonObject = new HashMap<String, Object>();
			List<JsonObject> jsonObjects = new ArrayList<JsonObject>();
			
			
			for(Cookie cookie : cookies) {
				JsonObject object = new JsonObject();
				object.addProperty("name", cookie.getName());
				object.addProperty("value", cookie.getValue());
				object.addProperty("domain", cookie.getDomain());
				object.addProperty("path", cookie.getPath());
				object.addProperty("maxAge", cookie.getMaxAge());
				jsonObjects.add(object);
				
				// System.out.println("이름: " + cookie.getName());
				// System.out.println("콘텐츠: " + cookie.getValue());
			}
			
			jsonObject.put("cookies", jsonObjects);
			
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(gson.toJson(jsonObject));
			
			
		}else {
			//page 요청
			request.getRequestDispatcher("/WEB-INF/views/cookie.jsp").forward(request, response);
		}
		
		
		
		Cookie[] cookies = request.getCookies(); // 무족건 배열로 가져오게됨. 
		response.addCookie(null);
		// 클라이언트가 쿠키를 가지고 있음. 요청 때 쿠키에서 정보를 가져와서 응답할 때 쿠키에 정보를 가져다 주는거임. 
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
		String value = request.getParameter("value");
		
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(60 * 60 * 24 * 365); // 1년동안 쿠키를 유지해라 ... jsessionid
		response.addCookie(cookie);
		
		response.setContentType("application/json; charset=utf-8"); 
		response.getWriter().print("{\"status\":true}");
		
	}

}
