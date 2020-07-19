<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Enumeration<String> params = request.getParameterNames();
while (params.hasMoreElements()) {
	String name = (String) params.nextElement();
	System.out.println(name + " : " + request.getParameter(name));
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	document.addEventListener('DOMContentLoaded', function(e) {

		document.querySelector('#eve_img').addEventListener('change', function(e) {
			document.querySelector('#pre_view').innerHTML = '';
			var get_file = e.target.files;

			var image = document.createElement('img');

			/* FileReader 객체 생성 */
			var reader = new FileReader();

			/* reader 시작시 함수 구현 */
			reader.onload = (function(aImg) {
				console.log(1);

				return function(e) {
					console.log(3);
					/* base64 인코딩 된 스트링 데이터 */
					aImg.src = e.target.result
					aImg.width = 300;
					aImg.height = 200;
				}
			})(image)

			if (get_file) {
				/* 
				    get_file[0] 을 읽어서 read 행위가 종료되면 loadend 이벤트가 트리거 되고 
				    onload 에 설정했던 return 으로 넘어간다.
				    이와 함게 base64 인코딩 된 스트링 데이터가 result 속성에 담겨진다.
				 */
				reader.readAsDataURL(get_file[0]);
				console.log(2);
			}
			document.querySelector('#pre_view').appendChild(image);
		});
	});
</script>
<body>
	<div id=pre_view></div>
	<form id="f_test" method="post" enctype="multipart/form-data"
		action="<%=request.getContextPath() %>/base/file_upload">
		<input type="file" id="eve_img" name="file"><br>
		<input type="text" name="text"> <input type="submit"
			value="전송">
	</form>
</body>
</html>