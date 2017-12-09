package cn.yunhe.entity;

public class GoodsInfo {
	
	private String bookSrc;
	private String bookName;
	private double bookPrice;
	public String getBookSrc() {
		return bookSrc;
	}
	public void setBookSrc(String bookSrc) {
		this.bookSrc = bookSrc;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	
	public GoodsInfo(){}
	public GoodsInfo(String bookSrc, String bookName, double bookPrice) {
		super();
		this.bookSrc = bookSrc;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
	}
	
	
	
	

}
