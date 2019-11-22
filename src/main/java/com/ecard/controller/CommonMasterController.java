package com.ecard.controller;

import com.ecard.pojo.Response;
import com.ecard.service.CommonMasterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "通用数据Controller", tags = { "通用数据操作接口" })
@RequestMapping("/commonMaster")
@CrossOrigin
public class CommonMasterController {
    @Autowired
    private CommonMasterService commonMasterService;

    /**
     * 给药频率下拉框选项
     *
     * @return
     */
    @PostMapping("/selectUsage")
    @ApiOperation(value = "给药频率下拉框选项", httpMethod = "POST")
    public Response selectUsage() {
        Response response = commonMasterService.selectUsage();
        return response;
    }

    /**
     * 给药途径下拉框选项
     *
     * @return
     */
    @PostMapping("/selectWay")
    @ApiOperation(value = "给药途径下拉框选项", httpMethod = "POST")
    public Response selectWay() {
        Response response = commonMasterService.selectWay();
        return response;
    }

    /**
     * 给药时机下拉框选项
     *
     * @return
     */
    @PostMapping("/selectExecWhen")
    @ApiOperation(value = "给药时机下拉框选项", httpMethod = "POST")
    public Response selectExecWhen() {
        Response response = commonMasterService.selectExecWhen();
        return response;
    }

    /**
     * 代煎方式下拉框选项
     *
     * @return
     */
    @PostMapping("/selectDecoctionWay")
    @ApiOperation(value = "代煎方式下拉框选项", httpMethod = "POST")
    public Response selectDecoctionWay() {
        Response response = commonMasterService.selectDecoctionWay();
        return response;
    }
}
