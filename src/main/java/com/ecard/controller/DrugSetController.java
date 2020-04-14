package com.ecard.controller;

import com.ecard.entity.DrugSet;
import com.ecard.pojo.Response;
import com.ecard.service.DrugSetService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "药品Controller", tags = { "药品操作接口" })
@RequestMapping("/drugset")
@CrossOrigin
public class DrugSetController {
    @Autowired
    private DrugSetService drugSetService;

    /**
     * 新增药品
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增药品", httpMethod = "POST")
    public Response insert(@RequestBody DrugSet drugSet) {
        Response response = drugSetService.insert(drugSet);
        return response;
    }

    /**
     * 修改药品
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改药品", httpMethod = "POST")
    public Response update(@RequestBody DrugSet drugSet) {
        Response response = drugSetService.update(drugSet);
        return response;
    }

    /**
     * 查询药品
     */
    @PostMapping("/select")
    @ApiOperation(value = "查询药品", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "departmentName", value = "科室名称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "category", value = "药物大分类  参见区分表 1：中药2：西药", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "keyword1", value = "拼音码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "version", value = "1：非商品 2：商品4:优惠商品", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "drugNo", value = "药品编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "rows", value = "显示条数", dataType = "Integer")
    })
    public Response select(
            @RequestParam(value = "departmentName", required = false) String departmentName,
            @RequestParam(value = "category", required = false) Integer category,
            @RequestParam(value = "keyword1", required = false) String keyword1,
            @RequestParam(value = "version", required = false) Integer version,
            @RequestParam(value = "drugNo", required = false) Long drugNo,
            @RequestParam(value = "disableFlag", required = false) Integer disableFlag,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "rows", required = false, defaultValue = "999") Integer rows
    ) {
        Response response = drugSetService.select(departmentName,category,keyword1,version,disableFlag,drugNo,page,rows);
        return response;
    }


    /**
     * 查询自助下单列表
     */
    @PostMapping("/selectZZXDList")
    @ApiOperation(value = "查询自助下单列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drugNo", value = "药品编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "rows", value = "显示条数", dataType = "Integer")
    })
    public Response selectZZXDList(
            @RequestParam(value = "drugNo", required = false) Long drugNo,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "rows", required = false, defaultValue = "999") Integer rows
    ) {
        Response response = drugSetService.selectZZXDList(drugNo,page,rows);
        return response;
    }

    /**
     * 查询药品详情
     */
    @PostMapping("/selectInfo")
    @ApiOperation(value = "查询药品详情", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drugSetNo", value = "药品编号", dataType = "Long")
    })
    public Response selectInfo(
            @RequestParam(value = "drugSetNo") Long drugSetNo
    ) {
        Response response = drugSetService.selectInfo(drugSetNo);
        return response;
    }


    /**
     * 查询药品邮费规则
     */
    @PostMapping("/selectPostage")
    @ApiOperation(value = "查询药品邮费规则", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drugSetNo", value = "药品编号或商品编号", dataType = "Long")
    })
    public Response selectPostage(
            @RequestParam(value = "drugSetNo") Long drugSetNo
    ) {
        Response response = drugSetService.selectPostage(drugSetNo);
        return response;
    }


}
