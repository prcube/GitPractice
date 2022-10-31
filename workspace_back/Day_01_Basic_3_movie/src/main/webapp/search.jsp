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
			<th colspan=4>Search
		</tr>
		<tr>
			<th>Id
			<th>Title
			<th>Genre
			<th>Launch_date
		</tr>
		<c:choose>
			<c:when test="${not empty slist }">
				<c:forEach var="i" items="${slist }">
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
					<td colspan=4 align=center>No result, check the title
				</tr>
			</c:otherwise>
		</c:choose>

		<tr>
			<td colspan=4>
				<form action="search.movies">
					<input type=text name=sid placeholder="Enter your keywords">
					<button>Search</button>
				</form>
		</tr>
		<tr>
			<td colspan=4 align=center><a href="index.jsp"><button>Index</button></a>
				<a href="output.movies"><button>Output</button></a>
		</tr>
</body>
</html>