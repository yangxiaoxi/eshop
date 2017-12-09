package cn.yunhe.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yunhe.util.pay.PayConfig;
import cn.yunhe.util.pay.PaymentUtil;


@WebServlet("/payRequest")
public class PayRequestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String p0_Cmd = "Buy";  	//代表接口类型（即时到帐支付接口、自动收款功能）
		String p1_MerId = PayConfig.APP_ID;	//合作者ID
		String keyValue = PayConfig.APP_SECRET;	//合作者的密钥
		String p4_Cur = "CNY";		//币种：人民币
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = PayConfig.REDIRECT_URL; //设置回调地址
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";  //设置是否需要回调（1代表要）
		
		String p2_Order = request.getParameter("orderNo");	//订单号
//		String p3_Amt = request.getParameter("price");	//订单金额
		String p3_Amt="0.01";
		System.out.println("订单号："+p2_Order);
		System.out.println("订单金额："+p3_Amt);
		String pd_FrpId = request.getParameter("pd_FrpId");	//所选银行编号
		
		//一串加密后的码（ 把所有参数拼接起来，再加密） 
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		
		
		request.setAttribute("p0_Cmd", p0_Cmd);
		request.setAttribute("p1_MerId", p1_MerId);
		request.setAttribute("p2_Order", p2_Order);
		request.setAttribute("p3_Amt", p3_Amt);
		request.setAttribute("p4_Cur", p4_Cur);
		request.setAttribute("p5_Pid", p5_Pid);
		request.setAttribute("p6_Pcat",p6_Pcat);
		request.setAttribute("p7_Pdesc", p7_Pdesc);
		request.setAttribute("p8_Url", p8_Url);
		request.setAttribute("p9_SAF", p9_SAF);
		request.setAttribute("pa_MP", pa_MP);
		request.setAttribute("pd_FrpId", pd_FrpId);
		request.setAttribute("pr_NeedResponse", pr_NeedResponse);
		request.setAttribute("hmac", hmac);
		request.getRequestDispatcher("payconfirm.jsp").forward(request, response);
		
	}
	
}
