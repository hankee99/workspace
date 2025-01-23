<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>이름으로 회원조회</h1>
	<hr>
	<form action="selectMemberName.jsp" method="post">
		<label for="memberName">이름</label>
		<input type="text" name="memberName" id="memberName"><br>
		<input type="submit" value="조회">
	</form>
</body>
</html>