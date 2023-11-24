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
 * Servlet implementation class DeleteScheduleController
 */
@WebServlet("/member/DeleteSchedule")
public class DeleteScheduleController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// session 유효성 검사
			HttpSession session = request.getSession();
			if(session.getAttribute("loginMember") == null) {
				// 로그인이 되어 있는 상태
				// redirect할 컨트롤러 url
				response.sendRedirect(request.getContextPath()+ "/member/memberHome");
				return;
			}
		request.getRequestDispatcher("/WEB-INF/view/member/scheduleList.jsp").forward(request, response);
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
		
		Schedule schedule = (Schedule)session.getAttribute("deleteSchedule");	
		Member member = (Member) session.getAttribute("loginMember");
		
		String memberId = member.getMemberId();
		int scheduleNo = schedule.getScheduleNo();
	
		
		
		ScheduleDao scheduleDao = new ScheduleDao();
		int row = scheduleDao.DeleteSchedule(scheduleNo, memberId);
		System.out.println(row);
		
		response.sendRedirect(request.getContextPath()+"/view/member/scheduleList.jsp");
		}
}
