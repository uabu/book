package bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Lbt {

    private int lid;            //订单编号
    private String orderNum;		//订单号
    private int userId;             //用户编号
    private String orderDate;       //订单日期
    private double money;			//订单金额
    private int orderStatus;     	//订单状态

    private List<OrderItem> oItem=new ArrayList<>();
    private User user=new User();

    public Lbt() {
    }

    public Lbt(Map<String, Object> map) {
		this.setLid((int) map.get("lid"));
		this.setOrderNum((String) map.get("orderNum"));
		this.setUserId((int) map.get("userId"));
		this.setOrderDate((String) map.get("orderDate"));
		this.setMoney((double) map.get("money"));
		this.setOrderStatus((int) map.get("orderStatus"));
	}

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}



	public List<OrderItem> getoItem() {
		return oItem;
	}

	public void setoItem(List<OrderItem> oItem) {
		this.oItem = oItem;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



}
