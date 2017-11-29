package org.dimigo.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChatCategoryAction implements IAction{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String chatCategory = request.getParameter("category");
		String chatName = request.getParameter("name");
		
		System.out.println("category : " + chatCategory + ", user : " + chatName);
		
		HttpSession session = request.getSession();
		session.setAttribute("category", chatCategory);
		session.setAttribute("chatName", chatName);
		
		RequestDispatcher rd = request.getRequestDispatcher("jsp/broadcast2.jsp");
		rd.forward(request, response);
		
	}

}
