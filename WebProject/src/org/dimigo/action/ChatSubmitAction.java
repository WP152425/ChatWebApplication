package org.dimigo.action;

import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dimigo.service.ChatService;

public class ChatSubmitAction implements IAction{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String chatName = URLDecoder.decode(request.getParameter("chatName"), "UTF-8");
		String chatContent = URLDecoder.decode(request.getParameter("chatContent"), "UTF-8");
		String chatCategory = request.getParameter("chatCategory");
		
		ChatService service = new ChatService();
		
		if (chatName == null || chatName.equals("") || chatContent == null || chatContent.equals("")){
			response.getWriter().write("0");
		} else {
			response.getWriter().write(service.chatSubmit(chatName, chatContent, chatCategory));
		}
	}

}
