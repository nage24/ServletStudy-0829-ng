<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
		<link rel="stylesheet" href="/static/css/signup.css">
		<script src="https://code.jquery.com/jquery-latest.min.js"></script>
	</head>
	<body>
		<form action="#">
			<table>
				<thead>
					<tr>
						<th colspan="2">회원가입</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>아이디: </td>
						<td><input type="text" name="user_id" class="user-id"></td>
						<!-- form요청 할 때 name 태그가 필요했는데 폼요청 할거 아니면 필요없다.  -->
						<!-- 클래스 언더바 쓰면 안됨! 나는 귀찮아서 그냥 뒀다... 방금 고쳤다.-->
					</tr>
					<tr>
						<td>비밀번호: </td>
						<td><input type="password" name="user_password" class="user-password"></td>
					</tr>
					<tr>
						<td>이름: </td>
						<td><input type="text" name="user_name" class="user-name"></td>
					</tr>
					<tr>
						<td>이메일: </td>
						<td><input type="text" name="user_email" class="user-email"></td>
					</tr>
					
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<button type="button" class="signup-button">회원가입</button>
							<button type="reset">다시작성</button>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
		<script type="text/javascript" src="/static/js/auth/signup.js"></script>
	</body>
</html>