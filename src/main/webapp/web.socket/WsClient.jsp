<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
 -->
<script>
	function connectWS() {
		var ws = new WebSocket("ws:\\\\localhost:8000\\base\\ws?");
		socket = ws;
		ws.open = function(message) {
			console.log(message);
		};
		// 서버로부터 메시지를 받았을 때
		ws.onmessage = function(event) {

			var data = event.data;
			$("#text_area").append("<br>" + data);
			$("#text_area").css("display", "block");
		};

		//브라우저 닫을시
		ws.onclose = function(event) {
			console.log("Server disconnect");
		};
		//브라우저 에러시 
		ws.onerror = function(event) {
			console.log("Server Error");
		};

	}
</script>
</head>
<body>
	<div id="text_area" style="border: 1px solid black ; overflow: auto; width: 500px; height: 150px;"></div>
	<input type="text" id="msg" value="123test" class="form-control">
	<input id="btnSend" value="메세지 전송" type="button">
	<input id="btnExit" value="나가기" type="button">
	<input id="testSend" value="공지" type="button">
	<script type="text/javascript">
		var userName = null;
		var socket = null;
		var msg_chat = "100\r\n"; //방채팅 
		var msg_null = "101#"; //빈 메시지를 전송했을 때 
		var msg_exit = "500#";
		/* 채팅 기능 선언부  */
		//connectWS();
		window.addEventListener('DOMContentLoaded', (e) => {
			userName = prompt('Input your name');
			connectWS();
			$('#btnSend').on('click', function(evt) {
				evt.preventDefault();
				// 메시지 전송
				let msg = $("#msg").val();
				if (msg.trim().length < 1) { //빈공간 문자열 출력 
					socket.send(msg_null + msg);
				} else {
					socket.send(msg_chat + msg);//소켓에 입력된 메시지를 보낸다.
				}
			});//////////////end of btnSend
			$("#msg").keydown(function(key) {
				//키의 코드가 13번일 경우 (13번은 엔터키)
				if (key.keyCode == 13) {
					//alert("엔터키를 눌렀습니다.");
					$("#btnSend").click();
				}
			});
			$("#btnExit").on('click', function(evt) {
				evt.preventDefault();
				socket.send(msg_exit);//소켓에 입력된 메시지를 보낸다.
			});//////////////end of exit
		});//////////////////end of ready
	</script>
</body>
</html>
