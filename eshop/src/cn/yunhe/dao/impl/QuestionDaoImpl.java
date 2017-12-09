package cn.yunhe.dao.impl;

import java.util.List;

import cn.yunhe.dao.QuestionDao;
import cn.yunhe.entity.Book;
import cn.yunhe.entity.Question;
import cn.yunhe.util.ROMjdbcUtil;

public class QuestionDaoImpl implements QuestionDao {
 
	@Override
	public List<Question> querytAll() {
		String sql = "select * from tb_question";
	    List<Question> questionLists =  ROMjdbcUtil.query(sql,null,Question.class);
		return  questionLists;
	}

}
