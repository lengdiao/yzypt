package com.ecard.controller;

import com.ecard.pojo.Response;
import com.ecard.service.DrugStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Api(value = "药店Controller", tags = { "药店操作接口" })
@RequestMapping("/drugStore")
@CrossOrigin
public class DrugStoreController {
    @Autowired
    private DrugStoreService drugStoreService;

    //新增药店
    @ApiOperation(value="新增药店")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drugStoreName", value = "药店名称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "contact", value = "负责人", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "contactPhone", value = "负责人手机号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "address", value = "地址", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "type", value = "类型", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer")
    })
    @PostMapping("/addDrugStore")
    public Response addDrugStore(
            @RequestParam(value = "drugStoreName") String drugStoreName,
            @RequestParam(value = "contact") String contact,
            @RequestParam(value = "contactPhone") String contactPhone,
            @RequestParam(value="address") String address,
            @RequestParam(value="type") String type,
            @RequestParam(value="disableFlag") Integer disableFlag) {
        Response response = drugStoreService.addDrugStore(drugStoreName,contact,contactPhone,address,type,disableFlag);
        return response;
    }


    //更新药店
    @ApiOperation(value="更新药店")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drugStoreNo", value = "药店编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "drugStoreName", value = "药店名称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "contact", value = "负责人", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "phone", value = "负责人手机号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "address", value = "地址", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "type", value = "类型", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer")
    })
    @PostMapping("/updateDrugStore")
    public Response updateDrugStore(
            @RequestParam(value = "drugStoreNo") Long drugStoreNo,
            @RequestParam(value = "drugStoreName") String drugStoreName,
            @RequestParam(value = "contact") String contact,
            @RequestParam(value = "phone") String contactPhone,
            @RequestParam(value="address") String address,
            @RequestParam(value="type") String type,
            @RequestParam(value="disableFlag") Integer disableFlag) {
        Response response = drugStoreService.updateDrugStore(drugStoreNo,drugStoreName,contact,contactPhone,address,type,disableFlag);
        return response;
    }


    //药店列表
    @ApiOperation(value="药店列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drugStoreName", value = "药店名称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "address", value = "地址", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "type", value = "类型", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "rows", value = "显示个数", dataType = "Integer")
    })
    @PostMapping("/findDrugStore")
    public Response findDrugStore(
            @RequestParam(value = "drugStoreName", required=false) String drugStoreName,
            @RequestParam(value="disableFlag", required=false) Integer disableFlag,
            @RequestParam(value = "address", required=false) String address,
            @RequestParam(value="type", required=false) String type,
            @RequestParam(value="page", required=false, defaultValue="1") Integer page,
            @RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
        Response response = drugStoreService.findDrugStore(drugStoreName,disableFlag,address,type,page,rows);
        return response;
    }

    //药店查询处方单
    @ApiOperation(value="药店查询处方单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "medOrderNo", value = "处方单编号", dataType = "Long")
    })
    @PostMapping("/findMallOrder")
    public Response findMallOrder(
            @RequestParam(value = "medOrderNo") Long medOrderNo) {
        Response response = drugStoreService.findMallOrder(medOrderNo);
        return response;
    }



    //药店修改处方单状态
    @ApiOperation(value="药店修改处方单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "mallNo", value = "订单编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "drugStoreNo", value = "发货药店编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "shippingStatus", value = "配送状态(1.未发货，0.已发货 , 2.未自提 ，3.已自提)", dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "shippingCompany", value = "配送公司", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "shippingNo", value = "快递单号", dataType = "String")
    })
    @PostMapping("/updateMedOrderStatus")
    public Response updateMedOrderStatus(
            @RequestParam(value = "mallNo") Long mallNo,
            @RequestParam(value = "drugStoreNo") Long drugStoreNo,
            @RequestParam(value = "shippingStatus", required=false) int shippingStatus,
            @RequestParam(value = "shippingCompany", required=false) String shippingCompany,
            @RequestParam(value = "shippingNo", required=false) String shippingNo) {
        Response response = drugStoreService.updateMedOrderStatus(mallNo,drugStoreNo
                ,shippingStatus,shippingCompany,shippingNo);
        return response;
    }


    //药店查询未发货订单
    @ApiOperation(value="药店查询未发货订单")
    @PostMapping("/findShippingStatusIs1")
    public Response findShippingStatusIs1() {
        Response response = drugStoreService.findShippingStatusIs1();
        return response;
    }






}
