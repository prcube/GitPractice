<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Signup</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
</head>
<style>
* {
	box-sizing: border-box;
}

div {
	border: 0.5px solid transparent;
}

.container {
	width: 400px;
	height: 500px;
	margin: auto;
}

.header {
	float: left;
	height: 10%;
}

.main {
	float: left;
	height: 80%;
}

.footer {
	height: 10%;
}

.left {
	/* height: 10%; */
	float: left;
	text-align: right;
	width: 30%;
}

.right {
	/* height: 10%; */
	width: 70%;
	float: left;
}

</style>
<script>
	window.onload = function() {

// 		idcheck.onclick = function() {
		id.onkeyup = function() {
			let id = document.getElementById("id").value;
			$.ajax({
				url:"/idcheck.mem",
				data:{"id":id}
			}).done(function(resp){
				if(resp=="true"){
					$("#idcheckresult").html("이미 사용중인 ID 입니다.");
				}else{
					$("#idcheckresult").html("사용 가능한 ID 입니다.");
				}
				
			});
// 			window.open("/idcheck.mem?id=" + id, "", "width=400, height=300");
			// 여기다가 절대경로 생성해주기 강사님 추천 idcheck.mem
		}

		let pw1 = document.getElementById("pw1");
		let pw2 = document.getElementById("pw2");
		let pw1result = document.getElementById("pw1result")

		pw1.onkeyup = function() {
			let regex = /^[A-Za-z0-9!@#$%]{10,20}$/
			let pw1answer = regex.test(pw1.value);
			if (pw1answer) {
				pw1result.innerHTML = "사용 가능한 PW입니다."
			} else {
				pw1result.innerHTML = "사용 불가능한 PW입니다."

			}
		}

		pw2.onkeyup = function() {
			if (pw1.value == pw2.value) {
				document.getElementById("pw2result").innerHTML = "패스워드가 일치합니다"
			} else {
				document.getElementById("pw2result").innerHTML = "패스워드가 일치하지않습니다."
			}
		}

		document.getElementById("btnSearch").onclick = function() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							document.getElementById('postcode').value = data.zonecode;
							document.getElementById("address1").value = data.roadAddress;
						}
					}).open();
		}

		let name = document.getElementById("name");
		let nameresult = document.getElementById("nameresult")

		name.onkeyup = function() {
			let regex = /^[가-힣]{3,5}$/g;
			let aname = regex.test(name.value);
			if (aname) {

				nameresult.innerHTML = "올바른 이름입니다.";
			} else {
				nameresult.innerHTML = "5글자이내 이름을 입력하세요";

			}
		}

		let phoneresult = document.getElementById("phoneresult");
		let phone = document.getElementById("phone");
		phone.onkeyup = function() {
			let regex = /010[0-9]{8}/g;
			let panswer = regex.test(phone.value);
			if (panswer) {
				phoneresult.innerHTML = "올바른 번호입니다.";
			} else {
				phoneresult.innerHTML = "번호를 확인하세요";
			}
		}

		let email = document.getElementById("email");
		let emailresult = document.getElementById("emailresult");
		email.onkeyup = function() {
			let regex = /[A-Za-z0-9].+?[@][A-Za-z0-9].+[.][A-Za-z0-9].+/g;
			let pemail = regex.test(email.value);
			if (pemail) {
				emailresult.innerHTML = "올바른 이메일입니다.";
			} else {
				emailresult.innerHTML = "이메일을 확인하세요";
			}
		}

		let inputname = document.getElementById("name")
		let inputphone = document.getElementById("phone")
		let inputid = document.getElementById("id")
		let frm = document.getElementById("frm")

		frm.onsubmit = function() {
			let str = inputphone.value;
			let name = inputname.value;
			let id = inputid.value;
			if (str == "") {
				Swal.fire({
					icon : 'error',
					title : 'Oops...',
					text : '핸드폰 번호를 입력하세요',
					confirmButtonText : '확인'
				}).then(function() {
					inputphone.focus();
				})
				return false;
			} else if (name == "") {
				Swal.fire({
					icon : 'error',
					title : 'Oops...',
					text : '이름을 입력해 주세요!',
					confirmButtonText : '확인'
				}).then(function() {
					inputname.focus();
				})
				return false;
			} else if (id == "") {
				Swal.fire({
					icon : 'error',
					title : 'Oops...',
					text : 'ID 를 입력해 주세요!',
					confirmButtonText : '확인'
				}).then(function() {
					inputid.focus();
				})
				return false;
			}
		}
	}
</script>
<body>
	<div class="container">
		<div>
			<div style="text-align: center;">회원 가입 정보 입력</div>
		</div>
		<form action="/insert.mem" method='post' id="frm">
			<div class=main>
				<div class="left">아이디 :</div>
				<div class="right">
					<input type="text" name=id id="id" placeholder="8~14글자"><span id="idcheckresult"></span>
<!-- 					<button type=button id="idcheck">중복확인</button><span id="idcheckresult"></span> -->
					<div class="idresult" id="idresult"></div>
				</div>

				<div class="left">패스워드 :</div>
				<div class="right">
					<input type="text" id="pw1">
					<div id="pw1result"></div>
				</div>

				<div class="left">패스워드 확인 :</div>
				<div class="right">
					<input type="text" id="pw2" name='pw'>
					<div class="pw2result" id="pw2result"></div>
				</div>

				<div class="left">이름 :</div>
				<div class="right">
					<input type="text" id="name" name="name">
					<div id="nameresult"></div>
				</div>

				<div class="left">전화번호 :</div>
				<div class="right">
					<input type="text" id="phone" name="phone">
					<div id="phoneresult"></div>
				</div>

				<div class="left">이메일 :</div>
				<div class="right">
					<input type="text" id="email" name="email">
					<div id="emailresult"></div>
				</div>

				<div class="left">우편번호 :</div>
				<div class="right">
					<input type="text" id="postcode" name="zipcode">

					<button type=button id="btnSearch">찾기</button>
				</div>

				<div class="left">주소1 :</div>
				<div class="right">
					<input type="text" id="address1" name="address1">
				</div>

				<div class="left">주소2 :</div>
				<div class="right">
					<input type="text" id="address2" name="address2">
				</div>
			</div>
			<button id="final">회원가입</button>
		</form>

		<form>
			<input type="button" value="다시입력" onClick="window.location.reload()">
		</form>
		<a href="/index.jsp"><button>뒤로가기</button></a>
	</div>



</body>
</html>