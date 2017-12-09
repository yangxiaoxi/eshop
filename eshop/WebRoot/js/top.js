
//顶部书本类型加载
$(function() {
	//ajax请求头部类型
	var data = {
		method:"top"
	}
	$.ajax({
		url:"typeServlet",
	    data:data,
	    type:"post",
	    dataType:"json",//json类型不需要解析
	    success:function(response){
	    	var typeArry = response.typeLsit;
	    	var str = "";
	    	for (var int = 0; int < typeArry.length; int++) {
				var type = typeArry[int];
				str+="<a href='product?type_id="+type.type_id+"'>"+type.type_name+"</a> &nbsp; &nbsp;"
			}
	    	$("#divmenu").html(str);
	    }
	});
});