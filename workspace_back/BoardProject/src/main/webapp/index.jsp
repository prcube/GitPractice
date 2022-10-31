<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
</head>
<style>
* {
	box-sizing: border-box;
}

div {
	border: 0.5px solid black;
}

.container {
	width: 300px;
	height: 200px;
	margin: auto;
	overflow: hidden;
}

.header {
	float: left;
	text-align: center;
	width: 100%;
	height: 15%
}

.lmain {
	float: left;
	width: 100%;
	height: 35%;
}

.id {
	height: 50%;
}

.pw {
	height: 50%;
}

#idbox {
	width: 30%;
	height: 100%;
	float: left;
}

#idinput {
	width: 70%;
	height: 100%;
}

#pwbox {
	width: 30%;
	height: 100%;
	float: left;
}

#pwinput {
	width: 70%;
	height: 100%;
}

.footer {
	float: left;
	text-align: center;
	height: 50%;
	width: 100%;
}

.login {
	height: 50px;
}
</style>
<body>

	<c:choose>
		<c:when test="${loginID != null }">
			<table border=1 align=center>
				<tr>
					<th colspan=4>${loginID }님안녕하세요
				</tr>
				<tr>
					<td><button id="toBoard">자유게시판</button>
					<td><button id="mypage" name="mypage">마이페이지</button>
					<td><button id="logout">로그아웃</button>
					<td><button id="memberout" name="memberout">회원탈퇴</button>
				</tr>
			</table>


			<script>
				$("#toBoard").on("click", function() {
					location.href = "/output.board?cpage=1";
				})

				$("#logout").on("click", function() {
					location.href = "/logout.mem";
				})

				$("#mypage").on("click", function() {
					location.href = "/mypage.mem";
				})

				$("#memberout").on("click", function() {
					if (confirm("탈퇴하십니까?")) {
						location.href = "/memberout.mem"
					}
				})
			</script>
		</c:when>
		<c:otherwise>
			<form action="/login.mem" method="post">
				<div class="container">
					<div class="header">Login Box</div>

					<div class="lmain">
						<div class=id>
							<div id=idbox>아이디 :</div>
							<input type="text" id=idinput name=id placeholder="Input your ID">
						</div>

						<div class=pw>
							<div id=pwbox>패스워드 :</div>
							<input type="text" id=pwinput name=pw placeholder="Input your PW">
						</div>
					</div>

					<div class="footer">
						<input type="submit" value="로그인" class="loginbtn"> <a
							href="member/signup.jsp"> <input type=button value="회원가입"></a><br>
						<input type="checkbox" name="rememberid">ID 기억하기
					</div>
				</div>
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>