package com.ecard.service.serviceImpl;

import com.ecard.entity.*;
import com.ecard.dao.*;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.queryResult.*;
import com.ecard.service.DrugStoreService;
import com.ecard.utils.RoleUtil;
import com.ecard.utils.WeiXinUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DrugStoreServiceImpl implements DrugStoreService {
    @Autowired
    private CloudPassInfoMapper cloudPassInfoMapper;
    @Autowired
    private DrugStoreMapper drugStoreMapper;
    @Autowired
    private MallOrderMapper mallOrderMapper;
    @Autowired
    private MedRecordMapper medRecordMapper;
    @Autowired
    private MedItemMapper medItemMapper;
    @Autowired
    private MedOrderMapper medOrderMapper;
    @Autowired
    private PtInfoMapper ptInfoMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private MrPictureMapper mrPictureMapper;
    @Autowired
    private ReceiptAddressMapper receiptAddressMapper;
    @Autowired
    private DrInfoMapper drInfoMapper;
    @Autowired
    private DrugSetMapper drugSetMapper;
    @Autowired
    private DiseaseMasterMapper diseaseMasterMapper;
    @Autowired
    private MallAddressMapper mallAddressMapper;
    @Autowired
    private PtOpenMapper ptOpenMapper;
    @Autowired
    private DisInfoMapper disInfoMapper;

    //String openId = "001";

    public Response addDrugStore(String drugStoreName, String contact, String contactPhone, String address,
                                 String type, Integer disableFlag) {
        ResponseHasData response = new ResponseHasData();
        try {
            CloudPassInfo cloudPassInfo = cloudPassInfoMapper.findByPhone(contactPhone);
            if(cloudPassInfo!=null) {
                response.setMsg("手机号存在重复");
                response.setStatus(1);
                return response;
            }
            CloudPassInfo cloud = new CloudPassInfo();
            cloud.setDisableFlag(0);
            cloud.setName(contact);
            cloud.setPassword("123456");
            cloud.setPhone(contactPhone);

            cloudPassInfoMapper.insertSelective(cloud);

            DrugStore drugStore = new DrugStore();
            drugStore.setCloudPassNo(cloud.getCloudPassNo());
            drugStore.setContact(contact);
            drugStore.setTelePhone(contactPhone);
            drugStore.setCreateTime(new Date());
            drugStore.setDisableFlag(disableFlag);
            drugStore.setAddress(address);
            drugStore.setDrugStoreName(drugStoreName);

            drugStoreMapper.insertSelective(drugStore);

            if(type.equals("1")){
                RoleUtil.accredit(cloud.getCloudPassNo(), "药店");
            }else if(type.equals("2")){
                RoleUtil.accredit(cloud.getCloudPassNo(), "发货点");
            }else if(type.equals("3")){
                RoleUtil.accredit(cloud.getCloudPassNo(), "药店兼发货点");
            }



            response.setStatus(0);
            response.setMsg("新增成功");
        } catch (Exception e) {
            response.setStatus(1);
            response.setMsg("新增失败");
            e.printStackTrace();
        }

        return response;
    }



    public Response updateDrugStore(Long drugStoreNo, String drugStoreName, String contact, String contactPhone,
                                    String address, String type, Integer disableFlag) {
        ResponseHasData response = new ResponseHasData();
        try {
            CloudPassInfo cloudPassInfo = cloudPassInfoMapper.selectByPhoneAndStoreNo(contactPhone,drugStoreNo);

            if(cloudPassInfo!=null) {
                response.setMsg("手机号存在重复");
                response.setStatus(1);
                return response;
            }

            CloudPassInfo passInfo = cloudPassInfoMapper.selectByDrugStoreNo(drugStoreNo);

            passInfo.setName(contact);
            passInfo.setDisableFlag(disableFlag);
            passInfo.setPhone(contactPhone);
            passInfo.setUpdateTime(new Date());
            cloudPassInfoMapper.updateByPrimaryKeySelective(passInfo);

            DrugStore drugStore = drugStoreMapper.selectByCloudPassNo(passInfo.getCloudPassNo());
            drugStore.setAddress(address);
            drugStore.setContact(contact);
            drugStore.setTelePhone(contactPhone);
            drugStore.setDisableFlag(disableFlag);
            drugStore.setDrugStoreName(drugStoreName);
            drugStoreMapper.updateByPrimaryKeySelective(drugStore);

            if(type.equals("1")){
                RoleUtil.accredit(passInfo.getCloudPassNo(), "药店");
            }else if(type.equals("2")){
                RoleUtil.accredit(passInfo.getCloudPassNo(), "发货点");
            }else if(type.equals("3")){
                RoleUtil.accredit(passInfo.getCloudPassNo(), "药店兼发货点");
            }

            response.setStatus(0);
            response.setMsg("更新成功");
        } catch (Exception e) {
            response.setStatus(1);
            response.setMsg("更新成功");
            e.printStackTrace();
        }
        return response;
    }



    public Response findDrugStore(String drugStoreName, Integer disableFlag,
                                  String address, String type, Integer page, Integer rows) {
        ResponseHasData response = new ResponseHasData();
        try {
            Page<?> pa =  PageHelper.startPage(page, rows);
            if(type!=null&&!type.equals("")){
                type=type.equals("1")?"3":(type.equals("2")?"4":"6");

            }
            int t = 0;
            if(type!=null&&!type.equals("")){
                t = Integer.parseInt(type);
            }

            List<DrugStore> piq =
                    drugStoreMapper.findDrugStore(drugStoreName,disableFlag,address,t);
            for(DrugStore drugStore:piq){
                if(drugStore.getType()!=null){
                    type=drugStore.getType().equals("3")?"1":(drugStore.getType().equals("4")?"2":"3");
                    drugStore.setType(type);
                }
            }
            //查询结果总数
            Long total = pa.getTotal();
            //创建分页条件
            PageInfo<DrugStore> pageData = new PageInfo<DrugStore>(piq,total.intValue());

            response.setStatus(0);
            response.setMsg("查询成功");
            response.setData(pageData);
        } catch (Exception e) {
            response.setStatus(0);
            response.setMsg("查询失败");
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public Response findShippingStatusIs1() {
        ResponseHasData response = new ResponseHasData();
        try {
            List<MallOrder> mallOrders = mallOrderMapper.findShippingStatusIs1();
            List<MallOrderQr> mallOrderQrs = new ArrayList<MallOrderQr>();

            for (MallOrder mallOrder:mallOrders){
                MallOrderQr mallOrderQr = new MallOrderQr();
                mallOrderQr.setMallOrder(mallOrder);
                MedOrder medOrder = medOrderMapper.selectByPrimaryKey(mallOrder.getMedOrderNo());
                mallOrderQr.setMedOrder(medOrder);
                MedRecord medRecord = medRecordMapper.selectByPrimaryKey(medOrder.getMedRecordNo());
                mallOrderQr.setMedRecord(medRecord);
                System.out.println(mallOrder.getPtNo());
                PtInfoQr ptInfo = ptInfoMapper.selectByPtNo(mallOrder.getPtNo());
                if(ptInfo.getIdNo()!=null&&!"".equals(ptInfo.getIdNo())&&!"null".equals(ptInfo.getIdNo())){
                    ptInfo.setAge(IdNOToAge(ptInfo.getIdNo()));
                }
                DrInfoQr drInfoQr = drInfoMapper.selectByDrNo(mallOrder.getDrNo());
                mallOrderQr.setDrInfoQr(drInfoQr);
                mallOrderQr.setPtInfoQr(ptInfo);

                if(mallOrder.getAddress()!=null&&!"".equals(mallOrder.getAddress())){
                    ReceiptAddress receiptAddress = mallAddressMapper.selectByPrimaryKey1(Long.valueOf(mallOrder.getAddress()));
                    mallOrderQr.setReceiptAddress(receiptAddress);
                }

                mallOrderQrs.add(mallOrderQr);
            }

            response.setData(mallOrderQrs);
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
    public Response findMallOrder(Long medOrderNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            MallOrder mallOrder = mallOrderMapper.selectByMedOrderNo1(medOrderNo);
            MallOrderQr mallOrderQr = new MallOrderQr();
            mallOrderQr.setMallOrder(mallOrder);
            MedOrder medOrder = medOrderMapper.selectByPrimaryKey(mallOrder.getMedOrderNo());
            mallOrderQr.setMedOrder(medOrder);
            MedRecord medRecord = medRecordMapper.selectByPrimaryKey(medOrder.getMedRecordNo());
            String diagContentName = medRecord.getDiagContent();
            if(diagContentName!=null&&!"".equals(diagContentName)){
                String[] diag = diagContentName.split(",");
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
            }


            medRecord.setDiagContentName(diagContentName);
            mallOrderQr.setMedRecord(medRecord);
            List<MedItemQr> medItemQrs = medItemMapper.selectByMedOrderNo(medOrder.getOrderNo());
            for (MedItemQr medItemQr:medItemQrs){
                DrugSet drugSet = drugSetMapper.selectByPrimaryKey(medItemQr.getDrugNo());
                medItemQr.setDrugSet(drugSet);
            }
            mallOrderQr.setMedItemQr(medItemQrs);
            PtInfoQr ptInfo = ptInfoMapper.selectByPtNo(mallOrder.getPtNo());
            if(ptInfo.getIdNo()!=null&&!"".equals(ptInfo.getIdNo())&&!"null".equals(ptInfo.getIdNo())){
                ptInfo.setAge(IdNOToAge(ptInfo.getIdNo()));
            }
            if(mallOrder.getAddress()!=null&&!"".equals(mallOrder.getAddress())){
                ReceiptAddress receiptAddress = mallAddressMapper.selectByPrimaryKey1(Long.valueOf(mallOrder.getAddress()));
                mallOrderQr.setReceiptAddress(receiptAddress);
            }

            DrInfoQr drInfoQr = drInfoMapper.selectByDrNo(mallOrder.getDrNo());
            mallOrderQr.setDrInfoQr(drInfoQr);
            mallOrderQr.setPtInfoQr(ptInfo);
            DisInfoQr disInfoQr = disInfoMapper.selectNameByDrNo(mallOrder.getDrNo());
            mallOrderQr.setDis1Name(disInfoQr.getDisName());
            mallOrderQr.setDis2Name(disInfoQr.getDisLeaderName());

            response.setData(mallOrderQr);
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
    public Response updateMedOrderStatus(Long mallNo, Long drugStoreNo,
                                         int shippingStatus, String shippingCompany, String shippingNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            MallOrder mallOrder = mallOrderMapper.selectByPrimaryKey(mallNo);
            MedOrder medOrder = medOrderMapper.selectByPrimaryKey(mallOrder.getMedOrderNo());
            if(shippingStatus==0||shippingStatus==3){
                mallOrder.setOrderStatus(0);
            }
            mallOrder.setDrugStoreNo(drugStoreNo);
            mallOrder.setShippingStatus(shippingStatus);
            mallOrder.setShippingCompany(shippingCompany);
            mallOrder.setShippingNo(shippingNo);
            if(shippingStatus==0&&shippingCompany!=null&&!"".equals(shippingCompany)&&shippingNo!=null&&!"".equals(shippingNo)){
                mallOrder.setShippingTime(new Date());
            }
            mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
            DrugStore drugStore = drugStoreMapper.selectByPrimaryKey(drugStoreNo);
            medOrder.setExecLoc(drugStore.getAddress());
            medOrder.setExecUser(drugStore.getContact());
            medOrder.setExecTime(new Date());
            medOrder.setDrugStoreNo(drugStore.getDrugStoreName());
            medOrder.setOrderStatus(2);
            medOrderMapper.updateByPrimaryKeySelective(medOrder);

            List<MedItemQr> medItemQr = medItemMapper.selectByMedOrderNo(medOrder.getOrderNo());

            Goods goods =goodsMapper.selectByPrimaryKey(medItemQr.get(0).getDrugNo());

            List<PtOpen> ptOpen = ptOpenMapper.selectByPtNo(mallOrder.getPtNo());

            if(shippingStatus==0&&shippingCompany!=null&&!"".equals(shippingCompany)&&shippingNo!=null&&!"".equals(shippingNo)){
                if(mallOrder.getPlatform()==1){
                    WeiXinUtils.shippingRemind( ptOpen.get(0).getOpenId(), goods.getGoodsName(), shippingStatus, shippingCompany, shippingNo,mallOrder.getPlatform());
                }else if(mallOrder.getPlatform()==2){
                    WeiXinUtils.shippingRemind( ptOpen.get(0).getSjOpenId(), goods.getGoodsName(), shippingStatus, shippingCompany, shippingNo,mallOrder.getPlatform());
                }
            }

            response.setStatus(0);
            response.setMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("修改失败");
        }
        return response;
    }


    public static int IdNOToAge(String IdNO){
        int leh = IdNO.length();
        String dates="";
        if (leh == 18) {
            //int se = Integer.valueOf(IdNO.substring(leh - 1)) % 2;
            dates = IdNO.substring(6, 10);
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year=df.format(new Date());
            int u=Integer.parseInt(year)-Integer.parseInt(dates);
            return u;
        }else{
            dates = IdNO.substring(6, 8);
            return Integer.parseInt(dates);
        }

    }
}
