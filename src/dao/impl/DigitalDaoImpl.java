package dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bean.*;
import  dao.DigitalDao;
import  util.DateUtil;
import  util.DbUtil;

public class DigitalDaoImpl implements DigitalDao {

	@Override
	public List<Digital> digitalList(PageBean pageBean) {
		List<Digital> list = new ArrayList<>();

		String sql = "select * from view_digital  limit ?,?";
		// 查询的分页结果集
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql, (pageBean.getCurPage() - 1) * pageBean.getMaxSize(),
				pageBean.getMaxSize());

		// 把查询的digital结果由List<Map<String, Object>>转换为List<digital>
		if (lm.size() > 0) {
			for (Map<String, Object> map : lm) {
				Digital digital = new Digital(map);
				list.add(digital);
			}
		}

		return list;
	}
	@Override
	public List<Digital> digitalListlbt() {
		List<Digital> list = new ArrayList<>();

		String sql = "select * from view_digital where lbt='1'";
		// 查询的分页结果集
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql );

		// 把查询的digital结果由List<Map<String, Object>>转换为List<digital>
		if (lm.size() > 0) {
			for (Map<String, Object> map : lm) {
				Digital digital = new Digital(map);
				list.add(digital);
			}
		}

		return list;
	}
	@Override
	public long digitalReadCount() {
		String sql = "select count(*) as count from s_digital";
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql);
		return lm.size() > 0 ? (long) lm.get(0).get("count") : 0;
	}

	@Override
	public boolean digitalAdd(Digital digital) {
		String sql = "insert into s_digital(digitalName,catalogId,price,description,imgId,addTime,lbt) values(?,?,?,?,?,?,?)";

		int i = DbUtil.excuteUpdate(sql, digital.getDigitalName(), digital.getCatalog().getCatalogId(),
				digital.getPrice(), digital.getDescription(), digital.getUpLoadImg().getImgId(),
				DateUtil.getTimestamp(),digital.getLbt());

		return i > 0 ? true : false;
	}

	@Override
	public Digital findDigitalById(int digitalId) {
		String sql = "select * from view_digital where digitalId=?";
		Digital digital = null;
		List<Map<String, Object>> list = DbUtil.executeQuery(sql, digitalId);
		if (list.size() > 0) {
			digital = new Digital(list.get(0));
		}
		return digital;
	}

	/**
	 *
	 */
	@Override
	public boolean findDigitalByDigitalName(String digitalName) {
		String sql = "select * from s_digital where digitalName=?";
		List<Map<String, Object>> list = DbUtil.executeQuery(sql, digitalName);
		return list.size() > 0 ? true : false;
	}

	/**
	 * 更新图书信息
	 */
	@Override
	public boolean digitalUpdate(Digital digital) {
		String sql = "update s_digital set catalogId=?,price=?,description=? where digitalId=?";
		int i = DbUtil.excuteUpdate(sql, digital.getCatalogId(),  digital.getPrice(),
				digital.getDescription(), digital.getDigitalId());
		return i > 0 ? true : false;
	}

	/**
	 * 图书删除
	 */
	@Override
	public boolean digitalDelById(int digitalId) {
		String sql = "delete from s_digital where digitalId=?";
		int i = DbUtil.excuteUpdate(sql, digitalId);
		return i > 0 ? true : false;
	}

	/**
	 * 批量查询
	 */
	@Override
	public String findimgIdByIds(String ids) {
		String imgIds = "";
		String sql = "select imgId from s_digital where digitalId in(" + ids + ")";
		List<Map<String, Object>> list = DbUtil.executeQuery(sql);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (i != list.size() - 1) {
					imgIds += list.get(i).get("imgId") + ",";
				} else {
					imgIds += list.get(i).get("imgId");
				}
			}
		}
		return imgIds;
	}

	// 批量删除
	@Override
	public boolean digitalBatDelById(String ids) {
		String sql = "delete from s_digital where digitalId in(" + ids + ")";
		int i = DbUtil.excuteUpdate(sql);
		return i > 0 ? true : false;
	}

	// 随机查询一定数量的书
	@Override
	public List<Digital> digitalList(int num) {
		List<Digital> list = new ArrayList<>();
		String sql = "select * from view_digital order by rand() LIMIT ?";
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql, num);
		// 把查询的digital结果由List<Map<String, Object>>转换为List<digital>
		if (lm.size() > 0) {
			for (Map<String, Object> map : lm) {
				Digital digital = new Digital(map);
				list.add(digital);
			}
		}
		return list;
	}

	/**
	 * 查询指定数量新书
	 */
	@Override
	public List<Digital> newDigitals(int num) {
		List<Digital> list = new ArrayList<>();
		String sql = "SELECT * FROM view_digital ORDER BY addTime desc limit 0,?";
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql, num);
		// 把查询的digital结果由List<Map<String, Object>>转换为List<Digital>
		if (lm.size() > 0) {
			for (Map<String, Object> map : lm) {
				Digital digital = new Digital(map);
				list.add(digital);
			}
		}
		return list;
	}

	@Override
	public List<Talk> findTalkList(int num) {
		List<Talk> list = new ArrayList<>();
		String sql = "SELECT * FROM Talk  where did =?  ORDER BY time desc ";
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql,num);
		// 把查询的digital结果由List<Map<String, Object>>转换为List<Digital>
		if (lm.size() > 0) {
			for (Map<String, Object> map : lm) {
				Talk digital = new Talk(map);
				list.add(digital);
			}
		}
		return list;
	}

	@Override
	public void addTalk(Talk talk) {
		String sql = "insert into talk(content,time,did) values(?,?,?)";

		int i = DbUtil.excuteUpdate(sql, talk.getContent(),
				DateUtil.getTimestamp(),talk.getDid());
	}

	/**
	 * 按分类id统计图书数量
	 */
	@Override
	public long digitalReadCount(int catalogId) {
		String sql = "select count(*) as count from s_digital where catalogId=?";
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql, catalogId);
		return lm.size() > 0 ? (long) lm.get(0).get("count") : 0;
	}

	/**
	 * 按分类id获取图书列表
	 */
	@Override
	public List<Digital> digitalList(PageBean pageBean, int catalogId) {
		List<Digital> list = new ArrayList<>();

		String sql = "select * from view_digital where catalogId=? limit ?,?";
		// 查询的分页结果集
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql, catalogId,
				(pageBean.getCurPage() - 1) * pageBean.getMaxSize(), pageBean.getMaxSize());

		// 把查询的digital结果由List<Map<String, Object>>转换为List<digital>
		if (lm.size() > 0) {
			for (Map<String, Object> map : lm) {
				Digital digital = new Digital(map);
				list.add(digital);
			}
		}
		return list;
	}

	/**
	 * 按分类id获取图书列表
	 */
	@Override
	public List<Digital> digitalList(PageBean pageBean, String digitalname) {
		List<Digital> list = new ArrayList<>();

		String sql = "select * from view_digital where digitalName like '%"+digitalname+"%' limit ?,?";
		// 查询的分页结果集
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql,
				(pageBean.getCurPage() - 1) * pageBean.getMaxSize(), pageBean.getMaxSize());

		// 把查询的digital结果由List<Map<String, Object>>转换为List<digital>
		if (lm.size() > 0) {
			for (Map<String, Object> map : lm) {
				Digital digital = new Digital(map);
				list.add(digital);
			}
		}
		return list;
	}


	@Override
	public long digitalReadCount(String digitalname) {
		String sql = "select count(*) as count from s_digital where digitalName like '%"+digitalname+"%'";
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql);
		return lm.size() > 0 ? (long) lm.get(0).get("count") : 0;
	}

}
