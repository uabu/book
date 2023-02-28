package servlet.digital;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Talk;
import bean.User;
import  dao.MsgDao;
import dao.UserDao;
import  dao.impl.MsgDaoImpl;
import dao.impl.UserDaoImpl;

/**
 * Servlet implementation class digitaldetailed
 */
@WebServlet("/msgdetail")
public class msgdetailed extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DETAIL_PATH="jsp/digital/msgdetails.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action=request.getParameter("action");
		if(action==null) {
			action="list";

		}
		switch(action){
			case "list":
				char title = (char) Integer.parseInt(request.getParameter("title"));
				MsgDao bd = new MsgDaoImpl();
				request.setAttribute("messageInfo", bd.findMessageById(title));
				request.getRequestDispatcher(DETAIL_PATH).forward(request, response);
				break;

		}
               


	}
	
}
