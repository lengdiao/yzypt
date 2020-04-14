package com.ecard.service;

import com.ecard.entity.DiseaseMaster;
import com.ecard.entity.WeiXinUser;
import com.ecard.pojo.Response;
import com.ecard.pojo.queryResult.DisInfoQr;

import java.util.Map;

public interface YzyptService {
    public void selectByOpenId(int status);
    public WeiXinUser getUserInfo(String accessToken, String openId);
    public Map<String, String> oauth2GetOpenid(String code);
    public Map<String , String> getAuthInfo(String code);

    Response selectMallOrder(Long mallNo, Integer orderStatus, Integer ptNo, Integer drNo, Integer page, Integer rows);

    Response updateByPhoneAndPass(String phone, String oldPassword, String newPassword);

    Response sendCodeSms(String phone);

    Response updateByCode(String phone, String code, String newCode);

    Response login(String phone, String passWord);

    Response getSign(String url);

    Response findMallOrder(Long mallNo, Long drugStoreNo, Integer orderStatus, String startDate, String endDate, Integer page, Integer rows, Long drNo, Long disNo1, Long disNo2);

    Response insertDiseaseMaster(DiseaseMaster diseaseMaster);

    Response updateDiseaseMaster(DiseaseMaster diseaseMaster);

    Response export(Long mallNo, Integer orderStatus, Integer ptNo, Integer drNo, String startDate, String endDate);

    Response saveDis(DisInfoQr disInfoQr);

    Response selectByDisNo(Long disno);

    Response updateDis(DisInfoQr disInfoQr);

    Response getDisLeader(String disLevel);

    Response insertDr(String disNo, String name, String hospital, String phone, String chiefNo, String practiceProfile, String title, String leaderDrNo, String drTitleCert, String drPracticeRegCert, String consultingHour, String idNo, int disableFlag, int type, String province, String city);

    Response updateDr(String drNo, String name, String hospital, String phone, String chiefNo, String practiceProfile, String title, String drTitleCert, String drPracticeRegCert, String consultingHour, String leaderDrNo, String idNo, int disableFlag, int type, String province, String city);

    Response count(int medOrderType, String drName, Long drugNo, String drPhone, Long disNo, Long disNo2, String startDate, String endDate, Long drNo, Integer page, Integer rows);

    Response disList(Long disno, String idNo, String name, String disLevel, Long disLeader, String sendDate, String endDate, Integer disableFlag, Integer page, Integer rows);

    Response selectMyDr(String disNo, String name, Integer page, Integer rows);

    Response getDisList(String disNo);

    void test();
}
