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
		//�ױ���Ӧ�ı����ʽ��gbk
		response.setCharacterEncoding("gb2312");
		response.setContentType("text/html;charset=gb2312");
		
		String p1_MerId = PayConfig.APP_ID;
		String keyValue = PayConfig.APP_SECRET;
		
		/**
		 // �̻����
		sValue.append(p1_MerId);
		// ҵ������
		sValue.append(r0_Cmd);
		// ֧�����
		sValue.append(r1_Code);
		// �ױ�֧��������ˮ��
		sValue.append(r2_TrxId);
		// ֧�����
		sValue.append(r3_Amt);
		// ���ױ���
		sValue.append(r4_Cur);
		// ��Ʒ����
		sValue.append(r5_Pid);
		// �̻�������
		sValue.append(r6_Order);
		// �ױ�֧����ԱID
		sValue.append(r7_Uid);
		// �̻���չ��Ϣ
		sValue.append(r8_MP);
		// ���׽����������
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
			response.getWriter().write("���ܺ����Ϣ�ѱ��޸ģ�����");
			return;
		}
		
		
		if("1".equals(r1_Code)){  //����֧���ɹ�
			
			/*
			 * ֧���ɹ��ص������Σ�����֪ͨ������֧����������е�p8_Url�ϣ�
			 * ������ض���;��������Ե�ͨѶ
			 */
			if("1".equals(r9_BType)){
				System.out.println("�ڶ��ζ����ţ�"+r6_Order);
				
				response.getWriter().write("֧���ɹ�����");//֧���ɹ��޸Ķ���״̬Ϊ��֧��
			  boolean isYes  =  orderBiz.updateStatus_Pay(r6_Order);
				 if(isYes){
					 response.sendRedirect("order?method=orderList");
				 }
				return;
			}
			if("2".equals(r9_BType)){
				//�����ҪӦ�����������д��,��success��ͷ,��Сд������
				System.out.println("��һ�ζ����ţ�"+r6_Order);
				response.getWriter().write("success");
				 boolean isYes  =  orderBiz.updateStatus_Pay(r6_Order);
				 if(isYes){
					 response.sendRedirect("order?method=orderList");
				 }
			}
		}
	}
}
