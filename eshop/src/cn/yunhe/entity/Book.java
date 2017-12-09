package cn.yunhe.entity;

import java.io.Serializable;


public class Book implements Serializable{
	private int book_id;
	private String book_name;
	private String book_logo;
	private double book_price;
	private double book_price_old;
	private String book_author;
	private String book_description;
	private String  book_press;
	private int type_id;
	private int book_count;
	
	
	public int getBook_count() {
		return book_count;
	}
	public void setBook_count(int book_count) {
		this.book_count = book_count;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getBook_logo() {
		return book_logo;
	}
	public void setBook_logo(String book_logo) {
		this.book_logo = book_logo;
	}
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
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}
	public String getBook_description() {
		return book_description;
	}
	public void setBook_description(String book_description) {
		this.book_description = book_description;
	}
	public String getBook_press() {
		return book_press;
	}
	public void setBook_press(String book_press) {
		this.book_press = book_press;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	
	

}
