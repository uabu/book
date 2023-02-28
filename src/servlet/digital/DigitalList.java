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
@WebServlet("/DigitalList")
public class DigitalList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int MAX_LIST_SIZE = 12;
	private static final String LIST_PATH="jsp/digital/digitallist.jsp";
   

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
			digitalList(request,response);
			break;
		}
	}

	private void digitalList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DigitalDao bd = new DigitalDaoImpl();
		int curPage = 1;
		String page = request.getParameter("page");
		if (page != null) {
			curPage = Integer.parseInt(page);
		}
		
		PageBean pb=null;
		List<Digital> digitalList=new ArrayList<Digital>();
		String catalogIdStr = request.getParameter("catalogId");//获取有没有分类id，没有就是查全部
		
		if(catalogIdStr!=null) {
			int catalogId=Integer.parseInt(catalogIdStr);
			pb = new PageBean(curPage, MAX_LIST_SIZE, bd.digitalReadCount(catalogId));
			digitalList = bd.digitalList(pb,catalogId);
			
			if(digitalList.size()>0) {
				request.setAttribute("title", digitalList.get(0).getCatalog().getCatalogName());//从返回的分类集合中第一个获取数据的分类
			}
			
		}else {
			pb = new PageBean(curPage, MAX_LIST_SIZE, bd.digitalReadCount());
			digitalList = bd.digitalList(pb);
			request.setAttribute("title", "所有商品");
		}
		
		request.setAttribute("pageBean", pb);
		request.setAttribute("digitalList",digitalList);
		
		request.getRequestDispatcher(LIST_PATH).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
