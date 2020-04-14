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
            @ApiImplicitParam(paramType="query", name = "idNo", value = "医生身份证号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "hospital", value = "所属医院", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "chiefNo", value = "科室", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "age", value = "年龄", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "practiceProfile", value = "执业情况简介", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "signature", value = "医生签章", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "title", value = "职称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "drTitleCert", value = "医师资格证号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "drPracticeRegCert", value = "医师执业证号码\t 执业医师注册证", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "consultingHour", value = "门诊时间(135上午,1上午,3下午)", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "type", value = "1:中医2：西医", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "province", value = "省名", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "city", value = "市名", dataType = "String")
    })
    public Response insert(
            @RequestParam(value = "drName") String drName,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "idNo", required = false) String idNo,
            @RequestParam(value = "hospital", required = false) String hospital,
            @RequestParam(value = "chiefNo", required = false) String chiefNo,
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "practiceProfile", required = false) String practiceProfile,
            @RequestParam(value = "signature", required = false) String signature,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "drTitleCert", required = false) String drTitleCert,
            @RequestParam(value = "drPracticeRegCert", required = false) String drPracticeRegCert,
            @RequestParam(value = "consultingHour", required = false) String consultingHour,
            @RequestParam(value = "disableFlag", required = false) Integer disableFlag,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "province", required = false) String province,
            @RequestParam(value = "city", required = false) String city) {
        Response response = doctorService.insert(drName, phone, idNo, hospital, chiefNo, age,
                practiceProfile, signature, title, drTitleCert, drPracticeRegCert,
                consultingHour, disableFlag,type,province,city);
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
            @ApiImplicitParam(paramType="query", name = "drNo", value = "医生编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "drName", value = "医生名字", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "phone", value = "医生电话", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "idNo", value = "医生身份证号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "hospital", value = "所属医院", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "chiefNo", value = "科室", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "age", value = "年龄", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "practiceProfile", value = "执业情况简介", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "signature", value = "医生签章", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "title", value = "职称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "drTitleCert", value = "医师资格证号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "drPracticeRegCert", value = "医师执业证号码\t 执业医师注册证", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "consultingHour", value = "门诊时间(135上午,1上午,3下午)", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "type", value = "1:中医2：西医", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "province", value = "省名", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "city", value = "市名", dataType = "String")
    })
    public Response update(
            @RequestParam(value = "drNo") Long drNo,
            @RequestParam(value = "drName", required = false) String drName,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "idNo", required = false) String idNo,
            @RequestParam(value = "hospital", required = false) String hospital,
            @RequestParam(value = "chiefNo", required = false) String chiefNo,
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "practiceProfile", required = false) String practiceProfile,
            @RequestParam(value = "signature", required = false) String signature,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "drTitleCert", required = false) String drTitleCert,
            @RequestParam(value = "drPracticeRegCert", required = false) String drPracticeRegCert,
            @RequestParam(value = "consultingHour", required = false) String consultingHour,
            @RequestParam(value = "disableFlag", required = false) Integer disableFlag,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "province", required = false) String province,
            @RequestParam(value = "city", required = false) String city) {
        Response response = doctorService.update(drNo, drName, phone, idNo, hospital, chiefNo, age,
                practiceProfile, signature, title, drTitleCert, drPracticeRegCert,
                consultingHour, disableFlag,type,province,city);
        return response;
    }

    /**
     * 医生修改个人信息
     *
     * @return
     */
    @PostMapping("/doctorUpdate")
    @ApiOperation(value = "医生修改个人信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drNo", value = "医生名字", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "drName", value = "医生名字", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "phone", value = "医生电话", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "idNo", value = "医生身份证号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "hospital", value = "所属医院", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "chiefNo", value = "科室", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "age", value = "年龄", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "practiceProfile", value = "执业情况简介", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "signature", value = "医生签章", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "title", value = "职称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "drTitleCert", value = "医师资格证号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "drPracticeRegCert", value = "医师执业证号码\t 执业医师注册证", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "consultingHour", value = "门诊时间(135上午,1上午,3下午)", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "code", value = "短信验证码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "province", value = "省名", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "city", value = "市名", dataType = "String")

    })
    public Response doctorUpdate(
            @RequestParam(value = "drNo") Long drNo,
            @RequestParam(value = "drName", required = false) String drName,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "idNo", required = false) String idNo,
            @RequestParam(value = "hospital", required = false) String hospital,
            @RequestParam(value = "chiefNo", required = false) String chiefNo,
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "practiceProfile", required = false) String practiceProfile,
            @RequestParam(value = "signature", required = false) String signature,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "drTitleCert", required = false) String drTitleCert,
            @RequestParam(value = "drPracticeRegCert", required = false) String drPracticeRegCert,
            @RequestParam(value = "consultingHour", required = false) String consultingHour,
            @RequestParam(value = "disableFlag", required = false) Integer disableFlag,
            @RequestParam(value = "code") String code,
            @RequestParam(value = "province", required = false) String province,
            @RequestParam(value = "city", required = false) String city) {
        Response response = doctorService.doctorUpdate(drNo, drName, phone, idNo, hospital, chiefNo, age,
                practiceProfile, signature, title, drTitleCert, drPracticeRegCert,
                consultingHour, disableFlag,code,province,city);
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
     * 查询医生
     *
     * @return
     */
    @PostMapping("/selectByDepartMent")
    @ApiOperation(value = "查询医生(按科室分类)", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drName", value = "医生名字", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "deptName", value = "科室名称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "city", value = "市名", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "rows", value = "显示条数", dataType = "Integer")
    })
    public Response selectByDepartMent(
            @RequestParam(value = "drName", required = false) String drName,
            @RequestParam(value = "deptName", required = false) String deptName,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value="page", required=false, defaultValue="1") Integer page,
            @RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
        Response response = doctorService.selectByDepartMent(drName,deptName,city,page,rows);
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
     * 医生点击待处理按钮
     *
     * @return
     */
    @PostMapping("/selectMedRecord")
    @ApiOperation(value = "医生点击待处理按钮", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "medRecordNo", value = "病例编号", dataType = "Long")
    })
    public Response selectMedRecord(
            @RequestParam(value = "medRecordNo") Long medRecordNo) {
        Response response = doctorService.selectMedRecord(medRecordNo);
        return response;
    }

    /**
     * 医生修改处方诊断、治疗计划
     *
     * @return
     */
    @PostMapping("/updateDiseaseMaster")
    @ApiOperation(value = "医生修改处方诊断、治疗计划,data返回对应处方模板", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "medRecordNo", value = "病例编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "dNo", value = "疾病编号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "plan", value = "治疗计划", dataType = "String")
    })
    public Response updateDiseaseMaster(
            @RequestParam(value = "medRecordNo") Long medRecordNo,
            @RequestParam(value = "dNo") String dNo,
            @RequestParam(value = "plan") String plan
            ) {
        Response response = doctorService.updateDiseaseMaster(medRecordNo,dNo,plan);
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
     * 医生删除处方模板
     *
     * @return
     */
    @PostMapping("/deleteTemplate")
    @ApiOperation(value = "医生修改处方模板", httpMethod = "POST")
    @ApiImplicitParams({
    @ApiImplicitParam(paramType="query", name = "templateNo", value = "模板编号", dataType = "Long")
            })
    public Response deleteTemplate(
            @RequestParam(value = "templateNo") Long templateNo) {
        Response response = doctorService.deleteTemplate(templateNo);
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
            @ApiImplicitParam(paramType="query", name = "dNo", value = "疾病编号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "templateType", value = "模板类型", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "templateName", value = "模板名称", dataType = "String")
    })
    public Response selectTemplates(
            @RequestParam(value = "drNo") Long drNo,
            @RequestParam(value = "dNo",required = false) String dNo,
            @RequestParam(value = "templateType",required = false) Integer templateType,
            @RequestParam(value = "templateName",required = false) String templateName
            ) {
        Response response = doctorService.selectTemplates(drNo,dNo,templateType,templateName);
        return response;
    }


    /**
     * 医生查询处方模板
     *
     * @return
     */
    @PostMapping("/selectTemplates1")
    @ApiOperation(value = "医生查询处方模板1", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drNo", value = "医生编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "dNo", value = "疾病编号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "templateType", value = "模板类型", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "templateName", value = "模板名称", dataType = "String")
    })
    public Response selectTemplates1(
            @RequestParam(value = "drNo") Long drNo,
            @RequestParam(value = "dNo",required = false) String dNo,
            @RequestParam(value = "templateType",required = false) Integer templateType,
            @RequestParam(value = "templateName",required = false) String templateName
    ) {
        Response response = doctorService.selectTemplates1(drNo,dNo,templateType,templateName);
        return response;
    }


    /**
     * 根据模板编号查询模板
     *
     * @return
     */
    @PostMapping("/selectTemplatesByNo")
    @ApiOperation(value = "根据模板编号查询模板", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "templateNo", value = "患者编号", dataType = "Long")
    })
    public Response selectTemplatesByNo(
            @RequestParam(value = "templateNo") Long templateNo) {
        Response response = doctorService.selectTemplatesByNo(templateNo);
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
            @ApiImplicitParam(paramType="query", name = "orderType", value = "处方单类型 1:中药2：西药", dataType = "Integer"),
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
    @PostMapping("/binding")
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
     * 医生通过手机号绑定病人
     *
     * @return
     */
    @PostMapping("/phoneBinding")
    @ApiOperation(value = "医生通过手机号绑定病人", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "phone", value = "患者手机号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "drNo", value = "drNo", dataType = "Long")
    })
    public Response phoneBinding(
            @RequestParam(value = "phone") String phone,
            @RequestParam(value="drNo") Long drNo) {
        Response response = doctorService.phoneBinding(phone,drNo);
        return response;
    }

    /**
     * 医生查询诊断下拉框
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


    /**
     * 根据疾病编号查询疾病
     *
     * @return
     */
    @PostMapping("/selectDisease")
    @ApiOperation(value = "根据疾病编号查询疾病", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "dNo", value = "疾病编号", dataType = "Long")
    })
    public Response selectDisease(
            @RequestParam(value="dNo") Long dNo) {
        Response response = doctorService.selectDisease(dNo);
        return response;
    }



    /**
     * 获取二维码
     *
     * @return
     */
    @GetMapping(value = "/wxQRCode")
    @ApiOperation(value = "医生二维码接口", httpMethod = "GET")
    public Response wxQRCode(
            @ApiParam(name = "drNo", value = "医生编号", required = true) @RequestParam("drNo") Long drNo) {
        Response response = doctorService.wxQRCode(drNo);
        return response;
    }


    /**
     * 医生查询个人信息
     *
     * @return
     */
    @GetMapping(value = "/selectDrInfo")
    @ApiOperation(value = "医生查询个人信息", httpMethod = "GET")
    public Response selectDrInfo(
            @ApiParam(name = "drNo", value = "医生编号", required = true)
            @RequestParam("drNo") Long drNo) {
        Response response = doctorService.selectDrInfo(drNo);
        return response;
    }


    /**
     * 医生查询我的病人
     *
     * @return
     */
    @PostMapping("/selectPt")
    @ApiOperation(value = "医生查询我的病人", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "name", value = "患者姓名", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "phone", value = "患者手机号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "drNo", value = "医生编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "rows", value = "显示条数", dataType = "Integer")
    })
    public Response selectPt(
            @RequestParam(value = "name", required=false) String name,
            @RequestParam(value = "phone", required=false) String phone,
            @RequestParam(value="drNo") Long drNo,
            @RequestParam(value="page", required=false, defaultValue="1") Integer page,
            @RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
        Response response = doctorService.selectPt(name,phone,drNo,page,rows);
        return response;
    }

    /**
     * 病人详情病例列表
     *
     * @return
     */
    @PostMapping("/selectPtRecordList")
    @ApiOperation(value = "病人详情病例列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "ptNo", value = "患者编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "drNo", value = "医生编号", dataType = "Long")
    })
    public Response selectPtRecordList(
            @RequestParam(value = "ptNo", required=false) Long ptNo,
            @RequestParam(value = "drNo", required=false) Long drNo) {
        Response response = doctorService.selectPtRecordList(ptNo,drNo);
        return response;
    }


    /**
     * 统计
     *
     * @return
     */
    @PostMapping("/count")
    @ApiOperation(value = "统计", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "name", value = "患者姓名或电话", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "drNo", value = "医生编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "startDate", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "drugNo", value = "药品编号", dataType = "Long")
    })
    public Response count(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value="drNo") Long drNo,
            @RequestParam(value="startDate",required = false) String startDate,
            @RequestParam(value = "drugNo",required = false) Long drugNo) {
        Response response = doctorService.count(name,drNo,startDate,drugNo);
        return response;
    }

    /**
     * 医生修改密码
     *
     * @return
     */
    @PostMapping("/updatePassword")
    @ApiOperation(value = "医生修改密码", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "phone", value = "患者手机号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "drNo", value = "医生编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "oldPassword", value = "原密码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "newPassword", value = "新密码", dataType = "String")
    })
    public Response updatePassword(
            @RequestParam(value = "phone") String phone,
            @RequestParam(value="drNo") Long drNo,
            @RequestParam(value="oldPassword") String oldPassword,
            @RequestParam(value="newPassword") String newPassword) {
        Response response = doctorService.updatePassword(phone,drNo,oldPassword,newPassword);
        return response;
    }

    /**
     * 处方导入查询
     *
     * @return
     */
    @PostMapping("/selectMaxOrderTime")
    @ApiOperation(value = "处方导入查询", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "ptNo", value = "患者编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "drNo", value = "医生编号", dataType = "Long")
    })
    public Response selectMaxOrderTime(
            @RequestParam(value = "ptNo") Long ptNo,
            @RequestParam(value = "drNo") Long drNo) {
        Response response = doctorService.selectMaxOrderTime(ptNo,drNo);
        return response;
    }

    /**
     * 医生注册
     *
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "医生注册", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drName", value = "医生名字", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "phone", value = "医生电话", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "hospital", value = "第一执业点医疗机构", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "chiefNo", value = "科室", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "practiceProfile", value = "执业情况简介", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "title", value = "职称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "type", value = "1:中医2：西医", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "disPhone", value = "代表手机号码", dataType = "String")
    })
    public Response register(
            @RequestParam(value = "drName") String drName,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "hospital", required = false) String hospital,
            @RequestParam(value = "chiefNo", required = false) String chiefNo,
            @RequestParam(value = "practiceProfile", required = false) String practiceProfile,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "disPhone", required = false) String disPhone) {
        Response response = doctorService.register(drName,phone,hospital,chiefNo,practiceProfile,title,type,disPhone);
        return response;
    }


    /**
     * 医生查询可提现金额
     *
     * @return
     */
    @PostMapping("/selectPacket")
    @ApiOperation(value = "医生查询可提现金额", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drNo", value = "医生编号", dataType = "Long")
    })
    public Response selectPacket(
            @RequestParam(value = "drNo") Long drNo) {
        Response response = doctorService.selectPacket(drNo);
        return response;
    }




}
