package com.ecard.dao;

import com.ecard.entity.UserQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserQuestionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserQuestion record);

    int insertSelective(UserQuestion record);

    UserQuestion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserQuestion record);

    int updateByPrimaryKey(UserQuestion record);

    List<UserQuestion> selectByPtNoAndTemId(@Param("ptNo") Long ptNo, @Param("templateNo") Long templateNo);
}