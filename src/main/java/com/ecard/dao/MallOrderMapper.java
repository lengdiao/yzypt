package com.ecard.dao;

import com.ecard.entity.MallOrder;
import com.ecard.pojo.queryResult.MallOrderCount;
import com.ecard.pojo.queryResult.PcCountQr;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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
            @Param("mallNo") Long mallNo,
            @Param("orderStatus") Integer orderStatus,
            @Param("ptNo") Integer ptNo,
            @Param("drNo") Integer drNo);

    Long selectId();

    MallOrder selectByMedOrderNo(Long medOrderNo);

    List<MallOrder> selectByPtNoAndPayStatus(Long ptNo);

    List<MallOrder> selectByOrderStatus(
            @Param("orderStatus") int orderStatus,
            @Param("ptNo") Long ptNo);

    List<MallOrder> selectByMnOsPnDns(
            @Param("mallNo") Long mallNo,
            @Param("orderStatus") Integer orderStatus,
            @Param("ptNo") Integer ptNo,
            @Param("drNo") Integer drNo,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);

    List<MallOrder> selectByDrugStoreNo(
            @Param("drugStoreNo") Long drugStoreNo,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);

    List<MallOrder> selectByMoOsSdEd(
            @Param("drugStoreNo") Long drugStoreNo,
            @Param("mallNo") Long mallNo,
            @Param("orderStatus") Integer orderStatus,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("drNo") Long drNo,
            @Param("disNo1") Long disNo1,
            @Param("disNo2") Long disNo2);


    MallOrder selectByMedOAndMedR(
            @Param("medOrderNo") Long medOrderNo,
            @Param("medRecordNo") Long medRecordNo);

    List<MallOrder> selectByPtNo1(Long ptNo, Long drNo);

    List<MallOrder> selectByPtNoAndPayStatus1(
            @Param("ptNo") Long ptNo,
            @Param("payStatus") Integer payStatus);

    List<MallOrder> selectByDrNoMedRecordStatus(Long drNo);

    MallOrder selectByMedRecordNo(Long medRecordNo);

    List<MallOrder> selectByDrNoPtNamePhone(
            @Param("drNo") Long drNo,
            @Param("phone") String phone,
            @Param("name") String name);

    List<MallOrder> selectByNamePhoneDrNoDate(
            @Param("name") String name,
            @Param("drNo") Long drNo,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);

    List<PcCountQr> selectByNamePhoneDrNoDateType1(
            @Param("name") String name,
            @Param("drNo") Long drNo,
            @Param("startDate") String startDate,
            @Param("drugNo") Long drugNo);

    List<PcCountQr> selectByNamePhoneDrNoDateType2(
            @Param("name") String name,
            @Param("drNo") Long drNo,
            @Param("startDate") String startDate,
            @Param("drugNo") Long drugNo);

    List<MallOrder> findShippingStatusIs1();

    List<MallOrderCount> selectByDate(
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);

    MallOrder selectMaxOrderTime(
            @Param("ptNo") Long ptNo,
            @Param("drNo") Long drNo);


    MallOrder selectByPtNoAndUpdateTime(Long ptNo);

    List<MallOrder> selectByPtNoAndMallNo(
            @Param("ptNo") Long ptNo,
            @Param("mallNo") Long mallNo);

    List<PcCountQr> selectByTypeDrNameDrugNoDate(
            @Param("medOrderType") int medOrderType,
            @Param("drName") String drName,
            @Param("drugNo") Long drugNo,
            @Param("disNo1") Long disNo1,
            @Param("disNo2") Long disNo2,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("drPhone") String drPhone,
            @Param("drNo") Long drNo);

    MallOrder selectByMedOrderNo1(Long medOrderNo);

    List<MallOrder> selectByPtNoAndPayStatusPacketId(
            @Param("ptNo") Long ptNo,
            @Param("payStatus") int payStatus,
            @Param("packetId") Long packetId);
}