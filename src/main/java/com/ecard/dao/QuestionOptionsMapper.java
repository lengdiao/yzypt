package com.ecard.dao;

import com.ecard.entity.QuestionOptions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionOptionsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QuestionOptions record);

    int insertSelective(QuestionOptions record);

    QuestionOptions selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QuestionOptions record);

    int updateByPrimaryKey(QuestionOptions record);

    List<QuestionOptions> selectByTemplateNo(Long templateNo);

    void deleteByGoodNo(Long goodNo);

    List<QuestionOptions> selectByTemplateNoQuestionNo(
            @Param("templateNo") Long templateNo,
            @Param("questionNo") Long questionNo);
}