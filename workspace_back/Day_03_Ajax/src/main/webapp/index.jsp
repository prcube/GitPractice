<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
</head>
<body>

	<fieldset>
		<Legend>Exam01. Simple AJAX</Legend>
		<button id="exam01">Exam01</button>
		<script>
			$("#exam01").on("click", function() {
				$.ajax({
					url : "/exam01.ajax",
					type : "get"
				});
				// ! AJAX는 응답을 기다리지 않고 다음 코드로 넘어간다.
			})
		</script>
	</fieldset>
	<hr>
	<fieldset>
		<Legend>Exam02.AJAX With Parameter</Legend>
		<button id="exam02">Exam02</button>
		<script>
			$("#exam02").on("click", function() {
				$.ajax({
					url : "/exam02.ajax",
					type : "get",
					data : {
						"key1" : "Value1",
						"key2" : "Value2"
					}
				});
				// ! AJAX는 응답을 기다리지 않고 다음 코드로 넘어간다.
			})
		</script>
	</fieldset>
	<hr>
	<fieldset>
		<Legend>Exam03.AJAX Response</Legend>
		<button id="exam03">Exam03</button>
		<script>
			$("#exam03").on("click", function() {
				$.ajax({
					url : "/exam03.ajax"
				}).done(function(resp) {
					console.log(resp);
				}); //ajax 통신이 끝났을 때 done을 사용
				// ! AJAX는 응답을 기다리지 않고 다음 코드로 넘어간다.
			})
		</script>
	</fieldset>
	<hr>
	<fieldset>
		<Legend>Exam04.AJAX Response with multiple values</Legend>
		<button id="exam04">Exam04</button>
		<script>
			$("#exam04").on("click", function() {
				$.ajax({
					url : "/exam04.ajax"
				}).done(function(resp) {
					console.log(resp);
					let array = JSON.parse(resp);
					console.log(array[0]);
					console.log(array[1]);
					console.log(array[2]);
				});
			})
		</script>
	</fieldset>
	<hr>


	<!-- 	dto 타입 반환해주는것 -->
	<fieldset>
		<Legend>Exam05.AJAX Response with Java Object</Legend>
		<button id="exam05">Exam05</button>
		<script>
			$("#exam05").on("click", function() {
				$.ajax({
					url : "/exam05.ajax"
				}).done(function(resp) {
					let result = JSON.parse(resp);
					console.log(result.seq);
				});
			})
		</script>
	</fieldset>
	<hr>
	<fieldset>
		<Legend>Exam06.AJAX Response with Java Object</Legend>
		<button id="exam06">Exam06</button>
		<script>
			$("#exam06").on("click", function() {
				$.ajax({
					url : "/exam06.ajax",
					
// 					ajax가 실행되고 돌아오는 데이터 타입이 Json
					dataType : "json" //이 문구를 쓸거면 106번을 안써도댐
				}).done(function(resp) {
					//let result = JSON.parse(resp);
					console.log(resp);
				});
			})
		</script>
	</fieldset>
	<hr>
	<fieldset>
		<Legend>Exam07.AJAX Response with non-type Java Object</Legend>
		<button id="exam07">Exam07</button>
		<script>
			$("#exam07").on("click", function() {
				$.ajax({
					url : "/exam07.ajax",
					dataType : "json"
				}).done(function(resp) {
					console.log(resp);
				});
			})
		</script>
	</fieldset>

</body>
</html>