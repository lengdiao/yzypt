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
            @ApiImplicitParam(paramType="query", name = "category", value = "药物大分类  参见区分表\\r\\n1：西药\\r\\n2：中成药\\r\\n3：草药\\r\\n4：化学品\\r\\n5：耗材\\r\\n6：设备\\r\\n7：药妆\\r\\n8：营养品", dataType = "INTEGER"),
            @ApiImplicitParam(paramType="query", name = "keyword1", value = "拼音码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "rows", value = "显示条数", dataType = "Integer")
    })
    public Response select(
            @RequestParam(value = "category", required = false) Integer category,
            @RequestParam(value = "keyword1", required = false) String keyword1,
            @RequestParam(value = "disableFlag", required = false) Integer disableFlag,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows
    ) {
        Response response = drugSetService.select(category,keyword1,disableFlag,page,rows);
        return response;
    }
}
