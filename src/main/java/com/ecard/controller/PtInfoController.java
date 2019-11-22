package com.ecard.controller;

import com.ecard.pojo.Response;
import com.ecard.service.PtInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "患者Controller", tags = { "患者操作接口" })
@RequestMapping("/ptInfo")
@CrossOrigin
public class PtInfoController {
    @Autowired
    private PtInfoService ptInfoService;

    /**
     * 发起复诊按钮
     *
     * @return
     */
    @PostMapping("/start")
    @ApiOperation(value = "发起复诊按钮", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drNo", value = "医生编号", dataType = "Long")
    })
    public Response insertMedOrder(@RequestParam(value = "drNo") Long drNo) {
        Response response = ptInfoService.start(drNo);
        return response;
    }

    /**
     * 患者录入复诊信息
     *
     * @return
     */
    @PostMapping("/insertMedOrder")
    @ApiOperation(value = "患者录入复诊信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "medRecordNo", value = "病例编号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "drNo", value = "医生编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "diagContent", value = "诊断内容A", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "subjective", value = "主诉S", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "objective", value = "查体O", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "plan", value = "计划P", dataType = "String")
    })
    public Response insertMedOrder(
            @RequestParam(value = "medRecordNo") String medRecordNo,
            @RequestParam(value = "drNo", required = false) Long drNo,
            @RequestParam(value = "diagContent", required = false) String diagContent,
            @RequestParam(value = "subjective", required = false) String subjective,
            @RequestParam(value = "objective", required = false) String objective,
            @RequestParam(value = "plan", required = false) String plan) {
        Response response = ptInfoService.insertMedOrder(medRecordNo, drNo, diagContent, subjective, objective, plan);
        return response;
    }

    /**
     * 添加首诊处方图片
     *
     * @return
     */
    @ApiOperation(value="添加首诊处方图片")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "img", value = "图片", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "name", value = "图片顺序号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "mallNo", value = "订单号", dataType = "Long")
    })
    @PostMapping("/addImg")
    public Response addImg(
            @RequestParam(value = "img") String img,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "mallNo") Long mallNo) {
        Response response = ptInfoService.addImg(img,name,mallNo);
        return response;
    }

    /**
     * 病人查询自己的处方单
     *
     * @return
     */
    @ApiOperation(value="病人查询自己的处方单")
    @PostMapping("/select")
    public Response select() {
        Response response = ptInfoService.select();
        return response;
    }


    /**
     * 患者注册
     *
     * @return
     */
    @ApiOperation(value="患者注册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "name", value = "姓名", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "sex", value = "性别", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "idNo", value = "订单号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "height", value = "身高", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "weight", value = "体重", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "phone", value = "电话号码", dataType = "String")
    })
    @PostMapping("/register")
    public Response register(
            @RequestParam(value = "name" ,required = false) String name,
            @RequestParam(value = "sex" ,required = false) String sex,
            @RequestParam(value = "idNo" ,required = false) String idNo,
            @RequestParam(value = "height" ,required = false) String height,
            @RequestParam(value = "weight" ,required = false) String weight,
            @RequestParam(value = "phone" ,required = false) String phone) {
        Response response = ptInfoService.register(name,sex,idNo,height,weight,phone);
        return response;
    }

    /**
     * 修改患者信息
     *
     * @return
     */
    @ApiOperation(value="修改患者信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "ptNo", value = "患者编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "name", value = "姓名", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "sex", value = "性别", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "idNo", value = "订单号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "height", value = "身高", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "weight", value = "体重", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "phone", value = "电话号码", dataType = "String")
    })
    @PostMapping("/updatePtInfo")
    public Response updatePtInfo(
            @RequestParam(value = "ptNo") Long ptNo,
            @RequestParam(value = "name" ,required = false) String name,
            @RequestParam(value = "sex" ,required = false) String sex,
            @RequestParam(value = "idNo" ,required = false) String idNo,
            @RequestParam(value = "height" ,required = false) String height,
            @RequestParam(value = "weight" ,required = false) String weight,
            @RequestParam(value = "phone" ,required = false) String phone) {
        Response response = ptInfoService.updatePtInfo(ptNo,name,sex,idNo,height,weight,phone);
        return response;
    }


    /**
     * 查询患者列表
     *
     * @return
     */
    @ApiOperation(value="查询患者列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "name", value = "姓名", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "idNo", value = "身份证号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "rows", value = "显示条数", dataType = "Integer")
    })
    @PostMapping("/selectPtInfo")
    public Response selectPtInfo(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "idNo" ,required = false) String idNo,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "rows", required = false) Integer rows) {
        Response response = ptInfoService.selectPtInfo(name,idNo,page,rows);
        return response;
    }

    /**
     * 患者查询可复诊医生
     *
     * @return
     */
    @ApiOperation(value="患者查询可复诊医生")
    @PostMapping("/selectDrList")
    public Response selectPtInfo() {
        Response response = ptInfoService.selectDrList();
        return response;
    }





}
