<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	width: 100%;
	height: 90vh;
}

.result {
	height: 70vh;
	text-align: center;
	font-size: 20px
}

td {
	text-align: center;
}
</style>

</head>
<body>
	<table border=1>
		<tr>
			<th>조회 결과
		</tr>
		<c:choose>
			<c:when test="${result }">
				<tr>
					<td class="result">이미 사용중인 ID 입니다.
				</tr>
				<tr>
					<td><button id="close">닫기</button>
					<script>
					document.getElementById("close").onclick=function(){
						opener.document.getElementById("id").value="";
						window.close();
					}
					</script>
				</tr>

			</c:when>

			<c:otherwise>
				<tr>
					<td class="result">"${id }"<br>은(는) 사용할 수 있는 ID 입니다.
				</tr>
				<tr>
					<td><button id="use">사용</button>
						<button id="cancel">취소</button> <script>
						document.getElementById("use").onclick=function(){
							window.close();
						}
						document.getElementById("cancel").onclick=function(){
							opener.document.getElementById("id").value="";
							window.close();
						}
						</script>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>

</body>
</html>