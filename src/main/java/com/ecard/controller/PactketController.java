package com.ecard.controller;

import com.ecard.entity.RedpacketRule;
import com.ecard.pojo.Response;
import com.ecard.pojo.queryResult.QuestionQr;
import com.ecard.service.PacketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "红包Controller", tags = { "红包操作接口" })
@RequestMapping("/packet")
@CrossOrigin
public class PactketController {

    @Autowired
    private PacketService packetService;

    /**
     * 添加红包规则
     */
    @PostMapping("/insertPacket")
    @ApiOperation(value = "添加红包规则", httpMethod = "POST")
    public Response insertPacket(@RequestBody RedpacketRule redpacketRule) {
        Response response = packetService.insertPacket(redpacketRule);
        return response;
    }

    /**
     * 修改红包规则
     */
    @PostMapping("/updatePacket")
    @ApiOperation(value = "修改红包规则", httpMethod = "POST")
    public Response updatePacket(@RequestBody RedpacketRule redpacketRule) {
        Response response = packetService.updatePacket(redpacketRule);
        return response;
    }

    /**
     * 查询红包规则
     *
     * @return
     */
    @PostMapping("/selectPacket")
    @ApiOperation(value = "查询红包规则", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "packetId", value = "Id", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "drugNo", value = "药品编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "rows", value = "显示条数", dataType = "Integer")
    })
    public Response selectPacket(
            @RequestParam(value = "packetId",required = false) Long packetId,
            @RequestParam(value = "drugNo",required = false) Long drugNo,
            @RequestParam(value="page", required=false, defaultValue="1") Integer page,
            @RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
        Response response = packetService.selectPacket(packetId,drugNo,page,rows);
        return response;
    }

    /**
     * 查询红包可用红包
     *
     * @return
     */
    @PostMapping("/selectUsablePacket")
    @ApiOperation(value = "查询红包可用红包", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "ptNo", value = "患者编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "drugNo", value = "药品编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "rows", value = "显示条数", dataType = "Integer")
    })
    public Response selectUsablePacket(
            @RequestParam(value = "ptNo",required = false) Long ptNo,
            @RequestParam(value = "drugNo",required = false) Long drugNo,
            @RequestParam(value="page", required=false, defaultValue="1") Integer page,
            @RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
        Response response = packetService.selectUsablePacket(ptNo,drugNo,page,rows);
        return response;
    }

    /**
     * 删除红包
     *
     * @return
     */
    @PostMapping("/deletePacket")
    @ApiOperation(value = "删除红包", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "packetId", value = "红包规则编号", dataType = "Long")
    })
    public Response deletePacket(
            @RequestParam(value = "packetId") Long packetId) {
        Response response = packetService.deletePacket(packetId);
        return response;
    }

}
