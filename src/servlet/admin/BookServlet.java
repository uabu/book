package servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import  bean.PageBean;
import  dao.DigitalDao;
import dao.MsgDao;
import  dao.BookDao;
import dao.CatalogDao;
import  dao.impl.DigitalDaoImpl;
import dao.impl.MsgDaoImpl;
import  dao.impl.BookDaoImpl;
import dao.impl.CatalogDaoImpl;
import net.sf.json.JSONObject;

/**
 * 商品分类servlet
 * Servlet implementation class catalogServlet
 */
@WebServlet("/jsp/admin/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BOOKLIST_PATH="msgManage/bookList.jsp";
	private static final String BOOKADD_PATH="msgManage/bookAdd.jsp";
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		switch(action) {
		case "list":
			bookList(request,response);
			break;
		case "add":
			bookAdd(request,response);
			break;
		case "del":
			bookDel(request,response);
			break;
		case "batDel":
			bookBatDel(request,response);
			break;
		case "find":
			bookFind(request,response);
			break;
		}
	}
	
	


	private void bookBatDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids=request.getParameter("ids");
		BookDao cd=new BookDaoImpl();
		
		if(cd.bookBatDelById(ids)) {
			request.setAttribute("bookMessage", "图书已批量删除");
			
		}else {
			request.setAttribute("bookMessage", "图书删除失败");
		}
		//用户删除成功失败都跳转到用户列表页面
		bookList(request, response);//通过servlet中listUser跳到用户列表
		
	}
	
	private void bookDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId=Integer.parseInt(request.getParameter("id"));
		BookDao cd= new BookDaoImpl();
		if(cd.bookDel(bookId)) {
			request.setAttribute("bookMessage", "该图书已删除");
		}else {
			request.setAttribute("bookMessage", "该图书删除失败");
		}
		bookList(request,response);
	}

	private void bookAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookName=request.getParameter("bookName");
		BookDao cd =new BookDaoImpl();
		if(cd.bookAdd(bookName)) {
			request.setAttribute("bookMessage", "增加图书成功");
			bookList(request,response);
		}else {
			request.setAttribute("bookMessage", "增加图书失败");
			request.getRequestDispatcher(BOOKADD_PATH).forward(request,response);
		}
		
	}

	//获取分类列表
	private void bookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int curPage = 1;
		String page = request.getParameter("page");
		if (page != null) {
			curPage = Integer.parseInt(page);
		}
		int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
		BookDao cd = new BookDaoImpl();
		
		PageBean pb = new PageBean(curPage, maxSize, cd.bookReadCount());
		
		request.setAttribute("pageBean", pb);
		
		request.setAttribute("bookList", cd.bookList(pb));
		request.getRequestDispatcher(BOOKLIST_PATH).forward(request, response);
	}
	// ajax查找商品是否存在
		private void bookFind(HttpServletRequest request, HttpServletResponse response) throws IOException {
			String bookName = request.getParameter("param");

			BookDao cd = new BookDaoImpl();
			// 这里实例化json对象需要导入6个jar包（
			// commons-lang-2.4.jar
			// ,commons-collections-3.2.1.jar,commons-beanutils-1.8.3.jar
			// json-lib-2.4-jdk15.jar ,ezmorph-1.0.6.jar ,commons-logging-1.1.3.jar）
			JSONObject json = new JSONObject();
			if (cd.findBookByBookName(bookName)) {
				json.put("info", "该分类已存在");
				json.put("status", "n");
			} else {
				json.put("info", "输入正确");
				json.put("status", "y");
			}
			response.getWriter().write(json.toString());

		}

}
