package dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bean.Book;
import bean.Message;
import bean.PageBean;
import dao.BookDao;
import util.DbUtil;

public class BookDaoImpl implements BookDao {
	/**
	 * 获取商品分类信息
	 */
	public List<Book> bookList(PageBean pb) {
		List<Book> list=new ArrayList<Book>();
		String sql = "select * from s_book limit ?,?";
		// 查询的分页结果集
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql, (pb.getCurPage() - 1) * pb.getMaxSize(),
				pb.getMaxSize());
		if(lm.size()>0){
			for(Map<String,Object> map:lm){
				Book book=new Book(map);
			list.add(book);
			}
		}	
		return list; 
	}

	public long bookReadCount() {
		long count=0;
		String sql="select count(*) as count from s_book";
		List<Map<String, Object>> lm=DbUtil.executeQuery(sql);
		if(lm.size()>0){
			count=(long) lm.get(0).get("count");
		}
		return count;
	}

	public List<Book> getBook() {
		List<Book> list=new ArrayList<Book>();
		String sql="select * from s_book";
		
		List<Map<String,Object>> lmso=DbUtil.executeQuery(sql);
		if(lmso.size()>0){
			for(Map<String,Object> map:lmso){
				Book book=new Book(map);
			list.add(book);
			}
		}	
		return list; 
	}

	public boolean bookDel(int bookId) {
		String sql = "delete from s_book where bookId=?";
		int i = DbUtil.excuteUpdate(sql, bookId);
		return i > 0 ? true : false;
	}

	public boolean bookBatDelById(String ids) {
		String sql="delete from s_book where bookId in("+ids+")";
		int i=DbUtil.excuteUpdate(sql);
		return i>0?true:false;
	}

	public boolean findBookByBookName(String bookName) {
		String sql = "select * from s_book where bookName=?";
		List<Map<String, Object>> list = DbUtil.executeQuery(sql, bookName);
		return list.size() > 0 ? true : false;
	}
 
	public Book findBookById(int id) {
		String sql = "select * from s_book where bookId=?";
		Book book = null;
		List<Map<String, Object>> list = DbUtil.executeQuery(sql, id);
		if (list.size() > 0) {
			book = new Book(list.get(0));
		}
		return book;
	}
 
	
	public boolean bookAdd(String bookName) {
		String sql="insert into s_book(bookName) values(?)";
		int i = DbUtil.excuteUpdate(sql, bookName);
		return i > 0 ? true : false;
	}

	@Override
	public long bookReadCount(int bookId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Book> bookList(PageBean pb, Object bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
