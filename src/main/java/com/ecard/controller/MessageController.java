package com.ecard.controller;

import com.ecard.dao.MessageMapper;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.service.MessageService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "留言Controller", tags = { "留言操作接口" })
@RequestMapping("/message")
@CrossOrigin
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 根据医生编号和病人编号查询留言，发起人医生
     *
     * @param drNo
     * @param ptNo
     * @return
     */
    @GetMapping(value = "/selectMsgByPtNo")
    @ApiOperation(value = "根据医生编号和病人编号查询留言，发起人患者", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drNo", value = "医生编号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "ptNo", value = "病人编号", dataType = "String")
    })
    public Response selectMsgByPtNo( @RequestParam(value = "drNo") String drNo,
                                    @RequestParam(value = "ptNo") String ptNo) {
        ResponseHasData response = messageService.selectMsgByPtNo(drNo, ptNo);
        return response;
    }

    /**
     * 医生留言给指定病人
     *
     * @param drNo
     * @param ptNo
     * @param messageContent
     * @return
     */
    @PostMapping(value = "/insertMsgByPtNo")
    @ApiOperation(value = "医生留言给指定病人", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drNo", value = "医生编号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "ptNo", value = "病人编号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "messageContent", value = "留言内容", dataType = "String")
    })
    public Response insertMsgByPtNo(@RequestParam(value = "drNo") String drNo,
                                    @RequestParam(value = "ptNo") String ptNo,
                                    @RequestParam(value = "messageContent") String messageContent) {
        ResponseHasData response = messageService.insertMsgByPtNo(drNo, ptNo, messageContent);
        return response;
    }


    /**
     * 根据医生编号和病人编号查询留言，发起人医生
     *
     * @param drNo
     * @param ptNo
     * @return
     */
    @GetMapping(value = "/selectMsgByDrNo")
    @ApiOperation(value = "根据医生编号和病人编号查询留言，发起人医生", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drNo", value = "医生编号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "ptNo", value = "病人编号", dataType = "String")
    })
    public Response selectMsgByDrNo(@RequestParam(value = "drNo") String drNo,
                                    @RequestParam(value = "ptNo") String ptNo) {
        ResponseHasData response = messageService.selectMsgByDrNo(drNo, ptNo);
        return response;
    }

    /**
     * 医生留言给指定病人
     *
     * @param drNo
     * @param ptNo
     * @param messageContent
     * @return
     */
    @PostMapping(value = "/insertMsgByDrNo")
    @ApiOperation(value = "医生留言给指定病人", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "drNo", value = "医生编号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "ptNo", value = "病人编号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "messageContent", value = "留言内容", dataType = "String")
    })
    public Response insertMsgByDrNo(@RequestParam(value = "drNo") String drNo,
                                    @RequestParam(value = "ptNo") String ptNo,
                                    @RequestParam(value = "messageContent") String messageContent) {
        ResponseHasData response = messageService.insertMsgByDrNo(drNo, ptNo, messageContent);
        return response;
    }
}
