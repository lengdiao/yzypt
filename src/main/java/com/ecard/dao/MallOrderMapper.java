package com.ecard.dao;

import com.ecard.entity.MallOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallOrderMapper {
    int deleteByPrimaryKey(Long mallNo);

    int insert(MallOrder record);

    int insertSelective(MallOrder record);

    MallOrder selectByPrimaryKey(Long mallNo);

    int updateByPrimaryKeySelective(MallOrder record);

    int updateByPrimaryKey(MallOrder record);

    List<MallOrder> selectByDrNo(Long drNo);

    List<MallOrder> selectByMnOsPnDn(
            @Param("mallNo") Integer mallNo,
            @Param("orderStatus") Integer orderStatus,
            @Param("ptNo") Integer ptNo,
            @Param("drNo") Integer drNo);

    Long selectId();

    MallOrder selectByMedOrderNo(Long orderNo);

    List<MallOrder> selectByPtNoAndPayStatus(Long ptNo);
}