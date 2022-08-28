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
	nameList.add("김준일");
	nameList.add("조문기");
	nameList.add("박소영");
	nameList.add("서재효");
	nameList.add("신승한");
	nameList.add("이승환");
	nameList.add("최연호");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table {
		border: 1px solid #141414
		border-collapse: collapse;
		
	}
	
	td, th {
		border: 1px solid #141414
		
		
		text-align: center;
	}
</style>

</head>
<body>
	<h1>hello jsp!</h1>
	
	<% // 자바 코드는 스크립틀릿 안에서. 일종의 main과 같이 실행부를 적어주는것임 . 
		for(int i = 0; i < 10; i++) {
	%>
	<label>이름 : </label><%=name %><br>
	
	<%		// 이런식으로 스크립틀릿안에 태그를 반복시킬 수도 있음. !!
		}
	%>
	
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