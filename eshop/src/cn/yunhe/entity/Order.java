package cn.yunhe.entity;

import java.util.List;

public class Order {
	private String order_id;
	private int user_id;
	private String address;
	private String person;//ÊÕ»õÈË
	private String phone;
	private String  order_contents;
	private double order_price ;
	private String  order_date;
	private int status_pay;
	private int status_send;
	private int status_receive;
	private int status;
	private List<Book> bookList;
	
	
	 
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public List<Book> getBookList() {
		return bookList;
	}
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOrder_contents() {
		return order_contents;
	}
	public void setOrder_contents(String order_contents) {
		this.order_contents = order_contents;
	}
	public double getOrder_price() {
		return order_price;
	}
	public void setOrder_price(double order_price) {
		this.order_price = order_price;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public int getStatus_pay() {
		return status_pay;
	}
	public void setStatus_pay(int status_pay) {
		this.status_pay = status_pay;
	}
	public int getStatus_send() {
		return status_send;
	}
	public void setStatus_send(int status_send) {
		this.status_send = status_send;
	}
	public int getStatus_receive() {
		return status_receive;
	}
	public void setStatus_receive(int status_receive) {
		this.status_receive = status_receive;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	

}
