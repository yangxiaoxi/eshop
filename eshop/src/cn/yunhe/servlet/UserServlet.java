package cn.yunhe.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import sun.awt.windows.ThemeReader;
import net.sf.json.JSONObject;
import cn.yunhe.biz.UserBiz;
import cn.yunhe.biz.impl.UserBizImpl;
import cn.yunhe.entity.User;
import cn.yunhe.util.MailUtilByYock;
import cn.yunhe.util.ParamUtil;

 
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private UserBiz userBiz = new UserBizImpl();
    public UserServlet() {
        super();
    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String method = request.getParameter("method");
		if("checkLongin".equals(method)){//��֤��½
			checkLogin(request,response);
		}
		if("exit".equals(method)){//�˳���½
			exit(request,response);
		}
		if("ckEmain".equals(method)){//��֤�û������Ƿ���ȷ
			  ckEmail(request,response);
		}
		if("ckName".equals(method)){
			ckUserName(request,response);
		}
		if("add".equals(method)){
			 String verifycode =  (String)request.getSession().getAttribute(ParamUtil.VERIFY_CODE);
			 String inputVerofycode = request.getParameter("verifycode");
			 if(verifycode.equalsIgnoreCase(inputVerofycode)){
				 addUser(request,response);//�����֤����ȷ������µ��û�
			 }else{
				 //�����֤�������������һ������
				 response.getWriter()//register.jsp'
					.println(
							"<script type='text/javascript'>alert('��֤�����');"
							+ "window.location.href('register.jsp');</script>");
				 
			 }
		}
		 
	}
	
	
	/**
	 * ����µ�ע���û�
	 * @param request
	 * @param response
	 */
	private void addUser(HttpServletRequest request,
			HttpServletResponse response) {
		
		String user_email = request.getParameter("email");
		String user_name = request.getParameter("userName");
		String user_pwd = request.getParameter("Firstpassword");//�û�����
		String user_sex = request.getParameter("radiobutton");//�û��Ա�
		String user_phone = request.getParameter("text2");//�û���ϵ��ʽ
		String user_address = request.getParameter("text");//�û���ַ
		String question_id = request.getParameter("question");//question �û�ѡ������id
		String question_answer = request.getParameter("answer");//�û��ܱ������
		
		User user = new User();
		user.setUser_email(user_email);
		user.setUser_name(user_name);
		user.setUser_pws(user_pwd);
		user.setUser_sex(user_sex);
		user.setUser_phone(user_phone);
		user.setUser_address(user_address);
		user.setQuestion_id(Integer.parseInt(question_id));
		user.setQuestion_answer(question_answer);
	    int  user_id = 	userBiz.addUser(user);
	    
	  if (user_id!=-1) {
		  try {
				response.sendRedirect("registersuccess.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			} 
			 //ע��ɹ��������ʼ�
		  String receiveEmail = "m13627327880@163.com";//�����ʼ�������
		  String receiveNick = user.getUser_name();//�����ʼ����ǳ�
		  String subject = "�����EShop�û�ע��";//�����ʼ������⣻
		  String host = request.getServerName();//������
		  int port = request.getServerPort();//��ȡ�˿ں�
		  String proName = request.getContextPath();//��ȡ��Ŀ��
		  String contents ="";      //�����ʼ�������
		  contents+="�װ���yunhejava@126.com</A>�����ã�";
		  contents+="<br>�Ѿ��յ�������ע����Ϣ����������ȷ�����ӣ���������EShop�˺ţ�<br>";
		  contents+="<a href='http://"+host+":"+port+proName+"/verifyEmailServlet?userId="+user_id+"'><h3>���ע�ᣬ������������֮��</h3></a>";
		  contents+="������ܵ�������ӵ�ַ���븴�Ʋ�ճ����������ĵ�ַ�����<br>http://"+host+":"+port+proName+"/verifyEmailServlet?userId="+user_id;
           
		  MailUtilByYock.sendMail(user_email, receiveNick, subject, contents);
		} else {//ע��ʧ��
			try {
				response.getWriter()
						.println(
								"<script type='text/javascript'>alert('ע��ʧ��');history.go(-1);</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**��֤�û����Ƿ��ظ�
	 * @param request
	 * @param response
	 */
	private void ckUserName(HttpServletRequest request,
			HttpServletResponse response) {
		String userName = request.getParameter("user_name");
		List<String> allUserName = userBiz.queryAllUser_name();
		 JSONObject jsonObjet = new JSONObject();
			for (String string : allUserName) {
				if(userName.equals(string)){
					 jsonObjet.put("status",-200);//��ʾ�û����Ѵ��ڣ�����ʹ��
				 }
			}
			try {
				response.getWriter().print(jsonObjet.toString());//��������json�ַ���д����Ӧ��
			} catch (IOException e) {
				e.printStackTrace();
			}
	}


	/**
	 * ��֤�û������Ƿ��ظ�
	 * @param request
	 * @param response
	 */
	private void ckEmail(HttpServletRequest request,
			HttpServletResponse response) {
		String email = request.getParameter("email");//��ȡ��������е�email
		
		List<String> allUserEmail =  userBiz.queryAllEmail();
		 JSONObject jsonObjet = new JSONObject();
		for (String string : allUserEmail) {
			if(email.equals(string)){
				 jsonObjet.put("status",-200);//��ʾ�����Ѵ��ڣ�����ʹ��
			 }
		}
		 try {
				response.getWriter().print(jsonObjet.toString());//��������json�ַ���д����Ӧ��
			} catch (IOException e) {
				e.printStackTrace();
			}
	}


	private void exit(HttpServletRequest request, HttpServletResponse response) {
		 //1,���session
		request.getSession().invalidate();
		//2,���cookie
		Cookie cookie = new Cookie(ParamUtil.USER_ID, null);
		cookie.setMaxAge(0);//����cookie�������
		response.addCookie(cookie);
		try {
			response.sendRedirect("login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}


	private void checkLogin(HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("userBiz", userBiz);
		
		String use_name = request.getParameter("userName");
		String password = request.getParameter("password");
		String[] checkbox = request.getParameterValues("checkbox");//��ȡ��ѡ���Ƿ�ѡ��
	     Object verifycode_count = request.getSession().getAttribute(ParamUtil.LONING_COUNT);//�˺�����������
	     JSONObject jo = new JSONObject();
	    if(verifycode_count!=null&&(int)verifycode_count>=3){
				String verifyCode = (String) request.getSession().getAttribute(ParamUtil.VERIFY_CODE);//��ȡ���ɵ���֤��
				String inputVerifycode = request.getParameter("verifycode");//��ȡ�������֤��
				if(verifyCode!=null &&inputVerifycode!=null ){
				if(!verifyCode.equalsIgnoreCase(inputVerifycode)){ 
							//�����֤�벻��ȷ
					try {//;window.location.href='login.jsp'
								//response.sendRedirect("login.jsp");
								response.getWriter().
								 print("<script type='text/javascript'>alert('��֤��������');window.location.href='login.jsp';</script>");
					} catch (IOException e) {
								e.printStackTrace();
					}
				} 
		}
		}
		User user = userBiz.Login(use_name, password);
			 if(user!=null){
				 if(checkbox!=null){
						//�����ѡ��ѡ�У���UserId����cookie
						Cookie cookie = new Cookie(ParamUtil.USER_ID, user.getUser_id()+"");
						cookie.setMaxAge(7*24*60*60);
						response.addCookie(cookie);
						//request.setAttribute("cookie", cookie);
				}
				 request.getSession().setAttribute(ParamUtil.LOGIN_USER, user);
				 String url =(String) request.getSession().getAttribute(ParamUtil.URL);
				 if(url==null){
					 url="index.jsp";
				 }
				 try {
					response.getWriter().
					 print("<script type='text/javascript'>alert('��½�ɹ�');window.location.href='"+url+"';</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				 Logger.getLogger(UserServlet.class).debug("�˺��������");
				 //���˺ź���������Σ�����ʾ����֤�룬
				 Object obj = request.getSession().getAttribute(ParamUtil.LONING_COUNT);
				 int login_count=0;
				 if(obj==null){//����ǵ�һ���������
					 login_count=1;
				 }else{//������ǵ�һ�����������ȡ������Ĵ�����+1��
					 login_count=(int)obj;
				 }
				 request.getSession().setAttribute("login_count",login_count+1);
				 Logger.getLogger(UserServlet.class).debug("login_count is"+ login_count);
				 try {
					//response.sendRedirect("login.jsp");
					response.getWriter().
					 print("<script type='text/javascript'>alert('�˺Ż��������');window.location.href='login.jsp'</script>");
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			 }
			
		} 

}
