package com.ecard.dao;

import com.ecard.entity.DrugSetPicture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DrugSetPictureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DrugSetPicture record);

    int insertSelective(DrugSetPicture record);

    DrugSetPicture selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DrugSetPicture record);

    int updateByPrimaryKey(DrugSetPicture record);

    List<DrugSetPicture> selectByGoodsNo(Long goodsNo);

    List<DrugSetPicture> selectByGoodsNoAndNumber(
            @Param("goodsNo") Long goodsNo,
            @Param("number") int number);

    List<DrugSetPicture> selectByGoodsNo1(Long goodsNo);
}