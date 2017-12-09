package cn.yunhe.biz.impl;

import java.util.List;

import cn.yunhe.biz.QuestionBiz;
import cn.yunhe.dao.QuestionDao;
import cn.yunhe.dao.impl.QuestionDaoImpl;
import cn.yunhe.entity.Question;

public class QuestionBizImpl implements QuestionBiz {
  private QuestionDao questionDao = new QuestionDaoImpl();
	 
	@Override
	public List<Question> querytAll() {
		return questionDao.querytAll();
	}

}
