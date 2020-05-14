package com.ecard.controller;


import com.ecard.ProjectConst;
import com.ecard.dao.PtOpenMapper;
import com.ecard.entity.MallOrder;
import com.ecard.entity.PtOpen;
import com.ecard.entity.UserQuestion;
import com.ecard.entity.WeiXinUser;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.queryResult.MallOrderQr;
import com.ecard.pojo.queryResult.MallOrderQrr;
import com.ecard.pojo.queryResult.UserQuestionQr;
import com.ecard.service.BuyService;
import com.ecard.utils.WeiXinUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "自主下单Controller", tags = { "自助下单操作接口" })
@RequestMapping("/buy")
@CrossOrigin(allowCredentials="true",maxAge = 3600)
public class BuyController {
    @Autowired
    private BuyService buyService;
    @Autowired
    private PtOpenMapper ptOpenMapper;

    /**
     * 创建订单
     *
     * @return
     */
    @PostMapping("/insertGoods")
    @ApiOperation(value = "创建订单", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "goodsNo", value = "商品编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "number", value = "购买数量", dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "remark", value = "订单备注", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "platform", value = "平台标识(1:易臻云2:视界)", dataType = "int")
    })
    public Response insertGoods(
            @RequestParam(value = "goodsNo") Long goodsNo,
            @RequestParam(value = "number") Long number,
            @RequestParam(value = "remark" ,required = false) String remark,
            @RequestParam(value = "platform") int platform) {
        Response response = buyService.insertGoods(goodsNo,number,remark,platform);
        return response;
    }

    /**
     * 查询是否已经录入问卷
     *
     * @return
     */
    @PostMapping("/isNullQuestion")
    @ApiOperation(value = "查询是否已经录入问卷(0:未填过、1：已填过)", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "goodsNo", value = "商品编号", dataType = "Long")
    })
    public Response isNullQuestion(
            @RequestParam(value = "goodsNo") Long goodsNo) {
        Response response = buyService.isNullQuestion(goodsNo);
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
        Response response = buyService.selectQuestion(goodsNo);
        return response;
    }

    /**
     * 录入问卷
     *
     * @return
     */
    @PostMapping("/insertQuestion")
    @ApiOperation(value = "录入问卷", httpMethod = "POST")
    public Response insertQuestion(
            @RequestBody UserQuestionQr userQuestion) {
        Response response = buyService.insertQuestion(userQuestion);
        return response;
    }

    /**
     * 修改订单
     *
     * @return
     */
    @PostMapping("/updateMallOrder")
    @ApiOperation(value = "修改订单", httpMethod = "POST")
    public Response updateMallOrder(
            @RequestBody MallOrderQr mallOrderQr) {
        Response response = buyService.updateMallOrder(mallOrderQr);
        return response;
    }


    /**
     * 立即支付
     *
     * @return
     */
    @PostMapping("/updateShippingStatus")
    @ApiOperation(value = "立即支付", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "mallNo", value = "订单编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "shippingStatus", value = "配送状态(1.未配送，0.已配送 , 2.未自提 ，3.已自提)", dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "address", value = "地址编号", dataType = "Long")
    })
    public Response updateShippingStatus(
            @RequestParam(value = "mallNo") Long mallNo,
            @RequestParam(value = "shippingStatus") int shippingStatus,
            @RequestParam(value = "address", required=false) Long address) {
        return buyService.updateShippingStatus(mallNo,shippingStatus,address);
    }


    /**
     * 保存病例图片按钮
     *
     * @return
     */
    @PostMapping("/saveMrPicture")
    @ApiOperation(value = "保存病例图片按钮", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "mallNo", value = "订单编号", dataType = "Long")
    })
    public Response saveMrPicture(
            @RequestParam(value = "mallNo") Long mallNo) {
        return buyService.saveMrPicture(mallNo);
    }

    /**
     * 支付
     * @param
     * @throws Exception
     */
    @GetMapping(value = "/wxpay")
    public void wxpay(@RequestParam("mallNo") String mallNo,
                      @RequestParam(value = "packetId",required = false) Long packetId) throws Exception {
        buyService.wxpay(mallNo,packetId);
    }


    //支付成功通知
    @ApiOperation(value="支付成功通知")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "mallNo", value = "订单编号", dataType = "Long")
    })
    @PostMapping("/notice")
    public Response notice(@RequestParam(value="mallNo") Long mallNo) throws Exception {
        return buyService.notice(mallNo);
    }


    @ApiOperation(value="判断是否需要不全信息")
    @PostMapping("/isRegister")
    public Response isRegister() {
        return buyService.isRegister();
    }



    //--------------------------视界----------------------------
    @ApiOperation(value="创建视界订单")
    @PostMapping("/createSJMallOrder")
    public Response createSJMallOrder(@RequestBody MallOrderQrr mallOrderQrr) {
        return buyService.createSJMallOrder(mallOrderQrr);
    }





}
