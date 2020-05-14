package com.ecard.dao;

import com.ecard.entity.DisInfo;
import com.ecard.pojo.queryResult.DisInfoQr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DisInfoMapper {
    int deleteByPrimaryKey(Long disNo);

    int insert(DisInfo record);

    int insertSelective(DisInfo record);

    DisInfo selectByPrimaryKey(Long disNo);

    int updateByPrimaryKeySelective(DisInfo record);

    int updateByPrimaryKey(DisInfo record);

	DisInfo selectByCloudPassNo(Long cloudPassNo);
	
	List<DisInfoQr> selectList(@Param("disno") Long disno,
                               @Param("IdNo") String IdNo,
                               @Param("name") String name,
                               @Param("disLevel") String disLevel,
                               @Param("disLeader") Long disLeader,
                               @Param("sendDate") String sendDate,
                               @Param("endDate") String endDate,
                               @Param("disableFlag") Integer disableFla);
	
	DisInfoQr selectDisInfoQrByCloudPassNo(Long cloudPassNo);

	DisInfoQr selectByNo(Long disno);

	List<DisInfo> listByDisLevel(String disLevel);

    DisInfo select1DisNo(Long disNo);

    List<DisInfoQr> getDisList(Long valueOf);

    DisInfoQr selectByPhone(String disPhone);

    DisInfoQr selectNameByDrNo(Long drNo);
}