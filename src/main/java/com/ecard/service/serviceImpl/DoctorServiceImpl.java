package com.ecard.service.serviceImpl;

import com.ecard.dao.*;
import com.ecard.entity.*;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.queryResult.*;
import com.ecard.service.DoctorService;
import com.ecard.utils.GetOpenIdUtils;
import com.ecard.utils.PostUtil;
import com.ecard.utils.RoleUtil;
import com.ecard.utils.WeiXinUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

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
    @Autowired
    private SednsmsMapper sednsmsMapper;
    @Autowired
    private MrPictureMapper mrPictureMapper;
    @Autowired
    private PostageMapper postageMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private ChiCountMapper chiCountMapper;
    @Autowired
    private CountMapper countMapper;
    @Autowired
    private DisInfoMapper disInfoMapper;
    @Autowired
    private DrDisRelationMapper drDisRelationMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private DoctorKitingMapper doctorKitingMapper;
    @Autowired
    private DoctorWalletMapper doctorWalletMapper;

    public Response insert(String drName, String phone, String idNo, String hospital,
                           String chiefNo, Integer age, String practiceProfile, String signature,
                           String title, String drTitleCert, String drPracticeRegCert,
                           String consultingHour, Integer disableFlag, Integer type, String province, String city, int platform, Long disNo) {
        ResponseHasData response = new ResponseHasData();

        try {
            CloudPassInfo passInfo = cloudPassInfoMapper.selectByPhone(phone);
            if (passInfo != null) {
                response.setMsg("电话号码重复注册");
                response.setStatus(1);
                return response;
            }
            CloudPassInfo cloudPassInfo = new CloudPassInfo();
            cloudPassInfo.setName(drName);
            cloudPassInfo.setPhone(phone);
            cloudPassInfo.setPassword("123456");
            cloudPassInfo.setCreateTime(new Date());
            cloudPassInfo.setIdNo(idNo);
            cloudPassInfo.setDisableFlag(disableFlag);
            cloudPassInfoMapper.insertSelective(cloudPassInfo);

            DrInfo drInfo = new DrInfo();
            drInfo.setCloudPassNo(cloudPassInfo.getCloudPassNo());
            drInfo.setAge(age);
            drInfo.setChiefNo(chiefNo);
            drInfo.setConsultingHour(consultingHour);
            drInfo.setCreateTime(new Date());
            drInfo.setDisableFlag(disableFlag);
            drInfo.setDrPracticeRegCert(drPracticeRegCert);
            drInfo.setDrTitleCert(drTitleCert);
            drInfo.setHospital(hospital);
            drInfo.setPracticeProfile(practiceProfile);
            drInfo.setSignature(signature);
            drInfo.setTitle(title);
            drInfo.setType(type);
            drInfo.setProvince(province);
            drInfo.setCity(city);
            drInfo.setPlatform(platform);
            drInfo.setHeadImg("https://www.yizhenyun.com.cn/yzypt/drImage/dr.jpg");
            drInfoMapper.insertSelective(drInfo);

            DrDisRelation drDisRelation = new DrDisRelation();
            drDisRelation.setDisableFlag(0);
            drDisRelation.setDisNo(disNo);
            drDisRelation.setDrNo(drInfo.getDrNo());
            drDisRelationMapper.insertSelective(drDisRelation);

            RoleUtil.accredit(cloudPassInfo.getCloudPassNo(), "医生");

            response.setStatus(0);
            response.setMsg("添加医生成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("添加医生失败");
            throw e;
        }

        return response;
    }

    public Response update(Long drNo, String drName, String phone, String idNo, String hospital,
                           String chiefNo, Integer age, String practiceProfile, String signature,
                           String title, String drTitleCert, String drPracticeRegCert,
                           String consultingHour, Integer disableFlag, Integer type, String province, String city,
                           int platform, Long disNo) {
        ResponseHasData response = new ResponseHasData();

        try {
            CloudPassInfo info = cloudPassInfoMapper.selectByPhoneIdNoDrNo(drNo, phone, idNo);
            if (info != null) {
                response.setStatus(1);
                response.setMsg("手机号码或身份证号码重复");
                return response;
            }
            DrInfo drInfo = drInfoMapper.selectByPrimaryKey(drNo);

            DrDisRelation drDisRelation = drDisRelationMapper.selectAllByDrNo(drNo);

            CloudPassInfo cloudPassInfo = cloudPassInfoMapper.selectByPrimaryKey(drInfo.getCloudPassNo());
            cloudPassInfo.setName(drName);
            cloudPassInfo.setPhone(phone);
            cloudPassInfo.setPassword("123456");
            cloudPassInfo.setCreateTime(new Date());
            cloudPassInfo.setIdNo(idNo);
            cloudPassInfo.setDisableFlag(disableFlag);
            cloudPassInfoMapper.updateByPrimaryKeySelective(cloudPassInfo);

            drInfo.setAge(age);
            drInfo.setCreateTime(new Date());
            drInfo.setDisableFlag(disableFlag);
            drInfo.setDrTitleCert(drTitleCert);
            drInfo.setHospital(hospital);
            drInfo.setPracticeProfile(practiceProfile);
            drInfo.setSignature(signature);
            drInfo.setTitle(title);
            drInfo.setConsultingHour(consultingHour);
            drInfo.setDrPracticeRegCert(drPracticeRegCert);
            drInfo.setType(type);
            drInfo.setProvince(province);
            drInfo.setCity(city);
            drInfo.setChiefNo(chiefNo);
            drInfo.setPlatform(platform);
            drInfoMapper.updateByPrimaryKeySelective(drInfo);

            if(drDisRelation!=null){
                drDisRelation.setDisNo(disNo);
                drDisRelationMapper.updateByPrimaryKeySelective(drDisRelation);
            }else{
                DrDisRelation drDisRelation1 = new DrDisRelation();
                drDisRelation1.setDisNo(disNo);
                drDisRelation1.setDrNo(drNo);
                drDisRelation1.setDisableFlag(0);
                drDisRelation1.setCreateTime(new Date());
                drDisRelationMapper.insertSelective(drDisRelation1);
            }


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
            Page<?> pa = PageHelper.startPage(page, rows);
            List<DrInfoQr> piq = drInfoMapper.selectByNameChiefTitleFlag(drName, chiefNo, title, disableFlag);
            //查询结果总数
            Long total = pa.getTotal();
            //创建分页条件
            PageInfo<DrInfoQr> pageData = new PageInfo<DrInfoQr>(piq, total.intValue());

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
            String openId = GetOpenIdUtils.getOpenId(code);
            //String openId = "001";
            CloudPassInfo cloudPassInfo = cloudPassInfoMapper.selectByPhoneAndPassword(phone, password, Long.valueOf("2"));
            if (cloudPassInfo != null) {
                DrInfoQr drInfoQr = drInfoMapper.selectByCloudPassNo(cloudPassInfo.getCloudPassNo());
                drInfoQr = drInfoMapper.selectByDrNo(drInfoQr.getDrNo());
                DrOpenId drOpenId = drOpenIdMapper.selectByOpenId(openId);
                if (drOpenId == null) {
                    DrOpenId drOpenId1 = new DrOpenId();
                    drOpenId1.setDrNo(drInfoQr.getDrNo());
                    drOpenId1.setOpenId(openId);
                    drOpenIdMapper.insertSelective(drOpenId1);
                }
                response.setStatus(0);
                response.setMsg("医生登录成功");
                response.setData(drInfoQr);
            } else {
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
            List<MedOrderQr> medOrderQrs = new ArrayList<MedOrderQr>();

            List<MallOrder> mallOrders = mallOrderMapper.selectByDrNoMedRecordStatus(drNo);
            for (MallOrder mallOrder : mallOrders) {
                MedOrder medOrder = medOrderMapper.selectByPrimaryKey(mallOrder.getMedOrderNo());
                PtInfoQr ptInfoQr = ptInfoMapper.selectByPtNo(mallOrder.getPtNo());
                MedOrderQr medOrderQr = new MedOrderQr();
                medOrderQr.setMallNo(mallOrder.getMallNo());
                medOrderQr.setMedRecordNo(Long.valueOf(medOrder.getMedRecordNo()));
                medOrderQr.setName(ptInfoQr.getName());
                medOrderQr.setOrderTime(mallOrder.getOrderTime());
                medOrderQr.setPtInfoQr(ptInfoQr);
                medOrderQrs.add(medOrderQr);
            }

            /*if(mallOrders.size()!=0){
                for(MallOrder mallOrder : mallOrders){
                    MallOrderQr mallOrderQr = new MallOrderQr();
                    mallOrderQr.setMallOrder(mallOrder);
                    MedOrder medOrder = medOrderMapper.selectByPrimaryKey(mallOrder.getMedOrderNo());
                    mallOrderQr.setMedOrder(medOrder);
                    MedRecord medRecord = medRecordMapper.selectByPrimaryKey(medOrder.getMedRecordNo());
                    mallOrderQr.setMedRecord(medRecord);
                    MrPicture[] mrPictures = mrPictureMapper.selectByMedRecordNo(medOrder.getMedRecordNo());
                    mallOrderQr.setMrPictures(mrPictures);
                    List<MedItemQr> medItemQrs = medItemMapper.selectByMedOrderNo(medOrder.getOrderNo());
                    mallOrderQr.setMedItemQr(medItemQrs);
                    PtInfoQr ptInfo = ptInfoMapper.selectByPtNo(mallOrder.getPtNo());
                    mallOrderQr.setPtInfoQr(ptInfo);
                    mallOrderQrs.add(mallOrderQr);
                }
            }*/

            response.setData(medOrderQrs);
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
            Long templateNo = templateMapper.selectMaxTemplateNo();
            List<TemplateDrugSetQr> list = templateQr.getList();
            Template template = new Template();
            template.setTemplateType(templateQr.getTemplateType());
            if (templateQr.getTemplateType() == 1) {
                template.setUsageNo(templateQr.getUsageNo());
            }
            template.setDrNo(templateQr.getDrNo());
            //template.setDNo(templateQr.getdNo().toString());
            template.setCreateTime(new Date());
            template.setTemplateName(templateQr.getTemplateName());
            template.setSpecialUsage(templateQr.getSpecialUsage());
            template.setPrescriptionNum(templateQr.getPrescriptionNum());
            template.setTemplateNo(templateNo + 1);
            template.setRemark(templateQr.getRemark());
            templateMapper.insertSelective(template);

            for (int i = 1; i < templateQr.getList().size() + 1; i++) {
                template.setDayDose(list.get(i - 1).getDayDose());
                template.setDose(list.get(i - 1).getDose());
                template.setDoseUnit(list.get(i - 1).getDoseUnit());
                template.setExecAim(list.get(i - 1).getExecAim());
                template.setExecWhen(list.get(i - 1).getExecWhen());
                if (templateQr.getTemplateType() == 2) {
                    template.setUsageNo(list.get(i - 1).getUsageNo());
                }
                template.setWayNo(list.get(i - 1).getWayNo());
                template.setTemplateNo(template.getTemplateId());
                template.setNumber(list.get(i - 1).getNumber());
                template.setDrugSetNo(list.get(i - 1).getDrugSetNo());
                if (i == 1) {
                    templateMapper.updateByPrimaryKeySelective(template);
                } else {
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
            templateMapper.deleteByTemplateNo(templateQr.getTemplateNo());
            Long templateNo = templateMapper.selectMaxTemplateNo();

            List<TemplateDrugSetQr> list = templateQr.getList();
            Template template = new Template();
            template.setTemplateType(templateQr.getTemplateType());
            if (templateQr.getTemplateType() == 1) {
                template.setUsageNo(templateQr.getUsageNo());
            }
            template.setDrNo(templateQr.getDrNo());
            template.setCreateTime(new Date());
            //template.setDNo(templateQr.getdNo());
            template.setSpecialUsage(templateQr.getSpecialUsage());
            template.setPrescriptionNum(templateQr.getPrescriptionNum());
            template.setRemark(templateQr.getRemark());
            template.setTemplateName(templateQr.getTemplateName());
            template.setTemplateNo(templateNo + 1);
            templateMapper.insertSelective(template);

            System.out.println("id=" + template.getTemplateId());
            System.out.println("size:" + templateQr.getList().size());

            for (int i = 1; i < templateQr.getList().size() + 1; i++) {
                template.setDayDose(list.get(i - 1).getDayDose());
                template.setDose(list.get(i - 1).getDose());
                template.setDoseUnit(list.get(i - 1).getDoseUnit());
                template.setExecAim(list.get(i - 1).getExecAim());
                template.setExecWhen(list.get(i - 1).getExecWhen());
                if (templateQr.getTemplateType() == 2) {
                    template.setUsageNo(list.get(i - 1).getUsageNo());
                }
                template.setWayNo(list.get(i - 1).getWayNo());
                template.setTemplateNo(template.getTemplateNo());
                template.setNumber(list.get(i - 1).getNumber());
                template.setDrugSetNo(list.get(i - 1).getDrugSetNo());
                if (i == 1) {
                    templateMapper.updateByPrimaryKeySelective(template);
                } else {
                    template.setTemplateId(null);
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
    public Response selectTemplates(Long drNo, String dNo, Integer templateType, String templateName) {
        ResponseHasData response = new ResponseHasData();
        try {

            List<TemplateQr> templateQrs = templateMapper.selectByDrNoTypeName(drNo, dNo, templateType, templateName);
            TemplateQr templateQr1 = new TemplateQr();
            templateQr1.setTemplateName("无");
            templateQrs.add(templateQr1);
            for (TemplateQr templateQr : templateQrs) {
                List<TemplateDrugSetQr> list = templateQr.getList();
                if (templateQr.getList() != null) {
                    for (TemplateDrugSetQr templateDrugSetQr : list) {
                        DrugSet drugSet = drugSetMapper.selectByPrimaryKey(templateDrugSetQr.getDrugSetNo());
                        templateDrugSetQr.setCommonName(drugSet.getCommonName());
                    }
                }

            }
            response.setStatus(0);
            response.setMsg("查询处方模板成功");
            response.setData(templateQrs);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询处方模板错误");
        }
        return response;
    }

    public Response selectTemplates1(Long drNo, String dNo, Integer templateType, String templateName) {
        ResponseHasData response = new ResponseHasData();
        try {

            List<TemplateQr> templateQrs = templateMapper.selectByDrNoTypeName(drNo, dNo, templateType, templateName);
            BigDecimal sum = new BigDecimal("0");
            for (TemplateQr templateQr : templateQrs) {
                List<TemplateDrugSetQr> list = templateQr.getList();
                for (TemplateDrugSetQr templateDrugSetQr : list) {
                    templateDrugSetQr.setDose1(templateDrugSetQr.getDose());
                    templateDrugSetQr.setDoseUnit1(templateDrugSetQr.getDoseUnit());
                    DrugSet drugSet = drugSetMapper.selectByPrimaryKey(templateDrugSetQr.getDrugSetNo());
                    templateDrugSetQr.setDivisor(drugSet.getDivisor());
                    templateDrugSetQr.setDoseUnit(drugSet.getDoseUnit());
                    templateDrugSetQr.setDosege(drugSet.getDosege());
                    templateDrugSetQr.setDosegeUnit(drugSet.getDosegeUnit());
                    templateDrugSetQr.setDividend(drugSet.getDividend());
                    templateDrugSetQr.setSaleUnit(drugSet.getSaleUnit());
                    templateDrugSetQr.setCommonName(drugSet.getCommonName());
                    templateDrugSetQr.setSaleGenPrice(drugSet.getSaleGenPrice());
                    templateQr.setUsageNo(templateDrugSetQr.getUsageNo());
                    sum = sum.add(drugSet.getSaleGenPrice().multiply(new BigDecimal(templateDrugSetQr.getNumber().toString())));
                }
                sum = sum.setScale(2, BigDecimal.ROUND_DOWN);
                templateQr.setPrice(sum);
            }
            response.setStatus(0);
            response.setMsg("查询处方模板成功");
            response.setData(templateQrs);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询处方模板错误");
        }
        return response;
    }

    @Override
    public Response selectDisease(Long dNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            DiseaseMaster diseaseMaster = diseaseMasterMapper.selectByPrimaryKey(dNo);
            response.setData(diseaseMaster);
            response.setStatus(0);
            response.setMsg("查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询失败");
        }
        return response;
    }

    @Override
    public Response selectMaxOrderTime(Long ptNo, Long drNo) {
        ResponseHasData response = new ResponseHasData();
        MallOrder mallOrder = mallOrderMapper.selectMaxOrderTime(ptNo, drNo);
        if (mallOrder == null) {
            response.setMsg("未查询到该患者的历史病例");
            response.setStatus(0);
            return response;
        }
        MedOrder medOrder = medOrderMapper.selectByPrimaryKey(mallOrder.getMedOrderNo());
        MedRecord medRecord = medRecordMapper.selectByPrimaryKey(medOrder.getMedRecordNo());
        List<MedItemQr> medItemQrs = medItemMapper.selectByMedOrderNo(medOrder.getOrderNo());
        if (mallOrder != null) {
            TemplateQr templateQr = new TemplateQr();
            templateQr.setTemplateType(medOrder.getOrderType());
            templateQr.setRemark(medOrder.getRemark());
            if (medOrder.getOrderType() == 1) {
                templateQr.setPrescriptionNum(medOrder.getPrescriptionNum());
                templateQr.setSpecialUsage(medOrder.getSpecialUsage());
                templateQr.setdNo(medRecord.getDiagContent());
                String diagContentName = medRecord.getDiagContent();
                String[] diag = diagContentName.split(",");
                if (medOrder.getOrderType() == 1) {
                    diagContentName = "";
                    //中医
                    for (int i = 0; i < diag.length; i++) {
                        String[] diag1 = diag[i].split("-");
                        diagContentName += diseaseMasterMapper.selectByPrimaryKey(Long.valueOf(diag1[0])).getChtName() + "-";
                        diagContentName += diseaseMasterMapper.selectByPrimaryKey(Long.valueOf(diag1[1])).getChtName() + ",";
                        System.out.println("中医" + diagContentName);
                    }
                    diagContentName = diagContentName.substring(0, diagContentName.length() - 1);
                } else {
                    diagContentName = "";
                    //西医
                    for (int i = 0; i < diag.length; i++) {
                        diagContentName += diseaseMasterMapper.selectByPrimaryKey(Long.valueOf(diag[i])).getChtName() + ",";
                        System.out.println("西医" + diagContentName);
                    }
                    diagContentName = diagContentName.substring(0, diagContentName.length() - 1);
                }
                templateQr.setdName(diagContentName);
                templateQr.setUsageNo(medItemQrs.get(0).getUsageNo());
                BigDecimal unitPrice = medOrder.getOrderAmount().divide(new BigDecimal(medOrder.getPrescriptionNum().toString()));
                medOrder.setUnitPrice(unitPrice);
                templateQr.setPrice(unitPrice);
                List<TemplateDrugSetQr> drugSets = new ArrayList<TemplateDrugSetQr>();
                for (MedItemQr medItemQr : medItemQrs) {
                    TemplateDrugSetQr drugSet = drugSetMapper.findByDrugNo(medItemQr.getDrugNo());
                    drugSet.setDoseUnit(medItemQr.getDoseUnit());
                    drugSet.setNumber(medItemQr.getNumber());
                    drugSets.add(drugSetMapper.findByDrugNo(medItemQr.getDrugNo()));
                }
                templateQr.setList(drugSets);
            } else {
                templateQr.setdNo(medRecord.getDiagContent());
                List<TemplateDrugSetQr> drugSets = new ArrayList<TemplateDrugSetQr>();
                templateQr.setUsageNo(medItemQrs.get(0).getUsageNo());
                String diagContentName = medRecord.getDiagContent();
                String[] diag = diagContentName.split(",");
                if (medOrder.getOrderType() == 1) {
                    diagContentName = "";
                    //中医
                    for (int i = 0; i < diag.length; i++) {
                        String[] diag1 = diag[i].split("-");
                        diagContentName += diseaseMasterMapper.selectByPrimaryKey(Long.valueOf(diag1[0])).getChtName() + "-";
                        diagContentName += diseaseMasterMapper.selectByPrimaryKey(Long.valueOf(diag1[1])).getChtName() + ",";
                        System.out.println("中医" + diagContentName);
                    }
                    diagContentName = diagContentName.substring(0, diagContentName.length() - 1);
                } else {
                    diagContentName = "";
                    //西医
                    for (int i = 0; i < diag.length; i++) {
                        diagContentName += diseaseMasterMapper.selectByPrimaryKey(Long.valueOf(diag[i])).getChtName() + ",";
                        System.out.println("西医" + diagContentName);
                    }
                    diagContentName = diagContentName.substring(0, diagContentName.length() - 1);
                }
                templateQr.setdName(diagContentName);

                for (MedItemQr medItemQr : medItemQrs) {
                    TemplateDrugSetQr drugSet = drugSetMapper.findByDrugNo(medItemQr.getDrugNo());
                    drugSet.setDoseUnit1(medItemQr.getDoseUnit());
                    drugSet.setDose1(medItemQr.getDose());
                    drugSet.setNumber(medItemQr.getNumber());
                    drugSets.add(drugSet);
                    drugSet.setUsageNo(medItemQrs.get(0).getUsageNo());
                    drugSet.setWayNo(medItemQrs.get(0).getWayNo());
                }
                templateQr.setList(drugSets);
            }

            response.setStatus(0);
            response.setMsg("查询成功");
            response.setData(templateQr);
        } else {
            response.setStatus(0);
            response.setMsg("没有历史病例");
        }
        return response;
    }

    @Override
    public Response deleteTemplate(Long templateNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            templateMapper.deleteByTemplateNo(templateNo);
            response.setMsg("删除成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("删除失败");
            response.setStatus(1);
        }
        return response;
    }

    @Override
    public Response register(String drName, String phone, String hospital, String chiefNo, String practiceProfile, String title, Integer type, String disPhone) {
        ResponseHasData response = new ResponseHasData();
        try {
            CloudPassInfo passInfo = cloudPassInfoMapper.selectByPhoneAndDoctor(phone);
            if (passInfo != null) {
                response.setMsg("电话号码重复注册");
                response.setStatus(0);
                return response;
            }
            CloudPassInfo cloudPassInfo = new CloudPassInfo();
            cloudPassInfo.setName(drName);
            cloudPassInfo.setPhone(phone);
            cloudPassInfo.setPassword("123456");
            cloudPassInfo.setCreateTime(new Date());
            cloudPassInfo.setDisableFlag(0);
            cloudPassInfoMapper.insertSelective(cloudPassInfo);

            DrInfo drInfo = new DrInfo();
            drInfo.setCloudPassNo(cloudPassInfo.getCloudPassNo());
            drInfo.setChiefNo(chiefNo);
            drInfo.setCreateTime(new Date());
            drInfo.setDisableFlag(0);
            drInfo.setHospital(hospital);
            drInfo.setPracticeProfile(practiceProfile);
            drInfo.setTitle(title);
            drInfo.setType(type);
            drInfoMapper.insertSelective(drInfo);

            DisInfoQr disInfo = disInfoMapper.selectByPhone(disPhone);
            if (disInfo == null) {
                response.setMsg("没有找到该代表");
                response.setStatus(1);
                return response;
            } else {
                DrDisRelation drDisRelation = new DrDisRelation();
                drDisRelation.setCreateTime(new Date());
                drDisRelation.setDisableFlag(0);
                drDisRelation.setDrNo(drInfo.getDrNo());
                drDisRelation.setDisNo(disInfo.getDisNo());
                drDisRelationMapper.insertSelective(drDisRelation);
            }

            RoleUtil.accredit(cloudPassInfo.getCloudPassNo(), "医生");

            response.setStatus(0);
            response.setMsg("注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("注册失败");
        }

        return response;
    }

    @Override
    public Response selectByDepartMent(String drName, String deptName, String city, Integer page, Integer rows) {
        ResponseHasData response = new ResponseHasData();

        try {
            Page<?> pa = PageHelper.startPage(page, rows);
            /*List<DepartmentQr> departmentQrs = new ArrayList<DepartmentQr>();
            List<Department> departments = departmentMapper.selectAll(deptName);
            for(Department department : departments){
                List<DrInfoQr> drInfoQrs = drInfoMapper.selectDepartment(department.getDeptName(),drName);
                DepartmentQr departmentQr = new DepartmentQr();
                //departmentQr.setDepartmentName(department.getDeptName());
                departmentQr.setDrInfoQrs(drInfoQrs);
                departmentQrs.add(departmentQr);
            }*/
            List<DrInfoQr> drInfoQrs = drInfoMapper.selectDepartment(deptName, drName, city);
            //查询结果总数
            Long total = pa.getTotal();
            //创建分页条件
            PageInfo<DrInfoQr> pageData = new PageInfo<DrInfoQr>(drInfoQrs, total.intValue());

            response.setStatus(0);
            response.setData(pageData);
            response.setMsg("查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询失败");
        }
        return response;
    }


    @Override
    public Response updateMedRecord(Long mallNo, String diagContent, String plan, Integer orderType,
                                    Integer prescriptionNum, String prescriptionName, Integer decoctionWay,
                                    Integer decoctionNum, Integer specialUsage) {
        ResponseHasData response = new ResponseHasData();
        try {
            MedOrder medOrder = medOrderMapper.selectByPrimaryKey(mallNo);
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
            MedOrder medOrder = medOrderMapper.selectByMedRecordNo(templateQr.getTemplateId());
            BigDecimal price = new BigDecimal("0");
            for (TemplateDrugSetQr list : templateQr.getList()) {
                MedItem medItem = new MedItem();
                medItem.setMedOrderNo(medOrder.getOrderNo());
                medItem.setNumber(Double.parseDouble(list.getNumber() + ""));
                medItem.setDayDose(list.getDayDose());
                medItem.setDose(list.getDose1());
                medItem.setDoseUnit(list.getDoseUnit1());
                medItem.setDrugNo(list.getDrugSetNo());
                medItem.setExecAim(list.getExecAim());
                medItem.setExecDay(list.getExecDay());
                medItem.setExecWhen(list.getExecWhen());
                if (templateQr.getTemplateType() == 1) {
                    medItem.setUsageNo(templateQr.getSpecialUsage());
                } else {
                    medItem.setUsageNo(list.getUsageNo());
                }

                medItem.setWayNo(list.getWayNo());

                DrugSet drugSet = drugSetMapper.selectByPrimaryKey(list.getDrugSetNo());
                price = price.add(drugSet.getSaleGenPrice().multiply(new BigDecimal(list.getNumber())));
                medItemMapper.insertSelective(medItem);
            }
            /*BigDecimal post = new BigDecimal("0");

            if(templateQr.getList().size()==1){
                List<Postage> postages = postageMapper.selectByNo(templateQr.getList().get(0).getDrugSetNo());
                if(postages.size()==0){
                    List<Postage> postage = postageMapper.selectByNo(Long.valueOf("0"));
                    post = postage.get(0).getPostage();
                }else{
                    post = postages.get(0).getPostage();
                }
            }else {
                List<Postage> postage = postageMapper.selectByNo(Long.valueOf("0"));
                post = postage.get(0).getPostage();
            }*/

            medOrder.setOrderType(templateQr.getTemplateType());
            medOrder.setUpdateTime(new Date());
            medOrderMapper.updateByPrimaryKeySelective(medOrder);

            if (medOrder.getOrderType() == 1) {
                price = price.multiply(new BigDecimal(templateQr.getPrescriptionNum()));
                medOrder.setSpecialUsage(templateQr.getSpecialUsage());
            }

            price = price.setScale(2, BigDecimal.ROUND_DOWN);

            medOrder.setOrderType(templateQr.getTemplateType());
            medOrder.setOrderAmount(price);
            medOrder.setOrderStatus(1);
            medOrder.setRemark(templateQr.getRemark());
            medOrder.setPrescriptionNum(templateQr.getPrescriptionNum());
            medOrderMapper.updateByPrimaryKeySelective(medOrder);

            //price = price.add(post);

            MallOrder mallOrder = mallOrderMapper.selectByMedOrderNo(medOrder.getOrderNo());
            mallOrder.setOrderAmount(price);
            mallOrder.setPayStatus(1);
            mallOrderMapper.updateByPrimaryKeySelective(mallOrder);

            MedRecord medRecord = medRecordMapper.selectByPrimaryKey(medOrder.getMedRecordNo());
            medRecord.setMedRecordStatus(0);
            medRecord.setUpdateTime(new Date());
            medRecordMapper.updateByPrimaryKeySelective(medRecord);

            PtInfoQr ptInfoQr = ptInfoMapper.selectByPtNo(mallOrder.getPtNo());

            List<PtOpen> ptOpens = ptOpenMapper.selectByPtNo(ptInfoQr.getPtNo());

            Long times = Long.valueOf(0);
            System.out.println(times + "秒后推送");
            Timer timer = new Timer();

            System.out.println(ptOpens.get(0).getOpenId());
            System.out.println(ptInfoQr.getName());
            System.out.println(mallOrder.getMedOrderNo());
            System.out.println(mallOrder.getShippingStatus());
            if(mallOrder.getPlatform()==1){
                MyTimerTask myTimerTask = new MyTimerTask(ptOpens.get(0).getOpenId(), ptInfoQr.getName(), medOrder.getMedRecordNo(), mallOrder.getShippingStatus(), medRecordMapper, medOrderMapper, mallOrder.getMedOrderNo(),mallOrder.getPlatform());
                //t通过timer定时定频率调用myTimerTask的业务逻辑
                timer.schedule(myTimerTask, times);
            }else if(mallOrder.getPlatform()==2){
                MyTimerTask myTimerTask = new MyTimerTask(ptOpens.get(0).getSjOpenId(), ptInfoQr.getName(), medOrder.getMedRecordNo(), mallOrder.getShippingStatus(), medRecordMapper, medOrderMapper, mallOrder.getMedOrderNo(),mallOrder.getPlatform());
                //t通过timer定时定频率调用myTimerTask的业务逻辑
                timer.schedule(myTimerTask, times);
            }


            response.setStatus(0);
            response.setMsg("提交成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("提交失败");
        }

        return response;
    }


    public Response Binding(Long ptNo, String code) {
        ResponseHasData response = new ResponseHasData();
        try {
            String openId = GetOpenIdUtils.getOpenId(code);
            //String openId = "001";
            List<PtOpen> ptOpens = ptOpenMapper.selectByPtNo(ptNo);
            PtOpen ptOpen = ptOpens.get(0);
            DrOpenId drOpenId = drOpenIdMapper.selectByOpenId(openId);

            if (ptOpen.getDrNo() != null && !"".equals(ptOpen.getDrNo())) {
                ptOpen.setDrNo(drOpenId.getDrNo());
                ptOpenMapper.insertSelective(ptOpen);
            } else {
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
    public Response selectDiseaseMaster(String keyword1, String chtType, Integer page, Integer rows) {
        ResponseHasData response = new ResponseHasData();
        try {
            Page<?> pa = PageHelper.startPage(page, rows);
            List<DiseaseMaster> diseaseMasters = diseaseMasterMapper.selectByKeyword1(keyword1, chtType);
            //查询结果总数
            Long total = pa.getTotal();
            //创建分页条件
            PageInfo<DiseaseMaster> pageData = new PageInfo<DiseaseMaster>(diseaseMasters, total.intValue());

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

    @Override
    public Response phoneBinding(String phone, Long drNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            PtInfo ptInfo = ptInfoMapper.selectByPhone(phone);
            if (ptInfo == null) {
                response.setMsg("绑定失败");
                response.setStatus(1);
                response.setMsg("没查询到该手机号的患者");
                return response;
            }
            List<PtOpen> ptOpens = ptOpenMapper.selectByPtNo(ptInfo.getPtNo());
            if (ptOpens.size() == 0) {
                PtOpen ptOpen = new PtOpen();
                ptOpen.setPtNo(ptInfo.getPtNo());
                ptOpen.setDrNo(drNo);
                ptOpenMapper.insertSelective(ptOpen);
            } else {
                PtOpen ptOpen = ptOpens.get(0);
                ptOpen.setDrNo(drNo);
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
    public Response doctorUpdate(Long drNo, String drName, String phone, String idNo,
                                 String hospital, String chiefNo, Integer age, String practiceProfile,
                                 String signature, String title, String drTitleCert,
                                 String drPracticeRegCert, String consultingHour,
                                 Integer disableFlag, String code, String province, String city) {
        ResponseHasData response = new ResponseHasData();

        try {
            Sednsms sednsms = sednsmsMapper.selectByPhone(phone);
            if (!sednsms.getCode().equals(code)) {
                response.setMsg(phone + "手机验证码错误，注册失败-----");
                response.setStatus(1);
                return response;
            } else if (sednsms.getExpiryDate().getTime() < new Date().getTime()) {
                response.setMsg(phone + "手机验证码失效 ，注册失败-----");
                response.setStatus(1);
                return response;
            }

            CloudPassInfo info = cloudPassInfoMapper.selectByPhoneIdNoDrNo(drNo, phone, idNo);
            if (info != null) {
                response.setStatus(1);
                response.setMsg("手机号码或身份证号码重复");
                return response;
            }

            DrInfo drInfo = drInfoMapper.selectByPrimaryKey(drNo);

            CloudPassInfo cloudPassInfo = cloudPassInfoMapper.selectByPrimaryKey(drInfo.getCloudPassNo());
            cloudPassInfo.setName(drName);
            cloudPassInfo.setPhone(phone);
            cloudPassInfo.setPassword("123456");
            cloudPassInfo.setCreateTime(new Date());
            cloudPassInfo.setIdNo(idNo);
            cloudPassInfo.setDisableFlag(disableFlag);
            cloudPassInfoMapper.updateByPrimaryKeySelective(cloudPassInfo);

            drInfo.setAge(age);
            drInfo.setCreateTime(new Date());
            drInfo.setDisableFlag(disableFlag);
            drInfo.setProvince(province);
            drInfo.setHospital(hospital);
            drInfo.setPracticeProfile(practiceProfile);
            drInfo.setSignature(signature);
            drInfo.setTitle(title);
            drInfo.setCity(city);
            drInfo.setChiefNo(chiefNo);
            drInfo.setDrTitleCert(drTitleCert);
            drInfo.setDrPracticeRegCert(drPracticeRegCert);
            drInfo.setConsultingHour(consultingHour);
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

    @Override
    public Response selectPacket(Long drNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            DoctorPacketQr doctorPacketQr = new DoctorPacketQr();
            DoctorWallet doctorWallet = doctorWalletMapper.selectByDrNo(drNo);
            BigDecimal kittingAmount = doctorKitingMapper.selectAmountByDrNo(drNo);
            if(doctorWallet==null){
                DoctorWallet doctorWallet1 = new DoctorWallet();
                doctorWallet1.setAmount(new BigDecimal("0"));
                doctorWallet1.setDrNo(drNo);
                doctorWalletMapper.insertSelective(doctorWallet1);

                if(kittingAmount==null){
                    doctorPacketQr.setKittingAmount(kittingAmount);
                    doctorPacketQr.setPacketAmount(doctorWallet1.getAmount());
                    doctorPacketQr.setRightAmount(doctorWallet1.getAmount());
                }else{
                    doctorPacketQr.setKittingAmount(kittingAmount);
                    doctorPacketQr.setPacketAmount(doctorWallet1.getAmount());
                    doctorPacketQr.setRightAmount(doctorWallet1.getAmount().subtract(kittingAmount));
                }


            }else{
                if(kittingAmount==null){
                    doctorPacketQr.setKittingAmount(new BigDecimal("0"));
                    doctorPacketQr.setPacketAmount(doctorWallet.getAmount());
                    doctorPacketQr.setRightAmount(doctorWallet.getAmount());
                }else{
                    doctorPacketQr.setKittingAmount(kittingAmount);
                    doctorPacketQr.setPacketAmount(doctorWallet.getAmount());
                    doctorPacketQr.setRightAmount(doctorWallet.getAmount().subtract(kittingAmount));
                }
            }
            response.setData(doctorPacketQr);
            response.setStatus(0);
            response.setMsg("查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询失败");
        }

        return response;
    }

    @Override
    public Response selectMedRecord(Long medRecordNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            MallOrder mallOrder = mallOrderMapper.selectByMedRecordNo(medRecordNo);
            MedRecord medRecord = medRecordMapper.selectByPrimaryKey(medRecordNo.toString());
            PtInfoQr ptInfoQr = ptInfoMapper.selectByPtNo(mallOrder.getPtNo());
            if (ptInfoQr.getIdNo() != null && !ptInfoQr.getIdNo().equals("null") && !ptInfoQr.getIdNo().equals("")
            ) {
                int age = IdNOToAge(ptInfoQr.getIdNo());
                ptInfoQr.setAge(age);
            }
            MedRecordPtInfoQr medRecordPtInfoQr = new MedRecordPtInfoQr();

            MrPicture[] mrPictures = mrPictureMapper.selectByMedRecordNo(medRecordNo.toString());
            medRecordPtInfoQr.setMrPictures(mrPictures);
            medRecordPtInfoQr.setMedRecord(medRecord);
            medRecordPtInfoQr.setPtInfoQr(ptInfoQr);
            response.setMsg("查询成功");
            response.setData(medRecordPtInfoQr);
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("查询失败");
            response.setStatus(1);
        }

        return response;
    }

    @Override
    public Response updateDiseaseMaster(Long medRecordNo, String dNo, String plan) {
        ResponseHasData response = new ResponseHasData();
        try {
            MedRecord medRecord = medRecordMapper.selectByPrimaryKey(medRecordNo.toString());
            medRecord.setDiagContent(dNo.toString());
            medRecord.setPlan(plan);
            medRecordMapper.updateByPrimaryKeySelective(medRecord);

            MallOrder mallOrder = mallOrderMapper.selectByMedRecordNo(medRecordNo);
            List<Template> templates = templateMapper.selectByDrNoDNo(mallOrder.getDrNo(), dNo);

            response.setMsg("修改成功");
            response.setStatus(0);
            response.setData(templates);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("修改成功");
            response.setStatus(0);
        }
        return response;
    }

    @Override
    public Response selectDrInfo(Long drNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            DrInfoQr drInfoQr = drInfoMapper.selectByDrNo(drNo);
            response.setMsg("查询成功");
            response.setStatus(0);
            response.setData(drInfoQr);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("查询失败");
            response.setStatus(0);
        }
        return response;
    }

    @Override
    public Response selectPt(String name, String phone, Long drNo, Integer page, Integer rows) {
        ResponseHasData response = new ResponseHasData();
        try {
            Page<?> pa = PageHelper.startPage(page, rows);
            List<MallOrder> mallOrders = mallOrderMapper.selectByDrNoPtNamePhone(drNo, phone, name);
            List<PtInfoQr> ptInfoQrs = new ArrayList<PtInfoQr>();
            for (MallOrder mallOrder : mallOrders) {
                PtInfoQr ptInfoQr = ptInfoMapper.selectByPtNo(mallOrder.getPtNo());
                if (ptInfoQr.getIdNo() != null && !"".equals(ptInfoQr.getIdNo()) && !"null".equals(ptInfoQr.getIdNo())) {
                    ptInfoQr.setAge(IdNOToAge(ptInfoQr.getIdNo()));
                }

                Message[] msg = messageMapper.selectPtNewMessage(ptInfoQr.getPtNo(), Long.valueOf(drNo)); // ReadStatus=0
                if (msg.length == 0) {
                    ptInfoQr.setNewMessage(0);
                } else {
                    ptInfoQr.setNewMessage(msg.length);
                }

                ptInfoQrs.add(ptInfoQr);

            }

            //查询结果总数
            Long total = pa.getTotal();
            //创建分页条件
            PageInfo<PtInfoQr> pageData = new PageInfo<PtInfoQr>(ptInfoQrs, total.intValue());
            response.setMsg("查询成功");
            response.setStatus(0);
            response.setData(pageData);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("查询失败");
            response.setStatus(1);
        }
        return response;
    }

    @Override
    public Response selectPtRecordList(Long ptNo, Long drNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            List<MallOrder> mallOrders = mallOrderMapper.selectByPtNo1(ptNo, drNo);
            List<MedOrderQr> medOrderQrs = new ArrayList<MedOrderQr>();
            for (MallOrder mallOrder : mallOrders) {
                MedOrder medOrder = medOrderMapper.selectByPrimaryKey(mallOrder.getMedOrderNo());
                MedOrderQr medOrderQr = new MedOrderQr();
                medOrderQr.setOrderStatus(medOrder.getOrderStatus());
                medOrderQr.setMallNo(mallOrder.getMallNo());
                medOrderQr.setPayStatus(mallOrder.getPayStatus());
                medOrderQr.setMedOrderNo(mallOrder.getMedOrderNo());
                medOrderQr.setOrderTime(mallOrder.getOrderTime());
                medOrderQr.setMedRecordNo(Long.valueOf(medOrder.getMedRecordNo()));
                if (mallOrder.getPayStatus() == 0) {
                    medOrderQr.setShippingStatus(mallOrder.getShippingStatus());
                }
                medOrderQrs.add(medOrderQr);
            }
            response.setData(medOrderQrs);
            response.setMsg("查询成功");
            response.setStatus(0);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.setMsg("查询失败");
            response.setStatus(1);
        }
        return response;
    }

    @Override
    public Response count(String name, Long drNo, String startDate, String endDate, Long drugNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

            DrCountQr drCountQr = new DrCountQr();
            double sum = 0;
            BigDecimal amountSum = new BigDecimal("0");
            BigDecimal saveAmount = new BigDecimal("0");
            double saveSum = 0;
            BigDecimal addAmount = new BigDecimal("0");
            double addSum = 0;
            List<PcCountQr> pcCountQrs = new ArrayList<PcCountQr>();

            if (drugNo != null) {
                //西药处方
                List<PcCountQr> mallOrders1 = mallOrderMapper.selectByNamePhoneDrNoDateType2(name, drNo, startDate,endDate, drugNo);
                if (mallOrders1.size() != 0) {
                    for (PcCountQr pcCountQr : mallOrders1) {
                        pcCountQr.setDate(sdf.parse(startDate));
                        pcCountQrs.add(pcCountQr);
                    }

                }
                List<PcCountQr> pcCountQrs1 = countMapper.count2(null, drugNo, null, null, null, startDate,endDate, drNo);
                if (pcCountQrs1.size() != 0) {
                    addAmount = addAmount.add(pcCountQrs1.get(0).getAddAmount());
                    addSum += pcCountQrs1.get(0).getAddSum();
                    saveAmount = saveAmount.add(pcCountQrs1.get(0).getSaveAmount());
                    saveSum += pcCountQrs1.get(0).getSaveSum();
                    sum += pcCountQrs1.get(0).getSum();
                    amountSum = amountSum.add(pcCountQrs1.get(0).getAmountSum());
                }

                drCountQr.setAddAmount(addAmount);
                drCountQr.setAddSum(addSum);
                drCountQr.setSaveAmount(saveAmount);
                drCountQr.setSaveSum(saveSum);
                drCountQr.setSum(sum);
                drCountQr.setAmountSum(amountSum);

                drCountQr.setPcCountQrList(pcCountQrs);
            } else {
                //中药处方
                List<PcCountQr> mallOrders = mallOrderMapper.selectByNamePhoneDrNoDateType1(name, drNo, startDate, drugNo);
                if (mallOrders.size() != 0) {
                    for (PcCountQr pcCountQr : mallOrders) {
                        pcCountQr.setDate(sdf.parse(startDate));
                        pcCountQrs.add(pcCountQr);
                    }
                }
                List<PcCountQr> pcCountQrs2 = chiCountMapper.selectByTypeDrNameDate1(null, drugNo, null, null, startDate, null, drNo);
                if (pcCountQrs2.size() != 0) {
                    addAmount = addAmount.add(pcCountQrs2.get(0).getAddAmount());
                    addSum += pcCountQrs2.get(0).getAddSum();
                    saveAmount = saveAmount.add(pcCountQrs2.get(0).getSaveAmount());
                    saveSum += pcCountQrs2.get(0).getSaveSum();
                    sum += pcCountQrs2.get(0).getSum();
                    amountSum = amountSum.add(pcCountQrs2.get(0).getAmountSum());
                }


                //西药处方
                List<PcCountQr> mallOrders1 = mallOrderMapper.selectByNamePhoneDrNoDateType2(name, drNo, startDate, endDate, drugNo);
                if (mallOrders1.size() != 0) {
                    for (PcCountQr pcCountQr : mallOrders1) {
                        pcCountQr.setDate(sdf.parse(startDate));
                        pcCountQrs.add(pcCountQr);
                    }
                }
                List<PcCountQr> pcCountQrs1 = countMapper.count2(null, drugNo, null, null, null, startDate, endDate, drNo);
                if (pcCountQrs1.size() != 0) {
                    addAmount = addAmount.add(pcCountQrs1.get(0).getAddAmount());
                    addSum += pcCountQrs1.get(0).getAddSum();
                    saveAmount = saveAmount.add(pcCountQrs1.get(0).getSaveAmount());
                    saveSum += pcCountQrs1.get(0).getSaveSum();
                    sum += pcCountQrs1.get(0).getSum();
                    amountSum = amountSum.add(pcCountQrs1.get(0).getAmountSum());
                }

                drCountQr.setAddAmount(addAmount);
                drCountQr.setAddSum(addSum);
                drCountQr.setSaveAmount(saveAmount);
                drCountQr.setSaveSum(saveSum);
                drCountQr.setSum(sum);
                drCountQr.setAmountSum(amountSum);
                drCountQr.setPcCountQrList(pcCountQrs);

            }


            response.setStatus(0);
            response.setMsg("查询成功");
            response.setData(drCountQr);

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询失败");
        }
        return response;
    }

    @Override
    public Response selectTemplatesByNo(Long templateNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            BigDecimal sum = new BigDecimal("0");
            List<TemplateQr> templateQrs = templateMapper.selectByTemplateNo(templateNo);
            for (TemplateQr templateQr : templateQrs) {
                List<TemplateDrugSetQr> list = templateQr.getList();
                for (TemplateDrugSetQr templateDrugSetQr : list) {
                    templateDrugSetQr.setDose1(templateDrugSetQr.getDose());
                    templateDrugSetQr.setDoseUnit1(templateDrugSetQr.getDoseUnit());
                    DrugSet drugSet = drugSetMapper.selectByPrimaryKey(templateDrugSetQr.getDrugSetNo());
                    templateDrugSetQr.setDivisor(drugSet.getDivisor());
                    templateDrugSetQr.setDoseUnit(drugSet.getDoseUnit());
                    templateDrugSetQr.setDosege(drugSet.getDosege());
                    templateDrugSetQr.setDosegeUnit(drugSet.getDosegeUnit());
                    templateDrugSetQr.setDividend(drugSet.getDividend());
                    templateDrugSetQr.setSaleUnit(drugSet.getSaleUnit());
                    templateDrugSetQr.setCommonName(drugSet.getCommonName());
                    templateDrugSetQr.setSaleGenPrice(drugSet.getSaleGenPrice());
                    templateQr.setUsageNo(templateDrugSetQr.getUsageNo());
                    sum = sum.add(drugSet.getSaleGenPrice().multiply(new BigDecimal(templateDrugSetQr.getNumber().toString())));
                }
                sum = sum.setScale(2, BigDecimal.ROUND_DOWN);
                templateQr.setPrice(sum);
            }
            response.setData(templateQrs);
            response.setStatus(0);
            response.setMsg("查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询失败");
        }

        return response;
    }

    @Override
    public Response updatePassword(String phone, Long drNo, String oldPassword, String newPassword) {
        ResponseHasData response = new ResponseHasData();
        try {
            DrInfoQr drInfo = drInfoMapper.selectByDrNo(drNo);
            CloudPassInfo passInfo = cloudPassInfoMapper.selectByPrimaryKey(drInfo.getCloudPassNo());
            if (passInfo.getPassword().equals(oldPassword)) {
                passInfo.setPassword(newPassword);
                cloudPassInfoMapper.updateByPrimaryKeySelective(passInfo);
            } else {
                response.setStatus(1);
                response.setMsg("修改失败，原密码错误");
                return response;
            }
            response.setStatus(0);
            response.setMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(0);
            response.setMsg("修改失败");
        }
        return response;
    }

    public Response wxQRCode(Long drNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            if (drNo == null) {
                response.setStatus(1);
                response.setMsg("生成失败！！！");
                return response;
            } else {
                DrInfoQr drInfoQr = drInfoMapper.selectByDrNo(drNo);
                String json2 = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \""+drNo +","+ drInfoQr.getPlatform() +"\"}}}";
                String access_token = "";
                if(drInfoQr.getPlatform()==1){
                    access_token = WeiXinUtils.getAccessToken().getToken();
                }else if(drInfoQr.getPlatform()==2){
                    access_token = WeiXinUtils.getSjAccessToken().getToken();
                }
                String urlStr = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + access_token;
                URL url = new URL(urlStr);
                String resultStr = PostUtil.sendPost(url, "application/x-www-form-urlencoded", json2);
                JSONObject jsonObject = new JSONObject(resultStr);
                String ticket = jsonObject.optString("ticket");// errCode
                String expire_seconds = jsonObject.optString("expire_seconds");// errMsg
                String url_ = jsonObject.optString("url");// errMsg
                String errCode = jsonObject.optString("errcode");// errCode
                response.setData(url_);
                response.setMsg("生成医生二维码成功！！！");
                response.setStatus(0);
                return response;
            }
        } catch (Exception e) {
            response.setStatus(1);
            response.setMsg("生成失败！！！");
            e.printStackTrace();
            return response;
        }
    }


    public static int IdNOToAge(String IdNO) {
        int leh = IdNO.length();
        String dates = "";
        if (leh == 18) {

            dates = IdNO.substring(6, 10);
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year = df.format(new Date());
            int u = Integer.parseInt(year) - Integer.parseInt(dates);
            return u;
        } else {
            dates = IdNO.substring(6, 8);
            return Integer.parseInt(dates);
        }

    }


}
