package com.ecard.controller;

import com.ecard.Constants;
import com.ecard.dao.CloudPassInfoMapper;
import com.ecard.dao.DisInfoMapper;
import com.ecard.dao.DrDisRelationMapper;
import com.ecard.dao.DrInfoMapper;
import com.ecard.entity.*;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.queryResult.DisInfoQr;
import com.ecard.pojo.queryResult.DrInfoQr;
import com.ecard.utils.RoleUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "读取ExcelController", tags = { "读取Excel接口" })
@RequestMapping("/Excel")
@CrossOrigin
public class ExcalController {
    @Autowired
    private DisInfoMapper disInfoMapper;
    @Autowired
    private CloudPassInfoMapper cloudPassInfoMapper;
    @Autowired
    private DrInfoMapper drInfoMapper;
    @Autowired
    private DrDisRelationMapper drDisRelationMapper;

    @GetMapping("/getExcal")
    @Transactional(rollbackFor=Exception.class)
    public ResponseHasData excalController() {
        ResponseHasData response = new ResponseHasData();
        try {
            System.out.println("----");
            File f=new File("/Users/hzh/Downloads/导入医生.xls");
            Workbook book=Workbook.getWorkbook(f);
            Sheet sheet=book.getSheet(0);
            //行
            for(int i=1;i<sheet.getRows();i++){
                DisInfoQr disInfoQr = disInfoMapper.selectByPhone(sheet.getCell(7,i).getContents());
                //判断是否有该代表
                if(disInfoQr==null){
                    //
                    List<CloudPassInfo> cpi = cloudPassInfoMapper.findByPhoneDis(null,sheet.getCell(7,i).getContents());
                    if (cpi.size()!=0) {
                        System.out.println(sheet.getCell(7,i).getContents()+"代表手机号重复");
                        continue;
                    }else{
                        CloudPassInfo cloudPassInfo = new CloudPassInfo();
                        cloudPassInfo.setName(sheet.getCell(5,i).getContents());
                        cloudPassInfo.setPhone(sheet.getCell(7,i).getContents());
                        cloudPassInfo.setPassword(Constants.PASSWORDS);
                        cloudPassInfo.setDisableFlag(0);
                        cloudPassInfo.setCreateTime(new Date());
                        cloudPassInfo.setCreateUser(Constants.SYSTEM_USER);
                        cloudPassInfoMapper.insertSelective(cloudPassInfo);
                        Long cloudPassNo = cloudPassInfo.getCloudPassNo();
                        // 授权
                        RoleUtil.accredit(cloudPassNo, "二级代表");
                        // 创建DisInfo
                        DisInfo disInfo = new DisInfo();
                        disInfo.setCloudPassNo(cloudPassNo);
                        disInfo.setDisName(sheet.getCell(5,i).getContents());
                        disInfo.setTelePhone(sheet.getCell(7,i).getContents());
                        String[] address1 = sheet.getCell(6,i).getContents().split(",");
                        disInfo.setAddress("[\""+address1[0]+"\",\""+address1[1]+"\"]");
                        disInfo.setDisLeader(Long.valueOf("8800000930"));
                        disInfo.setDisableFlag(0);
                        disInfo.setDisLevel("2");
                        disInfo.setCreateTime(new Date());
                        disInfo.setCreateUser(Constants.SYSTEM_USER);
                        disInfoMapper.insertSelective(disInfo);


                        DrInfo di = new DrInfo();
                        CloudPassInfo drcpi = new CloudPassInfo();
                        DrDisRelation ddr = new DrDisRelation();

                        CloudPassInfo CPI = cloudPassInfoMapper.selectByPhoneAndDoctor(sheet.getCell(1,i).getContents());
                        if (CPI != null) {
                            System.out.println(sheet.getCell(7,i).getContents()+"医生手机号重复");
                            continue;
                        }
                        drcpi.setName(sheet.getCell(0,i).getContents());
                        drcpi.setPassword("123456");
                        drcpi.setPhone(sheet.getCell(1,i).getContents());
                        drcpi.setCreateTime(new Date());
                        drcpi.setCreateUser(Constants.SYSTEM_USER);
                        drcpi.setDisableFlag(0);
                        cloudPassInfoMapper.insertSelective(drcpi);
                        RoleUtil.accredit(drcpi.getCloudPassNo(),"医生");
                        di.setChiefNo(sheet.getCell(2,i).getContents());
                        di.setCloudPassNo(drcpi.getCloudPassNo());
                        di.setCreateTime(new Date());
                        di.setCreateUser(Constants.SYSTEM_USER);
                        di.setDisableFlag(Constants.STATUS_SUCCESS);
                        String[] address = sheet.getCell(4,i).getContents().split(",");
                        di.setProvince(address[0]);
                        di.setCity(address[1]);
                        di.setType(sheet.getCell(3,i).getContents().equals("西医")?2:1);
                        drInfoMapper.insertSelective(di);

                        ddr.setCreateTime(new Date());
                        ddr.setCreateUser(Constants.SYSTEM_USER);
                        ddr.setDisableFlag(Constants.STATUS_SUCCESS);
                        ddr.setDisNo(Long.valueOf(disInfo.getDisNo()));
                        ddr.setDrNo(di.getDrNo());
                        drDisRelationMapper.insertSelective(ddr);

                    }


                }else{
                    DrInfo di = new DrInfo();
                    CloudPassInfo drcpi = new CloudPassInfo();
                    DrDisRelation ddr = new DrDisRelation();

                    CloudPassInfo CPI = cloudPassInfoMapper.selectByPhoneAndDoctor(sheet.getCell(1,i).getContents());
                    if (CPI != null) {
                        System.out.println(sheet.getCell(7,i).getContents()+"医生手机号重复");
                        continue;
                    }
                    drcpi.setName(sheet.getCell(0,i).getContents());
                    drcpi.setPassword("123456");
                    drcpi.setPhone(sheet.getCell(1,i).getContents());
                    drcpi.setCreateTime(new Date());
                    drcpi.setCreateUser(Constants.SYSTEM_USER);
                    drcpi.setDisableFlag(0);
                    cloudPassInfoMapper.insertSelective(drcpi);
                    RoleUtil.accredit(drcpi.getCloudPassNo(),"医生");
                    di.setChiefNo(sheet.getCell(2,i).getContents());
                    di.setCloudPassNo(drcpi.getCloudPassNo());
                    di.setCreateTime(new Date());
                    di.setCreateUser(Constants.SYSTEM_USER);
                    di.setDisableFlag(Constants.STATUS_SUCCESS);
                    String[] address = sheet.getCell(4,i).getContents().split(",");
                    di.setProvince(address[0]);
                    di.setCity(address[1]);
                    di.setType(sheet.getCell(3,i).getContents().equals("西医")?2:1);
                    drInfoMapper.insertSelective(di);

                    ddr.setCreateTime(new Date());
                    ddr.setCreateUser(Constants.SYSTEM_USER);
                    ddr.setDisableFlag(Constants.STATUS_SUCCESS);
                    ddr.setDisNo(disInfoQr.getDisNo());
                    ddr.setDrNo(di.getDrNo());
                    drDisRelationMapper.insertSelective(ddr);

                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response;

    }
}
