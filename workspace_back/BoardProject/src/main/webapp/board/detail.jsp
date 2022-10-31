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
<script>
	$(function() {
		$("#delete,#update").hide();
		if ("${loginID }" == "${DTO.writer}") {
			$("#delete,#update").show();
		}

		$("#delete").on("click", function() {
			if (confirm("삭제하시겠습니까?")) {
				location.href = "/deleteList.board?seq=" + ${DTO.seq}+""
			}
		})

		$("#update").on("click", function() {
			location.href = "/update.board?seq=" + ${DTO.seq}+""
		})

		$("#back").on("click", function() {
			location.href = "/output.board?cpage=1";
// 			history.back();
		})
		

		
		$("#commentswrite").on("click", function(){
			let comments = $("#comments").val();
			location.href = "/insert.comments?seq=" + ${DTO.seq}+"&comments="+comments+""
		})
		
		$("#modify").on("click", function(){
			$("input").removeAttr("readonly");
			$("#modify").css("display","none");
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
		
		$(".commentdel").on("click", function(){
			let comments = $(this).attr("seq");
			location.href = "/delete.comments?seq=" + comments;
		})
		
		$(".modify").on("click", function() {
			let comments = $(this).attr("seq");
			location.href = "/update.comments?seq=" + comments;
		})
		
		$("#getfiles").on("click", function() {
			let seq = $(this).attr("seq");
			location.href = "/detail.board?seq=" + ${DTO.seq}+""
		})
		
		
		
		
		
	})
</script>
</head>
<body>
	<table border="1" width="800px" align="center">
		<tr>
			<th colspan=3>자유게시판</th>
		</tr>
		<c:choose>
			<c:when test="${not empty DTO }">

				<tr>
					<th>작성자 : ${DTO.writer }</th>
					<th>작성 날짜 : ${DTO.write_date }</th>
					<th>조회수 : ${DTO.view_count }</th>
				</tr>

				<tr>
					<td colspan=3 height="400px">${DTO.contents }</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="5" height="400px" align=center>표시할 내용이 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
		<tr>
			<th><button id="getfiles" seq=${DTO.seq }>파일목록 불러오기</button>
			<c:forEach var="i" items="${list }">
<%-- 				${i.seq }.<a href="/files/${i.sysName }"> --%>
				<a href="/download.file?sysname=${i.sysName }&oriname=${i.oriName}">${i.oriName }</a><br>
<%-- 				${i.oriName }</a> --%>
			</c:forEach>
		</tr>

		<tr>
			<th>
				<button type=button id="delete">삭제하기</button>
			</th>
			<th>
				<button type=button id="update">수정하기</button>
			</th>
			<th><button type=button id="back">목록으로</button>
		</tr>
	</table>
	
	
	
	<br>
	<table border="1" width="800px" align="center">
		<tr>
			<th colspan=3>COMMENT</th>
		</tr>

		<c:choose>

			<c:when test="${not empty list2 }">

				<c:forEach var="list2" items="${list2 }">

					<tr>
						<th align=left>${list2.seq }</th>
						<th colspan=2 align=left>${list2.writer }</th>
						<th colspan=2>${list2.write_date }</th>
					</tr>
					<tr>
						<th><input type="text" name="name" value="${list2.contents }" readonly>
<!-- 						<th id="btns"><input type="button" value="수정하기" id="modify"></th> -->
						<th id="btns"><button class="modify" name="commentdel" id="modify" seq=${list2.seq }>수정하기</button></th>
						<th><button class="commentdel" name="commentdel" seq=${list2.seq }>삭제하기</button>
					</tr>
					<tr>

					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="5" height="200px" align=center>댓글이 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>

	</table>
	<br>
	
	
	
	<table border="1" width="800px" align="center">
		<tr>

			<th colspan=3><textarea name="comments" cols="110" rows="5"
					placeholder="댓글을 입력해주세요"></textarea></th>
		</tr>


		<tr>
			<th align="right">
				<button type="button" id="commentswrite">작성하기</button>
				<button type=button id="commentsupdate">수정하기</button>
				<button type=button id="commentsdelete">삭제하기</button>
			</th>
		</tr>
	</table>
	
</body>
</html>