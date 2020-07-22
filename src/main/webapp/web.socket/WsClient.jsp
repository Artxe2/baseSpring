<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
let userName;
let socket;
const msg_chat = "100\r\n"; //방채팅 
const msg_null = "101\r\n"; //빈 메시지를 전송했을 때 
const msg_exit = "500\r\n";
	/* 채팅 기능 선언부  */
	window.addEventListener('DOMContentLoaded', (e) => {
		userName = prompt('Input your name');
		document.querySelector("#user_name").innerHTML = document.querySelector("#user_name").innerHTML + '[ ' + userName + ' ]';
		connectWS();
		document.querySelector("#msg").addEventListener('keydown', (evt) => {
			if (evt.keyCode == 13) {
				evt.preventDefault();
				let msg = document.querySelector("#msg").value;
				socket.send(msg_chat + msg);
			}
		});
	});
	function connectWS() {
		socket = new WebSocket("ws:\\\\localhost:8000\\base\\ws?");
		socket.open = function(message) {
			console.log(message);
		};
		// 서버로부터 메시지를 받았을 때
		socket.onmessage = function(event) {
			document.querySelector("#text_area").innerHTML += event.data + "<br>";
			document.querySelector("#text_area").scrollTop = document.querySelector("#text_area").scrollHeight;
		};

		socket.onclose = function(event) {
			console.log("Server disconnect");
		};
		
		socket.onerror = function(event) {
			console.log("Server Error");
		};
	}
</script>
</head>
<body>
	<div id="user_name">My Name: </div>
	<div id="text_area"
		style="border: 1px solid black; overflow: auto; width: 600px; height: 350px;"></div>
	<select>
		<option>A(to Me)</option>
		<option>B(to All)</option>
	</select>
	<input type="text" id="msg" value="" class="form-control">

</body>
</html>
