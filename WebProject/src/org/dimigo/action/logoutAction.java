package org.dimigo.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class logoutAction implements IAction{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		// 로그아웃 처리
		HttpSession session = request.getSession();
		//session.removeAttribute("user");
		session.invalidate();  
		
		RequestDispatcher rd = request.getRequestDispatcher("jsp/home.jsp");
		rd.forward(request, response);
	}

	
	
}
