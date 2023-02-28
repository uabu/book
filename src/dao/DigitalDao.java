package dao;

import java.util.List;

import bean.Digital;
import bean.Catalog;
import bean.PageBean;

import bean.Talk;
import net.sf.json.JSONObject;

public interface DigitalDao {
	// 获取商品总数
	long digitalReadCount();

	//按书名获取商品总数
	long digitalReadCount(String digitalname);

	public List<Digital> digitalList(PageBean pageBean, String digitalname);

	// 获取商品分页列表(视图)
	List<Digital> digitalList(PageBean pageBean);
	// 获取商品分页列表(视图)
	List<Digital> digitalListlbt();
	// 按分类获取商品数量
	long digitalReadCount(int catalogId);

	// 按分类获取商品分页列表(视图)
	List<Digital> digitalList(PageBean pageBean, int catalogId);

	// 增加商品
	boolean digitalAdd(Digital digital);

	// 根据商品id查找商品信息(视图)
	Digital findDigitalById(int digitalId);

	// 根据商品名称查找商品是否存在
	boolean findDigitalByDigitalName(String digitalName);

	// 更新商品信息
	boolean digitalUpdate(Digital digital);

	// 根据id删除商品
	boolean digitalDelById(int digitalId);

	// 根据id串查询图片id串
	String findimgIdByIds(String ids);

	// 批量删除商品
	boolean digitalBatDelById(String ids);

	// 随机获取指定数量书
	List<Digital> digitalList(int num);

	// 获取指定数量新添加的商品
	List<Digital> newDigitals(int num);

	// 查找评论
	List<Talk> findTalkList(int num);
	//添加评论
	void addTalk(Talk talk);
}
