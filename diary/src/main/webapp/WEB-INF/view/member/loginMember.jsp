<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인</h1>
	<form id="loginForm" method="post" action="${pageContext.request.contextPath}/member/loginMember">
	<table border="1">
		<tr>
			<th>아이디</th>
			<td><input type="text" id="memberId" name="memberId"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" id="memberPw" name="memberPw"></td>
			
		</tr>
	</table>
	<button id="loginBtn">로그인</button>
	</form>
	<a href="${pageContext.request.contextPath}/member/addMember">회원가입</a>
</body>
</html>