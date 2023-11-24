package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ScheduleDao;
import vo.Member;

/**
 * Servlet implementation class ScheduleListByDayController
 */
@WebServlet("/member/ScheduleList")
public class ScheduleListByDayController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// session 유효성 검사
				
				HttpSession session = request.getSession();
				if(session.getAttribute("loginMember") == null) {
					response.sendRedirect(request.getContextPath()+"/member/loginMember");
					return;
				}
				
			// 1) 출력하고자 하는 년/월/일과 일정 내용
			Member member = (Member) session.getAttribute("loginMember");
			String memberId = member.getMemberId();
			int targetY = Integer.parseInt(request.getParameter("targetY"));
			int targetM = Integer.parseInt(request.getParameter("targetM"));
			int targetD = Integer.parseInt(request.getParameter("targetD"));
			String memo = request.getParameter("memo");
			System.out.println(memberId +"<--mamberId");
			ScheduleDao scheduleDao = new ScheduleDao();
			List<Map<String, Object>> list =
			scheduleDao.selectScheduleByDay(memberId, targetY, targetM, targetD);
			
			request.setAttribute("memberId", memberId);
			request.setAttribute("targetY", targetY);
			request.setAttribute("targetM", targetM);
			request.setAttribute("targetD", targetD);
			request.setAttribute("memo", memo);
			
			request.setAttribute("list", list);
		

		request.getRequestDispatcher("/WEB-INF/view/member/scheduleList.jsp").forward(request, response);
	}

}
