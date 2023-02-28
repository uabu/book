package dao;

import java.util.List;
import bean.Book;
import bean.PageBean;



public interface BookDao {
	//获取商品分类信息
	List<Book> getBook();
	//获取商品分类信息（分页）
	List<Book> bookList(PageBean pb);
	//统计总数
	long bookReadCount();
	//分类删除
	boolean bookDel(int bookId);
	//分类批量删除
	boolean bookBatDelById(String ids);
	//分类批量删除
	Book findBookById(int id);
	//查找分类名称
	boolean findBookByBookName(String bookName);
	//增加分类
	boolean bookAdd(String bookName);
	long bookReadCount(int bookId);
	List<Book> bookList(PageBean pb, Object bookId);
}
