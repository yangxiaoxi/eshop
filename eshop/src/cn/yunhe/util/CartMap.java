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
		 User user =(User) arg0.getSession().getAttribute(ParamUtil.LOGIN_USER);//����û��Ƿ��½
		
		 //1,��session�ۻ���ʱ�� ���û��ѵ�¼  ���� session�й��ﳵ��¼�������ݿ�
		 if(user!=null){
			 //1,1��������ύ�ɹ��������session�еĹ��ﳵ���ڴ������ݿ�
			  if(arg0.getSession().getAttribute("orderSuccess")!=null){
				  arg0.getSession().removeAttribute(ParamUtil.CART_MAP);
			  }
			   System.out.println("�ۻ�");
			   CartMap cartMap = (CartMap)arg0.getSession().getAttribute(ParamUtil.CART_MAP);
			   cartBiz.addCart(user.getUser_id(),cartMap);//��Ӷ��������ݿ�
			}
		}
 
}
