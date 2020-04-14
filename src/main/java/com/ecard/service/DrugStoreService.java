package com.ecard.service;

import com.ecard.pojo.Response;

public interface DrugStoreService {


    Response addDrugStore(String drugStoreName, String contact, String contactPhone, String address, String type, Integer disableFlag);

    Response updateDrugStore(Long drugStoreNo, String drugStoreName, String contact, String contactPhone, String address, String type, Integer disableFlag);

    Response findMallOrder(Long medOrderNo);

    Response findDrugStore(String drugStoreName, Integer disableFlag, String address, String type, Integer page, Integer rows);

    Response findShippingStatusIs1();

    Response updateMedOrderStatus(Long mallNo, Long drugStoreNo, int shippingStatus, String shippingCompany, String shippingNo);
}
