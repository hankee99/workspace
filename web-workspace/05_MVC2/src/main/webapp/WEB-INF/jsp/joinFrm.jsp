<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입</h1>
    <hr>
    <form action="/join" method="post">
        <table border="1">
            <tr>
                <th>
                    <label for="memberId">아이디</label>
                </th>
                <td>
                    <input type="text" name="memberId" id="memberId">
                </td>
            </tr>
            <tr>
                <th>
                    <label for="memberPw">비밀번호</label>
                </th>
                <td>
                    <input type="password" name="memberPw" id="memberPw">
                </td>
            </tr>
            <tr>
                <th>
                    <label for="memberName">이름</label>
                </th>
                <td>
                    <input type="text" name="memberName" id="memberName">
                </td>
            </tr>
            <tr>
                <th>
                    <label for="memberPhone">전화번호</label>
                </th>
                <td>
                    <input type="text" name="memberPhone" id="memberPhone">
                </td>
            </tr>
            <tr>
                <th>
                    <label for="memberAddr">주소</label>
                </th>
                <td>
                    <input type="text" name="memberAddr" id="memberAddr">
                </td>
            </tr>
            <tr>
                <th colspan="2">
                    <input type="submit" value="회원가입">
                </th>
            </tr>
        </table>
    </form>
</body>
</html>