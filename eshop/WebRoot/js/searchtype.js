//加载所有的type
$(function(){
	var url = "typeServlet";
	var data = {
			method :"all"
	};
	$.post(url,data,function(response){
		var typeArry = $.parseJSON(response);
		var str = " <option value='-1'>--选择类别</option>";
		for (var int = 0; int < typeArry.typeLsit.length;int++) {
			var type = typeArry.typeLsit[int];
		 	str+= "<option value='"+type.type_id+"'>"+type.type_name +"</option>";
		}
		  $("#select").html(str);
	});
});
