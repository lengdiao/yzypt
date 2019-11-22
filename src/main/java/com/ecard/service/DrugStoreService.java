package com.ecard.service;

import com.ecard.pojo.Response;

import java.util.Date;

public interface DrugStoreService {


    Response addDrugStore(String drugStoreName, String contact, String contactPhone, String address, Integer disableFlag);

    Response updateDrugStore(Long drugStoreNo, String drugStoreName, String contact, String contactPhone, String address, Integer disableFlag, String oldCode);

    Response findDrugStore(String drugStoreName, Integer disableFlag, Integer page, Integer rows);

    Response findMallOrder(Long mallNo);

    Response updateMedOrderStatus(Long no, Long mallNo, Integer orderStatus, Integer payStatus, Date payTime, String execLoc, Integer status);
}
