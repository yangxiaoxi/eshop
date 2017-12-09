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
		if("checkLongin".equals(method)){//验证登陆
			checkLogin(request,response);
		}
		if("exit".equals(method)){//退出登陆
			exit(request,response);
		}
		if("ckEmain".equals(method)){//验证用户邮箱是否正确
			  ckEmail(request,response);
		}
		if("ckName".equals(method)){
			ckUserName(request,response);
		}
		if("add".equals(method)){
			 String verifycode =  (String)request.getSession().getAttribute(ParamUtil.VERIFY_CODE);
			 String inputVerofycode = request.getParameter("verifycode");
			 if(verifycode.equalsIgnoreCase(inputVerofycode)){
				 addUser(request,response);//如果验证码正确则添加新的用户
			 }else{
				 //如果验证码错误，则跳回上一个界面
				 response.getWriter()//register.jsp'
					.println(
							"<script type='text/javascript'>alert('验证码错误');"
							+ "window.location.href('register.jsp');</script>");
				 
			 }
		}
		 
	}
	
	
	/**
	 * 添加新的注册用户
	 * @param request
	 * @param response
	 */
	private void addUser(HttpServletRequest request,
			HttpServletResponse response) {
		
		String user_email = request.getParameter("email");
		String user_name = request.getParameter("userName");
		String user_pwd = request.getParameter("Firstpassword");//用户密码
		String user_sex = request.getParameter("radiobutton");//用户性别
		String user_phone = request.getParameter("text2");//用户联系方式
		String user_address = request.getParameter("text");//用户地址
		String question_id = request.getParameter("question");//question 用户选的问题id
		String question_answer = request.getParameter("answer");//用户密保问题答案
		
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
			 //注册成功，发送邮件
		  String receiveEmail = "m13627327880@163.com";//接收邮件的邮箱
		  String receiveNick = user.getUser_name();//接收邮件的昵称
		  String subject = "请完成EShop用户注册";//接收邮件的主题；
		  String host = request.getServerName();//主机名
		  int port = request.getServerPort();//获取端口号
		  String proName = request.getContextPath();//获取项目名
		  String contents ="";      //接收邮件的内容
		  contents+="亲爱的yunhejava@126.com</A>，您好！";
		  contents+="<br>已经收到了您的注册信息。请点击以下确认链接，立即激活EShop账号：<br>";
		  contents+="<a href='http://"+host+":"+port+proName+"/verifyEmailServlet?userId="+user_id+"'><h3>完成注册，立即体验娱乐之旅</h3></a>";
		  contents+="如果不能点击该链接地址，请复制并粘贴到浏览器的地址输入框<br>http://"+host+":"+port+proName+"/verifyEmailServlet?userId="+user_id;
           
		  MailUtilByYock.sendMail(user_email, receiveNick, subject, contents);
		} else {//注册失败
			try {
				response.getWriter()
						.println(
								"<script type='text/javascript'>alert('注册失败');history.go(-1);</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**验证用户名是否重复
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
					 jsonObjet.put("status",-200);//表示用户名已存在，不能使用
				 }
			}
			try {
				response.getWriter().print(jsonObjet.toString());//将创建的json字符串写入响应流
			} catch (IOException e) {
				e.printStackTrace();
			}
	}


	/**
	 * 验证用户密码是否重复
	 * @param request
	 * @param response
	 */
	private void ckEmail(HttpServletRequest request,
			HttpServletResponse response) {
		String email = request.getParameter("email");//获取请求参数中的email
		
		List<String> allUserEmail =  userBiz.queryAllEmail();
		 JSONObject jsonObjet = new JSONObject();
		for (String string : allUserEmail) {
			if(email.equals(string)){
				 jsonObjet.put("status",-200);//表示邮箱已存在，不能使用
			 }
		}
		 try {
				response.getWriter().print(jsonObjet.toString());//将创建的json字符串写入响应流
			} catch (IOException e) {
				e.printStackTrace();
			}
	}


	private void exit(HttpServletRequest request, HttpServletResponse response) {
		 //1,清除session
		request.getSession().invalidate();
		//2,清除cookie
		Cookie cookie = new Cookie(ParamUtil.USER_ID, null);
		cookie.setMaxAge(0);//这是cookie立马过期
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
		String[] checkbox = request.getParameterValues("checkbox");//获取复选框是否被选中
	     Object verifycode_count = request.getSession().getAttribute(ParamUtil.LONING_COUNT);//账号密码错误次数
	     JSONObject jo = new JSONObject();
	    if(verifycode_count!=null&&(int)verifycode_count>=3){
				String verifyCode = (String) request.getSession().getAttribute(ParamUtil.VERIFY_CODE);//获取生成的验证码
				String inputVerifycode = request.getParameter("verifycode");//获取输入的验证码
				if(verifyCode!=null &&inputVerifycode!=null ){
				if(!verifyCode.equalsIgnoreCase(inputVerifycode)){ 
							//如果验证码不正确
					try {//;window.location.href='login.jsp'
								//response.sendRedirect("login.jsp");
								response.getWriter().
								 print("<script type='text/javascript'>alert('验证码错误错误');window.location.href='login.jsp';</script>");
					} catch (IOException e) {
								e.printStackTrace();
					}
				} 
		}
		}
		User user = userBiz.Login(use_name, password);
			 if(user!=null){
				 if(checkbox!=null){
						//如果复选框被选中，则将UserId存入cookie
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
					 print("<script type='text/javascript'>alert('登陆成功');window.location.href='"+url+"';</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				 Logger.getLogger(UserServlet.class).debug("账号密码错误");
				 //当账号和密码错三次，就显示出验证码，
				 Object obj = request.getSession().getAttribute(ParamUtil.LONING_COUNT);
				 int login_count=0;
				 if(obj==null){//如果是第一次输入错误
					 login_count=1;
				 }else{//如果不是第一次输入错误，则取出错误的次数并+1；
					 login_count=(int)obj;
				 }
				 request.getSession().setAttribute("login_count",login_count+1);
				 Logger.getLogger(UserServlet.class).debug("login_count is"+ login_count);
				 try {
					//response.sendRedirect("login.jsp");
					response.getWriter().
					 print("<script type='text/javascript'>alert('账号或密码错误');window.location.href='login.jsp'</script>");
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			 }
			
		} 

}
