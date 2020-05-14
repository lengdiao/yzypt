package com.ecard.dao;

import com.ecard.entity.Activity;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface ActivityMapper {
    int deleteByPrimaryKey(Long activityNo);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Long activityNo);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    Activity selectByGoodsNoFlag(
            @Param("goodsNo") Long goodsNo,
            @Param("disableFlag") Integer disableFlag);
}