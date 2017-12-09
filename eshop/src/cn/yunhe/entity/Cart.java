package cn.yunhe.entity;

public class Cart {
	private int cart_id; //购物车id
	private int book_id;//书本id
	private  int num;//商品数量
	private int user_id;//用户id
	private String book_name;
	private double book_price;
	private double book_price_old;
	
	
	public double getBook_price() {
		return book_price;
	}
	public void setBook_price(double book_price) {
		this.book_price = book_price;
	}
	public double getBook_price_old() {
		return book_price_old;
	}
	public void setBook_price_old(double book_price_old) {
		this.book_price_old = book_price_old;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	 
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
	

}
