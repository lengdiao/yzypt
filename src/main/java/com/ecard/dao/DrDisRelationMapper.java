package com.ecard.dao;

import com.ecard.entity.DrDisRelation;
import com.ecard.pojo.queryResult.DrInfoQr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DrDisRelationMapper {
	int deleteByPrimaryKey(Long id);

	int insert(DrDisRelation record);

	int insertSelective(DrDisRelation record);

	DrDisRelation selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(DrDisRelation record);

	int updateByPrimaryKey(DrDisRelation record);

	List<DrInfoQr> selectByDisNo(@Param("disNo") Long disNo, @Param("name") String name, @Param("phone") String phone);

	Long selectByDrNo(long parseLong);

	DrDisRelation selectAllByDrNo(Long drNo);
}