package dao.impl;

import bean.Book;
import bean.Digital;
import bean.Message;
import bean.PageBean;
import dao.MsgDao;
import util.DateUtil;
import util.DbUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public  class MsgDaoImpl implements MsgDao {
	@Override
	public List<Message> messageList1(PageBean pageBean) {
		List<Message> list = new ArrayList<>();

		String sql = "select * from view_msg  limit ?,?";
		// 查询的分页结果集
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql, (pageBean.getCurPage() - 1) * pageBean.getMaxSize(),
				pageBean.getMaxSize());

		// 把查询的digital结果由List<Map<String, Object>>转换为List<digital>
		if (lm.size() > 0) {
			for (Map<String, Object> map : lm) {
				Message message = new Message(map);
				list.add(message);
			}
		}

		return list;
	}
	@Override
	public List<Message> messageListlbt() {
		List<Message> list = new ArrayList<>();

		String sql = "select * from view_msg where lbt='1'";
		// 查询的分页结果集
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql );

		// 把查询的digital结果由List<Map<String, Object>>转换为List<digital>
		if (lm.size() > 0) {
			for (Map<String, Object> map : lm) {
				Message message = new Message(map);
				list.add(message);
			}
		}

		return list;
	}



	@Override
	public long msgReadCount() {
		long count=0;
		String sql="select count(*) as count from message";
		List<Map<String, Object>> lm=DbUtil.executeQuery(sql);
		if(lm.size()>0){
			count=(long) lm.get(0).get("count");
		}
		return count;
	}
	
	@Override
	public long msgReadCount(String messagename) {
		long count=0;
		String sql="select count(*) as count from message where messageName like '%"+messagename+"%'";
		List<Map<String, Object>> lm=DbUtil.executeQuery(sql);
		if(lm.size()>0){
			count=(long) lm.get(0).get("count");
		}
		return count;
	}

	@Override
	public List<Message> messageList(PageBean pageBean) {
		List<Message> lu=new ArrayList<>();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		
		String sql="select * from message limit ?,?";
		
		list=DbUtil.executeQuery(sql,(pageBean.getCurPage()-1)*pageBean.getMaxSize(),pageBean.getMaxSize());
		
		if(list.size()>0) {
			for(Map<String,Object> map:list) {
				Message u=new Message(map);
				lu.add(u);
			}
		}
		
		return lu;
	}
	@Override
	public Message findMessageById(int chapters) {
		String sql = "select * from message where id=?";
		Message message = null;
		List<Map<String, Object>> list = DbUtil.executeQuery(sql, chapters);
		if (list.size() > 0) {
			message = new Message(list.get(0));
		}
		return message;
	}
 

	/**
	 *
	 */
	@Override
	public List<Message> messageList() {
		List<Message> lu=new ArrayList<>();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();

		String sql="select * from message ";

		list=DbUtil.executeQuery(sql);

		if(list.size()>0) {
			for(Map<String,Object> map:list) {
				Message u=new Message(map);
				lu.add(u);
			}
		}

		return lu;
	}
	@Override
	public List<Message> messageList(PageBean pageBean,String messagename) {
		List<Message> lu=new ArrayList<>();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		
		String sql="select * from message where title like '%"+messagename+"%' limit ?,?";
		
		list=DbUtil.executeQuery(sql,(pageBean.getCurPage()-1)*pageBean.getMaxSize(),pageBean.getMaxSize());
		
		if(list.size()>0) {
			for(Map<String,Object> map:list) {
				Message u=new Message(map);
				lu.add(u);
			}
		}
		
		return lu;
	}

	@Override
	public boolean findMessage(String messageName) {
		String sql="select * from message where messageName=?";
		List<Map<String,Object>> list=DbUtil.executeQuery(sql, messageName);
		return list.size()>0?true:false;
	}
	
	/**
	 * @param message 要增加的文章对象
	 * @return 返回一个boolean true文章增加成功 false文章增加失败
	 */
	@Override
	public boolean messageAdd(Message message) {
		String sql= "insert into message (title,content,addppname,addppid,addtime,chapters,lbt,bookId) values(?,?,?,?,?,?,?,?)";
		
		int i= DbUtil.excuteUpdate(sql, message.getTitle(),message.getContent(),message.getAddppname(),message.getAddppid(),
				message.getAddtime(), message.getChapters(),message.getLbt(),message.getBookId());
		
		return i>0?true:false;	
	}

	/**
	 * @param id 根据id查找一个文章信息
	 * @return 返回一个list文章信息集合
	 */
	//查找指定id文章信息
	@Override
	public Message findMessage(Integer id) {
		String sql="select * from message where id=?";
		Message u=null;
		List<Map<String,Object>> list=DbUtil.executeQuery(sql, id);
		if(list.size()>0) {
			u=new Message(list.get(0));
		}
		return u;
	}
	/**
	 * 
	 * @param
	 * @return boolean
	 */
	@Override
	public boolean messageUpdate(Message message) {
		String sql="update message set title=?,content=?,chapters=?,lbt=? where id=?";
		int i=DbUtil.excuteUpdate(sql,message.getTitle(),message.getContent(),message.getChapters(),message.getLbt(),message.getId());
		
		return i>0?true:false;
	}
	/**
	 * @param id 要删除的文章id
	 * @return 返回boolean true删除文章成功，false删除文章失败
	 */
	@Override
	public boolean delMessage(int id) {
		String sql="delete from message where id=?";
		int i=DbUtil.excuteUpdate(sql, id);
		return i>0?true:false;
	}
	/**
	 * @param ids 要批量删除id组的字符串
	 */
	@Override
	public boolean batDelMessage(String ids) {
		String sql="delete from message where id in ("+ids+")";
		int i=DbUtil.excuteUpdate(sql);
		return i>0?true:false;
	}

	@Override
	public List<Message> msgList(PageBean pb, int bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long bookReadCount(int bookId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long digitalReadCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long digitalReadCount(String messagename) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 按分类id统计图书数量
	 */
	@Override
	public long messageReadCount(int bookId) {
		String sql = "select count(*) as count from message where bookId=?";
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql, bookId);
		return lm.size() > 0 ? (long) lm.get(0).get("count") : 0;
	}

	/**
	 * 按分类id获取图书列表
	 */
	@Override
	public List<Message> messageList(PageBean pageBean, int bookId) {
		List<Message> list = new ArrayList<>();

		String sql = "select * from message where bookId=? limit ?,?";
		// 查询的分页结果集
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql, bookId,
				(pageBean.getCurPage() - 1) * pageBean.getMaxSize(), pageBean.getMaxSize());

		// 把查询的digital结果由List<Map<String, Object>>转换为List<digital>
		if (lm.size() > 0) {
			for (Map<String, Object> map : lm) {
				Message message = new Message(map);
				list.add(message);
			}
		}
		return list;
	}

	/**
	 * 按分类id获取图书列表
	 */
	@Override
	public List<Message> msgList(PageBean pageBean, String title) {
		List<Message> list = new ArrayList<>();

		String sql = "select * from message where title like '%"+title+"%' limit ?,?";
		// 查询的分页结果集
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql,
				(pageBean.getCurPage() - 1) * pageBean.getMaxSize(), pageBean.getMaxSize());

		// 把查询的digital结果由List<Map<String, Object>>转换为List<digital>
		if (lm.size() > 0) {
			for (Map<String, Object> map : lm) {
				Message message = new Message(map);
				list.add(message);
			}
		}
		return list;
	}


	@Override
	public long messageReadCount(String title) {
		String sql = "select count(*) as count from message where title like '%"+title+"%'";
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql);
		return lm.size() > 0 ? (long) lm.get(0).get("count") : 0;
	}

	@Override
	public long messageReadCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long messqgeReadCount(String title) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean msgAdd(Book message) {
		// TODO Auto-generated method stub
		return false;
	}

}