<%@page import="kr.co.iei.member.vo.Member"%>
<%@page import="kr.co.iei.member.service.MemberService"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	request.setCharacterEncoding("utf-8");
    	MemberService memberService = new MemberService();
    	
    	String memberName = request.getParameter("memberName");
    	
    	ArrayList<Member> list = memberService.selectMemberName(memberName);
    
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	td>a{
		text-decoration: none;
		font-weight: 900;
		color: fuchsia;
	}
</style>
</head>
<body>
	<h1>이름으로 조회 결과</h1>
	<hr>
	<%if(list.isEmpty()){ %>
	<h3>조회결과가 없습니다.</h3>
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
		
		
		<%for(Member m : list){%>
			<tr>
				<td><%=m.getMemberNo() %></td>
				<td><a href="/jsp/selectMemberId.jsp?memberId=<%=m.getMemberId() %>"><%=m.getMemberId() %></a></td>
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
			
		<%} %>	
	</table>
	<%} %>
	<a href="/">메인으로..</a>
</body>
</html>