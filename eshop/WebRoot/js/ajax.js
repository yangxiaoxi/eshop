/** 
 * 传统方式  ajax请求
	 * 得到XMLHttpRequest对象 
	 */
	function getajaxHttp() {
		var xmlHttp;
		try {
			// Firefox, Opera 8.0+, Safari  
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			// Internet Explorer  
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					alert("您的浏览器不支持AJAX！");
					return false;
				}
			}
		}
		return xmlHttp;
	}
	
	/** 
	 * 发送ajax请求
	 * url--url 
	 * methodtype(post/get) 
	 * con (true(异步)|false(同步)) 
	 * functionName(回调方法名，不需要引号,这里只有成功的时候才调用) 
	 * (注意：这方法有二个参数，一个就是xmlhttp,一个就是要处理的对象) 
	 * obj需要到回调方法中处理的对象 
	 */
	function ajaxrequest(url, methodtype, con, functionName, obj) {
		//1>实例化一个XMLHttpRequest对象
		var xmlhttp = getajaxHttp();
		//2>设置一个回调函数
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				if (xmlhttp.status == 200) {
					//xmlhttp.responseText就是响应的内容
					functionName(xmlhttp.responseText);
				}
			}

		};
		//打开连接
		xmlhttp.open(methodtype, url, con);
		//开始请求
		xmlhttp.send();
	}