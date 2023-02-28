package servlet.digital;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Message;
import bean.PageBean;
import dao.MsgDao;
import dao.impl.MsgDaoImpl;

/**
 * Servlet implementation class DigitalList
 */
@WebServlet("/MsgList2")
public class MsgList2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int MAX_LIST_SIZE = 12;
	private static final String BOOKLIST_PATH="jsp/digital/msglist.jsp";
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String seachname = new String(request.getParameter("seachname").getBytes("iso-8859-1"),"utf-8");
		String seachname1 = request.getParameter("seachname");
		messageList(request,response,seachname1);
	}

	private void messageList(HttpServletRequest request, HttpServletResponse response, String seachname) throws ServletException, IOException {
		MsgDao bd = new MsgDaoImpl();
		int curPage = 1;
		String page = request.getParameter("page");
		if (page != null) {
			curPage = Integer.parseInt(page);
		}
		
		PageBean pb=null;
		List<Message> msgList=new ArrayList<Message>();
		List<Message> messageList;
		if(seachname == null || seachname == "") {
			pb = new PageBean(curPage, MAX_LIST_SIZE, bd.msgReadCount(seachname));
			messageList = bd.messageList(pb);
		}else {
			pb = new PageBean(curPage, MAX_LIST_SIZE, bd.msgReadCount(seachname));
			messageList = bd.messageList(pb,seachname);
		}
		request.setAttribute("title", "所有图书");
		
		request.setAttribute("pageBean", pb);
		request.setAttribute("messageList",messageList);
		
		request.getRequestDispatcher(BOOKLIST_PATH).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
