package cn.softwolf.pojo;

public class Product {
	private String id;
	private String name;
	private String image;
	private double price;
	private long commentCount; // 评论量
	private String praiseRate; // 好评率

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(long commentCount) {
		this.commentCount = commentCount;
	}

	public String getPraiseRate() {
		return praiseRate;
	}

	public void setPraiseRate(String praiseRate) {
		this.praiseRate = praiseRate;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", image=" + image + ", price=" + price + ", commentCount="
				+ commentCount + ", praiseRate=" + praiseRate + "]";
	}

}
