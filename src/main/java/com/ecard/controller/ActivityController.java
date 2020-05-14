package com.ecard.controller;

import com.ecard.pojo.Response;
import com.ecard.pojo.queryResult.ActivityDrugSet;
import com.ecard.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "优惠商品Controller", tags = { "优惠商品操作接口" })
@RequestMapping("/activity")
@CrossOrigin
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    /**
     * 新增优惠商品
     *
     * @return
     */
    @PostMapping("/insertActivity")
    @ApiOperation(value = "新增优惠商品", httpMethod = "POST")
    public Response insertGoods(@RequestBody ActivityDrugSet activityDrugSet) {
        Response response = activityService.insertActivity(activityDrugSet);
        return response;
    }

    /**
     * 修改优惠商品
     *
     * @return
     */
    @PostMapping("/updateActivity")
    @ApiOperation(value = "修改优惠商品", httpMethod = "POST")
    public Response updateActivity(@RequestBody ActivityDrugSet activityDrugSet) {
        Response response = activityService.updateActivity(activityDrugSet);
        return response;
    }
}
