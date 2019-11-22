package com.ecard.service;

import com.ecard.pojo.Response;

public interface PtInfoService {

    Response insertMedOrder(String medRecordNo, Long drNo, String diagContent, String subjective, String objective, String plan);

    Response start(Long drNo);

    Response addImg(String img, String name, Long mallNo);

    Response select();

    Response register(String name, String sex, String idNo, String height, String weight, String phone);

    Response updatePtInfo(Long ptNo, String name, String sex, String idNo, String height, String weight, String phone);

    Response selectPtInfo(String name, String idNo, Integer page, Integer rows);

    Response selectDrList();
}
