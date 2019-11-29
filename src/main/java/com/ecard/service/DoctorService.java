package com.ecard.service;

import com.ecard.pojo.Response;
import com.ecard.pojo.queryResult.TemplateQr;

public interface DoctorService {

    Response insert(String drName, String phone, String idCard, String hospital, String province, String chiefNo, Integer age, String address, String practiceProfile, String signature, String introducer, String company, String title, String drTitleCert, String drPracticeRegCert, String consultingHour, Integer disableFlag);

    Response update(Long drNo, String drName, String phone, String idCard, String hospital, String province, String chiefNo, Integer age, String address, String practiceProfile, String signature, String introducer, String company, String title, String drTitleCert, String drPracticeRegCert, String consultingHour, Integer disableFlag);

    Response select(String drName, String chiefNo, String title, Integer disableFlag, Integer page, Integer rows);

    Response login(String phone, String password, String code);

    Response selectMallOrderList(Long drNo);

    Response selectAccessTokenList();

    Response insertTemplates(TemplateQr templateQr);

    Response updateTemplates(TemplateQr templateQr);

    Response selectTemplates(Long drNo, Integer templateType, String templateName, Integer page, Integer rows);

    Response updateMedRecord(Long mallNo, String diagContent, String plan, Integer orderType, Integer prescriptionNum, String prescriptionName, Integer decoctionWay, Integer decoctionNum, Integer specialUsage);

    Response updateDrugSet(TemplateQr templateQr);

    Response Binding(Long ptNo, String code);

    Response selectDiseaseMaster(String keyword1, String chtType, Integer page, Integer rows);
}
