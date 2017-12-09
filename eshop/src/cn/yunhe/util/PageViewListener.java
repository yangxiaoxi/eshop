package cn.yunhe.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cn.yunhe.biz.PvBiz;
import cn.yunhe.biz.impl.PvBizImpl;

public class PageViewListener implements HttpSessionListener {

	
	private PvBiz pvBiz = new PvBizImpl();
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		 //在session创建的时候算一次页面访问量
		String session_id = arg0.getSession().getId();
		pvBiz.addPv(session_id);
		System.out.println("一次页面请求");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
	}

}
