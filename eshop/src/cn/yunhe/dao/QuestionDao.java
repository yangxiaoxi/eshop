package cn.yunhe.dao;

import java.util.List;

import cn.yunhe.entity.Question;

public interface QuestionDao {
   /**
    * 查询所有的问题
    * @return
   */
List<Question> querytAll();
}
