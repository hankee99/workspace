<%@page import="kr.co.iei.member.vo.Member"%>
<%@page import="kr.co.iei.member.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	request.setCharacterEncoding("utf-8");
    	String memberId = request.getParameter("memberId");
    	MemberService memberService = new MemberService();
    	Member m = memberService.selectMemberId(memberId);
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>아이디로 회원조회</h1>
	<hr>
	<%if(m == null){ %>
	<h3>조회결과 없음</h3>
	<%}else{ %>
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
				<td><%=m.getMemberId() %></td>
				<td><%=m.getMemberPw() %></td>
				<td><%=m.getMemberName() %></td>
				<td><%=m.getMemberPhone() %></td>
				<td><%=m.getMemberAddr() %></td>
				<%if(m.getMemberLevel() == 1){ %>
				<td>관리자</td>
				<%}else if(m.getMemberLevel() == 2){ %>
				<td>정회원</td>
				<%}else if(m.getMemberLevel() == 3){ %>
				<td>준회원</td>
				<%} %>
				<td><%=m.getEnrollDate() %></td>
			</tr>
			</table>
	<%} %>
	<a href="/">메인으로..</a>
</body>
</html>