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
            @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer")
    })
    @PostMapping("/addDrugStore")
    public Response addDrugStore(
            @RequestParam(value = "drugStoreName") String drugStoreName,
            @RequestParam(value = "contact") String contact,
            @RequestParam(value = "contactPhone") String contactPhone,
            @RequestParam(value="address") String address,
            @RequestParam(value="disableFlag") Integer disableFlag) {
        Response response = drugStoreService.addDrugStore(drugStoreName,contact,contactPhone,address,disableFlag);
        return response;
    }


    //更新药店
    @ApiOperation(value="更新药店")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drugStoreNo", value = "药店编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "drugStoreName", value = "药店名称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "contact", value = "负责人", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "phone", value = "负责人手机号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "oldCode", value = "原手机号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "address", value = "地址", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer")
    })
    @PostMapping("/updateDrugStore")
    public Response updateDrugStore(
            @RequestParam(value = "drugStoreNo") Long drugStoreNo,
            @RequestParam(value = "drugStoreName") String drugStoreName,
            @RequestParam(value = "contact") String contact,
            @RequestParam(value = "phone") String contactPhone,
            @RequestParam(value = "oldCode") String oldCode,
            @RequestParam(value="address") String address,
            @RequestParam(value="disableFlag") Integer disableFlag) {
        Response response = drugStoreService.updateDrugStore(drugStoreNo,drugStoreName,contact,contactPhone,address,disableFlag,oldCode);
        return response;
    }


    //药店列表
    @ApiOperation(value="药店列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drugStoreName", value = "药店名称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "rows", value = "显示个数", dataType = "Integer")
    })
    @PostMapping("/findDrugStore")
    public Response findDrugStore(
            @RequestParam(value = "drugStoreName", required=false) String drugStoreName,
            @RequestParam(value="disableFlag", required=false) Integer disableFlag,
            @RequestParam(value="page", required=false, defaultValue="1") Integer page,
            @RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
        Response response = drugStoreService.findDrugStore(drugStoreName,disableFlag,page,rows);
        return response;
    }

    //药店查询处方单
    @ApiOperation(value="药店查询处方单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "mallNo", value = "订单编号", dataType = "Long")
    })
    @PostMapping("/findMallOrder")
    public Response findMallOrder(
            @RequestParam(value = "mallNo", required=false) Long mallNo) {
        Response response = drugStoreService.findMallOrder(mallNo);
        return response;
    }

    //药店修改处方单状态
    @ApiOperation(value="药店修改处方单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "mallNo", value = "订单编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "drugStoreNo", value = "发货药店编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "orderStatus", value = "订单状态(0为已完成，1为未完成,", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "payStatus", value = "支付状态（0为成功，1为失败）", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "payTime", value = "支付时间", dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "execLoc", value = "药店名称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "status", value = "处方单状态\t 参见区分表0.未完成处方 \\r\\n1:未付款 \\r\\n2:未取药\\r\\n 3.已取药4：处方单驳回5：没药\\r\\n6：处方单失效", dataType = "Integer"),
    })
    @PostMapping("/updateMedOrderStatus")
    public Response updateMedOrderStatus(
            @RequestParam(value = "mallNo", required=false) Long mallNo,
            @RequestParam(value = "drugStoreNo", required=false) Long drugStoreNo,
            @RequestParam(value = "orderStatus", required=false) Integer orderStatus,
            @RequestParam(value = "payStatus", required=false) Integer payStatus,
            @RequestParam(value = "payTime", required=false) Date payTime,
            @RequestParam(value = "execLoc", required=false) String execLoc,
            @RequestParam(value = "status", required=false) Integer status) {
        Response response = drugStoreService.updateMedOrderStatus(mallNo,drugStoreNo,orderStatus,payStatus,payTime,execLoc,status);
        return response;
    }


}
