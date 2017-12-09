 //加载问题列表
$(function() {
	var str = "<option value=''>- 选择一个问题 -</option>";
		  var data = {};
		  $.ajax({//jquery 利用ajax请求所有的 问题
			    url : "question",                                 //要提交的URL路径
				type : "post",                          //发送请求的方式
				data : data,                           //要发送到服务器的数据
				dataType : "json",                //指定传输的数据格式  可以为json，若为json就不需要解析
				success : function(result) {//请求成功后要执行的代码
				 var objArry  = result.qLists;//获取问题集合
				 var user_id =result.user_id;
				 for (var int = 0; int < objArry.length; int++) {
					// var question = objArry[]
					 if(user_id!=null &&user_id== objArry[int].question_id){
					    str+="<option selected='selected' value='"+objArry[int].question_id+"' >"+objArry[int].question_title+"</option>"
					 }else{
						 str+="<option  value='"+objArry[int].question_id+"' >"+objArry[int].question_title+"</option>"
					 }
				 }
				  $("#question").html(str);
				}
			});
});