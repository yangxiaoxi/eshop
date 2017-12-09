package cn.yunhe.util;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import cn.yunhe.biz.IpBiz;
import cn.yunhe.biz.impl.IpBizImpl;

public class Iplistener implements ServletRequestListener {

	
	private IpBiz ipBiz = new IpBizImpl();
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		 //1，当请求销毁的时候 ，将请求的用户id存在数据库
		
		HttpServletRequest request = (HttpServletRequest) arg0.getServletRequest();
		String[]  urlArray = request.getRequestURI().split("/");
		String url = urlArray[urlArray.length-1];
		if(!url.endsWith(".js")&&
		   !url.endsWith(".css")&&
		   !url.endsWith(".gif")&&
		   !url.endsWith(".bmp")&&
		   !url.endsWith(".jpg")&&
		   !url.endsWith(".png")){
		   ipBiz.addIp(request.getRemoteHost());
		}
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
	}

}
