package org.dimigo.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dimigo.service.UserService;
import org.dimigo.util.CommonUtil;
import org.dimigo.vo.UserVO;

public class infoAction implements IAction{
	
	private void validate(UserVO vo) throws Exception {
		if (CommonUtil.isEmpty(vo.getId())) throw new Exception("���̵� �Է��ϼ���.");
		if (CommonUtil.isEmpty(vo.getPwd())) throw new Exception("��й�ȣ�� �Է��ϼ���.");
		if (CommonUtil.isEmpty(vo.getName())) throw new Exception("�̸��� �Է��ϼ���.");
		if (CommonUtil.isEmpty(vo.getNickname())) throw new Exception("�г����� �Է��ϼ���.");
	}
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset");
			
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String nickname = request.getParameter("nickname");
			
			UserVO user = new UserVO(id, name, nickname);
			user.setPwd(pwd);
			
			validate(user);
			
			UserService service = new UserService();
			service.info(user, request);
			
			RequestDispatcher rd = request.getRequestDispatcher("jsp/login.jsp");
			rd.forward(request, response);
			
		} catch(Exception e){
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("jsp/info.jsp");
			rd.forward(request, response);
		}
	}
	
}