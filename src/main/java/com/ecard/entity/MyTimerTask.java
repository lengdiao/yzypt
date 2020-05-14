package com.ecard.entity;

import com.ecard.dao.*;
import com.ecard.utils.WeiXinUtils;

import java.util.Date;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

	private String openId;
	private String name;
	private String medRecordNo;
	private int shippingStatus;
	private MedRecordMapper medRecordMapper;
	private MedOrderMapper medOrderMapper;
	private Long medOrderNo;
	private int platform;

	public MyTimerTask(String openId, String name, String medRecordNo, int shippingStatus,
					   MedRecordMapper medRecordMapper, MedOrderMapper medOrderMapper, Long medOrderNo, int platform){
		this.name = name;
		this.medRecordNo = medRecordNo;
		this.shippingStatus = shippingStatus;
		this.openId = openId;
		this.medRecordMapper = medRecordMapper;
		this.medOrderMapper = medOrderMapper;
		this.medOrderNo = medOrderNo;
		this.platform = platform;
    }

    @Override
	public void run() {
		WeiXinUtils.remind(openId, name, Long.valueOf(medRecordNo), shippingStatus,platform);
		MedRecord medRecord = medRecordMapper.selectByPrimaryKey(medRecordNo);
		medRecord.setMedRecordStatus(0);
		medRecord.setUpdateTime(new Date());
		medRecordMapper.updateByPrimaryKeySelective(medRecord);
		MedOrder medOrder = medOrderMapper.selectByPrimaryKey(medOrderNo);
		medOrder.setUpdateTime(new Date());
		medOrderMapper.updateByPrimaryKeySelective(medOrder);
	}


	
	

}
