<%@page import="kr.co.iei.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Member m = (Member)request.getAttribute("m");
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원정보수정</h1>
	<hr>
	<form action="/update" method="post">
		<table border="1">
			<tr>
				<th>회원번호</th>
				<td><%=m.getMemberNo()%></td>
			</tr>
			<tr>
				<th>아이디</th>
				<td>
					<%=m.getMemberId()%>
					<input type="hidden" name="memberId" value="<%=m.getMemberId()%>">
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="memberPw" value="<%=m.getMemberPw()%>"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><%=m.getMemberName()%></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="memberPhone" value="<%=m.getMemberPhone()%>"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="memberAddr" value="<%=m.getMemberAddr()%>"></td>
			</tr>
			<tr>
				<th>회원등급</th>
				<td><%=m.getMemberLevel()%></td>
			</tr>
			<tr>
				<th>가입일</th>
				<td><%=m.getEnrollDate()%></td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="정보수정">
				</th>
			</tr>
		</table>
	</form>
</body>
</html>