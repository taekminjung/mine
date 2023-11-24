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
	<h1>비밀번호 변경</h1>
	<form id="addForm" method="post" action="${pageContext.request.contextPath}/member/modifyPwMember">
		<table border="1">
			<tr>
				<td>현재 비밀번호</td>
				<td><input type="password" id="beforePw" name="beforePw"></td>
			</tr>
			<tr>
				<td>변경 할 비밀번호</td>
				<td><input type="password" id="afterPw" name="afterPw"></td>
			</tr>
		</table>
		<button id="addbtn">변경하기</button>
	</form>
</body>

<script type="text/javascript">
	$('#addbtn').click(function(){
		if($('#memberId').val().length < 1) {
			alert('새로운 아이디를 입력하세요');
		} else if ($('#memberPw1').val().length < 1) {
			alert('현재 비밀번호를 입력하세요');
		} else if ($('#memberPw2').val().length < 1) {
			alert('새 비밀번호를 입력하세요');
		} else if ($('#memberPw1').val() == $('#memberPw2').val()) {
			alert('이전 비밀번호와 변경 할 비밀번호가 같습니다');
		} else {
			$('#addbtn').submit();
		}
	});
</script>
</html>