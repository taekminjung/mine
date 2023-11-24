package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import dao.ScheduleDao;
import vo.Member;
import vo.Schedule;

/**
 * Servlet implementation class UpdateScheduleController
 */
@WebServlet("/member/updateSchedule")
public class UpdateScheduleController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					// session 유효성 검사
					HttpSession session = request.getSession();
					if(session.getAttribute("loginMember") == null) {
						// 로그인이 안 되어 있는 상태
						// redirect할 컨트롤러 url
						response.sendRedirect(request.getContextPath()+ "/member/loginMember");
						return;
					}
					int scheduleNo = Integer.parseInt(request.getParameter("scheduleNo"));
					int targetY = Integer.parseInt(request.getParameter("targetY"));
					int targetM = Integer.parseInt(request.getParameter("targetM"));
					int targetD = Integer.parseInt(request.getParameter("targetD"));
					request.setAttribute("scheduleNo", scheduleNo);
					request.setAttribute("targetY", targetY);
					request.setAttribute("targetM", targetM);
					request.setAttribute("targetD", targetD);
					
		request.getRequestDispatcher("/WEB-INF/view/member/updateSchedule.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//update액션
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			// 로그인이 안 되어 있는 상태
			// redirect할 컨트롤러 url
			response.sendRedirect(request.getContextPath()+ "/member/loginMember");
			return;
		}
		
		request.setCharacterEncoding("utf-8");
		
		Schedule schedule = (Schedule)session.getAttribute("updateSchedule");	
		
		Member member = (Member) session.getAttribute("loginMember");
		String memberId = member.getMemberId();
		int targetY = Integer.parseInt(request.getParameter("targetY"));
		int targetM = Integer.parseInt(request.getParameter("targetM"));
		int targetD = Integer.parseInt(request.getParameter("targetD"));
		int scheduleNo = Integer.parseInt(request.getParameter("scheduleNo"));
		String scheduleMemo = request.getParameter("scheduleMemo");
		request.setAttribute("scheduleNo", scheduleNo);
		request.setAttribute("targetY", targetY);
		request.setAttribute("targetM", targetM);
		request.setAttribute("targetD", targetD);
		
		ScheduleDao scheduleDao = new ScheduleDao();
		int row = scheduleDao.updateSchedule(scheduleMemo, scheduleNo, memberId, targetY, targetM, targetD);
		System.out.println(row);
		
		response.sendRedirect(request.getContextPath()+"/member/ScheduleList?");
	}

}
