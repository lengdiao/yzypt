package com.ecard.service.serviceImpl;

import com.ecard.dao.*;
import com.ecard.entity.*;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.queryResult.DrugSetGoodsQr;
import com.ecard.pojo.queryResult.DrugSetGoodsQrs;
import com.ecard.service.DrugSetService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DrugSetServiceImpl implements DrugSetService {
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
    private DrugSetPictureMapper drugSetPictureMapper;
    @Autowired
    private PostageMapper postageMapper;
    @Autowired
    private DiseaseMasterMapper diseaseMasterMapper;
    @Autowired
    private DepartmentDrugsetMapper departmentDrugsetMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private ActivityMapper activityMapper;


    public Response insert(DrugSet drugSet) {
        ResponseHasData response = new ResponseHasData();
        try {
            drugSet.setVersion(1);
            drugSetMapper.insertSelective(drugSet);
            if(drugSet.getDepartmentName()!=null){
                for(String departmentName : drugSet.getDepartmentName()){
                    DepartmentDrugset departmentrDrugset = new DepartmentDrugset();
                    List<Department> departments = departmentMapper.selectByNameFlag(departmentName,0);
                    departmentrDrugset.setDepartmentNo(departments.get(0).getDeptID());
                    departmentrDrugset.setDrugSetNo(drugSet.getDrugSetNo());
                    departmentDrugsetMapper.insertSelective(departmentrDrugset);
                }
            }

            response.setStatus(0);
            response.setMsg("新增药品成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("新增药品失败");
        }
        return response;
    }


    public Response update(DrugSet drugSet) {
        ResponseHasData response = new ResponseHasData();
        try {
            if(drugSet.getDrugSetNo()==null){
                response.setStatus(1);
                response.setMsg("缺少drugSetNo参数");
                return response;
            }
            drugSetMapper.updateByPrimaryKeySelective(drugSet);

            departmentDrugsetMapper.deleteByDrugNo(drugSet.getDrugSetNo());
            if(drugSet.getDepartmentName()!=null){
                for(String departmentName : drugSet.getDepartmentName()) {
                    DepartmentDrugset departmentrDrugset = new DepartmentDrugset();
                    List<Department> departments = departmentMapper.selectByNameFlag(departmentName, 0);
                    departmentrDrugset.setDepartmentNo(departments.get(0).getDeptID());
                    departmentrDrugset.setDrugSetNo(drugSet.getDrugSetNo());
                    departmentDrugsetMapper.insertSelective(departmentrDrugset);
                }
            }

            response.setStatus(0);
            response.setMsg("更新药品成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("更新药品失败");
        }
        return response;
    }

    @Override
    public Response select(String departmentName, Integer category, String keyword1, Integer version, Integer disableFlag, Long drugNo, Integer page, Integer rows) {
        ResponseHasData response = new ResponseHasData();
        try {
            List<DrugSetGoodsQr> drugSetGoodsQrs = new ArrayList<DrugSetGoodsQr>();

            Page<?> pa =  PageHelper.startPage(page, rows);

            List<DrugSet> piq = drugSetMapper.selectByCaKeyDis(departmentName,category,keyword1,disableFlag,version,drugNo);
            for(DrugSet drugSet:piq){
                DrugSetGoodsQr drugSetGoodsQr = new DrugSetGoodsQr();
                if (drugSet.getVersion()==2||drugSet.getVersion()==3){
                    Goods goods = goodsMapper.selectByPrimaryKey(drugSet.getDrugSetNo());
                    if(goods.getDescribe()!=null&&!"".equals(goods.getDescribe())){
                        drugSet.setDescribe(goods.getDescribe());
                    }
                    //主病、症候中文
                    if(goods.getDiagContent()!=null&&!"".equals(goods.getDiagContent())){
                        String diagContentName = goods.getDiagContent();
                        String[] diag = diagContentName.split(",");
                        if(!diagContentName.equals("null-null")&&!diagContentName.equals("null")) {
                            if (drugSet.getCategory().equals("1")) {
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
                            goods.setDiagContentName(diagContentName);
                        }

                    }
                    //商品图片
                    List<DrugSetPicture> drugSetPictures = drugSetPictureMapper.selectByGoodsNo(drugSet.getDrugSetNo());
                    if(drugSetPictures.size()!=0){
                        double d = Math.random();
                        int q = (int)(d*100);
                        drugSet.setTitleImg(drugSetPictures.get(0).getTitleImg());
                        String img[] = new String[drugSetPictures.size()];
                        for(int i=0;i<drugSetPictures.size();i++){
                            img[i] = drugSetPictures.get(i).getDetailsImg();
                        }
                        drugSet.setDetailsImg(img);
                    }
                    drugSetGoodsQr.setGoods(goods);
                }else if(drugSet.getVersion()==4){
                    Activity activities = activityMapper.selectByGoodsNoFlag(drugSet.getDrugSetNo(),null);
                    drugSetGoodsQr.setActivity(activities);
                    drugSet.setIsUpload(activities.getIsUpload());
                    drugSet.setIsRecipe(activities.getIsRecipe());
                    List<DrugSetPicture> drugSetPictures = drugSetPictureMapper.selectByGoodsNo(drugSet.getDrugSetNo());
                    if(drugSetPictures.size()!=0){
                        double d = Math.random();
                        int q = (int)(d*100);
                        drugSet.setTitleImg(drugSetPictures.get(0).getTitleImg());
                        String img[] = new String[drugSetPictures.size()];
                        for(int i=0;i<drugSetPictures.size();i++){
                            img[i] = drugSetPictures.get(i).getDetailsImg();
                        }
                        drugSet.setDetailsImg(img);
                    }
                }
                List<String> departmentNames = departmentDrugsetMapper.selectByDrugNo(drugSet.getDrugSetNo());
                if(departmentNames.size()!=0){
                    drugSet.setDepartmentName(departmentNames);
                }
                drugSetGoodsQr.setDrugSet(drugSet);
                drugSetGoodsQrs.add(drugSetGoodsQr);
            }
            //查询结果总数
            Long total = pa.getTotal();
            //创建分页条件
            PageInfo<DrugSetGoodsQr> pageData = new PageInfo<DrugSetGoodsQr>(drugSetGoodsQrs,total.intValue());

            response.setStatus(0);
            response.setMsg("查询药品成功");
            response.setData(pageData);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询药品失败");
        }
        return response;
    }

    @Override
    public Response selectInfo(Long drugSetNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            DrugSetGoodsQrs drugSetGoodsQrs = new DrugSetGoodsQrs();
            DrugSet drugSet = drugSetMapper.selectByPrimaryKey(drugSetNo);
            if (drugSet.getVersion()==1){
                List<String> departmentNames = departmentDrugsetMapper.selectByDrugNo(drugSet.getDrugSetNo());
                drugSet.setDepartmentName(departmentNames);
                drugSetGoodsQrs.setDrugSet(drugSet);
            }else if(drugSet.getVersion()==2||drugSet.getVersion()==3){
                Goods goods = goodsMapper.selectByPrimaryKey(drugSetNo);
                List<DrugSetPicture> drugSetPictures = drugSetPictureMapper.selectByGoodsNo(drugSet.getDrugSetNo());
                if(drugSetPictures.size()!=0){
                    double d = Math.random();
                    int q = (int)(d*100);
                    drugSet.setTitleImg(drugSetPictures.get(0).getTitleImg());
                    String img[] = new String[drugSetPictures.size()];
                    for(int i=0;i<drugSetPictures.size();i++){
                        img[i] = drugSetPictures.get(i).getDetailsImg();
                    }
                    drugSet.setDetailsImg(img);
                }
                List<String> departmentNames = departmentDrugsetMapper.selectByDrugNo(drugSet.getDrugSetNo());
                drugSet.setDepartmentName(departmentNames);
                drugSetGoodsQrs.setDrugSet(drugSet);
                drugSetGoodsQrs.setGoods(goods);
                List<QuestionTemplate> questionTemplate = questionTemplateMapper.selectByGoodsNo(drugSetNo);
                drugSetGoodsQrs.setQuestionTemplates(questionTemplate);
                if(questionTemplate.size()!=0){
                    List<QuestionOptions> questionOptions = questionOptionsMapper.selectByTemplateNo(questionTemplate.get(0).getTemplateNo());
                    drugSetGoodsQrs.setQuestionOptions(questionOptions);
                }
            }else if(drugSet.getVersion()==4){
                List<DrugSetPicture> drugSetPictures = drugSetPictureMapper.selectByGoodsNo(drugSet.getDrugSetNo());
                if(drugSetPictures.size()!=0){
                    double d = Math.random();
                    int q = (int)(d*100);
                    drugSet.setTitleImg(drugSetPictures.get(0).getTitleImg());
                    String img[] = new String[drugSetPictures.size()];
                    for(int i=0;i<drugSetPictures.size();i++){
                        img[i] = drugSetPictures.get(i).getDetailsImg();
                    }
                    drugSet.setDetailsImg(img);
                }
                List<QuestionTemplate> questionTemplate = questionTemplateMapper.selectByGoodsNo(drugSetNo);
                drugSetGoodsQrs.setQuestionTemplates(questionTemplate);
                if(questionTemplate.size()!=0){
                    List<QuestionOptions> questionOptions = questionOptionsMapper.selectByTemplateNo(questionTemplate.get(0).getTemplateNo());
                    drugSetGoodsQrs.setQuestionOptions(questionOptions);
                }
                drugSetGoodsQrs.setDrugSet(drugSet);
                Activity activities = activityMapper.selectByGoodsNoFlag(drugSet.getDrugSetNo(),null);
                drugSetGoodsQrs.setActivity(activities);
            }
            response.setStatus(0);
            response.setMsg("查询药品详情成功");
            response.setData(drugSetGoodsQrs);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询药品失败");
        }
        return response;
    }


    public Response selectPostage(Long drugSetNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            List<Postage> postages = postageMapper.selectByNo(drugSetNo);
            if(postages.size()==0){
                postages = postageMapper.selectByNo(Long.valueOf("0"));
            }
            response.setStatus(0);
            response.setMsg("查询成功");
            response.setData(postages);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询失败");
        }
        return response;
    }

    @Override
    public Response selectZZXDList(Long drugNo, Integer page, Integer rows) {
        ResponseHasData response = new ResponseHasData();
        try {
            List<DrugSetGoodsQr> drugSetGoodsQrs = new ArrayList<DrugSetGoodsQr>();

            Page<?> pa =  PageHelper.startPage(page, rows);

            List<DrugSet> piq = drugSetMapper.selectByCaKeyDis1(drugNo);
            for(DrugSet drugSet:piq){
                DrugSetGoodsQr drugSetGoodsQr = new DrugSetGoodsQr();
                if (drugSet.getVersion()==2||drugSet.getVersion()==3){
                    Goods goods = goodsMapper.selectByPrimaryKey(drugSet.getDrugSetNo());
                    if(goods.getDescribe()!=null&&!"".equals(goods.getDescribe())){
                        drugSet.setDescribe(goods.getDescribe());
                    }
                    //主病、症候中文
                    if(goods.getDiagContent()!=null&&!"".equals(goods.getDiagContent())){
                        String diagContentName = goods.getDiagContent();
                        String[] diag = diagContentName.split(",");
                        if(!diagContentName.equals("null-null")&&!diagContentName.equals("null")) {
                            if (drugSet.getCategory().equals("1")) {
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
                            goods.setDiagContentName(diagContentName);
                        }

                    }
                    //商品图片
                    List<DrugSetPicture> drugSetPictures = drugSetPictureMapper.selectByGoodsNo(drugSet.getDrugSetNo());
                    if(drugSetPictures.size()!=0){
                        double d = Math.random();
                        int q = (int)(d*100);
                        drugSet.setTitleImg(drugSetPictures.get(0).getTitleImg());
                        String img[] = new String[drugSetPictures.size()];
                        for(int i=0;i<drugSetPictures.size();i++){
                            img[i] = drugSetPictures.get(i).getDetailsImg();
                        }
                        drugSet.setDetailsImg(img);
                    }
                    drugSetGoodsQr.setGoods(goods);
                }else if(drugSet.getVersion()==4){
                    Activity activities = activityMapper.selectByGoodsNoFlag(drugSet.getDrugSetNo(),null);
                    drugSetGoodsQr.setActivity(activities);
                    drugSet.setIsUpload(activities.getIsUpload());
                    drugSet.setIsRecipe(activities.getIsRecipe());
                    List<DrugSetPicture> drugSetPictures = drugSetPictureMapper.selectByGoodsNo(drugSet.getDrugSetNo());
                    if(drugSetPictures.size()!=0){
                        double d = Math.random();
                        int q = (int)(d*100);
                        drugSet.setTitleImg(drugSetPictures.get(0).getTitleImg());
                        String img[] = new String[drugSetPictures.size()];
                        for(int i=0;i<drugSetPictures.size();i++){
                            img[i] = drugSetPictures.get(i).getDetailsImg();
                        }
                        drugSet.setDetailsImg(img);
                    }
                }
                List<String> departmentNames = departmentDrugsetMapper.selectByDrugNo(drugSet.getDrugSetNo());
                if(departmentNames.size()!=0){
                    drugSet.setDepartmentName(departmentNames);
                }
                drugSetGoodsQr.setDrugSet(drugSet);
                drugSetGoodsQrs.add(drugSetGoodsQr);
            }
            //查询结果总数
            Long total = pa.getTotal();
            //创建分页条件
            PageInfo<DrugSetGoodsQr> pageData = new PageInfo<DrugSetGoodsQr>(drugSetGoodsQrs,total.intValue());

            response.setStatus(0);
            response.setMsg("查询药品成功");
            response.setData(pageData);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询药品失败");
        }
        return response;
    }
}
