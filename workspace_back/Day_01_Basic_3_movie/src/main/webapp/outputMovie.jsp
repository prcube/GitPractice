<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border=1 align=center>
		<tr>
			<th colspan=4>NetflixManager
		</tr>
		<tr>
			<th>Id
			<th>Title
			<th>Genre
			<th>Launch_date
		</tr>
		<c:choose>
			<c:when test="${not empty list }">
				<c:forEach var="i" items="${list }">
					<tr>
						<td>${i.seq }
						<td>${i.title }
						<td>${i.genre }
						<td>${i.launch_date }
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<th colspan=4>출력할 내용이 없습니다.
				</tr>
			</c:otherwise>
		</c:choose>

		<tr>
			<td colspan=4 align=center>
				<form action="delete.movies">
					<input type=text name=seq placeholder="Input id to delete">
					<button>Delete</button>
				</form>
		</tr>

		<tr>
			<td colspan=4 align=center>
				<form action="update.movies">
					<input type=text name=seq placeholder="Input id to modify"><br>
					<input type=text name=title placeholder="Input title to modify"><br>
					<input type=text name=genre placeholder="Input genre to modify"><br>
					<button>Modify</button>

				</form>
		</tr>
		<tr>
			<td colspan=4 align=center><a href="search.jsp"><button>Search</button></a>
		</tr>

		<tr>
			<td colspan=4 align=center><a href="index.jsp"><button>Back</button></a>
		</tr>
	</table>

</body>
</html>