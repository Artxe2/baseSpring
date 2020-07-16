<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>    
<%
	List<Map<String,Object>> list = 
		(List<Map<String,Object>>)request.getAttribute("list");
	if(list != null){
		out.print(list.get(0).get("b_title"));
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
게시판 목록 가져오기
</body>
</html>