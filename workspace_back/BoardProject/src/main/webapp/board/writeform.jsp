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
	<form action="/insert.board" method="post" enctype="multipart/form-data">
		<table border="1" width="800px" align="center">
			<tr>
				<th>자유게시판 글 작성하기</th>
			</tr>
			<tr>
				<td colspan="5" ><select>
						<option>뉴스</option>
						<option>유머</option>
						<option>사설</option>
				</select> <input type="text" name=title placeholder="글 제목을 입력하세요." size="80"></td>
			</tr>
			<tr>
				<td colspan="5" height="400"><textarea name=contents
						placeholder="글 내용을 입력하세요." cols="110" rows="20"></textarea></td>
			</tr>

			<tr>
				<td><button id="fileAdd" type=button>+</button></td>

				<td colspan="4" align=right><a href="/output.board"> <input
						type="button" value="목록으로">
				</a> <input type="submit" value="작성완료" id="input"></td>
			</tr>
		</table>
	</form>
	<script>
	
	let count = 0;
	$("#fileAdd").on("click", function() {
		/* 파일 4개까지만 통제 */
		if ($("input[type=file]").length > 4) {
			alert("파일은 최대 5개까지만 업로드가 가능합니다.");
			return;
		}
		let fileDiv = $("<div>");

		/* 누르면 input타입파일을 만듬. */
		let inputFile = $("<input>");
		inputFile.attr("type", "file");
		inputFile.attr("name", "file" + count++);

		let delBtn = $("<a>");
		delBtn.html("x");
		delBtn.addClass("line-del");
		delBtn.on("click", function() {
			$(this).parent().remove();
		});

		/* 파일지움.  */
		fileDiv.append(inputFile);
		fileDiv.append(delBtn);

		$("#fileAdd").parent().after(fileDiv);

	})
</script>
	

</body>
</html>