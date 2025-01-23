<%@page import="kr.co.iei.member.service.MemberService"%>
<%@page import="kr.co.iei.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	//1.인코딩
    	request.setCharacterEncoding("utf-8");
    	//2.값추출
    	Member m = new Member();
    	m.setMemberId(request.getParameter("memberId"));
    	m.setMemberPw(request.getParameter("memberPw"));
    	m.setMemberName(request.getParameter("memberName"));
    	m.setMemberPhone(request.getParameter("memberPhone"));
    	m.setMemberAddr(request.getParameter("memberAddr"));
    	//3.비즈니스 로직
    	MemberService memberService = new MemberService();
    	int result = memberService.insertMember(m);
    	//4.결과처리
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.success{
		color : green;
	}
	.fail{
		color : red;
	}
</style>
</head>
<body>
	<h1>회원가입 결괴</h1>
	<hr>
	<%if(result > 0){ %>
	<h3 class="success">회원가입 성공!</h3>
	<%}else{ %>
	<h3 class="fail">회원가입 실패 ㅋ</h3>
	<%} %>
	<a href="/">메인페이지로..</a>
</body>
</html>