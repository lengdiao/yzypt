package com.ecard.service.serviceImpl;

import com.ecard.dao.*;
import com.ecard.entity.*;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.queryResult.*;
import com.ecard.service.DoctorService;
import com.ecard.utils.GetOpenIdUtils;
import com.ecard.utils.RoleUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DrInfoMapper drInfoMapper;
    @Autowired
    private CloudPassInfoMapper cloudPassInfoMapper;
    @Autowired
    private MallOrderMapper mallOrderMapper;
    @Autowired
    private ApiDiseaseMapper apiDiseaseMapper;
    @Autowired
    private TemplateMapper templateMapper;
    @Autowired
    private MedOrderMapper medOrderMapper;
    @Autowired
    private MedRecordMapper medRecordMapper;
    @Autowired
    private MedItemMapper medItemMapper;
    @Autowired
    private DrugSetMapper drugSetMapper;
    @Autowired
    private PtInfoMapper ptInfoMapper;
    @Autowired
    private DrOpenIdMapper drOpenIdMapper;
    @Autowired
    private PtOpenMapper ptOpenMapper;
    @Autowired
    private DiseaseMasterMapper diseaseMasterMapper;

    public Response insert(String drName, String phone, String idCard, String hospital, String province, String chiefNo,
                           Integer age, String address, String practiceProfile, String signature,
                           String introducer, String company, String title, String drTitleCert,
                           String drPracticeRegCert, String consultingHour, Integer disableFlag) {
        ResponseHasData response = new ResponseHasData();

        try {
            CloudPassInfo passInfo = cloudPassInfoMapper.selectByPhoneAndDoctor(phone);
            if(passInfo!=null){
                response.setMsg("电话号码重复注册");
                response.setStatus(0);
                return response;
            }
            CloudPassInfo cloudPassInfo = new CloudPassInfo();
            cloudPassInfo.setName(drName);
            cloudPassInfo.setPhone(phone);
            cloudPassInfo.setPassword("123456");
            cloudPassInfo.setCreateTime(new Date());
            cloudPassInfo.setIdNo(idCard);
            cloudPassInfo.setDisableFlag(disableFlag);
            cloudPassInfoMapper.insertSelective(cloudPassInfo);

            DrInfo drInfo = new DrInfo();
            drInfo.setCloudPassNo(cloudPassInfo.getCloudPassNo());
            drInfo.setAddress(address);
            drInfo.setAge(age);
            drInfo.setChiefNo(chiefNo);
            drInfo.setCompany(company);
            drInfo.setConsultingHour(consultingHour);
            drInfo.setCreateTime(new Date());
            drInfo.setDisableFlag(disableFlag);
            drInfo.setDrPracticeRegCert(drPracticeRegCert);
            drInfo.setProvince(province);
            drInfo.setDrTitleCert(drTitleCert);
            drInfo.setHospital(hospital);
            drInfo.setIntroducer(introducer);
            drInfo.setPracticeProfile(practiceProfile);
            drInfo.setSignature(signature);
            drInfo.setTitle(title);
            drInfoMapper.insertSelective(drInfo);

            RoleUtil.accredit(cloudPassInfo.getCloudPassNo(),"医生");

            response.setStatus(0);
            response.setMsg("添加医生成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("添加医生失败");
        }

        return response;
    }

    public Response update(Long drNo, String drName, String phone, String idCard,
                           String hospital, String province, String chiefNo, Integer age,
                           String address, String practiceProfile, String signature,
                           String introducer, String company, String title, String drTitleCert,
                           String drPracticeRegCert, String consultingHour, Integer disableFlag) {
        ResponseHasData response = new ResponseHasData();

        try {
            DrInfo drInfo = drInfoMapper.selectByPrimaryKey(drNo);

            CloudPassInfo cloudPassInfo = cloudPassInfoMapper.selectByPrimaryKey(drInfo.getCloudPassNo());
            cloudPassInfo.setName(drName);
            cloudPassInfo.setPhone(phone);
            cloudPassInfo.setPassword("123456");
            cloudPassInfo.setCreateTime(new Date());
            cloudPassInfo.setIdNo(idCard);
            cloudPassInfo.setDisableFlag(disableFlag);
            cloudPassInfoMapper.updateByPrimaryKeySelective(cloudPassInfo);

            drInfo.setAddress(address);
            drInfo.setAge(age);
            drInfo.setChiefNo(chiefNo);
            drInfo.setCompany(company);
            drInfo.setConsultingHour(consultingHour);
            drInfo.setCreateTime(new Date());
            drInfo.setDisableFlag(disableFlag);
            drInfo.setDrPracticeRegCert(drPracticeRegCert);
            drInfo.setProvince(province);
            drInfo.setDrTitleCert(drTitleCert);
            drInfo.setHospital(hospital);
            drInfo.setIntroducer(introducer);
            drInfo.setPracticeProfile(practiceProfile);
            drInfo.setSignature(signature);
            drInfo.setTitle(title);
            drInfoMapper.updateByPrimaryKeySelective(drInfo);

            response.setStatus(0);
            response.setMsg("更新医生成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("更新医生失败");
        }
        return response;
    }

    public Response select(String drName, String chiefNo, String title, Integer disableFlag, Integer page, Integer rows) {
        ResponseHasData response = new ResponseHasData();
        try {
            Page<?> pa =  PageHelper.startPage(page, rows);
            List<DrInfoQr> piq = drInfoMapper.selectByNameChiefTitleFlag(drName,chiefNo,title,disableFlag);
            //查询结果总数
            Long total = pa.getTotal();
            //创建分页条件
            PageInfo<DrInfoQr> pageData = new PageInfo<DrInfoQr>(piq,total.intValue());

            response.setStatus(0);
            response.setMsg("查询医生成功");
            response.setData(pageData);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询医生失败");
        }
        return response;
    }

    public Response login(String phone, String password, String code) {
        ResponseHasData response = new ResponseHasData();
        try {
            //String openId = GetOpenIdUtils.getOpenId(code);
            String openId = "001";
            CloudPassInfo cloudPassInfo = cloudPassInfoMapper.selectByPhoneAndPassword(phone,password,Long.valueOf("2"));
            if(cloudPassInfo!=null){
                DrInfoQr drInfoQr = drInfoMapper.selectByCloudPassNo(cloudPassInfo.getCloudPassNo());
                DrOpenId drOpenId = drOpenIdMapper.selectByOpenId(openId);
                if(drOpenId==null){
                    DrOpenId drOpenId1 = new DrOpenId();
                    drOpenId1.setDrNo(drInfoQr.getDrNo());
                    drOpenId1.setOpenId(openId);
                    drOpenIdMapper.insertSelective(drOpenId1);
                }
                response.setStatus(0);
                response.setMsg("医生登录成功");
                response.setData(drInfoQr);
            }else{
                response.setStatus(1);
                response.setMsg("医生登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("医生登录失败");
        }
        return response;
    }

    @Override
    public Response selectMallOrderList(Long drNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            List<MallOrderQr> mallOrderQrs = new ArrayList<MallOrderQr>();

            List<MallOrder> mallOrders = mallOrderMapper.selectByDrNo(drNo);
            if(mallOrders.size()!=0){
                for(MallOrder mallOrder : mallOrders){
                    MallOrderQr mallOrderQr = new MallOrderQr();
                    mallOrderQr.setMallOrder(mallOrder);
                    MedOrder medOrder = medOrderMapper.selectByPrimaryKey(mallOrder.getMedOrderNo());
                    mallOrderQr.setMedOrder(medOrder);
                    MedRecord medRecord = medRecordMapper.selectByPrimaryKey(medOrder.getMedRecordNo());
                    mallOrderQr.setMedRecord(medRecord);
                    List<MedItemQr> medItemQrs = medItemMapper.selectByMedOrderNo(medOrder.getOrderNo());
                    mallOrderQr.setMedItemQr(medItemQrs);
                    PtInfoQr ptInfo = ptInfoMapper.selectByPtNo(mallOrder.getPtNo());
                    mallOrderQr.setPtInfoQr(ptInfo);
                    mallOrderQrs.add(mallOrderQr);
                }
            }
            response.setData(mallOrderQrs);
            response.setMsg("查询未处理问诊成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询未处理问诊失败");
        }
        return response;
    }

    @Override
    public Response selectAccessTokenList() {
        ResponseHasData response = new ResponseHasData();
        try {
            List<ApiDisease> apiDiseases = apiDiseaseMapper.selectAll();
            response.setData(apiDiseases);
            response.setMsg("查询诊断列表成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询诊断列表失败");
        }
        return response;
    }

    @Override
    public Response insertTemplates(TemplateQr templateQr) {
        ResponseHasData response = new ResponseHasData();
        try {
            List<TemplateDrugSetQr> list = templateQr.getList();
            Template template = new Template();
            template.setTemplateType(templateQr.getTemplateType());
            template.setDrNo(templateQr.getDrNo());
            template.setCreateTime(new Date());
            template.setTemplateName(templateQr.getTemplateName());
            templateMapper.insertSelective(template);

            for(int i = 1 ; i<templateQr.getList().size()+1 ; i++){
                template.setDayDose(list.get(i-1).getDayDose());
                template.setDose(list.get(i-1).getDose());
                template.setDoseUnit(list.get(i-1).getDoseUnit());
                template.setExecAim(list.get(i-1).getExecAim());
                template.setExecWhen(list.get(i-1).getExecWhen());
                template.setUsageNo(list.get(i-1).getUsageNo());
                template.setWayNo(list.get(i-1).getWayNo());
                template.setTemplateNo(template.getTemplateId());
                template.setNumber(list.get(i-1).getNumber());
                template.setDrugSetNo(list.get(i-1).getDrugSetNo());
                if (i==1){
                    templateMapper.updateByPrimaryKeySelective(template);
                }else{
                    template.setTemplateId(null);
                    templateMapper.insertSelective(template);
                }
            }
            response.setMsg("新增模板处方成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("新增模板处方失败");
        }
        return response;
    }

    @Override
    public Response updateTemplates(TemplateQr templateQr) {
        ResponseHasData response = new ResponseHasData();
        try {
            templateMapper.deleteByPrimaryKey(templateQr.getTemplateId());

            List<TemplateDrugSetQr> list = templateQr.getList();
            Template template = new Template();
            template.setTemplateType(templateQr.getTemplateType());
            template.setDrNo(templateQr.getDrNo());
            template.setCreateTime(new Date());
            templateMapper.insertSelective(template);

            for(int i = 1 ; i<templateQr.getList().size() ; i++){
                template.setDayDose(list.get(i-1).getDayDose());
                template.setDose(list.get(i-1).getDose());
                template.setDoseUnit(list.get(i-1).getDoseUnit());
                template.setExecAim(list.get(i-1).getExecAim());
                template.setExecWhen(list.get(i-1).getExecWhen());
                template.setUsageNo(list.get(i-1).getUsageNo());
                template.setWayNo(list.get(i-1).getWayNo());
                template.setTemplateNo(template.getTemplateId());
                template.setNumber(list.get(i-1).getNumber());
                if (i==1){
                    templateMapper.updateByPrimaryKeySelective(template);
                }else{
                    templateMapper.insertSelective(template);
                }
            }
            response.setMsg("修改模板处方成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("修改模板处方失败");
        }
        return response;
    }

    @Override
    public Response selectTemplates(Long drNo, Integer templateType, String templateName, Integer page, Integer rows) {
        ResponseHasData response = new ResponseHasData();
        try {
            Page<?> pa =  PageHelper.startPage(page, rows);
            List<TemplateQr> piq = templateMapper.selectByDrNoTypeName(drNo,templateType,templateName);
            //查询结果总数
            Long total = pa.getTotal();
            //创建分页条件
            PageInfo<TemplateQr> pageData = new PageInfo<TemplateQr>(piq,total.intValue());

            response.setStatus(0);
            response.setMsg("查询处方模板成功");
            response.setData(pageData);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询处方模板错误");
        }
        return response;
    }

    @Override
    public Response updateMedRecord(Long mallNo, String diagContent, String plan, Integer orderType,
                                    Integer prescriptionNum, String prescriptionName, Integer decoctionWay,
                                    Integer decoctionNum, Integer specialUsage) {
        ResponseHasData response = new ResponseHasData();
        try {
            MedOrder medOrder= medOrderMapper.selectByPrimaryKey(mallNo);
            MedRecord medRecord = medRecordMapper.selectByPrimaryKey(medOrder.getMedRecordNo());
            medOrder.setDecoctionNum(decoctionNum);
            medOrder.setDecoctionWay(decoctionWay);
            medOrder.setOrderType(orderType);
            medOrder.setDiagContent(diagContent);
            medOrder.setSpecialUsage(specialUsage.toString());
            medOrder.setPrescriptionName(prescriptionName);
            medOrder.setPrescriptionNum(prescriptionNum);
            medOrderMapper.updateByPrimaryKeySelective(medOrder);

            medRecord.setPlan(plan);
            medRecord.setDiagContent(diagContent);
            medRecordMapper.updateByPrimaryKeySelective(medRecord);

            response.setMsg("编辑成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("编辑失败");
            response.setStatus(1);
        }

        return response;
    }

    @Override
    public Response updateDrugSet(TemplateQr templateQr) {
        ResponseHasData response = new ResponseHasData();
        try {
            BigDecimal price = new BigDecimal("0");
            for(TemplateDrugSetQr list :templateQr.getList()){
                MedItem medItem = new MedItem();
                medItem.setMedOrderNo(templateQr.getTemplateId());
                medItem.setNumber(Double.parseDouble(list.getNumber()+""));
                medItem.setDayDose(list.getDayDose());
                medItem.setDose(list.getDose());
                medItem.setDoseUnit(list.getDoseUnit());
                medItem.setDrugNo(list.getDrugSetNo());
                medItem.setExecAim(list.getExecAim());
                medItem.setExecDay(list.getExecDay());
                medItem.setExecWhen(list.getExecWhen());
                medItem.setUsageNo(list.getUsageNo());
                medItem.setWayNo(list.getWayNo());

                DrugSet drugSet = drugSetMapper.selectByPrimaryKey(list.getDrugSetNo());
                price = price.add(drugSet.getSaleGenPrice().multiply(new BigDecimal(list.getNumber())));
                medItemMapper.insertSelective(medItem);
            }
            MedOrder medOrder = medOrderMapper.selectByMedRecordNo(templateQr.getTemplateId());
            medOrder.setOrderStatus(1);
            medOrderMapper.updateByPrimaryKeySelective(medOrder);
            MallOrder mallOrder = mallOrderMapper.selectByMedOrderNo(medOrder.getOrderNo());
            mallOrder.setOrderAmount(price);
            mallOrder.setPayStatus(1);
            mallOrderMapper.updateByPrimaryKeySelective(mallOrder);

            response.setStatus(0);
            response.setMsg("添加药品成功,并修改处方单状态为未付款状态");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("添加药品失败");
        }

        return response;
    }

    @Override
    public Response Binding(Long ptNo, String code) {
        ResponseHasData response = new ResponseHasData();
        try {
            //String openId = GetOpenIdUtils.getOpenId(code);
            String openId = "001";
            List<PtOpen> ptOpens = ptOpenMapper.selectByPtNo(ptNo);
            PtOpen ptOpen = ptOpens.get(0);
            DrOpenId drOpenId = drOpenIdMapper.selectByOpenId(openId);

            if(ptOpen.getDrNo()!=null&&!"".equals(ptOpen.getDrNo())){
                ptOpen.setDrNo(drOpenId.getDrNo());
                ptOpenMapper.insertSelective(ptOpen);
            }else{
                ptOpen.setDrNo(drOpenId.getDrNo());
                ptOpenMapper.updateByPrimaryKeySelective(ptOpen);
            }


            response.setMsg("绑定成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("绑定失败");
        }

        return response;
    }

    @Override
    public Response selectDiseaseMaster(String keyword1, Integer page, Integer rows) {
        ResponseHasData response = new ResponseHasData();
        try {
            Page<?> pa =  PageHelper.startPage(page, rows);
            List<DiseaseMaster> diseaseMasters = diseaseMasterMapper.selectByKeyword1(keyword1);
            //查询结果总数
            Long total = pa.getTotal();
            //创建分页条件
            PageInfo<DiseaseMaster> pageData = new PageInfo<DiseaseMaster>(diseaseMasters,total.intValue());

            response.setStatus(0);
            response.setMsg("查询诊断成功");
            response.setData(pageData);
        } catch (Exception e) {
            response.setStatus(1);
            response.setMsg("查询诊断成功");
            e.printStackTrace();
        }
        return response;
    }


}
