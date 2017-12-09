package cn.yunhe.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.yunhe.biz.OrderBiz;
import cn.yunhe.biz.impl.OrderBizImpl;
import cn.yunhe.entity.Order;
import cn.yunhe.entity.User;
import cn.yunhe.util.ParamUtil;
import cn.yunhe.util.pay.PayConfig;
import cn.yunhe.util.pay.PaymentUtil;


@WebServlet("/payResponse")
public class PayResponseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final OrderBiz orderBiz = new OrderBizImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//易宝响应的编码格式是gbk
		response.setCharacterEncoding("gb2312");
		response.setContentType("text/html;charset=gb2312");
		
		String p1_MerId = PayConfig.APP_ID;
		String keyValue = PayConfig.APP_SECRET;
		
		/**
		 // 商户编号
		sValue.append(p1_MerId);
		// 业务类型
		sValue.append(r0_Cmd);
		// 支付结果
		sValue.append(r1_Code);
		// 易宝支付交易流水号
		sValue.append(r2_TrxId);
		// 支付金额
		sValue.append(r3_Amt);
		// 交易币种
		sValue.append(r4_Cur);
		// 商品名称
		sValue.append(r5_Pid);
		// 商户订单号
		sValue.append(r6_Order);
		// 易宝支付会员ID
		sValue.append(r7_Uid);
		// 商户扩展信息
		sValue.append(r8_MP);
		// 交易结果返回类型
		sValue.append(r9_BType);
		 * 
		 * 
		 */
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String  r8_MP= request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");
		String hmac = request.getParameter("hmac");
		
		
		
		boolean b = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);
		if(!b){
			response.getWriter().write("加密后的信息已被修改！！！");
			return;
		}
		
		
		if("1".equals(r1_Code)){  //处理支付成功
			
			/*
			 * 支付成功回调有两次，都会通知到在线支付请求参数中的p8_Url上：
			 * 浏览器重定向;服务器点对点通讯
			 */
			if("1".equals(r9_BType)){
				System.out.println("第二次订单号："+r6_Order);
				
				response.getWriter().write("支付成功！！");//支付成功修改定当状态为已支付
			  boolean isYes  =  orderBiz.updateStatus_Pay(r6_Order);
				 if(isYes){
					 response.sendRedirect("order?method=orderList");
				 }
				return;
			}
			if("2".equals(r9_BType)){
				//如果需要应答机制则必须回写流,以success开头,大小写不敏感
				System.out.println("第一次订单号："+r6_Order);
				response.getWriter().write("success");
				 boolean isYes  =  orderBiz.updateStatus_Pay(r6_Order);
				 if(isYes){
					 response.sendRedirect("order?method=orderList");
				 }
			}
		}
	}
}
