package com.ecard.service.serviceImpl;

import com.ecard.entity.*;
import com.ecard.dao.*;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.queryResult.MallOrderQr;
import com.ecard.pojo.queryResult.MedItemQr;
import com.ecard.pojo.queryResult.PtInfoQr;
import com.ecard.service.DrugStoreService;
import com.ecard.utils.RoleUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Response addDrugStore(String drugStoreName, String contact, String contactPhone, String address,
                                 Integer disableFlag) {
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

            RoleUtil.accredit(cloud.getCloudPassNo(), "药店");

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
                                    String address, Integer disableFlag, String oldCode) {
        ResponseHasData response = new ResponseHasData();
        try {
            CloudPassInfo cloudPassInfo = cloudPassInfoMapper.selectByPhoneAndStoreNo(contactPhone,drugStoreNo);
            if(contactPhone!=oldCode) {
                if(cloudPassInfo!=null) {
                    response.setMsg("手机号存在重复");
                    response.setStatus(1);
                    return response;
                }
            }
            cloudPassInfo.setName(contact);
            cloudPassInfo.setDisableFlag(disableFlag);
            cloudPassInfo.setPhone(contactPhone);
            cloudPassInfo.setUpdateTime(new Date());
            cloudPassInfoMapper.updateByPrimaryKeySelective(cloudPassInfo);

            DrugStore drugStore = drugStoreMapper.selectByCloudPassNo(cloudPassInfo.getCloudPassNo());
            drugStore.setAddress(address);
            drugStore.setContact(contact);
            drugStore.setTelePhone(contactPhone);
            drugStore.setDisableFlag(disableFlag);
            drugStore.setDrugStoreName(drugStoreName);
            drugStoreMapper.updateByPrimaryKeySelective(drugStore);

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
                                  Integer page, Integer rows) {
        ResponseHasData response = new ResponseHasData();
        try {
            Page<?> pa =  PageHelper.startPage(page, rows);
            List<DrugStore> piq =
                    drugStoreMapper.findDrugStore(drugStoreName,disableFlag);
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
    public Response findMallOrder(Long mallNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            MallOrder mallOrder = mallOrderMapper.selectByPrimaryKey(mallNo);
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
    public Response updateMedOrderStatus(Long mallNo, Long drugStoreNo, Integer orderStatus,
                                         Integer payStatus, Date payTime, String execLoc, Integer status) {
        ResponseHasData response = new ResponseHasData();
        try {
            MallOrder mallOrder = mallOrderMapper.selectByPrimaryKey(mallNo);
            MedOrder medOrder = medOrderMapper.selectByPrimaryKey(mallOrder.getMedOrderNo());
            mallOrder.setOrderStatus(orderStatus);
            mallOrder.setDrugStoreNo(drugStoreNo);
            mallOrder.setPayStatus(payStatus);
            mallOrder.setPayTime(payTime);
            mallOrderMapper.updateByPrimaryKeySelective(mallOrder);

            medOrder.setExecLoc(execLoc);
            medOrder.setOrderStatus(status);
            medOrderMapper.updateByPrimaryKeySelective(medOrder);

            response.setStatus(0);
            response.setMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("修改失败");
        }
        return response;
    }

}
