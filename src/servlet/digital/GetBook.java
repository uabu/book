package servlet.digital;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import dao.BookDao;
import dao.DigitalDao;
import dao.MsgDao;
import dao.impl.BookDaoImpl;
import dao.impl.DigitalDaoImpl;
import dao.impl.MsgDaoImpl;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetCatalog
 */
@WebServlet("/GetBook")
public class GetBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json"); 
		JSONObject json = new JSONObject();
		BookDaoImpl cd=new BookDaoImpl();
		MsgDao bd=new MsgDaoImpl();
		List<Book> book = cd.getBook();
		
		//这里返回查询每个分类的数量
		for(int i=0;i<book.size();i++) {
			Book c = book.get(i);
			long size=bd.messageReadCount(c.getBookId());
			c.setBookSize(size);
		}
		json.put("book", book);
		response.getWriter().append(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
