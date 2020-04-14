package com.ecard.service;

import com.ecard.pojo.Response;
import com.ecard.pojo.queryResult.TemplateQr;

public interface DoctorService {
    
    Response select(String drName, String chiefNo, String title, Integer disableFlag, Integer page, Integer rows);

    Response login(String phone, String password, String code);

    Response selectMallOrderList(Long drNo);

    Response selectAccessTokenList();

    Response insertTemplates(TemplateQr templateQr);

    Response updateTemplates(TemplateQr templateQr);

    Response selectTemplates(Long drNo, String dNo, Integer templateType, String templateName);

    Response updateMedRecord(Long mallNo, String diagContent, String plan, Integer orderType, Integer prescriptionNum, String prescriptionName, Integer decoctionWay, Integer decoctionNum, Integer specialUsage);

    Response updateDrugSet(TemplateQr templateQr);

    Response Binding(Long ptNo, String code);

    Response selectDiseaseMaster(String keyword1, String chtType, Integer page, Integer rows);

    Response phoneBinding(String phone, Long drNo);
    
    Response wxQRCode(Long drNo);

    Response insert(String drName, String phone, String idCard, String hospital, String chiefNo, Integer age, String practiceProfile, String signature, String title, String drTitleCert, String drPracticeRegCert, String consultingHour, Integer disableFlag, Integer type, String province, String city);

    Response update(Long drNo, String drName, String phone, String idNo, String hospital, String chiefNo, Integer age, String practiceProfile, String signature, String title, String drTitleCert, String drPracticeRegCert, String consultingHour, Integer disableFlag, Integer type, String province, String city);


    Response selectMedRecord(Long medRecordNo);

    Response updateDiseaseMaster(Long medRecordNo, String dNo, String plan);

    Response selectDrInfo(Long drNo);

    Response selectPt(String name, String phone, Long drNo, Integer page, Integer rows);

    Response selectPtRecordList(Long ptNo, Long drNo);

    Response count(String name, Long drNo, String startDate, Long drugNo);

    Response selectTemplatesByNo(Long templateNo);

    Response updatePassword(String phone, Long drNo, String oldPassword, String newPassword);

    Response selectTemplates1(Long drNo, String dNo, Integer templateType, String templateName);

    Response selectDisease(Long dNo);

    Response selectMaxOrderTime(Long ptNo, Long drNo);

    Response deleteTemplate(Long templateNo);

    Response register(String drName, String phone, String hospital, String chiefNo, String practiceProfile, String title, Integer type, String disPhone);

    Response selectByDepartMent(String drName, String deptName, String city, Integer page, Integer rows);

    Response doctorUpdate(Long drNo, String drName, String phone, String idNo, String hospital, String chiefNo, Integer age, String practiceProfile, String signature, String title, String drTitleCert, String drPracticeRegCert, String consultingHour, Integer disableFlag, String code, String province, String city);

    Response selectPacket(Long drNo);
}
