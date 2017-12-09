package cn.yunhe.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cn.yunhe.biz.PvBiz;
import cn.yunhe.biz.impl.PvBizImpl;

public class PageViewListener implements HttpSessionListener {

	
	private PvBiz pvBiz = new PvBizImpl();
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		 //��session������ʱ����һ��ҳ�������
		String session_id = arg0.getSession().getId();
		pvBiz.addPv(session_id);
		System.out.println("һ��ҳ������");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
	}

}
