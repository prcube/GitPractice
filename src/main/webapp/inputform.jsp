<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
</head>
<style>
	*{
	text-align : center;
	}
</style>
<body>

	<form action = "input.git">
		<table border = 1 align = center>
			<tr>
				<th>Input Message
			</tr>
			<tr>
				<td><input type = text name = name placeholder = "Input your name">
			</tr>
			<tr>
				<td><input type = text name = msg placeholder = "input your msg">
			</tr>
			
			<tr>
				<td><button>보내기</button>
				<a href = "index.jsp"><button type = button>뒤로가기</button></a>
			</tr>
			
		
		
		</table>
	
	
	</form>


</body>
</html>