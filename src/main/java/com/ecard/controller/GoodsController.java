package com.ecard.controller;

import com.ecard.entity.Goods;
import com.ecard.pojo.Response;
import com.ecard.pojo.queryResult.DrugSetGoodsQr;
import com.ecard.pojo.queryResult.GoodsQr;
import com.ecard.pojo.queryResult.QuestionQr;
import com.ecard.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Api(value = "商品Controller", tags = { "商品操作接口" })
@RequestMapping("/goods")
@CrossOrigin
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 添加商品问卷模板
     */
    @PostMapping("/insertTemplate")
    @ApiOperation(value = "添加商品问卷模板", httpMethod = "POST")
    public Response insertTemplate(@RequestBody List<QuestionQr> template) {
        Response response = goodsService.insertTemplate(template);
        return response;
    }


    /**
     * 修改商品问卷模板
     */
    @PostMapping("/updateTemplate")
    @ApiOperation(value = "修改商品问卷模板", httpMethod = "POST")
    public Response updateTemplate(@RequestBody List<QuestionQr> template) {
        Response response = goodsService.updateTemplate(template);
        return response;
    }


    /**
     * 添加商品
     */
    @PostMapping("/insert")
    @ApiOperation(value = "添加商品", httpMethod = "POST")
    public Response insert(@RequestBody GoodsQr goodsQr) {
        Response response = goodsService.insert(goodsQr);
        return response;
    }


    /**
     * 修改商品
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改商品", httpMethod = "POST")
    public Response update(@RequestBody GoodsQr goodsQr) {
        Response response = goodsService.update(goodsQr);
        return response;
    }


    /**
     * 查询问卷
     *
     * @return
     */
    @PostMapping("/selectQuestion")
    @ApiOperation(value = "查询问卷", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "goodsNo", value = "商品编号", dataType = "Long")
    })
    public Response selectQuestion(
            @RequestParam(value = "goodsNo") Long goodsNo) {
        Response response = goodsService.selectQuestion(goodsNo);
        return response;
    }

}
