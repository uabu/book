package dao;

import bean.PageBean;
import bean.Book;
import bean.Digital;
import bean.Message;

import java.util.List;


public interface MsgDao {
	
	//获取总记录数
	long messageReadCount();
	//根据文章名获取总记录数
	long messqgeReadCount(String title);
	//获取文章列表（分页显示）
	List<Message> messageList(PageBean pageBean);
    //获取文章列表
    List<Message> messageList();
    List<Message> messageListlbt();
	// 按分类获取商品数量
	//根据文章名模糊查询获取文章列表（分页显示）
	List<Message> messageList(PageBean pageBean, String title);
	//查找文章名是否存在
	boolean findMessage(String title);
	//根据id获取一个文章的信息
	Message findMessage(Integer id);
	//增加文章
	boolean messageAdd(Message message);
	//更新文章
	boolean messageUpdate(Message message);
	//根据id删除一个文章
	boolean delMessage(int id);
	//根据一组id字符串批量删除文章
	boolean batDelMessage(String ids);
	List<Message> msgList(PageBean pb, int bookId);
	long bookReadCount(int bookId);
	long msgReadCount();
	long msgReadCount(String bookIdStr);
	long messageReadCount(String title);
	List<Message> msgList(PageBean pageBean, String title);
	List<Message> messageList(PageBean pageBean, int bookId);
	long messageReadCount(int bookId);
	long digitalReadCount(String messagename);
	long digitalReadCount();
	List<Message> messageList1(PageBean pageBean);
	Message findMessageById(int chapters);
	static Object findMsgById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	boolean msgAdd(Book message);


	
}
