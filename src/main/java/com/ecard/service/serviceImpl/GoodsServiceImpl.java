package com.ecard.service.serviceImpl;

import com.ecard.dao.*;
import com.ecard.entity.*;
import com.ecard.entity.QuestionOptions;
import com.ecard.entity.QuestionTemplate;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.queryResult.GoodsQr;
import com.ecard.pojo.queryResult.QuestionQr;
import com.ecard.pojo.queryResult.QuestionQrr;
import com.ecard.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private DrugSetMapper drugSetMapper;
    @Autowired
    private MallOrderMapper mallOrderMapper;
    @Autowired
    private QuestionOptionsMapper questionOptionsMapper;
    @Autowired
    private QuestionTemplateMapper questionTemplateMapper;
    @Autowired
    private DepartmentDrugsetMapper departmentDrugsetMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Response insertTemplate(List<QuestionQr> template) {
        ResponseHasData response = new ResponseHasData();
        try {
            Long goodsNo = template.get(0).getQuestionTemplate().getGoodsNo();
            Goods goods = goodsMapper.selectByPrimaryKey(goodsNo);
            goods.setGoodsStatus(1);
            goodsMapper.updateByPrimaryKeySelective(goods);
            Long templateNo = questionTemplateMapper.selectId();
            for (QuestionQr questionQr:template){
                QuestionTemplate questionTemplate = questionQr.getQuestionTemplate();
                questionTemplate.setTemplateNo(templateNo);
                questionTemplateMapper.insertSelective(questionTemplate);
                List<QuestionOptions> questionOptions = questionQr.getQuestionOptions();
                for(QuestionOptions questionOption:questionOptions){
                    questionOption.setTemplateNo(templateNo);
                    questionOptionsMapper.insertSelective(questionOption);
                }

            }
            response.setMsg("新增问卷成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("新增问卷失败");
            response.setStatus(1);
        }
        return response;
    }



    @Override
    public Response updateTemplate(List<QuestionQr> template) {
        ResponseHasData response = new ResponseHasData();
        try {
            if(template.get(0).getQuestionTemplate().getDisableFlag()==1){
                Long goodNo = template.get(0).getQuestionTemplate().getGoodsNo();
                Goods goods = goodsMapper.selectByPrimaryKey(goodNo);
                questionOptionsMapper.deleteByGoodNo(goodNo);
                questionTemplateMapper.deleteByGoodNo(goodNo);
                goods.setGoodsStatus(2);
                goodsMapper.updateByPrimaryKeySelective(goods);
            }else {
                Long goodNo = template.get(0).getQuestionTemplate().getGoodsNo();
                questionOptionsMapper.deleteByGoodNo(goodNo);
                questionTemplateMapper.deleteByGoodNo(goodNo);

                Long templateNo = questionTemplateMapper.selectId();
                for (QuestionQr questionQr:template){
                    QuestionTemplate questionTemplate = questionQr.getQuestionTemplate();
                    questionTemplate.setTemplateNo(templateNo);
                    questionTemplateMapper.insertSelective(questionTemplate);
                    List<QuestionOptions> questionOptions = questionQr.getQuestionOptions();
                    for(QuestionOptions questionOption:questionOptions){
                        questionOption.setTemplateNo(templateNo);
                        questionOptionsMapper.insertSelective(questionOption);
                    }

                }
            }

            response.setMsg("修改问卷成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("修改问卷失败");
            response.setStatus(1);
        }
        return response;
    }

    @Override
    public Response insert(GoodsQr goodsQr) {
        ResponseHasData response = new ResponseHasData();
        try {
            DrugSet drugSet = new DrugSet();
            Goods goods = new Goods();

            drugSet.setHisDrugCode(goodsQr.getHisDrugCode());
            drugSet.setCommonName(goodsQr.getCommonName());
            drugSet.setItemName(goodsQr.getItemName());
            drugSet.setManufactory(goodsQr.getManufactory());
            drugSet.setCategory(goodsQr.getCategory());
            drugSet.setEssentialDrug(goodsQr.getEssentialDrug());
            drugSet.setCtrlDrug(goodsQr.getCtrlDrug());
            drugSet.setHighAlert(goodsQr.getHighAlert());
            drugSet.setDividend(goodsQr.getDividend());
            drugSet.setSaleUnit(goodsQr.getSaleUnit());
            drugSet.setDivisor(goodsQr.getDivisor());
            drugSet.setDoseUnit(goodsQr.getDoseUnit());
            drugSet.setDosege(goodsQr.getDosege());
            drugSet.setDosegeUnit(goodsQr.getDosegeUnit());
            drugSet.setRemark(goodsQr.getRemark());
            drugSet.setKeyword1(goodsQr.getKeyword1());
            drugSet.setSaleGenPrice(goodsQr.getSaleGenPrice());
            drugSet.setApprovalNumber(goodsQr.getApprovalNumber());
            drugSet.setValidity(goodsQr.getValidity());
            drugSet.setApiNo(goodsQr.getApiNo());
            drugSet.setDisableFlag(goodsQr.getDisableFlag());
            drugSet.setForm(goodsQr.getForm());
            drugSet.setMaterial(goodsQr.getMaterial());
            drugSet.setVersion(goodsQr.getVersion());
            drugSet.setIsRegistration(goodsQr.getIsRegistration());
            drugSetMapper.insertSelective(drugSet);

            goods.setGoodsNo(drugSet.getDrugSetNo());
            goods.setGoodsName(drugSet.getCommonName());
            goods.setPrice(drugSet.getSaleGenPrice());
            goods.setDescribe(goodsQr.getDescribe());
            goods.setSubjective(goodsQr.getSubjective());
            goods.setDiagContent(goodsQr.getDiagContent());
            goods.setWayNo(goodsQr.getWayNo());
            goods.setUsageNo(goodsQr.getUsageNo());
            goods.setIsRecipe(goodsQr.getIsRecipe());
            goods.setIsUpload(goodsQr.getIsUpload());
            goods.setGoodsStatus(goodsQr.getGoodsStatus());
            goods.setStatus(goodsQr.getStatus());
            goodsMapper.insertSelective(goods);

            if(goodsQr.getDepartmentName()!=null) {
                for (String departmentName : goodsQr.getDepartmentName()) {
                    DepartmentDrugset departmentrDrugset = new DepartmentDrugset();
                    List<Department> departments = departmentMapper.selectByNameFlag(departmentName, 0);
                    departmentrDrugset.setDepartmentNo(departments.get(0).getDeptID());
                    departmentrDrugset.setDrugSetNo(drugSet.getDrugSetNo());
                    departmentDrugsetMapper.insertSelective(departmentrDrugset);
                }
            }

            response.setStatus(0);
            response.setMsg("添加商品成功");
            response.setData(goods);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("添加商品失败");
        }
        return response;
    }

    @Override
    public Response update(GoodsQr goodsQr) {
        ResponseHasData response = new ResponseHasData();
        try {
            DrugSet drugSet = new DrugSet();
            Goods goods = new Goods();

            drugSet.setDrugSetNo(goodsQr.getDrugSetNo());
            drugSet.setHisDrugCode(goodsQr.getHisDrugCode());
            drugSet.setCommonName(goodsQr.getCommonName());
            drugSet.setItemName(goodsQr.getItemName());
            drugSet.setManufactory(goodsQr.getManufactory());
            drugSet.setCategory(goodsQr.getCategory());
            drugSet.setEssentialDrug(goodsQr.getEssentialDrug());
            drugSet.setCtrlDrug(goodsQr.getCtrlDrug());
            drugSet.setHighAlert(goodsQr.getHighAlert());
            drugSet.setDividend(goodsQr.getDividend());
            drugSet.setSaleUnit(goodsQr.getSaleUnit());
            drugSet.setDivisor(goodsQr.getDivisor());
            drugSet.setDoseUnit(goodsQr.getDoseUnit());
            drugSet.setDosege(goodsQr.getDosege());
            drugSet.setDosegeUnit(goodsQr.getDosegeUnit());
            drugSet.setRemark(goodsQr.getRemark());
            drugSet.setKeyword1(goodsQr.getKeyword1());
            drugSet.setSaleGenPrice(goodsQr.getSaleGenPrice());
            drugSet.setApprovalNumber(goodsQr.getApprovalNumber());
            drugSet.setValidity(goodsQr.getValidity());
            drugSet.setApiNo(goodsQr.getApiNo());
            drugSet.setDisableFlag(goodsQr.getDisableFlag());
            drugSet.setVersion(goodsQr.getVersion());
            drugSet.setIsRegistration(goodsQr.getIsRegistration());
            drugSetMapper.updateByPrimaryKeySelective(drugSet);

            goods.setGoodsNo(drugSet.getDrugSetNo());
            goods.setGoodsName(drugSet.getCommonName());
            goods.setDescribe(goodsQr.getDescribe());
            goods.setSubjective(goodsQr.getSubjective());
            goods.setDiagContent(goodsQr.getDiagContent());
            goods.setWayNo(goodsQr.getWayNo());
            goods.setUsageNo(goodsQr.getUsageNo());
            goods.setGoodsStatus(goodsQr.getGoodsStatus());
            goods.setStatus(goodsQr.getStatus());
            goodsMapper.updateByPrimaryKeySelective(goods);


            departmentDrugsetMapper.deleteByDrugNo(drugSet.getDrugSetNo());
            if(goodsQr.getDepartmentName()!=null) {
                for (String departmentName : goodsQr.getDepartmentName()) {
                    DepartmentDrugset departmentrDrugset = new DepartmentDrugset();
                    List<Department> departments = departmentMapper.selectByNameFlag(departmentName, 0);
                    departmentrDrugset.setDepartmentNo(departments.get(0).getDeptID());
                    departmentrDrugset.setDrugSetNo(drugSet.getDrugSetNo());
                    departmentDrugsetMapper.insertSelective(departmentrDrugset);
                }
            }


            response.setStatus(0);
            response.setMsg("修改商品成功");
            response.setData(goods);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("修改商品失败");
        }
        return response;
    }

    @Override
    public Response selectQuestion(Long goodsNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            List<QuestionQr> questionQrs = new ArrayList<QuestionQr>();


            List<QuestionTemplate> questionTemplates = questionTemplateMapper.selectByGoodsNo(goodsNo);
            for (QuestionTemplate questionTemplate:questionTemplates){
                QuestionQr questionQr = new QuestionQr();
                questionQr.setQuestionTemplate(questionTemplate);
                List<QuestionOptions> questionOptions = questionOptionsMapper.selectByTemplateNoQuestionNo(questionTemplate.getTemplateNo(),questionTemplate.getQuestionNo());
                questionQr.setQuestionOptions(questionOptions);
                questionQrs.add(questionQr);
            }

            response.setStatus(0);
            response.setMsg("查询成功");
            response.setData(questionQrs);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询失败");
        }

        return response;
    }

}
