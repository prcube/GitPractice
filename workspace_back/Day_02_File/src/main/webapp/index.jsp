<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js">
</script>
</head>
<body>
	<form action="/upload.file" method="post" enctype="multipart/form-data">
		<input type=text name="msg"><br> <input type=file
			name="file"><br> <input type=submit>
	</form>
	<a href="/list.file"><button>파일 목록 불러오기</button></a>
	<fieldset>
		<legend>File list</legend>
		<c:forEach var="i" items="${list }">
			<div>${i.seq }. <a href="/files/${i.sysName }">${i.oriName }</a></div>
		</c:forEach>
	</fieldset>

</body>
</html>