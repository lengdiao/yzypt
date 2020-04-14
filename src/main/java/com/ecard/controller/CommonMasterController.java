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
     * 通用数据
     *
     * @return
     */
    @PostMapping("/select")
    @ApiOperation(value = "1:费用分类2:药物大分类3:基本药物分类4:管制药品5:高警讯药品7:给药频率8:给药途径13:剂型21:给药时机22:给药目的26:特殊给药时机30:中药特殊用法31:代煎方法80:医保分类", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "bigCategoryNo", value = "通用数据编号", dataType = "Integer")
    })
    public Response select(
            @RequestParam(value = "bigCategoryNo") Integer bigCategoryNo) {
        Response response = commonMasterService.select(bigCategoryNo);
        return response;
    }
}
