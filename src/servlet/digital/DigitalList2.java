package servlet.digital;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Digital;
import bean.PageBean;
import dao.DigitalDao;
import dao.impl.DigitalDaoImpl;

/**
 * Servlet implementation class DigitalList
 */
@WebServlet("/DigitalList2")
public class DigitalList2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int MAX_LIST_SIZE = 12;
	private static final String BOOKLIST_PATH="jsp/digital/digitallist.jsp";
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String seachname = new String(request.getParameter("seachname").getBytes("iso-8859-1"),"utf-8");
		String seachname1 = request.getParameter("seachname");
		digitalList(request,response,seachname1);
	}

	private void digitalList(HttpServletRequest request, HttpServletResponse response, String seachname) throws ServletException, IOException {
		DigitalDao bd = new DigitalDaoImpl();
		int curPage = 1;
		String page = request.getParameter("page");
		if (page != null) {
			curPage = Integer.parseInt(page);
		}
		
		PageBean pb=null;
		List<Digital> digitalList=new ArrayList<Digital>();
		if(seachname == null || seachname == "") {
			pb = new PageBean(curPage, MAX_LIST_SIZE, bd.digitalReadCount());
			digitalList = bd.digitalList(pb);
		}else {
			pb = new PageBean(curPage, MAX_LIST_SIZE, bd.digitalReadCount(seachname));
			digitalList = bd.digitalList(pb,seachname);
		}
		request.setAttribute("title", "所有商品");
		
		request.setAttribute("pageBean", pb);
		request.setAttribute("digitalList",digitalList);
		
		request.getRequestDispatcher(BOOKLIST_PATH).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
