<%@page import="kr.co.iei.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	int result = (Integer)request.getAttribute("result");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%if(result > 0){ %>
	<h1>회원가입 성공</h1>
	<%}else{ %>
	<h1>회원가입 실패</h1>
	<%} %>
	<a href="/">메인으로</a>
</body>
</html>