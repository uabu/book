package bean;

import util.MathUtils;

public class CartItem {
	private Digital digital;
	private int quantity;//数量
	private double subtotal;//小计
	
	public CartItem() {}
	
	
	
	public CartItem(Digital digital, int quantity) {
		super();
		this.setDigital(digital);
		this.setQuantity(quantity);
	}

	public Digital getDigital() {
		return digital;
	}
	public void setDigital(Digital digital) {
		this.digital = digital;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.subtotal = MathUtils.getTwoDouble(quantity*digital.getPrice());
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	

	@Override
	public String toString() {
		return "CartItem [digital=" + digital + ", quantity=" + quantity + ", subtotal=" + subtotal + "]";
	}
	
	
	
}
