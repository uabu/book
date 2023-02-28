package bean;

import java.util.Date;
import java.util.Map;

public class Digital {

	private int digitalId; // 商品编号
	private String digitalName; //  商品名称
	private double price; // 价格
	private String description; // 描述信息
	private int catalogId; //商品分类id
	private int imgId; // 图片id
	private int lbt; // 图片id
	private Date addTime;//上架时间
	private Catalog catalog = new Catalog();
	private UpLoadImg upLoadImg = new UpLoadImg();

	public Digital() {
	}


	public Digital(Map<String, Object> map) {
		this.digitalId = (int) map.get("digitalId");
		this.digitalName = (String) map.get("digitalName");
		this.price = (double) map.get("price");
		this.description = (String) map.get("description");
		this.addTime=(Date) map.get("addTime");
		this.catalog = new Catalog(map);
		this.upLoadImg = new UpLoadImg(map);
		this.lbt =  (int) map.get("lbt");
	}


	public int getLbt() {
		return lbt;
	}

	public void setLbt(int lbt) {
		this.lbt = lbt;
	}

	public int getDigitalId() {
		return digitalId;
	}

	public void setDigitalId(int digitalId) {
		this.digitalId = digitalId;
	}

	public String getDigitalName() {
		return digitalName;
	}

	public void setDigitalName(String digitalName) {
		this.digitalName = digitalName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public UpLoadImg getUpLoadImg() {
		return upLoadImg;
	}

	public void setUpLoadImg(UpLoadImg upLoadImg) {
		this.upLoadImg = upLoadImg;
	}

	@Override
	public String toString() {
		return "Digital [digitalId=" + digitalId + ", digitalName=" + digitalName + ", price=" + price + ", description=" + description
				+ ", catalogId=" + catalogId + ", imgId=" + imgId + ", addTime=" + addTime + ", catalog=" + catalog
				+ ", upLoadImg=" + upLoadImg + "]";
	}







}
