package servlet.digital;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Talk;
import bean.User;
import  dao.DigitalDao;
import dao.UserDao;
import  dao.impl.DigitalDaoImpl;
import dao.impl.UserDaoImpl;

/**
 * Servlet implementation class digitaldetailed
 */
@WebServlet("/digitaldetail")
public class digitaldetailed extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DETAIL_PATH="jsp/digital/digitaldetails.jsp";

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
				int digitalId = Integer.parseInt(request.getParameter("digitalId"));
				DigitalDao bd = new DigitalDaoImpl();
				request.setAttribute("digitalInfo", bd.findDigitalById(digitalId));
				request.setAttribute("talkList", bd.findTalkList(digitalId));
				request.getRequestDispatcher(DETAIL_PATH).forward(request, response);
				break;
			case "addtalk":
				talkAdd(request,response);
				break;

		}



	}
	private void talkAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Talk talk = new Talk(
				request.getParameter("content"),
				request.getParameter("digitalId"));

		int digitalId = Integer.parseInt(request.getParameter("digitalId"));
		DigitalDao bd = new DigitalDaoImpl();
		bd.addTalk(talk);
		request.setAttribute("digitalInfo", bd.findDigitalById(digitalId));
		request.setAttribute("talkList", bd.findTalkList(digitalId));
		request.getRequestDispatcher(DETAIL_PATH).forward(request, response);
	}
}
