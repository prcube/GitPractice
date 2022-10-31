<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	src = "https://code.jquery.com/jquery-3.6.1.js"
</script>
</head>
<body>
	<table border="1" align=center width="800px">
		<tr>
			<th colspan="5">자유게시판</th>
		</tr>
		<tr align=center>
			<td width="5%">번호</td>
			<td width="50%">제목</td>
			<td width="15%">작성자</td>
			<td width="25%">날짜</td>
			<td width="5%">조회</td>
		</tr>

		<c:choose>
			<c:when test="${not empty list }">
				<c:forEach var="i" items="${list }">
					<tr>
						<td width="5%">${i.seq }</td>
						<td width="50%"><a href="/detail.board?seq=${i.seq }">${i.title }</a></td>
						<td width="15%">${i.writer }</td>
						<td width="25%">${i.write_date }</td>
						<td width="5%">${i.view_count }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="5" height="400px" align=center>표시할 내용이 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>

		<tr>
			<td colspan="5" align=center>${navi }</td>
			
		</tr>
		<tr>
			<td colspan="5" align=right><a href="/index.jsp"> <input
					type="button" value="목록으로">
			</a> <a href="/board/writeform.jsp"><input type="button" value="작성하기"></a></td>

		</tr>
	</table>

</body>
</html>