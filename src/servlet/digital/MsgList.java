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
import bean.Book;
import bean.PageBean;
import dao.DigitalDao;
import dao.MsgDao;
import dao.impl.BookDaoImpl;
import dao.impl.DigitalDaoImpl;
import dao.impl.MsgDaoImpl;

/**
 * Servlet implementation class messageList
 */
@WebServlet("/MsgList")
public class MsgList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int MAX_LIST_SIZE = 12;
	private static final String LIST_PATH="jsp/digital/msglist.jsp";
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");
		if(action==null) {
			action="list";
		}
		switch(action) {
		case "d":
			break;
			
		case "list"://默认全部商品列表
			messageList(request,response);
			break;
		}
	}

	public void messageList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MsgDao bd = new MsgDaoImpl();
		int curPage = 1;
		String page = request.getParameter("page");
		if (page != null) {
			curPage = Integer.parseInt(page);
		}
		
		PageBean pb=null;
		List<Message> messageList=new ArrayList<Message>();
		String bookIdStr = request.getParameter("bookId");//获取有没有分类id，没有就是查全部
		
		if(bookIdStr!=null) {
			int bookId=Integer.parseInt(bookIdStr);
			pb = new PageBean(curPage, MAX_LIST_SIZE, bd.bookReadCount(bookId));
			messageList = bd.messageList(pb,bookId);
			
			if(messageList.size()>0) {
				//根据图书id查询图书信息
			  
				BookDaoImpl bookDao=new BookDaoImpl();
				Book book=bookDao.findBookById(messageList.get(0).getBookId());
				request.setAttribute("title", book.getBookName());//从返回的分类集合中第一个获取数据的分类
			}
			
		}else {
			pb = new PageBean(curPage, MAX_LIST_SIZE, bd.msgReadCount());
			messageList = bd.messageList(pb);
			request.setAttribute("title", "所有图书");
		}
		
		request.setAttribute("pageBean", pb);
		request.setAttribute("messageList",messageList);
		
		request.getRequestDispatcher(LIST_PATH).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
