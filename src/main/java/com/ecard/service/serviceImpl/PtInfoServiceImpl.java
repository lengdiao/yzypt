package com.ecard.service.serviceImpl;

import com.ecard.dao.*;
import com.ecard.entity.*;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.queryResult.DrInfoQr;
import com.ecard.pojo.queryResult.MallOrderQr;
import com.ecard.pojo.queryResult.MedItemQr;
import com.ecard.pojo.queryResult.PtInfoQr;
import com.ecard.service.PtInfoService;
import com.ecard.utils.UploadUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PtInfoServiceImpl implements PtInfoService {
    @Autowired
    private PtOpenMapper ptOpenMapper;
    @Autowired
    private PtInfoMapper ptInfoMapper;
    @Autowired
    private MedRecordMapper medRecordMapper;
    @Autowired
    private MallOrderMapper mallOrderMapper;
    @Autowired
    private MedOrderMapper medOrderMapper;
    @Autowired
    private MedItemMapper medItemMapper;
    @Autowired
    private CloudPassInfoMapper cloudPassInfoMapper;
    @Autowired
    private DrInfoMapper drInfoMapper;

    String openId = "001";


    @Override
    public Response start(Long drNo) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
        //String openId = user.getOpenId();

        ResponseHasData response = new ResponseHasData();
        try {
            PtOpen ptOpen = ptOpenMapper.findByPtOpenId(openId);
            MedRecord medRecord = new MedRecord();
            medRecord.setDrNo(drNo);
            medRecord.setCreateTime(new Date());
            medRecord.setPtNo(ptOpen.getPtNo());
            medRecord.setVisitDate(new Date());
            medRecord.setMedRecordStatus(1);
            Long medRecordNo = medRecordMapper.selectId();
            medRecord.setMedRecordNo(medRecordNo);
            medRecordMapper.insertSelective(medRecord);

            response.setMsg("返回病例编号");
            response.setStatus(0);
            response.setData(medRecord.getMedRecordNo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("发起复诊错误");
        }

        return response;
    }

    @Override
    public Response insertMedOrder(String medRecordNo, Long drNo, String diagContent,
                                   String subjective, String objective, String plan) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
        //String openId = user.getOpenId();

        ResponseHasData response = new ResponseHasData();
        try {
            PtOpen ptOpen = ptOpenMapper.findByPtOpenId(openId);
            String phone = ptOpenMapper.getPhone(openId);

            MedRecord medRecord = medRecordMapper.selectByPrimaryKey(medRecordNo);
            medRecord.setDrNo(drNo);
            medRecord.setCreateTime(new Date());
            medRecord.setPtNo(ptOpen.getPtNo());
            medRecord.setDiagContent(diagContent);
            medRecord.setSubjective(subjective);
            medRecord.setObjective(objective);
            medRecord.setPlan(plan);
            medRecord.setVisitDate(new Date());
            medRecord.setMedRecordStatus(1);
            medRecordMapper.updateByPrimaryKeySelective(medRecord);

            MedOrder medOrder = new MedOrder();
            medOrder.setCreateTime(new Date());
            medOrder.setMedRecordNo(medRecordNo);
            medOrder.setOrderStatus(0);
            Long medOrderNo = medOrderMapper.selectId();
            medOrder.setOrderNo(medOrderNo);
            medOrderMapper.insertSelective(medOrder);

            MallOrder mallOrder = new MallOrder();
            mallOrder.setDrNo(drNo);
            mallOrder.setMedOrderNo(medOrder.getOrderNo());
            mallOrder.setPtNo(ptOpen.getPtNo());
            mallOrder.setCreateTime(new Date());
            mallOrder.setMallNo(medOrder.getOrderNo());
            mallOrder.setOrderStatus(1);
            mallOrder.setOrderTime(new Date());
            mallOrder.setPhone(phone);
            Long mallOrderNo = mallOrderMapper.selectId();
            mallOrder.setMallNo(mallOrderNo);
            mallOrderMapper.insertSelective(mallOrder);

            response.setStatus(0);
            response.setMsg("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("保存失败");
        }

        return response;
    }


    @Override
    public Response addImg(String img, String name, Long mallNo) {
        ResponseHasData response = new ResponseHasData();
        System.out.println(img);
        try {
            UploadUtil.generateImage(img,"webapps/yzypt/WEB-INF/classes/static/images/"+mallNo+"/"+mallNo+name+".jpg");
            response.setMsg("修改图片成功");
            response.setStatus(0);
            response.setData("http://www.yizhenyun.com.cn/yzypt/images/"+mallNo+"/"+mallNo+name+".jpg");

            MallOrder mallOrder = mallOrderMapper.selectByPrimaryKey(mallNo);
            MedOrder medOrder = medOrderMapper.selectByPrimaryKey(mallOrder.getMedOrderNo());
            MedRecord medRecord = medRecordMapper.selectByPrimaryKey(medOrder.getMedRecordNo());
            if(medRecord.getObjective()==null||"".equals(medRecord.getObjective())){
                medRecord.setObjective(response.getData().toString());
                medRecordMapper.updateByPrimaryKeySelective(medRecord);
            }else {
                medRecord.setObjective(medRecord.getObjective()+"|"+response.getData().toString());
                medRecordMapper.updateByPrimaryKeySelective(medRecord);
            }
        } catch (Exception e) {
            response.setMsg("保存图片失败");
            response.setStatus(1);
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response select() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
        //String openId = user.getOpenId();

        ResponseHasData response = new ResponseHasData();
        try {
            PtOpen ptOpen = ptOpenMapper.findByPtOpenId(openId);

            List<MallOrderQr> mallOrderQrs = new ArrayList<MallOrderQr>();

            List<MallOrder> mallOrders = mallOrderMapper.selectByPtNoAndPayStatus(ptOpen.getPtNo());
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
                    mallOrderQrs.add(mallOrderQr);
                }
            }
            response.setStatus(0);
            response.setMsg("查询成功");
            response.setData(mallOrderQrs);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询失败");
        }
        return response;
    }

    @Override
    public Response register(String name, String sex, String idNo, String height, String weight, String phone) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
        //String openId = user.getOpenId();

        ResponseHasData response = new ResponseHasData();
        try {
            CloudPassInfo passInfo = cloudPassInfoMapper.selectByIdNo(idNo);
            if(passInfo!=null){
                response.setStatus(1);
                response.setMsg("重复注册");
                return response;
            }
            CloudPassInfo cloudPassInfo = new CloudPassInfo();
            cloudPassInfo.setIdNo(idNo);
            cloudPassInfo.setCreateTime(new Date());
            cloudPassInfo.setDisableFlag(0);
            cloudPassInfo.setName(name);
            cloudPassInfo.setPhone(phone);
            cloudPassInfoMapper.insertSelective(cloudPassInfo);
            PtInfo ptInfo = new PtInfo();
            ptInfo.setCloudPassNo(cloudPassInfo.getCloudPassNo());
            ptInfo.setCreateTime(new Date());
            ptInfo.setDisableFlag(0);
            ptInfo.setHeight(Double.valueOf(height));
            ptInfo.setWeight(Double.valueOf(weight));
            ptInfo.setSex(sex);
            ptInfo.setBirthDay(new SimpleDateFormat("yyyy-MM-dd").parse(getBirthday(idNo)));
            ptInfoMapper.insertSelective(ptInfo);
            PtOpen ptOpen = new PtOpen();
            ptOpen.setOpenId(openId);
            ptOpen.setPtNo(ptInfo.getPtNo());
            ptOpenMapper.insertSelective(ptOpen);

            response.setStatus(0);
            response.setMsg("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("保存失败");
        }
        return response;
    }

    @Override
    public Response updatePtInfo(Long ptNo, String name, String sex, String idNo, String height, String weight, String phone) {
        ResponseHasData response = new ResponseHasData();

        try {
            PtInfo ptInfo = ptInfoMapper.selectByPrimaryKey(ptNo);
            ptInfo.setHeight(Double.valueOf(height));
            ptInfo.setWeight(Double.valueOf(weight));
            ptInfo.setSex(sex);
            ptInfo.setBirthDay(new Date(getBirthday(idNo)));
            ptInfo.setUpdateTime(new Date());
            ptInfoMapper.updateByPrimaryKeySelective(ptInfo);

            CloudPassInfo cloudPassInfo = cloudPassInfoMapper.selectByPrimaryKey(ptInfo.getCloudPassNo());
            cloudPassInfo.setIdNo(idNo);
            cloudPassInfo.setDisableFlag(0);
            cloudPassInfo.setName(name);
            cloudPassInfo.setPhone(phone);
            cloudPassInfo.setUpdateTime(new Date());
            cloudPassInfoMapper.updateByPrimaryKeySelective(cloudPassInfo);

            response.setStatus(0);
            response.setMsg("保存成功");
        } catch (NumberFormatException e) {
            response.setStatus(1);
            response.setMsg("保存失败");
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public Response selectPtInfo(String name, String idNo, Integer page, Integer rows) {
        ResponseHasData response = new ResponseHasData();

        try {
            Page<?> pa =  PageHelper.startPage(page, rows);
            List<PtInfoQr> piq =
                    ptInfoMapper.selectPtInfo(name,idNo);
            //查询结果总数
            Long total = pa.getTotal();
            //创建分页条件
            PageInfo<PtInfoQr> pageData = new PageInfo<PtInfoQr>(piq,total.intValue());

            response.setData(pageData);
            response.setStatus(0);
            response.setMsg("查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询错误");
        }
        return response;
    }

    @Override
    public Response selectDrList() {
        ResponseHasData response = new ResponseHasData();
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            HttpSession session = request.getSession();
            WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
            //String openId = user.getOpenId();

            List<PtOpen> ptOpens = ptOpenMapper.selectBtOpenId(openId);
            List<DrInfoQr> drInfoQrs = new ArrayList<DrInfoQr>();
            if(ptOpens.size()!=0){
                for(PtOpen ptOpen : ptOpens){
                    DrInfoQr drInfoQr = drInfoMapper.selectByDrNo(ptOpen.getDrNo());
                    drInfoQrs.add(drInfoQr);
                }
            }
            response.setData(drInfoQrs);
            response.setMsg("查询成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("查询失败");
            response.setStatus(1);
        }
        return response;
    }

    public String getBirthday(String idNo){
        String birthday = null;
        if(idNo.length()==15){
            birthday = "19"+idNo.substring(6,8)+"-"+idNo.substring(8,10)+"-"+idNo.substring(10,12);
        }else if(idNo.length()==18){
            birthday =idNo.substring(6,10)+"-"+idNo.substring(10,12)+"-"+idNo.substring(12,14);
        }
        return birthday;
    }


}
