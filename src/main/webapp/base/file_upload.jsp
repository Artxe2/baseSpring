<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			let get_files = e.target.files;
			let image = document.createElement('img');
			let reader = new FileReader();
			reader.onload = (function(aImg) {
				console.log(1);
				return function(e) {
					console.log(3);
					aImg.src = e.target.result
					aImg.width = 450;
					aImg.height = 300;
				}
			})(image)
			if (get_files) {
				console.log(2);
				for (let i = 0; i < get_files.length; i++) {
					if (get_files[i].type && get_files[i].type.indexOf('image') > -1) {						
						reader.readAsDataURL(get_files[i]);
						break;
					}
				}
			}
			document.querySelector('#pre_view').appendChild(image);
		});
	});
</script>
<body>
	<div id=pre_view></div>
	<form id="f_test" method="post" enctype="multipart/form-data"
		action="<%=request.getContextPath() %>/base/file_upload">
		<input type="file" id="eve_img" name="i_file" multiple><br>
		<input type="text" name="i_text"> <input type="submit"
			value="Send">
	</form>
</body>
</html>