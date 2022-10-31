<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message outputForm</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
</head>
<body>
	<table border=1 text-align=center>
		<tr>
			<th>seq
			<th>name
			<th>msg
		</tr>
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach var="i" items="${list}">
					<tr>
						<td>${i.seq}
						<td>${i.name}
						<td>${i.msg}
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td>출력할 내용이 없습니다.
				</tr>
			</c:otherwise>
		</c:choose>
		<tr>
			<td colspan=3><a href="index.html"><button>back</button></a>
		</tr>

	</table>

</body>
</html>