package com.ecard.service.serviceImpl;

import com.ecard.Constants;
import com.ecard.dao.*;
import com.ecard.entity.*;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.queryResult.*;
import com.ecard.service.PtInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
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
    @Autowired
    private SednsmsMapper sednsmsMapper;
    @Autowired
    private TemporaryPtOpenMapper temporaryPtOpenMapper;
    @Autowired
    private ReceiptAddressMapper receiptAddressMapper;
    @Autowired
    private MrPictureMapper mrPictureMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private DrugSetPictureMapper drugSetPictureMapper;
    @Autowired
    private DrugSetMapper drugSetMapper;
    @Autowired
    private PostageMapper postageMapper;
    @Autowired
    private DiseaseMasterMapper diseaseMasterMapper;
    @Autowired
    private MallAddressMapper mallAddressMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private RedpacketRecordMapper redpacketRecordMapper;
    @Autowired
    private RedpacketRuleMapper redpacketRuleMapper;

    String openId = "oLkiUwbC5SFlF0oyN5dcs_-Ujz8s";
    private BigDecimal amount;
    private String openId1;


    @Override
    public Response start(Long drNo) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
        //String openId = user.getOpenId();

        ResponseHasData response = new ResponseHasData();
        try {
            List<PtOpen> ptOpen = ptOpenMapper.selectBtOpenId(openId);
            MedRecord medRecord = new MedRecord();
            medRecord.setDrNo(drNo);
            medRecord.setCreateTime(new Date());
            medRecord.setPtNo(ptOpen.get(0).getPtNo());
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
            List<PtOpen> ptOpen = ptOpenMapper.selectBtOpenId(openId);
            List<MallOrder> mallOrder1 = mallOrderMapper.selectByPtNoAndPayStatus1(ptOpen.get(0).getPtNo(),0);

            MedRecord medRecord = medRecordMapper.selectByPrimaryKey(medRecordNo);
            medRecord.setDrNo(drNo);
            medRecord.setCreateTime(new Date());
            medRecord.setPtNo(ptOpen.get(0).getPtNo());
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
            medOrder.setAddStatus(mallOrder1.size()>0?2:1);
            medOrderMapper.insertSelective(medOrder);

            MallOrder mallOrder = new MallOrder();
            mallOrder.setDrNo(drNo);
            mallOrder.setMedOrderNo(medOrder.getOrderNo());
            mallOrder.setPtNo(ptOpen.get(0).getPtNo());
            mallOrder.setCreateTime(new Date());
            mallOrder.setMallNo(medOrder.getOrderNo());
            mallOrder.setOrderStatus(1);
            mallOrder.setOrderTime(new Date());
            Long mallOrderNo = mallOrderMapper.selectId();
            mallOrder.setMallNo(mallOrderNo);
            mallOrder.setVersion(2);
            mallOrderMapper.insertSelective(mallOrder);

            if(mallOrder.getVersion()==2){
                List<PtOpen> ptOpenss = ptOpenMapper.selectByPtNo(mallOrder.getPtNo());
                PtOpen ptOpen1 = ptOpenMapper.selectByDrNoAndOpenId(mallOrder.getDrNo(),ptOpen.get(0).getOpenId());
                if(ptOpen1==null){
                    PtOpen ptOpen2 = new PtOpen();
                    ptOpen2.setDrNo(mallOrder.getDrNo());
                    ptOpen2.setPtNo(mallOrder.getPtNo());
                    ptOpen2.setOpenId(ptOpenss.get(0).getOpenId());
                    ptOpenMapper.insertSelective(ptOpen2);
                }
            }

            response.setStatus(0);
            response.setMsg("保存成功");
            response.setData("{\"medRecordNo\":"+medRecord.getMedRecordNo()+"\"mallNo\":"+mallOrder.getMallNo()+"}");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("保存失败");
        }

        return response;
    }



    @Override
    public Response register(String name, String sex, String idNo, String height, String weight, String phone, String code) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
        //String openId = user.getOpenId();

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

            List<PtOpen> open = ptOpenMapper.selectBtOpenId(openId);
            if(open.size()!=0){
                PtInfoQr ptInfo = ptInfoMapper.selectByPtNo(open.get(0).getPtNo());
                if("".equals(ptInfo.getPhone())||ptInfo.getPhone()==null){
                    //需要补全信息
                    CloudPassInfo cloudPassInfo = cloudPassInfoMapper.selectByPrimaryKey(ptInfo.getCloudPassNo());
                    cloudPassInfo.setIdNo(idNo);
                    cloudPassInfo.setCreateTime(new Date());
                    cloudPassInfo.setDisableFlag(0);
                    cloudPassInfo.setName(name);
                    cloudPassInfo.setPhone(phone);
                    cloudPassInfoMapper.updateByPrimaryKeySelective(cloudPassInfo);
                    PtInfo ptInfo1 = ptInfoMapper.selectByPrimaryKey(ptInfo.getPtNo());
                    ptInfo1.setCloudPassNo(cloudPassInfo.getCloudPassNo());
                    ptInfo1.setCreateTime(new Date());
                    ptInfo1.setDisableFlag(0);
                    ptInfo1.setHeight(Double.valueOf(height));
                    ptInfo1.setWeight(Double.valueOf(weight));
                    ptInfo1.setSex(sex);
                    ptInfo1.setBirthDay(new SimpleDateFormat("yyyy-MM-dd").parse(getBirthday(idNo)));
                    ptInfoMapper.updateByPrimaryKeySelective(ptInfo1);
                }
            }else{
                //注册
                CloudPassInfo cloudPassInfo = new CloudPassInfo();
                cloudPassInfo.setIdNo(idNo);
                cloudPassInfo.setCreateTime(new Date());
                cloudPassInfo.setDisableFlag(0);
                cloudPassInfo.setName(name);
                cloudPassInfo.setPhone(phone);
                cloudPassInfoMapper.insertSelective(cloudPassInfo);
                PtInfo ptInfo1 = new PtInfo();
                ptInfo1.setCloudPassNo(cloudPassInfo.getCloudPassNo());
                ptInfo1.setCreateTime(new Date());
                ptInfo1.setDisableFlag(0);
                ptInfo1.setHeight(Double.valueOf(height));
                ptInfo1.setWeight(Double.valueOf(weight));
                ptInfo1.setSex(sex);
                ptInfo1.setBirthDay(new SimpleDateFormat("yyyy-MM-dd").parse(getBirthday(idNo)));
                ptInfoMapper.insertSelective(ptInfo1);
                PtOpen ptOpen = new PtOpen();
                ptOpen.setOpenId(openId);
                ptOpen.setPtNo(ptInfo1.getPtNo());
                ptOpenMapper.insertSelective(ptOpen);
            }


            response.setStatus(0);
            response.setMsg("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("保存失败");
        }
        return response;
    }


    public Response updatePtInfo(Long ptNo, String name, String sex, String idNo, String height, String weight, String phone) {
        ResponseHasData response = new ResponseHasData();

        try {
            CloudPassInfo info = cloudPassInfoMapper.selectByPhoneIdNoPtNo(ptNo,phone,idNo);
            if(info!=null){
                response.setStatus(1);
                response.setMsg("手机号码或身份证号码重复");
                return response;
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            PtInfo ptInfo = ptInfoMapper.selectByPrimaryKey(ptNo);
            ptInfo.setHeight(Double.valueOf(height));
            ptInfo.setWeight(Double.valueOf(weight));
            ptInfo.setSex(sex);
            ptInfo.setBirthDay(formatter.parse(getBirthday(idNo)));
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
        } catch (Exception e) {
            response.setStatus(1);
            response.setMsg("保存失败");
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public Response binging(String drNo, String openId) {
        ResponseHasData response = new ResponseHasData();
        List<PtOpen> ptOpen = ptOpenMapper.selectBtOpenId(openId);
        if (ptOpen.size()!=0) {
            PtOpen ptOpen1 = new PtOpen();
            ptOpen1.setPtNo(ptOpen.get(0).getPtNo());
            if (drNo == null || drNo.equals("")) {
            } else {
                ptOpen1.setDrNo(Long.parseLong(drNo));
            }
            ptOpen1.setOpenId(openId);
            PtOpen ptOpen2=ptOpenMapper.selectByDrNoAndOpenId(Long.parseLong(drNo),openId);
            if(ptOpen2==null||ptOpen2.equals("")) {
                ptOpenMapper.insertSelective(ptOpen1);}
            response.setStatus(Constants.STATUS_SUCCESS);
            response.setMsg("此手机已有绑定关系，直接跳转");
            return response;
        }
        CloudPassInfo cloudInfo = null;
        try {
            cloudInfo = new CloudPassInfo();
            cloudInfo.setCreateTime(new Date());
            cloudInfo.setCreateUser(Constants.SYSTEM_USER);
            cloudPassInfoMapper.insertSelective(cloudInfo);
        } catch (Exception e) {
            System.out.println("CloudPassInfo插入失败");
            response.setStatus(Constants.STATUS_FAIL);
            response.setMsg("CloudPassInfo插入失败");
            return response;
        }
        PtInfo ptInfo = null;
        try {
            ptInfo = new PtInfo();
            ptInfo.setCloudPassNo(cloudInfo.getCloudPassNo());
            ptInfo.setCreateTime(new Date());
            ptInfo.setCreateUser(Constants.SYSTEM_USER);
            ptInfo.setBirthDay(new Date());
            ptInfoMapper.insertSelective(ptInfo);
        } catch (Exception e) {
            System.out.println("PtInfo插入失败");
            response.setStatus(Constants.STATUS_FAIL);
            response.setMsg("PtInfo插入失败");
            return response;
        }
        try {
            PtOpen ptOpen1 = new PtOpen();
            ptOpen1.setPtNo(ptInfo.getPtNo());
            if (drNo == null || drNo.equals("")) {
            } else {
                ptOpen1.setDrNo(Long.parseLong(drNo));
            }
            ptOpen1.setOpenId(openId);
            ptOpenMapper.insertSelective(ptOpen1);
        } catch (NumberFormatException e) {
            System.out.println("PtOpen插入失败");
            response.setStatus(Constants.STATUS_FAIL);
            response.setMsg("PtOpen插入失败");
            return response;
        }
        response.setStatus(Constants.STATUS_SUCCESS);
        response.setMsg("绑定关系成功");
        return response;
    }


    @Override
    public Response ptInfoUpdate(Long ptNo, String name, String sex, String idNo, String height, String weight, String phone, String code) {
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

            CloudPassInfo info = cloudPassInfoMapper.selectByPhoneIdNoPtNo(ptNo,phone,idNo);
            if(info!=null){
                response.setStatus(1);
                response.setMsg("手机号码或身份证号码重复");
                return response;
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            PtInfo ptInfo = ptInfoMapper.selectByPrimaryKey(ptNo);
            ptInfo.setHeight(Double.valueOf(height));
            ptInfo.setWeight(Double.valueOf(weight));
            ptInfo.setSex(sex);
            ptInfo.setBirthDay(formatter.parse(getBirthday(idNo)));
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
        } catch (Exception e) {
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
    public Response selectDrList(Integer page, Integer rows) {
        ResponseHasData response = new ResponseHasData();
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            HttpSession session = request.getSession();
            WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
            //String openId = user.getOpenId();
            List<PtOpen> ptOpens = ptOpenMapper.selectBtOpenId(openId);
            Page<?> pa =  PageHelper.startPage(page, rows);
            List<DrInfoQr> drInfoQrs = drInfoMapper.selectByPtOpenId(openId);
            for(DrInfoQr drInfoQr:drInfoQrs){
                List<Message> msg = messageMapper.selectDrNewMessage(ptOpens.get(0).getPtNo(), drInfoQr.getDrNo()); // ReadStatus=0
                if (msg.size() == 0) {
                    drInfoQr.setNewMessage(0);
                } else {
                    drInfoQr.setNewMessage(msg.size());
                }
            }
            //查询结果总数
            Long total = pa.getTotal();
            //创建分页条件
            PageInfo<DrInfoQr> pageData = new PageInfo<DrInfoQr>(drInfoQrs,total.intValue());
            response.setData(pageData);
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

    public ResponseHasData insertReceiptAddress(ReceiptAddress ra) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
        //String openId = user.getOpenId();

        List<PtOpen> ptOpen = ptOpenMapper.selectBtOpenId(openId);
        Long ptNo = ptOpen.get(0).getPtNo();
        Long cloudPassNo = ptInfoMapper.selectByPrimaryKey(ptNo).getCloudPassNo();
        List<ReceiptAddress> list = new ArrayList<ReceiptAddress>();
        ResponseHasData response = new ResponseHasData();
        ReceiptAddress receiptAddress = new ReceiptAddress();
        list = receiptAddressMapper.selectByPtNo(ptNo);
        try {
            if (list.size() > 4) {
                response.setMsg("添加地址大于5条，添加失败");
                response.setStatus(Constants.STATUS_FAIL);
                return response;
            }
            receiptAddress.setPtNo(ptNo);
            if (ra.getMark() == Constants.ADDRESS_MARK_ON) {
                receiptAddressMapper.updateMarkZero(ptNo);
            }
            receiptAddress.setProvince(ra.getProvince());
            receiptAddress.setCity(ra.getCity());
            receiptAddress.setCounty(ra.getCounty());
            receiptAddress.setPostalCode(ra.getPostalCode());
            receiptAddress.setAreaCode(ra.getAreaCode());
            receiptAddress.setMark(ra.getMark());
            receiptAddress.setName(ra.getName());
            receiptAddress.setPhone(ra.getPhone());
            receiptAddress.setAddressDetail(ra.getAddressDetail());
            ReceiptAddress receiptAddress1 = null;
            receiptAddress1 = receiptAddressMapper.selectReceiptAddress(receiptAddress);
            if (receiptAddress1 == null) {
                receiptAddressMapper.insertSelective(receiptAddress);
                response.setStatus(Constants.STATUS_SUCCESS);
                response.setMsg("新添加成功");
                response.setData(receiptAddress.getAddId());
                return response;
            } else {
                response.setMsg("存在相同地址，添加失败");
                response.setStatus(Constants.STATUS_FAIL);
                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("添加失败");
            response.setStatus(Constants.STATUS_FAIL);
            return response;
        }
    }

    /**
     * 修改收货地址
     *
     * @return
     */
    public Response updateReceiptAddress(ReceiptAddress ra) {
        ResponseHasData response = new ResponseHasData();
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            HttpSession session = request.getSession();
            WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
            //String openId = user.getOpenId();

            List<PtOpen> ptOpen = ptOpenMapper.selectBtOpenId(openId);
            Long ptNo = ptOpen.get(0).getPtNo();
            if (ra.getMark() == Constants.ADDRESS_MARK_ON) {
                receiptAddressMapper.updateMarkZero(ptNo);
            }
            ReceiptAddress receiptAddress = new ReceiptAddress();
            receiptAddress.setMark(ra.getMark());
            receiptAddress.setAddId(ra.getAddId());
            receiptAddress.setName(ra.getName());
            receiptAddress.setPhone(ra.getPhone());
            receiptAddress.setProvince(ra.getProvince());
            receiptAddress.setCity(ra.getCity());
            receiptAddress.setCounty(ra.getCounty());
            receiptAddress.setPostalCode(ra.getPostalCode());
            receiptAddress.setAreaCode(ra.getAreaCode());
            receiptAddress.setAddressDetail(ra.getAddressDetail());
            receiptAddress.setUpdateTime(new Date());
            receiptAddress.setUpdateUser(Constants.SYSTEM_USER);// 用户自己修改
            receiptAddressMapper.updateByPrimaryKeySelective(receiptAddress);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg( "修改失败");
            response.setStatus(Constants.STATUS_FAIL);
            return response;
        }

    }

    /**
     * 删除收货地址
     *
     * @param addId
     * @return
     */
    public Response deleteReceiptAddress(String addId) {
        ResponseHasData response = new ResponseHasData();
        try {
            receiptAddressMapper.deleteByPrimaryKey(Long.parseLong(addId));
            response.setMsg("删除成功");
            return response;
        } catch (Exception e) {
            response.setMsg("删除发货地址失败");
            response.setStatus(Constants.STATUS_FAIL);
            return response;
        }
    }

    /**
     * 获取所有收货地址
     *
     * @return
     */
    public Response selectReceiptAddress() {
        ResponseHasData response = new ResponseHasData();
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            HttpSession session = request.getSession();
            WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
            //String openId = user.getOpenId();

            List<PtOpen> ptOpen = ptOpenMapper.selectBtOpenId(openId);

            if (ptOpen.size()!=0) {
                Long ptNo = ptOpen.get(0).getPtNo();
                List<ReceiptAddress> list = receiptAddressMapper.selectByPtNo(ptNo);
                response.setData(list);
            }
            return response;
        } catch (Exception e) {
            response.setMsg("查询发货地址失败");
            response.setStatus(Constants.STATUS_FAIL);
            return response;
        }
    }

    @Override
    public Response selectInfo() {
        ResponseHasData response = new ResponseHasData();
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            HttpSession session = request.getSession();
            WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
            //String openId = user.getOpenId();

            List<PtOpen> ptOpen = ptOpenMapper.selectBtOpenId(openId);

            if(ptOpen.size()!=0){
                PtInfo ptInfo = ptInfoMapper.selectByPrimaryKey(ptOpen.get(0).getPtNo());
                CloudPassInfo passInfo = cloudPassInfoMapper.selectByPtNo(ptInfo.getPtNo());
                ptInfo.setName(passInfo.getName());
                ptInfo.setIdNo(passInfo.getIdNo());
                ptInfo.setPhone(passInfo.getPhone());
                response.setData(ptInfo);
                response.setStatus(0);
                response.setMsg("查询成功");
            }else{
                response.setMsg("未查询到该患者");
                response.setStatus(1);
            }
            return response;
        } catch (Exception e) {
            response.setMsg("查询失败");
            response.setStatus(Constants.STATUS_FAIL);
            return response;
        }
    }

    @Override
    public Response findMallOrder(int orderStatus) {
        ResponseHasData response = new ResponseHasData();
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            HttpSession session = request.getSession();
            WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
            //String openId = user.getOpenId();

            List<PtOpen> ptOpen = ptOpenMapper.selectBtOpenId(openId);
            PtInfo ptInfo = ptInfoMapper.selectByPrimaryKey(ptOpen.get(0).getPtNo());

            //邮费
            BigDecimal postage = new BigDecimal("0");

            List<MallOrderQr> mallOrderQrs = new ArrayList<MallOrderQr>();
            List<MallOrder> mallOrders = mallOrderMapper.selectByOrderStatus(orderStatus,ptInfo.getPtNo());
            if(mallOrders.size()!=0){
                for(MallOrder mallOrder : mallOrders){
                    MallOrderQr mallOrderQr = new MallOrderQr();
                    mallOrderQr.setMallOrder(mallOrder);
                    MedOrder medOrder = medOrderMapper.selectByPrimaryKey(mallOrder.getMedOrderNo());
                    mallOrderQr.setMedOrder(medOrder);
                    MedRecord medRecord = medRecordMapper.selectByPrimaryKey(medOrder.getMedRecordNo());
                    mallOrderQr.setMedRecord(medRecord);
                    MrPicture[] mrPictures = mrPictureMapper.selectByMedRecordNo(medOrder.getMedRecordNo());
                    mallOrderQr.setMrPictures(mrPictures);
                    if(mallOrder.getAddress()!=null&&!"".equals(mallOrder.getAddress())){
                        ReceiptAddress receiptAddress = mallAddressMapper.selectByPrimaryKey1(Long.valueOf(mallOrder.getAddress()));
                        mallOrderQr.setReceiptAddress(receiptAddress);
                    }
                    List<MedItemQr> medItemQrs = medItemMapper.selectByMedOrderNo(medOrder.getOrderNo());
                    for(int i = 0 ; i <medItemQrs.size() ; i++){
                        DrugSet drugSet = drugSetMapper.selectByPrimaryKey(medItemQrs.get(i).getDrugNo());
                        if (drugSet.getVersion()==2||drugSet.getVersion()==3){
                            Goods goods = goodsMapper.selectByPrimaryKey(drugSet.getDrugSetNo());
                            drugSet.setDescribe(goods.getDescribe());
                            drugSet.setIsRecipe(goods.getIsRecipe());
                            drugSet.setIsUpload(goods.getIsUpload());
                            List<DrugSetPicture> drugSetPictures = drugSetPictureMapper.selectByGoodsNo(drugSet.getDrugSetNo());
                            if(drugSetPictures.size()!=0){
                                double d = Math.random();
                                int q = (int)(d*100);
                                drugSet.setTitleImg(drugSetPictures.get(0).getTitleImg());
                                String img[] = new String[drugSetPictures.size()];
                                for(int j=0;j<drugSetPictures.size();j++){
                                    img[j] = drugSetPictures.get(j).getDetailsImg();
                                }
                                drugSet.setDetailsImg(img);

                            }
                            medItemQrs.get(i).setDrugSet(drugSet);
                        }else if(drugSet.getVersion()==4){
                            Activity activity = activityMapper.selectByGoodsNoFlag(drugSet.getDrugSetNo(),null);
                            drugSet.setIsRecipe(activity.getIsRecipe());
                            drugSet.setIsUpload(activity.getIsUpload());
                            List<DrugSetPicture> drugSetPictures = drugSetPictureMapper.selectByGoodsNo(drugSet.getDrugSetNo());
                            if(drugSetPictures.size()!=0){
                                double d = Math.random();
                                int q = (int)(d*100);
                                drugSet.setTitleImg(drugSetPictures.get(0).getTitleImg());
                                String img[] = new String[drugSetPictures.size()];
                                for(int j=0;j<drugSetPictures.size();j++){
                                    img[j] = drugSetPictures.get(j).getDetailsImg();
                                }
                                drugSet.setDetailsImg(img);
                            }
                            medItemQrs.get(i).setDrugSet(drugSet);
                            medItemQrs.get(i).setActivity(activity);
                        }
                        //--------------------------------------------------------
                        if(mallOrder.getShippingStatus()==1){
                            double number = medItemQrs.get(i).getNumber();
                            String s = number+"";
                            List<Postage> postages = postageMapper.selectByNo(medItemQrs.get(i).getDrugNo());
                            if(postages.size()==0){
                                postages = postageMapper.selectByNo(Long.valueOf("0"));
                            }
                            System.out.println("购买数量："+number);
                            String regex = postages.get(0).getCondition();
                            System.out.println("正则表达式："+regex);
                            boolean flag = s.matches(regex);
                            System.out.println("flag:"+flag);
                            if(flag){
                                postage = postage.add(postages.get(0).getPostage());
                            }
                        }
                    }
                    mallOrderQr.setMedItemQr(medItemQrs);
                    PtInfoQr ptInfo1 = ptInfoMapper.selectByPtNo(mallOrder.getPtNo());
                    mallOrderQr.setPtInfoQr(ptInfo1);
                    mallOrderQr.setPostage(postage);
                    Long packetId = mallOrderQr.getMallOrder().getPacketId();
                    if(packetId!=null){
                        RedpacketRecord redpacketRecord = redpacketRecordMapper.selectByPrimaryKey(packetId);
                        mallOrderQr.getMallOrder().setPacketAmount(redpacketRecord.getAmount());
                    }else{
                        mallOrderQr.getMallOrder().setPacketAmount(new BigDecimal("0"));
                    }

                    mallOrderQrs.add(mallOrderQr);
                    postage = new BigDecimal("0");
                }
            }
            response.setStatus(0);
            response.setMsg("查询成功");
            response.setData(mallOrderQrs);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("查询失败");
            response.setStatus(Constants.STATUS_FAIL);
            return response;
        }
    }

    @Override
    public Response selectMedOrderList(Integer payStatus) {
        ResponseHasData response = new ResponseHasData();
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            HttpSession session = request.getSession();
            WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
            //String openId = user.getOpenId();

            List<PtOpen> ptOpen = ptOpenMapper.selectBtOpenId(openId);
            PtInfo ptInfo = ptInfoMapper.selectByPrimaryKey(ptOpen.get(0).getPtNo());
            List<MallOrder> mallOrders = mallOrderMapper.selectByPtNoAndPayStatus1(ptInfo.getPtNo(),payStatus);

            List<MedOrderQr> medOrderQrs = new ArrayList<MedOrderQr>();
            for(MallOrder mallOrder:mallOrders){
                MedOrder medOrder = medOrderMapper.selectByPrimaryKey(mallOrder.getMedOrderNo());
                MedOrderQr medOrderQr = new MedOrderQr();
                medOrderQr.setOrderStatus(medOrder.getOrderStatus());
                medOrderQr.setMallNo(mallOrder.getMallNo());
                medOrderQr.setPayStatus(mallOrder.getPayStatus());
                medOrderQr.setMedOrderNo(mallOrder.getMedOrderNo());
                medOrderQr.setOrderTime(mallOrder.getOrderTime());
                medOrderQr.setMedRecordNo(Long.valueOf(medOrder.getMedRecordNo()));
                if(mallOrder.getPayStatus()==0){
                    medOrderQr.setShippingStatus(mallOrder.getShippingStatus());
                }
                medOrderQrs.add(medOrderQr);
            }
            response.setData(medOrderQrs);
            response.setMsg("查询成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("查询失败");
            response.setStatus(1);
        }
        return response;
    }

    @Override
    public Response selectMedOrderInfo(Long medOrderNo, Long medRecordNo) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");

        ResponseHasData response = new ResponseHasData();
        try {
            //PtOpen ptOpen = ptOpenMapper.findByPtOpenId(openId);

            MallOrder mallOrder = mallOrderMapper.selectByMedOAndMedR(medOrderNo,medRecordNo);

            DrInfoQr drInfoQr = drInfoMapper.selectByDrNo(mallOrder.getDrNo());
            MallOrderQr mallOrderQr = new MallOrderQr();
            mallOrderQr.setMallOrder(mallOrder);
            MedOrder medOrder = medOrderMapper.selectByPrimaryKey(mallOrder.getMedOrderNo());
            if(medOrder.getOrderType()==1){
                BigDecimal unitPrice = medOrder.getOrderAmount().divide(new BigDecimal(medOrder.getPrescriptionNum().toString()));
                medOrder.setUnitPrice(unitPrice);
            }
            mallOrderQr.setMedOrder(medOrder);
            MedRecord medRecord = medRecordMapper.selectByPrimaryKey(medOrder.getMedRecordNo());
            String diagContentName = medRecord.getDiagContent();
            String[] diag = diagContentName.split(",");
            System.out.println("diag"+diag);
            if(medOrder.getOrderType()==1){
                diagContentName = "";
                //中医
                for(int i = 0 ; i < diag.length ; i++){
                    String[] diag1 = diag[i].split("-");
                    diagContentName += diseaseMasterMapper.selectByPrimaryKey(Long.valueOf(diag1[0])).getChtName()+"-";
                    diagContentName += diseaseMasterMapper.selectByPrimaryKey(Long.valueOf(diag1[1])).getChtName()+",";
                    System.out.println("中医"+diagContentName);
                }
                diagContentName = diagContentName.substring(0,diagContentName.length()-1);
            }else{
                diagContentName = "";
                //西医
                for(int i = 0 ; i < diag.length ; i++){
                    diagContentName += diseaseMasterMapper.selectByPrimaryKey(Long.valueOf(diag[i])).getChtName()+",";
                    System.out.println("西医"+diagContentName);
                }
                diagContentName = diagContentName.substring(0,diagContentName.length()-1);
            }

            medRecord.setDiagContentName(diagContentName);
            mallOrderQr.setMedRecord(medRecord);
            MrPicture[] mrPictures = mrPictureMapper.selectByMedRecordNo(medOrder.getMedRecordNo());
            mallOrderQr.setMrPictures(mrPictures);
            List<MedItemQr> medItemQrs = medItemMapper.selectByMedOrderNo(medOrder.getOrderNo());
            for (MedItemQr medItemQr:medItemQrs){
                DrugSet drugSet = drugSetMapper.selectByPrimaryKey(medItemQr.getDrugNo());
                medItemQr.setDrugSet(drugSet);
            }

            /*BigDecimal post = new BigDecimal("0");
            //计算邮费
            if(medItemQrs.size()==1){
                List<Postage> postages = postageMapper.selectByNo(medItemQrs.get(0).getDrugNo());
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




            if(mallOrder.getAddress()!=null&&!"".equals(mallOrder.getAddress())){
                ReceiptAddress receiptAddress = mallAddressMapper.selectByPrimaryKey1(Long.valueOf(mallOrder.getAddress()));
                mallOrderQr.setReceiptAddress(receiptAddress);
            }
            mallOrderQr.setMedItemQr(medItemQrs);
            PtInfoQr ptInfo = ptInfoMapper.selectByPtNo(mallOrder.getPtNo());
            mallOrderQr.setPtInfoQr(ptInfo);
            mallOrderQr.setDrInfoQr(drInfoQr);



            //mallOrderQr.setPostage(post);


            response.setStatus(0);
            response.setMsg("查询成功");
            response.setData(mallOrderQr);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询失败");
        }
        return response;

    }

    @Override
    public Response selectShipping(Long orderNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            MallOrder mallOrder = mallOrderMapper.selectByMedOrderNo(orderNo);
            response.setMsg("查询成功");
            response.setStatus(0);
            response.setData(mallOrder);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("查询失败");
            response.setStatus(1);
        }
        return response;
    }


}
