<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>   
<%@ page import="com.google.gson.Gson"%>
<%
	List<Map<String,Object>> list = 
		(List<Map<String,Object>>)request.getAttribute("list");
	//out.print(list.get(0).get("b_title"));
	Gson g = new Gson();
	String imsi = g.toJson(list);
	out.print(imsi);
%>    
