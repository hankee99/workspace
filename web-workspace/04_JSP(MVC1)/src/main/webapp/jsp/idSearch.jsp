<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>아이디로 회원조회</h1>
	<hr>
	<form action="selectMemberId.jsp" method="get">
		<label for="memberId">아이디</label>
		<input type="text" name="memberId" id="memberId"><br>
		<input type="submit" value="조회">
	</form>
</body>
</html>