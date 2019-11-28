package com.ecard.dao;

import com.ecard.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {
    int deleteByPrimaryKey(Long messageNo);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long messageNo);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

	Message[] selectPtNewMessage(@Param("ptNo") Long ptNo, @Param("drNo") long drNo);

	List<Message> selectMsgByDrNo(@Param("drNo") long drNo, @Param("ptNo") long ptNo);

	Message[] selectDrNewMessage(@Param("ptNo") Long ptNo, @Param("drNo") long drNo);
}