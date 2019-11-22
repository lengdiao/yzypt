package com.ecard.service;

import com.ecard.entity.WeiXinUser;
import com.ecard.pojo.Response;

import java.util.Map;

public interface YzyptService {
    public void selectByOpenId(int status);
    public WeiXinUser getUserInfo(String accessToken, String openId);
    public Map<String, String> oauth2GetOpenid(String code);
    public Map<String , String> getAuthInfo(String code);

    Response selectMallOrder(Integer mallNo, Integer orderStatus, Integer ptNo, Integer drNo, Integer page, Integer rows);

    Response updateByPhoneAndPass(String phone, String oldPassword, String newPassword);

    Response sendCodeSms(String phone);

    Response updateByCode(String phone, String code, String newCode);
}
