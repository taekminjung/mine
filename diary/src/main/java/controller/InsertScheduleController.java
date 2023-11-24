package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ScheduleDao;
import vo.Member;
import vo.Schedule;

/**
 * Servlet implementation class InsertScheduleController
 */
@WebServlet("/member/InsertSchedule")
public class InsertScheduleController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			// 로그인이 안 되어 있는 상태
			// redirect할 컨트롤러 url
			response.sendRedirect(request.getContextPath()+ "/member/loginMember");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/view/member/memberHome").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
				HttpSession session = request.getSession();
				if(session.getAttribute("loginMember") == null) {
					// 로그인이 안 되어 있는 상태
					// redirect할 컨트롤러 url
					response.sendRedirect(request.getContextPath()+ "/member/memberHome");
					return;
				}
				request.setCharacterEncoding("utf-8");
				
				Schedule schedule = (Schedule)session.getAttribute("updateSchedule");	
				
				Member member = (Member) session.getAttribute("loginMember");
				String memberId = member.getMemberId();
				
				int targetY = Integer.parseInt(request.getParameter("targetY"));
				int targetM = Integer.parseInt(request.getParameter("targetM"));
				int targetD = Integer.parseInt(request.getParameter("targetD"));
				String scheduleMemo = request.getParameter("scheduleMemo");
				
				ScheduleDao scheduleDao = new ScheduleDao();
				int row = scheduleDao.InsertSchedule(memberId, scheduleMemo, targetY, targetM, targetD);
				//디버깅
				System.out.println(row);
				request.getRequestDispatcher("/WEB-INF/view/member/scheduleList.jsp").forward(request, response);
	}

}
