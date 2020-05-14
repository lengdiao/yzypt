package com.ecard.dao;

import com.ecard.entity.Goods;
import org.apache.ibatis.annotations.Param;

public interface GoodsMapper {
    int deleteByPrimaryKey(Long goodsNo);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long goodsNo);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    Goods selectByNameAndSpecification(
            @Param("goodsName") String goodsName,
            @Param("specification") String specification);
}