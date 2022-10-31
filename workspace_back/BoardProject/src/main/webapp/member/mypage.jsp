<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My page</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script>
	$(function(){
		$("#modify").on("click",function(){
			$("input").removeAttr("readonly");
			
			$("#toHome,#modify").css("display","none");
			
			let btnModify = $("<button>");
			btnModify.text("수정완료");
			btnModify.css("margin-right","5px")
			
			let btnCancel = $("<button>");
			btnCancel.attr("type","button");
			btnCancel.text("취소");
			btnCancel.on("click",function(){location.reload();});
			
			$("#btns").append(btnModify);
			$("#btns").append(btnCancel);
		})
	})
</script>
</head>
<body>
<form action="/update.mem" method="post">
	<table border=1 align=center>
		<tr>
			<th colspan=4>${loginID }님의 마이페이지
		</tr>

		<c:forEach var="i" items="${list }">
			<tr>
				<th>Name :
				<th><input type="text" name="name" value="${i.name }" readonly>
			</tr>
			<tr>
				<th>Phone :
				<th><input type="text" name="phone" value="${i.phone }" readonly>
			</tr>
			<tr>
				<th>Email :
				<th><input type="text" name="email" value="${i.email }" readonly>
			</tr>
			<tr>
				<th>날짜 :
				<th><input type="text" name="signup_date" value="${i.signup_date }" readonly>
			</tr>
		</c:forEach>
		
		<th colspan=4 id="btns">
		<input type="button" value="정보 수정" id="modify">
		<a href="/index.jsp"><input type="button" value="뒤로가기"></a>
	</table>
</form>
</body>
</html>