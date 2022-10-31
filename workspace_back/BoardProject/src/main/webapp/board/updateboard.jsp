<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script>

</script>
</head>
<body>

	<form action="/updateList.board?seq=${DTO.seq}" method="post">
		<table border="1" width="800px" align="center">

			<tr>
				<th colspan=3>자유게시판2222222222</th>
			</tr>

			<c:choose>
				<c:when test="${not empty DTO }">
					<tr>
						<th>작성자 : ${DTO.writer }</th>
						<th>작성 날짜 : ${DTO.write_date }</th>
						<th>조회수 : ${DTO.view_count }</th>
					</tr>

					<tr>
						<td colspan=3 height="400px"><textarea cols="110" rows="30"	id="contents" name="contents">
						${DTO.contents }</textarea></td>
					</tr>

				</c:when>
			</c:choose>



			<tr>
				<th></th>
				<th><a href="/updateList.board?seq=${DTO.seq}"><button id="update">수정하기</button></a>
				<th><a href="/output.board"><input type="button"
						value="목록으로"></a></th>
			</tr>

		</table>
	</form>
</body>
</html>