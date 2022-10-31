<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
* {
	text-align: center;
}
</style>
</head>
<body>
	<form action="input.movies">

		<table border=1 align=center>
			<tr>
				<th>Input Movie Title
			</tr>
			<tr>
				<td><input type=text name=title placeholder="Input Movie Title">
			</tr>
			<tr>
				<td><input type=text name=genre placeholder="Input Movie Genre">
			</tr>
			<!-- 
			<tr>
				<td><input type=text name=launch_date placeholder="Input Movie Launch_date">
			</tr>
			 -->
			<tr>
				<td><button>Apply</button> <a href="index.jsp"><button
							type=button>back</button></a>
			</tr>
		</table>
	</form>

</body>
</html>