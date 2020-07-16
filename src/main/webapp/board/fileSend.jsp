<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>첨부파일 처리</title>
</head>
<body>
<form method="post" action="fileAdd" enctype="multipart/form-data">
	<input type="hidden" name="bm_no" value="10"/>
	<input type="file" label="첨부 파일 " size="200" id="bs_file" name="bs_file"/>
	<input type="submit" value="전송"/>
</form>
</body>
</html>