<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%! 
	// %! ; 선언식
	String name = "김준일";
	List<String> nameList = new ArrayList<String>();
%>

<% 
	// 스크립트릿 ; 실행부
	nameList.add("신짱구");
    nameList.add("김철수");
    nameList.add("한유리");
    nameList.add("이훈이");
    nameList.add("맹구");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table {
		border: 1px solid #333333
		border-collapse: collapse;
	}
	
	td, th {
		border: 1px solid #333333
		text-align: center;
	}
</style>

</head>
<body>
	<h1>hello jsp!</h1>
	
	<br>
	<table>
		<tr>
			<th>번호</th>
			<th>이름</th>
		</tr>
		
		<% 
			for(int i = 0; i < nameList.size(); i++) {
		%>
		
		<tr>
			<td><%=i + 1 %> </td>
			<td><%=nameList.get(i) %> </td>
		</tr>
		
		<%
			}
		%>
		
		
		
	</table>
	
	
</body>
</html>