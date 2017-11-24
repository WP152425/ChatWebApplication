package org.dimigo.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dimigo.service.UserService;
import org.dimigo.util.CommonUtil;
import org.dimigo.vo.UserVO;

public class signupAction implements IAction{
	
	private void validate(UserVO vo) throws Exception{
		if (CommonUtil.isEmpty(vo.getId())) throw new Exception("아이디를 입력하세요.");
		if (CommonUtil.isEmpty(vo.getPwd())) throw new Exception("비밀번호를 입력하세요.");
		if (CommonUtil.isEmpty(vo.getName())) throw new Exception("이름을 입력하세요.");
		if (CommonUtil.isEmpty(vo.getNickname())) throw new Exception("닉네임을 입력하세요.");
	}

	@Override
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
			service.signup(user);
			
			RequestDispatcher rd = request.getRequestDispatcher("jsp/login.jsp");
			rd.forward(request, response);
			
		} catch(Exception e){
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("jsp/signup.jsp");
			rd.forward(request, response);
		}
	}

}
