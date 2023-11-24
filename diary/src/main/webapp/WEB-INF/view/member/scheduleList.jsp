<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
 <!-- [jQuery] -->
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>

    <!-- [Bootstrap] -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>안녕하세요 <mark>${loginMember.memberId}</mark>님</h1>
	<h2>오늘(${targetY}년 ${targetM}월 ${targetD}일)의 일정입니다</h2>
	<table class="table table-bordered">
		<c:forEach var="m" items="${list}">
		<tr>
			<td>${day}일</td>
			<td>${m.scheduleNo}번</td>
			<td>${m.memo}</td>
			<td><a href="${pageContext.request.contextPath}/member/updateSchedule?scheduleNo=${m.scheduleNo}&targetY=${targetY}&targetM=${targetM}&targetD=${targetD}">수정</a></td>
			<td><a href="${pageContext.request.contextPath}/member/DeleteSchedule?scheduleNo=${m.scheduleNo}">삭제</a></td>
		</tr>
		</c:forEach>
	</table>
	<div>
	<form action="${pageContext.request.contextPath}/member/InsertSchedule?scheduleNo=${m.scheduleNo}">
	<input type="hidden" name="year" value="${targetY}">
	<input type="hidden" name="month" value="${targetM}">
	<input type="hidden" name="day" value="${targetD}">
	<textarea rows="10" cols="100"></textarea>
	<button type="button">일정 추가</button>
	</form>
	</div>
	
</body>
</html>