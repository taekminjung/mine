package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;

@WebServlet("/member/removeMember")
public class RemoveMemberController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			// 로그인이 되어 있는 상태
			// redirect할 컨트롤러 url
			response.sendRedirect(request.getContextPath()+ "/member/memberHome");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/view/member/removeMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			// 로그인이 되어 있는 상태
			// redirect할 컨트롤러 url
			response.sendRedirect(request.getContextPath()+ "/member/memberHome");
			return;
		}
		
		request.setCharacterEncoding("utf-8");
		
		Member member = (Member)session.getAttribute("loginMember");	
		
		int memberNo = member.getMemberNo();
		String memberPw	 = request.getParameter("memberPw");
		
		MemberDao memberDao = new MemberDao();
		int row = memberDao.removeMember(memberNo, memberPw);
		System.out.println(row);
		
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/member/loginMember");
	}

}