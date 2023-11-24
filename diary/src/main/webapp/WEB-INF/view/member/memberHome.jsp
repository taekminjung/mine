<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<style>
  a {
  	  text-decoration: none;
  }
</style>
</head>
<body>
	<h1>안녕하세요!! <mark>${loginMember.memberId}</mark>님</h1>
	<div>
		<a href="${pageContext.request.contextPath}/member/logoutMember">로그아웃</a>
		<a href="${pageContext.request.contextPath}/member/modifyPwMember"">비밀번호수정</a>
		<a href="">회원탈퇴</a>
	</div>
	
	<!-- 달력 -->
	<div>
		<h1>${targetY}년 ${targetM+1}월</h1>	
	<div>
			<a href="${pageContext.request.contextPath}/member/memberHome?targetY=${targetY}&targetM=${targetM-1}">이전달</a>
			
			<!-- 만약 href속성값 매개값이 많으면 c:url jstl을 사용하면 가독성 높일 수 있다 -->
			<c:url var = "nextUrl" value="/member/memberHome">
				<c:param name="targetY" value="${targetY}"></c:param>
				<c:param name="targetM" value="${targetM+1}"></c:param>	
			</c:url>
			<a href="${nextUrl}">다음달</a>
		</div>
		
		<table class="table table-bordered">
			<tr>
				<c:forEach var="i" begin="1" end="${totalTd}" step="1">
					<c:set var="d" value="${(i - beginBlank)}"></c:set>
					<td>
						<c:if test="${d < 1 || d > lastD}">
							&nbsp;
						</c:if>
						<c:if test="${!(d < 1 || d > lastD)}">
							
							<a href="${pageContext.request.contextPath}/member/ScheduleList?targetY=${targetY}&targetM=${targetM+1}&targetD=${i - beginBlank}">
								${d}
							</a>
							<div>
							<c:forEach var="m" items="${list}">
								<c:if test="${m.scheduleDay == d}">
								<div>${m.cnt}개의 일정</div>
								<div>${m.memo}</div>
								</c:if>
							</c:forEach>
								</div>
						</c:if>
			
						<c:if test="${i< totalTd && i%7==0}">
							</tr><tr>
						</c:if>
					</td>
				</c:forEach>
			</tr>
		</table>
	</div>
</body>
</html>