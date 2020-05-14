package com.ecard.dao;

import com.ecard.entity.QuestionTemplate;

import java.util.List;

public interface QuestionTemplateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QuestionTemplate record);

    int insertSelective(QuestionTemplate record);

    QuestionTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QuestionTemplate record);

    int updateByPrimaryKey(QuestionTemplate record);

    List<QuestionTemplate> selectByGoodsNo(Long drugSetNo);

    void deleteByGoodNo(Long goodNo);

    Long selectId();
}