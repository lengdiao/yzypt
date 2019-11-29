package com.ecard.controller;

import com.ecard.pojo.Response;
import com.ecard.pojo.queryResult.TemplateQr;
import com.ecard.service.DoctorService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "医生Controller", tags = { "医生操作接口" })
@RequestMapping("/doctor")
@CrossOrigin
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    /**
     * 新增医生
     *
     * @return
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增医生", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drName", value = "医生名字", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "phone", value = "医生电话", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "idCard", value = "医生身份证号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "hospital", value = "所属医院", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "province", value = "省名", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "chiefNo", value = "科室", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "age", value = "年龄", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "address", value = "地址", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "practiceProfile", value = "执业情况简介", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "signature", value = "医生签章", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "introducer", value = "介绍人", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "company", value = "介绍人单位", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "title", value = "职称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "drTitleCert", value = "医师资格证号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "drPracticeRegCert", value = "医师执业证号码\t 执业医师注册证", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "consultingHour", value = "门诊时间(135上午,1上午,3下午)", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer")
    })
    public Response insert(
            @RequestParam(value = "drName") String drName,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "idCard", required = false) String idCard,
            @RequestParam(value = "hospital", required = false) String hospital,
            @RequestParam(value = "province", required = false) String province,
            @RequestParam(value = "chiefNo", required = false) String chiefNo,
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "practiceProfile", required = false) String practiceProfile,
            @RequestParam(value = "signature", required = false) String signature,
            @RequestParam(value = "introducer", required = false) String introducer,
            @RequestParam(value = "company", required = false) String company,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "drTitleCert", required = false) String drTitleCert,
            @RequestParam(value = "drPracticeRegCert", required = false) String drPracticeRegCert,
            @RequestParam(value = "consultingHour", required = false) String consultingHour,
            @RequestParam(value = "disableFlag", required = false) Integer disableFlag) {
        Response response = doctorService.insert(drName, phone, idCard, hospital, province, chiefNo, age, address,
                practiceProfile, signature, introducer, company, title, drTitleCert, drPracticeRegCert,
                consultingHour, disableFlag);
        return response;
    }


    /**
     * 修改医生信息
     *
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改医生信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drNo", value = "医生名字", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "drName", value = "医生名字", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "phone", value = "医生电话", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "idCard", value = "医生身份证号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "hospital", value = "所属医院", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "province", value = "省名", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "chiefNo", value = "科室", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "age", value = "年龄", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "address", value = "地址", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "practiceProfile", value = "执业情况简介", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "signature", value = "医生签章", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "introducer", value = "介绍人", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "company", value = "介绍人单位", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "title", value = "职称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "drTitleCert", value = "医师资格证号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "drPracticeRegCert", value = "医师执业证号码\t 执业医师注册证", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "consultingHour", value = "门诊时间(135上午,1上午,3下午)", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer")
    })
    public Response update(
            @RequestParam(value = "drNo") Long drNo,
            @RequestParam(value = "drName", required = false) String drName,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "idCard", required = false) String idCard,
            @RequestParam(value = "hospital", required = false) String hospital,
            @RequestParam(value = "province", required = false) String province,
            @RequestParam(value = "chiefNo", required = false) String chiefNo,
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "practiceProfile", required = false) String practiceProfile,
            @RequestParam(value = "signature", required = false) String signature,
            @RequestParam(value = "introducer", required = false) String introducer,
            @RequestParam(value = "company", required = false) String company,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "drTitleCert", required = false) String drTitleCert,
            @RequestParam(value = "drPracticeRegCert", required = false) String drPracticeRegCert,
            @RequestParam(value = "consultingHour", required = false) String consultingHour,
            @RequestParam(value = "disableFlag", required = false) Integer disableFlag) {
        Response response = doctorService.update(drNo, drName, phone, idCard, hospital, province, chiefNo, age, address,
                practiceProfile, signature, introducer, company, title, drTitleCert, drPracticeRegCert,
                consultingHour, disableFlag);
        return response;
    }

    /**
     * 查询医生
     *
     * @return
     */
    @PostMapping("/select")
    @ApiOperation(value = "查询医生", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drName", value = "医生名字", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "chiefNo", value = "科室", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "title", value = "职称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "rows", value = "显示条数", dataType = "Integer")
    })
    public Response select(
            @RequestParam(value = "drName", required = false) String drName,
            @RequestParam(value = "chiefNo", required = false) String chiefNo,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "disableFlag", required = false) Integer disableFlag,
            @RequestParam(value="page", required=false, defaultValue="1") Integer page,
            @RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
        Response response = doctorService.select(drName, chiefNo, title, disableFlag,page,rows);
        return response;
    }

    /**
     * 医生登录
     *
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "医生登录", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "phone", value = "医生电话", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "password", value = "密码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "code", value = "wxCode", dataType = "String")
    })
    public Response login(
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "code", required = false) String code) {
        Response response = doctorService.login(phone,password,code);
        return response;
    }

    /**
     * 医生查询未处理问诊列表
     *
     * @return
     */
    @PostMapping("/selectMallOrderList")
    @ApiOperation(value = "医生查询未处理问诊", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drNo", value = "医生编号", dataType = "Long")
    })
    public Response selectMallOrderList(
            @RequestParam(value = "drNo") Long drNo) {
        Response response = doctorService.selectMallOrderList(drNo);
        return response;
    }

    /**
     * 查询诊断列表
     *
     * @return
     */
    @PostMapping("/selectAccessTokenList")
    @ApiOperation(value = "无用", httpMethod = "POST")
    @ApiImplicitParams({
            })
    public Response selectAccessTokenList() {
        Response response = doctorService.selectAccessTokenList();
        return response;
    }

    /**
     * 医生添加处方模板
     *
     * @return
     */
    @PostMapping("/insertTemplates")
    @ApiOperation(value = "医生添加处方模板", httpMethod = "POST")
    public Response insertTemplates(@RequestBody TemplateQr templateQr) {
        Response response = doctorService.insertTemplates(templateQr);
        return response;
    }

    /**
     * 医生修改处方模板
     *
     * @return
     */
    @PostMapping("/updateTemplates")
    @ApiOperation(value = "医生修改处方模板", httpMethod = "POST")
    public Response updateTemplates(@RequestBody TemplateQr templateQr) {
        Response response = doctorService.updateTemplates(templateQr);
        return response;
    }

    /**
     * 医生查询处方模板
     *
     * @return
     */
    @PostMapping("/selectTemplates")
    @ApiOperation(value = "医生查询处方模板", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drNo", value = "医生编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "templateType", value = "模板类型", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "templateName", value = "模板名称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "rows", value = "显示条数", dataType = "Integer")
    })
    public Response selectTemplates(
            @RequestParam(value = "drNo") Long drNo,
            @RequestParam(value = "templateType") Integer templateType,
            @RequestParam(value = "templateName") String templateName,
            @RequestParam(value="page", required=false, defaultValue="1") Integer page,
            @RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
        Response response = doctorService.selectTemplates(drNo,templateType,templateName,page,rows);
        return response;
    }

    /**
     * 医生编辑处方单
     *
     * @return
     */
    @PostMapping("/updateMedRecord")
    @ApiOperation(value = "医生编辑处方单", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "mallNo", value = "订单编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "diagContent", value = "诊断内容A", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "plan", value = "计划P", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "orderType", value = "处方单类型 按照药品大分类\\r\\n1:西药\\r\\n2：中成药\\r\\n3:草药4:模板", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "prescriptionNum", value = "处方贴数(中药)", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "prescriptionName", value = "处方名称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "decoctionWay", value = "代煎方式(中药)1:自煎 2：院内煎", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "decoctionNum", value = "代煎贴数(中药)", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "specialUsage", value = "用法(1.口服 2.外敷)", dataType = "Integer")
    })
    public Response updateMedRecord(
            @RequestParam(value = "mallNo") Long mallNo,
            @RequestParam(value = "diagContent") String diagContent,
            @RequestParam(value = "plan") String plan,
            @RequestParam(value = "orderType") Integer orderType,
            @RequestParam(value = "prescriptionNum") Integer prescriptionNum,
            @RequestParam(value = "prescriptionName") String prescriptionName,
            @RequestParam(value = "decoctionWay") Integer decoctionWay,
            @RequestParam(value = "decoctionNum") Integer decoctionNum,
            @RequestParam(value = "specialUsage") Integer specialUsage) {
        Response response = doctorService.updateMedRecord(mallNo,diagContent,plan,orderType,prescriptionNum,
                prescriptionName,decoctionWay,decoctionNum,specialUsage);
        return response;
    }

    /**
     * 医生修改处方药品
     *
     * @return
     */
    @PostMapping("/updateDrugSet")
    @ApiOperation(value = "医生修改处方药品", httpMethod = "POST")
    public Response updateDrugSet(@RequestBody TemplateQr templateQr) {
        Response response = doctorService.updateDrugSet(templateQr);
        return response;
    }

    /**
     * 医生绑定病人
     *
     * @return
     */
    @PostMapping("/Binding")
    @ApiOperation(value = "医生绑定病人", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "ptNo", value = "患者编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "code", value = "wxCode", dataType = "String")
    })
    public Response Binding(
            @RequestParam(value = "ptNo", required=false) Long ptNo,
            @RequestParam(value="code", required=false) String code) {
        Response response = doctorService.Binding(ptNo,code);
        return response;
    }

    /**
     * 医生查询诊断
     *
     * @return
     */
    @PostMapping("/selectDiseaseMaster")
    @ApiOperation(value = "医生查询诊断下拉框", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "keyword1", value = "输入码1", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "chtType", value = "病种类型（1:西医诊断 2:中医主病 3:中医症候）", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "rows", value = "显示条数", dataType = "Integer")
    })
    public Response selectDiseaseMaster(
            @RequestParam(value="keyword1", required=false) String keyword1,
            @RequestParam(value="chtType", required=false) String chtType,
            @RequestParam(value="page", required=false, defaultValue="1") Integer page,
            @RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
        Response response = doctorService.selectDiseaseMaster(keyword1,chtType,page,rows);
        return response;
    }



}
