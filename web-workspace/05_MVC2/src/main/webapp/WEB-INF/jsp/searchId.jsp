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
	<h1>조회결과</h1>
	<hr>
	<%if(m != null){ %>
		<table border="1">
		<tr>
			<th>회원번호</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>주소</th>
			<th>회원등급</th>
			<th>가입일</th>
		</tr>
		<tr>
				<td><%=m.getMemberNo() %></td>
				<td><%=m.getMemberId()%></td>
				<td><%=m.getMemberPw() %></td>
				<td><%=m.getMemberName() %></td>
				<td><%=m.getMemberPhone() %></td>
				<td><%=m.getMemberAddr() %></td>
				<td><%=m.getMemberLevel() %></td>
				<td><%=m.getEnrollDate() %></td>
			</tr>
	</table>
	<a href="/">메인으로</a><br>
	<a href="/updateFrm?memberId=<%=m.getMemberId()%>">정보수정</a>
	<%}else{ %>
		<h3>조회결과 없음</h3>
		<a href="/">메인으로</a>
	<%} %>
	
	
</body>
</html>