<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>MVC1(전한길)</h1>
	<hr>
	<%-- jsp주석 -- %>
	<%-- 
		전체회원조회
		1. DB작업 필요한지 -> O
		2. query : select * from member_tbl order by 1
		3. 사용자에게 입력받을 값 없음 -> Controller 호출 (MVC1구조에서 Controller는 JSP가 담당)
	 --%>
	 <h3><a href="/jsp/allMember.jsp">1. 전체회원조회</a></h3>
	 <!--
	 	회원가입
	 	1. DB작업 필요한지 -> O
	 	2. query : insert into member_tbl values(member_seq.nextval,?,?,?,?,?,3,to_char(sysdate,'yyyy-mm-dd'))
	 	3. 사용자에게 입력받을 값 -> 아이디,비번,이름,전번,주소 -> 입력양식으로 이동
	   -->
	   <h3><a href="/jsp/joinFrm.jsp">2. 회원가입</a></h3>
	   <!-- 이름으로 회원조회 -> 입력한 이름이 포함된 회원 조회 -->
	   <h3><a href="/jsp/nameSearch.jsp">3. 이름으로 회원조회</a></h3>
	   
	   <h3><a href="/jsp/idSearch.jsp">4. 아이디로 회원조회</a></h3>
</body>
</html>