<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cookie</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
	<style type="text/css">
		
		* {
			box-sizing: border-box;
		}
		
		body {
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
		}
		
		table {
			border-collapse: collapse;
			border: 1px solid #141414;
			width: 1300px;
		
		}
		
		th, td {
			border: 1px solid #141414;
			text-align: center;
		}
		
		
		
	</style>
</head>
<body>
	<input class="cookie-key" placeholder="key값">
	<input class="cookie-value" placeholder="value값">
	<button type="button" class="add-button">수정</button>


	<table>
		<thead>
			<tr>
				<td>번호</td>
				<td>이름</td>
				<td>콘텐츠</td>
				<td>도메인</td>
				<td>경로</td>
				<td>만료시간</td>
			
			</tr>
		</thead>
		<tbody>
		
		</tbody>
	</table>
	<script type="text/javascript" src="/static/js/cookie.js"></script>
</body>
</html>