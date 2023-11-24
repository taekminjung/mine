<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>안녕하세요 <mark>${loginMember.memberId}</mark>님</h1>
	<h2>${targetY}년 ${targetM}월 ${targetD}일의 일정을 수정해주세요</h2>
	<br>
	<table class="table table-bordered">
		<form action="${pageContext.request.contextPath}/member/updateSchedule?scheduleNo=${scheduleNo}" method="post">
		<input type="hidden" name="targetY" value="${targetY}">
		<input type="hidden" name="targetM" value="${targetM}">
		<input type="hidden" name="targetD" value="${targetD}">
		<textarea rows="10" cols="100" name="scheduleMemo"></textarea>
		<button type="submit">일정 수정</button>
		</form>
	</table>
</body>
</html>