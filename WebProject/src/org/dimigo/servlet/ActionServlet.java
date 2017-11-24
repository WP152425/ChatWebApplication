package org.dimigo.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dimigo.action.IAction;
import org.dimigo.action.infoAction;
import org.dimigo.action.logoutAction;
import org.dimigo.action.signupAction;
import org.dimigo.action.loginAction;

/**
 * Servlet implementation class ActionServlet
 */
@WebServlet("*.do")
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, IAction> actions = new HashMap<>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	actions.put("login", new loginAction());
    	actions.put("logout", new logoutAction());
    	actions.put("info", new infoAction());
    	actions.put("signup", new signupAction());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			// 1. uri(/login.do)로부터 login 추출
			String uri = request.getRequestURI();
			System.out.println("uri : "+ uri);
			
			String actionName = uri.substring(uri.lastIndexOf("/")+1);
			actionName = actionName.substring(0,actionName.indexOf("."));
			System.out.println("actionName : " + actionName);
			
			// action 객체 가져오기
			IAction action = actions.get(actionName);
			if (action == null){
				throw new Exception(actionName + "에 해당하는 Action 클래스가 없습니다.");
			}
			
			action.execute(request, response);
		} catch(Exception e){
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("jsp/error.jsp");
			rd.forward(request, response);
		}
	}

}
