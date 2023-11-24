<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js">
</script>
</head>
<body>
	<h1>회원탈퇴</h1>
	<form id="removeForm" method="post" action="${pageContext.request.contextPath}/member/removeMember">
		<table border="1">
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" id="memberPw" name="memberPw"></td>
			</tr>
		</table>
		<button id="addbtn">회원탈퇴</button>
	</form>
</body>

<script type="text/javascript">
	$('#addbtn').click(function(){
		if($('#memberPw').val().length < 1) {
			alert('비밀번호를 입력하세요');
		} else {
			$('#addbtn').submit();
		}
	});
</script>
</html>