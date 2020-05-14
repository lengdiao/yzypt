package com.ecard.controller;

import com.ecard.entity.ReceiptAddress;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.service.PtInfoService;
import io.swagger.annotations.*;
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
     * 患者查询个人信息
     *
     * @return
     */
    @PostMapping("/selectInfo")
    @ApiOperation(value = "患者查询个人信息", httpMethod = "POST")
    public Response selectInfo() {
        Response response = ptInfoService.selectInfo();
        return response;
    }

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
            @ApiImplicitParam(paramType="query", name = "plan", value = "计划P", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "platform", value = "平台标识(1:易臻云2:视界)", dataType = "String")
    })
    public Response insertMedOrder(
            @RequestParam(value = "medRecordNo") String medRecordNo,
            @RequestParam(value = "drNo", required = false) Long drNo,
            @RequestParam(value = "diagContent", required = false) String diagContent,
            @RequestParam(value = "subjective", required = false) String subjective,
            @RequestParam(value = "objective", required = false) String objective,
            @RequestParam(value = "plan", required = false) String plan,
            @RequestParam(value = "platform") String platform) {
        Response response = ptInfoService.insertMedOrder(medRecordNo, drNo, diagContent, subjective, objective, plan,platform);
        return response;
    }

    /**
     * 病人查询自己的处方单列表
     *
     *
     */
    @ApiOperation(value="病人查询自己的处方单列表")
    @PostMapping("/selectMedOrderList")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "payStatus", value = "支付标识（0支付1未支付）", dataType = "Integer")
    })
    public Response selectMedOrderList(
            @RequestParam(value = "payStatus",required = false) Integer payStatus
    ) {
        Response response = ptInfoService.selectMedOrderList(payStatus);
        return response;
    }


    /**
     * 病人查询处方单详情
     *
     * @return
     */
    @ApiOperation(value="病人查询处方单详情")
    @PostMapping("/selectMedOrderInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "medOrderNo", value = "处方单编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "medRecordNo", value = "病例编号", dataType = "Long")
    })
    public Response selectMedOrderInfo(
            @RequestParam(value = "medOrderNo",required = false) Long medOrderNo,
            @RequestParam(value = "medRecordNo",required = false) Long medRecordNo
    ) {
        Response response = ptInfoService.selectMedOrderInfo(medOrderNo,medRecordNo);
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
            @ApiImplicitParam(paramType="query", name = "idNo", value = "身份证号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "height", value = "身高", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "weight", value = "体重", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "phone", value = "电话号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "code", value = "验证码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "url", value = "跳转地址", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "drugSetNo", value = "药品编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "goodsNo", value = "商品编号", dataType = "Long")
    })
    @PostMapping("/register")
    public Response register(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "sex" ,required = false) String sex,
            @RequestParam(value = "idNo") String idNo,
            @RequestParam(value = "height" ,required = false) String height,
            @RequestParam(value = "weight" ,required = false) String weight,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "code") String code,
            @RequestParam(value = "url" ,required = false) String url,
            @RequestParam(value = "drugSetNo" ,required = false) String drugSetNo,
            @RequestParam(value = "goodsNo" ,required = false) String goodsNo) {
        Response response = ptInfoService.register(name,sex,idNo,height,weight,phone,code,url,drugSetNo,goodsNo);
        return response;
    }

    /**
     * 患者修改信息
     *
     * @return
     */
    @ApiOperation(value="患者修改信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "ptNo", value = "患者编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "name", value = "姓名", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "sex", value = "性别", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "idNo", value = "身份证号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "height", value = "身高", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "weight", value = "体重", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "phone", value = "电话号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "code", value = "验证码", dataType = "String")
    })
    @PostMapping("/ptInfoUpdate")
    public Response ptInfoUpdate(
            @RequestParam(value = "ptNo") Long ptNo,
            @RequestParam(value = "name" ,required = false) String name,
            @RequestParam(value = "sex" ,required = false) String sex,
            @RequestParam(value = "idNo" ,required = false) String idNo,
            @RequestParam(value = "height" ,required = false) String height,
            @RequestParam(value = "weight" ,required = false) String weight,
            @RequestParam(value = "phone" ,required = false) String phone,
            @RequestParam(value = "code") String code) {
        Response response = ptInfoService.ptInfoUpdate(ptNo,name,sex,idNo,height,weight,phone,code);
        return response;
    }

    /**
     * 管理员修改患者信息
     *
     * @return
     */
    @ApiOperation(value="管理员修改患者信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "ptNo", value = "患者编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "name", value = "姓名", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "sex", value = "性别", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "idNo", value = "身份证号码", dataType = "String"),
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
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "idNo" ,required = false) String idNo,
            @RequestParam(value="page", required=false, defaultValue="1") Integer page,
            @RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
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
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "rows", value = "显示条数", dataType = "Integer")
    })
    public Response selectPtInfo(
            @RequestParam(value="page", required=false, defaultValue="1") Integer page,
            @RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
        Response response = ptInfoService.selectDrList(page,rows);
        return response;
    }


    /**
     * 病人扫码绑定医生
     *
     * @return
     */
    @ApiOperation(value="病人扫码绑定医生")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drNo", value = "医生编号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "openId", value = "openId", dataType = "String")
            })
    @PostMapping("/binging")
    public Response binging(
            @RequestParam(value = "drNo") String drNo,
            @RequestParam(value = "openId") String openId) {
        Response response = ptInfoService.binging(drNo,openId);
        return response;
    }


    /***
     * 新增收货地址
     *
     * @param ra
     * @return
     */
    @PostMapping(value = "insertReceiptAddress")
    @ApiOperation(value = "新增收货地址", httpMethod = "POST")
    public Response insertReceiptAddress(@RequestBody ReceiptAddress ra) {
        ResponseHasData response = ptInfoService.insertReceiptAddress(ra);
        return response;
    }

    /**
     * 修改收货地址
     *
     * @param ra
     * @return
     */
    @PostMapping(value = "updateReceiptAddress")
    @ApiOperation(value = "修改收货地址", httpMethod = "POST")
    public Response updateReceiptAddress(@RequestBody ReceiptAddress ra) {
        Response response = ptInfoService.updateReceiptAddress(ra);
        return response;
    }

    /**
     * 删除收货地址
     *
     * @param addId
     * @return
     */
    @PostMapping(value = "deleteReceiptAddress")
    @ApiOperation(value = "删除收货地址", httpMethod = "POST")
    public Response deleteReceiptAddress(@ApiParam(name = "addId", value = "地址编号", required = true) String addId) {
        Response response = ptInfoService.deleteReceiptAddress(addId);
        return response;
    }

    /**
     * 查询收货地址
     *
     * @return
     */
    @GetMapping(value = "selectReceiptAddress")
    @ApiOperation(value = "查询收货地址", httpMethod = "GET")
    public Response selectReceiptAddress() {
        Response response = ptInfoService.selectReceiptAddress();
        return response;
    }

    //全部，未完成，已完成    未发货，未自提，未上传病例
    /**
     * 患者查询订单
     *
     * @return
     */
    @PostMapping(value = "/findMallOrder")
    @ApiOperation(value = "患者查询订单(自主下单用)", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "orderStatus", value = "订单状态(0为已完成，1为未完成,2全部）", dataType = "int")
    })
    public Response findMallOrder(
            @RequestParam(value = "orderStatus") int orderStatus) {
        Response response = ptInfoService.findMallOrder(orderStatus);
        return response;
    }

    /**
     * 查询物流
     *
     * @return
     */
    @PostMapping(value = "/selectShipping")
    @ApiOperation(value = "查询物流", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "orderNo", value = "处方单号", dataType = "Long")
    })
    public Response selectShipping(
            @RequestParam(value = "orderNo") Long orderNo) {
        Response response = ptInfoService.selectShipping(orderNo);
        return response;
    }



}
