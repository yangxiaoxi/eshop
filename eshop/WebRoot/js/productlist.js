//加载所有的type
$(function(){
	var url = "typeServlet";
	var data = {
			method :"all"
	};
	$.post(url,data,function(response){
		var typeArry = $.parseJSON(response);
		var str = "";
		for (var int = 0; int < typeArry.typeLsit.length;int++) {
			var type = typeArry.typeLsit[int];
			str+="<tr><td class='listtd'>" +
					"<img src='images/miniicon.gif' width='9' height='6' />" +
					"&nbsp;&nbsp;&nbsp;&nbsp;<a href='product?type_id="+type.type_id +"'>"+
					" "+type.type_name+"</a></td></tr>"
		}
		$("#alltype").append(str);
	});
});