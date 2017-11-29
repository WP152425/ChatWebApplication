package org.dimigo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dimigo.service.ChatService;

public class ChatListAction implements IAction{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ChatService service = new ChatService();
		String listType = request.getParameter("listType");
		String chatCategory = request.getParameter("chatCategory");
		
		if (listType == null || listType.equals("")) response.getWriter().write("");
		else if (listType.equals("today")) response.getWriter().write(service.getToday());
		else if (listType.equals("ten")) response.getWriter().write(service.getTen(chatCategory));
		else {
			try {
				Integer.parseInt(listType);
				response.getWriter().write(service.getID(listType, chatCategory));
			} catch(Exception e){
				response.getWriter().write("");
			}
		}
	}
	
}
