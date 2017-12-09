package cn.yunhe.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;

import com.sun.xml.internal.bind.v2.TODO;

import cn.yunhe.biz.CartBiz;
import cn.yunhe.biz.UserBiz;
import cn.yunhe.biz.impl.CartBizImpl;
import cn.yunhe.biz.impl.UserBizImpl;
import cn.yunhe.util.CartMap;
import cn.yunhe.util.ParamUtil;

public class User  implements HttpSessionBindingListener,HttpSessionActivationListener,Serializable {
	
	private static final long serialVersionUID = 1L;
	private int user_id;
	private String user_name;
	private String user_pwd;
	private String user_email;
	private String user_sex;
	private String user_phone;
	private String user_address;
	private int question_id;
	private String question_answer;
	private int is_actived;
	private int is_online;
	private UserBiz userBiz = new UserBizImpl();
	
	
	
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public int getIs_online() {
		return is_online;
	}
	public void setIs_online(int is_online) {
		this.is_online = is_online;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pws() {
		return user_pwd;
	}
	public void setUser_pws(String user_pws) {
		this.user_pwd = user_pws;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getQuestion_answer() {
		return question_answer;
	}
	public void setQuestion_answer(String question_answer) {
		this.question_answer = question_answer;
	}
	public int getIs_actived() {
		return is_actived;
	}
	public void setIs_actived(int is_actived) {
		this.is_actived = is_actived;
	}
	public User(){}
	public User(int user_id, String user_name, String user_pws,
			String user_email, String user_sex, String user_phone,
			String user_address, int question_id, String question_answer,
			int is_actived) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_pwd = user_pws;
		this.user_email = user_email;
		this.user_sex = user_sex;
		this.user_phone = user_phone;
		this.user_address = user_address;
		this.question_id = question_id;
		this.question_answer = question_answer;
		this.is_actived = is_actived;
	}
	
	
	//活化session  
	@Override
	public void sessionDidActivate(HttpSessionEvent arg0) {
		 userBiz.updateIs_online(1, this.user_id);
	}
	//钝化session
	@Override
	public void sessionWillPassivate(HttpSessionEvent arg0) {
		 userBiz.updateIs_online(0, this.user_id);
	}
	//user对象在session内
	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		//1，当用户登录时，用户的状态改为在线
		 userBiz.updateIs_online(1, this.user_id);
		 //,2,当用户对象登录时 ，将数据库的购物车信息同步到session购物车
		 CartBiz cartBiz = new CartBizImpl();
		 HttpSession session = arg0.getSession();
		 CartMap cartMap = (CartMap)session.getAttribute(ParamUtil.CART_MAP);//session中的购物车
		 CartMap dbCartMap = new CartMap();//数据库中的购物车对象
	     List<Cart> cartList = 	cartBiz.queryCartByUser_id(this.user_id);//根据用户id查出用户的购物车信息     
	     for (Cart cart : cartList) {
	    	 dbCartMap.put(cart.getBook_id(), cart.getNum());
		 }
	     Set<Integer>  allkeys =  dbCartMap.keySet();//数据库中的所有购物车对象的key（book_id）
	    //2.1 当session没有数据库对象
	     if(cartMap==null || cartMap.size()==0){
	    	    //2.1.1 当数据库中有对象,取出数据库的数据
	    	     if(dbCartMap!=null  ){ 
	    	    	 cartMap = dbCartMap;
	    	     } 
	     }else{//2.2 当session中有数据库对象
	         //2.2.1当数据库中有对象
	    	 if(dbCartMap!=null){
	    		 for (Integer book_id : allkeys) {
	    			 if(cartMap.containsKey(book_id)){//如果sesssion的购物车中包含  数据库购物车的book_id
	    				 cartMap.put(book_id, cartMap.get(book_id)+dbCartMap.get(book_id));
		    		 }else{//如果不包含
		    			 cartMap.put(book_id, dbCartMap.get(book_id));
		    		 }
				}
	    	 }
	     }
	     session.setAttribute(ParamUtil.CART_MAP, cartMap);
	}
	 
	//user对象不在session对象内，移除
	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		 userBiz.updateIs_online(0, this.user_id);
	}
	
	
	

}
