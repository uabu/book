package bean;

import java.util.Map;

public class OrderItem {

    private int itemId;           //订单项编号
    private int digitalId;          //商品编号
    private int orderId;       //订单编号
    private int quantity;      //数量
    
    private Digital digital;
    public OrderItem() {
    }

   
    public OrderItem(Map<String, Object> map) {
		this.setOrderId((int) map.get("orderId"));
		this.setDigitalId((int) map.get("digitalId"));
		this.setItemId((int) map.get("itemId"));
		this.setQuantity((int) map.get("quantity"));
	}


	public int getItemId() {
		return itemId;
	}


	public void setItemId(int itemId) {
		this.itemId = itemId;
	}


	public int getDigitalId() {
		return digitalId;
	}


	public void setDigitalId(int digitalId) {
		this.digitalId = digitalId;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Digital getDigital() {
		return digital;
	}


	public void setDigital(Digital digital) {
		this.digital = digital;
	}


	


    
}
