package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.Question;

public interface QuestionBiz {
	
	 /**
	    * 查询所有的问题
	    * @return
	   */
	List<Question> querytAll();

}
