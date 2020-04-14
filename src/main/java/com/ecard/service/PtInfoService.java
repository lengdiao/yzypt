package com.ecard.service;

import com.ecard.entity.ReceiptAddress;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;

public interface PtInfoService {

    Response insertMedOrder(String medRecordNo, Long drNo, String diagContent, String subjective, String objective, String plan);

    Response start(Long drNo);

    Response register(String name, String sex, String idNo, String height, String weight, String phone, String code);

    Response ptInfoUpdate(Long ptNo, String name, String sex, String idNo, String height, String weight, String phone, String code);

    Response selectPtInfo(String name, String idNo, Integer page, Integer rows);

    Response selectDrList(Integer page, Integer rows);

    Response updatePtInfo(Long ptNo, String name, String sex, String idNo, String height, String weight, String phone);

    Response binging(String drNo, String openId);

    ResponseHasData insertReceiptAddress(ReceiptAddress ra);

    Response updateReceiptAddress(ReceiptAddress ra);

    Response deleteReceiptAddress(String addId);

    Response selectReceiptAddress();

    Response selectInfo();

    Response findMallOrder(int orderStatus);

    Response selectMedOrderList(Integer payStatus);

    Response selectMedOrderInfo(Long medOrderNo,Long medRecordNo);

    Response selectShipping(Long orderNo);
}
