package servlet.digital;

import bean.Book;
import bean.Catalog;
import bean.Digital;
import bean.Message;
import bean.PageBean;
import bean.UpLoadImg;
import dao.BookDao;
import dao.CatalogDao;
import dao.DigitalDao;
import dao.MsgDao;
import dao.UpLoadImgDao;
import dao.impl.BookDaoImpl;
import dao.impl.CatalogDaoImpl;
import dao.impl.DigitalDaoImpl;
import dao.impl.MsgDaoImpl;
import dao.impl.UpLoadImgDaoImpl;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import util.DateUtil;
import util.RanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class MessageManageServlet
 */
@WebServlet("/MsgServlet")
public class MsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USERLIST_PATH="jsp/admin/msgManage/msgList.jsp";//文章列表页面地址
	private static final String USERADD_PATH="jsp/admin/msgManage/msgAdd.jsp";//文章增加页面地址
	private static final String USEREDIT_PATH="jsp/admin/msgManage/msgEdit.jsp";//文章修改页面地址
	private static final String USERDETAIL_PATH="jsp/admin/msgManage/msgDetail.jsp";//文章修改页面地址
    private static final String DETAIL_PATH="jsp/digital/msgdetails.jsp";//首页文章详情页
	private JSONObject map;


    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if(action==null) {
			action="list";
		}
		switch(action){
		case "list":
			msgList(request,response);
			break;
		case "addView":
			addView(request,response);
			break;
		case "add":
			msgAdd(request,response);
			break;
		case "update":
			msgUpdate(request,response);
			break;
		case "edit":
			msgEdit(request,response);
			break;
		case "del":
			userDel(request,response);
			break;
		case "batDel":
			userBatDel(request,response);
			break;
		case "find":
			adminFind(request,response);
			break;
		case "detail":
			datail(request,response);
			break;
		case "seach":
			seachMsg(request,response);
		}
	}

	private void seachMsg(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		MsgDao ud=new MsgDaoImpl();
		int curPage=1;
		String page=request.getParameter("page");
		if(page!=null){
			curPage=Integer.parseInt(page);
		}
		//获取xml中设置的每页显示大小参数
		int maxSize=Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
		String username = request.getParameter("title");
		PageBean pageBean=null;
		if(username != null && username != "") {
			pageBean=new PageBean(curPage,maxSize,ud.msgReadCount(username));
			request.setAttribute("messageList", ud.messageList(pageBean,username));
		}else {
			pageBean=new PageBean(curPage,maxSize,ud.msgReadCount());
			request.setAttribute("messageList", ud.messageList(pageBean));
		}
		request.setAttribute("pageBean", pageBean);
		
		request.getRequestDispatcher(USERLIST_PATH).forward(request, response);
		
	}

	private void datail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
	    MsgDao ud=new MsgDaoImpl();
		Message msg= ud.findMessage(Integer.valueOf(id));
		BookDao bookDao=new BookDaoImpl();
		Book book=bookDao.findBookById(msg.getBookId());
		msg.setBook(bookDao.findBookById(msg.getBookId()));
		request.setAttribute("messageInfo",msg);//这里回去是Message对象
		request.getRequestDispatcher(USERDETAIL_PATH).forward(request, response);
		
	}

	private void userBatDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids=request.getParameter("ids");
		MsgDao ud=new MsgDaoImpl();
		
		if(ud.batDelMessage(ids)) {
			request.setAttribute("userMessage", "文章已批量删除");
		}else {
			request.setAttribute("userMessage", "文章批量删除失败");
		}
		//文章删除成功失败都跳转到文章列表页面
		msgList(request, response);//通过servlet中listMessage跳到文章列表
		
	}

	private void userDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		MsgDao ud=new MsgDaoImpl();
		if(ud.delMessage(id)) {
			request.setAttribute("userMessage", "文章已删除");
		}else {
			request.setAttribute("userMessage", "文章删除失败");
		}
		//文章删除成功失败都跳转到文章列表页面
		msgList(request, response);//通过servlet中listMessage跳到文章列表
		
	}
	


	private void msgUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    Message user=new Message();
	    user.setId(Integer.parseInt(request.getParameter("id")));
	    user.setChapters(request.getParameter("chapters"));
	    user.setTitle(request.getParameter("title"));
	    user.setContent(request.getParameter("content"));
	    user.setLbt(Integer.parseInt(request.getParameter("lbt")));
	    
		MsgDao ud=new MsgDaoImpl();
		if(ud.messageUpdate(user)) {
			request.setAttribute("userMessage", "文章更新成功");
			msgList(request, response);//通过servlet中listMessage跳到文章列表
		}else {
			//更新失败跳转到修改页面
			request.setAttribute("userMessage", "文章更新失败");
			request.setAttribute("userInfo", ud.findMessage(Integer.valueOf(user.getId())));//这里回去是Admin对象
			request.getRequestDispatcher(USEREDIT_PATH).forward(request, response);
		}
		
	}

	private void msgEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		MsgDao ud=new MsgDaoImpl();
		BookDao bookDao = new BookDaoImpl();
		
		request.setAttribute("book", bookDao.getBook());//获取商品分类信息
	 
		request.setAttribute("messageInfo", ud.findMessageById(Integer.parseInt(id)));
		request.getRequestDispatcher(USEREDIT_PATH).forward(request, response);
		
	}

	private void adminFind(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userName=request.getParameter("param");
		MsgDao ud=new MsgDaoImpl();
		JSONObject json=new JSONObject();
		if(ud.findMessage(userName)){
			json.put("info", "文章名已存在");
			json.put("status", "n");
		}else{
			json.put("info", "文章名可以使用");
			json.put("status", "y");
		}
		response.getWriter().write(json.toString());
		
	}
	//图书类型
	private void msgAddReq(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookDao cd = new BookDaoImpl();
		request.setAttribute("book", cd.getBook());
		request.getRequestDispatcher(USERADD_PATH).forward(request, response);

	}


	private void addView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		BookDao bookDao = new BookDaoImpl();
		
		request.setAttribute("book", bookDao.getBook());//获取商品分类信息
	  
		request.getRequestDispatcher(USERADD_PATH).forward(request, response);
		
	}


	private void msgAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MsgDao ad=new MsgDaoImpl();
		/*Message user=new Message(
				request.getParameter("title"),
				request.getParameter("content"),
				request.getParameter("chapters"),
				 DateUtil.show(),
				"",
				"",
				0, 0);*/
		Message user=new 	Message();
		user.setTitle(request.getParameter("title"));
		user.setBookId(Integer.parseInt(request.getParameter("book")));
		user.setContent(request.getParameter("content"));
		user.setChapters(request.getParameter("chapters"));
		user.setAddtime( DateUtil.show());
		user.setLbt(Integer.parseInt(request.getParameter("lbt"))); 
					//添加之前判断文章名是否在库中存在
			//执行dao层添加操作
		// 商品分类信息
			if(ad.messageAdd(user)){
				request.setAttribute("userMessage", "文章添加成功！");
				msgList(request, response);//通过servlet中listMessage跳到文章列表
			}else{
				request.setAttribute("userMessage", "文章添加失败！");
				request.getRequestDispatcher(USERADD_PATH).forward(request, response);
			}}

			


	private void msgList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MsgDao ud=new MsgDaoImpl();
		int curPage=1;
		String page=request.getParameter("page");
		if(page!=null){
			curPage=Integer.parseInt(page);
		}
		//获取xml中设置的每页显示大小参数
		int maxSize=Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
        String id = request.getParameter("id");
        String ids = request.getParameter("ids");
        if(StringUtils.isNotEmpty(ids)  ){
            int integer = Integer.valueOf(id);
            request.setAttribute("message", ud.findMessage(integer));

            request.getRequestDispatcher(DETAIL_PATH).forward(request, response);
        }else {
            PageBean pageBean=new PageBean(curPage,maxSize,ud.digitalReadCount());

            request.setAttribute("messageList", ud.messageList(pageBean));
            request.setAttribute("pageBean", pageBean);
            request.getRequestDispatcher(USERLIST_PATH).forward(request, response);
        }
		

		
	}

}
