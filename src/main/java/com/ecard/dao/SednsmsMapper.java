package com.ecard.dao;

import com.ecard.entity.Sednsms;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SednsmsMapper {
    
	void save(Sednsms sednsms);
	
	Sednsms selectByPhone(String phone);

	void update(Sednsms sednsms);
}
