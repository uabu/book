package servlet.admin;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import  bean.Digital;
import  bean.Catalog;
import  bean.PageBean;
import  bean.UpLoadImg;
import  dao.DigitalDao;
import  dao.CatalogDao;
import  dao.UpLoadImgDao;
import  dao.impl.DigitalDaoImpl;
import  dao.impl.CatalogDaoImpl;
import  dao.impl.UpLoadImgDaoImpl;
import  util.RanUtil;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class DigitalManageServlet
 */
@WebServlet("/jsp/admin/DigitalManageServlet")
public class DigitalManageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String BOOKLIST_PATH = "digitalManage/digitalList.jsp";// 商品列表页面地址
	private static final String BOOKADD_PATH = "digitalManage/digitalAdd.jsp";// 商品增加页面地址
	private static final String BOOKEDIT_PATH = "digitalManage/digitalEdit.jsp";// 商品修改页面地址
	private static final String BOOKDETAIL_PATH = "digitalManage/digitalDetail.jsp";// 商品详情页面地址
	private static final String BOOKIMGDIR_PATH="images/digital/digitalimg/";//商品图片保存文件夹相对路径

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "list":
			digitalList(request, response);
			break;
			case "list2":
				digitalListlbt(request, response);
				break;
		case "detail":
			digitalDetail(request, response);
			break;
		case "addReq":
			digitalAddReq(request, response);
			break;
		case "add":
			digitalAdd(request, response);
			break;
		case "edit":
			digitalEdit(request, response);
			break;
		case "update":
			digitalUpdate(request,response);
			break;
		case "find":
			digitalFind(request, response);
			break;
		case "updateImg":
			updateImg(request,response);
			break;
		case "del":
			digitalDel(request,response);
			break;
		case "batDel":
			digitalBatDel(request,response);
			break;
		case "seach":
			seachDigital(request,response);
		}
	}
	private void seachDigital(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int curPage = 1;
		String page = request.getParameter("page");
		if (page != null) {
			curPage = Integer.parseInt(page);
		}
		int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
		String digitalname = request.getParameter("digitalname");
		DigitalDao bd = new DigitalDaoImpl();
		PageBean pb = null;
		if(digitalname != null && digitalname != "") {
			pb = new PageBean(curPage, maxSize, bd.digitalReadCount(digitalname));
			request.setAttribute("digitalList", bd.digitalList(pb,digitalname));
		}else {
			pb = new PageBean(curPage, maxSize, bd.digitalReadCount());
			request.setAttribute("digitalList", bd.digitalList(pb));
		}

		request.setAttribute("pageBean", pb);
		request.getRequestDispatcher(BOOKLIST_PATH).forward(request, response);
	}

	//商品批量删除
	private void digitalBatDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids=request.getParameter("ids");
		DigitalDao bd=new DigitalDaoImpl();
		UpLoadImgDao uid=new UpLoadImgDaoImpl();
		File contextPath=new File(request.getServletContext().getRealPath("/"));

		String imgIds=bd.findimgIdByIds(ids);//批量查询图片的id并组成一组字符串

		List<UpLoadImg> list = uid.findImgByIds(imgIds);
		if(bd.digitalBatDelById(ids)) {
			request.setAttribute("digitalMessage", "商品已批量删除");
			if(uid.imgBatDelById(imgIds)) {
				for(UpLoadImg uli:list) {
					//批量删除本地文件
					File f=new File(contextPath,uli.getImgSrc());
					if(f.exists()) {
						f.delete();
					}
				}
			}
		}else {
			request.setAttribute("digitalMessage", "商品批量删除失败");
		}
		//用户删除成功失败都跳转到用户列表页面
		digitalList(request, response);//通过servlet中listUser跳到用户列表
	}

	//商品删除
	private void digitalDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		File contextPath=new File(request.getServletContext().getRealPath("/"));
		DigitalDao bd=new DigitalDaoImpl();
		UpLoadImgDao uid=new UpLoadImgDaoImpl();
		Digital digital=bd.findDigitalById(id);
		//这里先删除数据库商品信息，再删除商品图片及本地硬盘图片信息
		if(bd.digitalDelById(id)) {
			request.setAttribute("digitalMessage", "商品已删除");
			if(uid.imgDelById(digital.getImgId())) {
				//删除本地文件
				File f=new File(contextPath,digital.getUpLoadImg().getImgSrc());
				if(f.exists()) {
					f.delete();
				}
			}
		}else {
			request.setAttribute("digitalMessage", "商品删除失败");
		}

		//用户删除成功失败都跳转到用户列表页面
		digitalList(request, response);//通过servlet中listUser跳到用户列表

	}

	//商品更新
	private void digitalUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DigitalDao digitalDao=new DigitalDaoImpl();
		Digital digital=new Digital();
		digital.setDigitalId(Integer.parseInt(request.getParameter("digitalId")));
		digital.setCatalogId(Integer.parseInt(request.getParameter("catalog")));
		digital.setPrice(Double.parseDouble(request.getParameter("price")));
		digital.setDescription(request.getParameter("description"));

		if(digitalDao.digitalUpdate(digital)) {
			request.setAttribute("digitalMessage", "修改成功");
			digitalList(request, response);
		}else {
			request.setAttribute("digitalMessage", "图片失败");
			request.setAttribute("digitalInfo", digitalDao.findDigitalById(digital.getDigitalId()));
			request.getRequestDispatcher(BOOKEDIT_PATH).forward(request, response);
		}
	}

	// 更新商品图片
	private void updateImg(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int digitalId = Integer.parseInt(request.getParameter("id"));
		boolean flag = false;
		String imgSrc = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		String imgName = null;
		String contentType = null;

		DigitalDao digitalDao = new DigitalDaoImpl();
		UpLoadImgDao upImgDao = new UpLoadImgDaoImpl();

		File contextPath=new File(request.getServletContext().getRealPath("/"));
		File dirPath = new File( contextPath,BOOKIMGDIR_PATH);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}

		DiskFileItemFactory dfif = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(dfif);
		List<FileItem> parseRequest = null;
		try {
			parseRequest = servletFileUpload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		Iterator<FileItem> iterator = parseRequest.iterator();
		while (iterator.hasNext()) {
			FileItem fileItem = iterator.next();
			if (!fileItem.isFormField()) {

				inputStream = fileItem.getInputStream();
				contentType = fileItem.getContentType();
				if ("image/jpeg".equals(contentType)) {
					imgName = RanUtil.getUUID() + ".jpg";
					flag = true;
				}
				if ("image/png".equals(contentType)) {
					imgName = RanUtil.getUUID() + ".png";
					flag = true;
				}

			}

		}
		if (flag) {
			imgSrc = BOOKIMGDIR_PATH + imgName;
			outputStream = new FileOutputStream(new File(contextPath,imgSrc));
			IOUtils.copy(inputStream, outputStream);
			outputStream.close();
			inputStream.close();
			//根据商品id去查询图片信息
			Digital digital = digitalDao.findDigitalById(digitalId);
			UpLoadImg upImg = digital.getUpLoadImg();
			// 删除旧图片文件如果存在
			File oldImg = new File(contextPath,digital.getUpLoadImg().getImgSrc());
			if (oldImg.exists()) {
				oldImg.delete();
			}
			upImg.setImgName(imgName);
			upImg.setImgSrc(imgSrc);
			upImg.setImgType(contentType);


			if (upImgDao.imgUpdate(upImg)) {
				request.setAttribute("digitalMessage", "图片修改成功");
			} else {
				request.setAttribute("digitalMessage", "图片修改失败");
			}
		} else {
			request.setAttribute("digitalMessage", "图片修改失败");
		}
		digitalEdit(request,response);
	}

	// 获取商品分类信息
	private void digitalAddReq(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CatalogDao cd = new CatalogDaoImpl();
		request.setAttribute("catalog", cd.getCatalog());
		request.getRequestDispatcher(BOOKADD_PATH).forward(request, response);

	}

	// 商品增加
	private void digitalAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;

		Map<String, String> map = new HashMap<>();
		InputStream inputStream = null;
		OutputStream outputStream = null;
		File dirPath = new File(request.getServletContext().getRealPath("/") + BOOKIMGDIR_PATH);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}

		DiskFileItemFactory dfif = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(dfif);
		// 解决乱码
		servletFileUpload.setHeaderEncoding("ISO8859_1");

		List<FileItem> parseRequest = null;
		try {
			parseRequest = servletFileUpload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		Iterator<FileItem> iterator = parseRequest.iterator();

		while (iterator.hasNext()) {
			FileItem fileItem = iterator.next();
			// 判断是否是表单的普通字段true为普通表单字段，false为上传文件内容
			if (fileItem.isFormField()) {
				String name = new String(fileItem.getFieldName().getBytes("ISO8859_1"), "utf-8");
				String value = new String(fileItem.getString().getBytes("ISO8859_1"), "utf-8");
				map.put(name, value);
			} else {
				String imgName = null;

				String contentType = fileItem.getContentType();

				if ("image/jpeg".equals(contentType)) {
					imgName = RanUtil.getUUID() + ".jpg";
					flag = true;
				}
				if ("image/png".equals(contentType)) {
					imgName = RanUtil.getUUID() + ".png";
					flag = true;
				}
				if (flag) {
					inputStream = fileItem.getInputStream();
					File file = new File(dirPath, imgName);
					outputStream = new FileOutputStream(file);
					// 保存img信息到map集合中，后面传入对象使用
					map.put("imgName", imgName);
					map.put("imgSrc", BOOKIMGDIR_PATH + imgName);
					map.put("imgType", contentType);
				}

			}
		}
		// 如果上传的内容小于3个必填项或者图片没有或类型不正确返回
		if (map.size() < 3 || !flag) {
			request.setAttribute("digitalMessage", "商品添加失败");
			digitalAddReq(request, response);
		} else {
			// 验证通过才可以保存图片流到本地
			IOUtils.copy(inputStream, outputStream);
			outputStream.close();
			inputStream.close();

			// 把map集合中存储的表单数据提取出来转换为digital对象
			// 这里要求商品增加的字段要和数据库字段一致，不然map集合转对象会出错
			Digital digital = new Digital();
			digital.setDigitalName(map.get("digitalName"));
			digital.setPrice(Double.parseDouble(map.get("price")));
			digital.setDescription(map.get("desc"));
			digital.setLbt(Integer.valueOf(map.get("lbt")));
			// 商品分类信息
			Catalog catalog = digital.getCatalog();
			catalog.setCatalogId(Integer.parseInt(map.get("catalog")));
			// 图片信息
			UpLoadImg upLoadImg = digital.getUpLoadImg();
			upLoadImg.setImgName(map.get("imgName"));
			upLoadImg.setImgSrc(map.get("imgSrc"));
			upLoadImg.setImgType(map.get("imgType"));

			// 增加商品先增加商品图片,商品图片增加成功了在添加商品信息
			UpLoadImgDao uid = new UpLoadImgDaoImpl();
			if (uid.imgAdd(digital.getUpLoadImg())) {
				// 获取商品图片添加后的id
				Integer imgId = uid.findIdByImgName(upLoadImg.getImgName());
				upLoadImg.setImgId(imgId);
				DigitalDao bd = new DigitalDaoImpl();
				if (bd.digitalAdd(digital)) {
					request.setAttribute("digitalMessage", "商品添加成功");
					digitalList(request, response);
				} else {
					request.setAttribute("digitalMessage", "商品添加失败");
					digitalAddReq(request, response);
				}
			} else {
				// 图片添加失败就判定商品添加失败
				request.setAttribute("digitalMessage", "商品添加失败");
				digitalAddReq(request, response);
			}

		}
	}

	// 获取商品列表
	private void digitalList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int curPage = 1;
		String page = request.getParameter("page");
		if (page != null) {
			curPage = Integer.parseInt(page);
		}
		int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
		DigitalDao bd = new DigitalDaoImpl();
		PageBean pb = new PageBean(curPage, maxSize, bd.digitalReadCount());

		request.setAttribute("pageBean", pb);
		request.setAttribute("digitalList", bd.digitalList(pb));
		request.getRequestDispatcher(BOOKLIST_PATH).forward(request, response);
	}

	// 获取商品列表2
	private void digitalListlbt(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int curPage = 1;
		String page = request.getParameter("page");
		if (page != null) {
			curPage = Integer.parseInt(page);
		}
		int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
		DigitalDao bd = new DigitalDaoImpl();
		PageBean pb = new PageBean(curPage, maxSize, bd.digitalReadCount());

		request.setAttribute("pageBean", pb);
		request.setAttribute("digitalList", bd.digitalListlbt());
		request.getRequestDispatcher(BOOKLIST_PATH).forward(request, response);
	}
	// 商品详情页
	private void digitalDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		DigitalDao bd = new DigitalDaoImpl();
		request.setAttribute("digitalInfo", bd.findDigitalById(Integer.parseInt(id)));
		request.getRequestDispatcher(BOOKDETAIL_PATH).forward(request, response);

	}

	// 接收商品修改请求
	private void digitalEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int digitalId = Integer.parseInt(request.getParameter("id"));
		DigitalDao digitalDao = new DigitalDaoImpl();
		CatalogDao catalogDao = new CatalogDaoImpl();
		request.setAttribute("catalog", catalogDao.getCatalog());//获取商品分类信息
		request.setAttribute("digitalInfo", digitalDao.findDigitalById(digitalId));//获取商品信息byId
		request.getRequestDispatcher(BOOKEDIT_PATH).forward(request, response);
	}

	// ajax查找商品是否存在
	private void digitalFind(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String digitalName = request.getParameter("param");

		DigitalDao bd = new DigitalDaoImpl();
		// 这里实例化json对象需要导入6个jar包（
		// commons-lang-2.4.jar
		// ,commons-collections-3.2.1.jar,commons-beanutils-1.8.3.jar
		// json-lib-2.4-jdk15.jar ,ezmorph-1.0.6.jar ,commons-logging-1.1.3.jar）
		JSONObject json = new JSONObject();
		if (bd.findDigitalByDigitalName(digitalName)) {
			json.put("info", "该商品已存在");
			json.put("status", "n");
		} else {
			json.put("info", "输入正确");
			json.put("status", "y");
		}
		response.getWriter().write(json.toString());

	}

}
