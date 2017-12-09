package cn.yunhe.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;

import cn.yunhe.biz.CartBiz;
import cn.yunhe.biz.impl.CartBizImpl;
import cn.yunhe.entity.Book;
import cn.yunhe.entity.Cart;
import cn.yunhe.entity.User;

public class CartMap extends HashMap<Integer, Integer> implements Serializable ,HttpSessionActivationListener {

	

	@Override
	public void sessionDidActivate(HttpSessionEvent arg0) {
         		
	}
 
	@Override
	public void sessionWillPassivate(HttpSessionEvent arg0) {
	   CartBiz cartBiz = new CartBizImpl();
		 User user =(User) arg0.getSession().getAttribute(ParamUtil.LOGIN_USER);//检测用户是否登陆
		
		 //1,当session钝化的时候 且用户已登录  ，将 session中购物车记录存入数据库
		 if(user!=null){
			 //1,1如果订单提交成功，则清除session中的购物车，在存入数据库
			  if(arg0.getSession().getAttribute("orderSuccess")!=null){
				  arg0.getSession().removeAttribute(ParamUtil.CART_MAP);
			  }
			   System.out.println("钝化");
			   CartMap cartMap = (CartMap)arg0.getSession().getAttribute(ParamUtil.CART_MAP);
			   cartBiz.addCart(user.getUser_id(),cartMap);//添加订单到数据库
			}
		}
 
}
