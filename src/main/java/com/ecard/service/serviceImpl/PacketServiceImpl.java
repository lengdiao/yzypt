package com.ecard.service.serviceImpl;

import com.ecard.dao.DrugSetMapper;
import com.ecard.dao.RedpacketRecordMapper;
import com.ecard.dao.RedpacketRuleMapper;
import com.ecard.entity.RedpacketRecord;
import com.ecard.entity.RedpacketRule;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.queryResult.DrInfoQr;
import com.ecard.service.PacketService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PacketServiceImpl implements PacketService {
    @Autowired
    private RedpacketRuleMapper redpacketRuleMapper;
    @Autowired
    private RedpacketRecordMapper redpacketRecordMapper;
    @Autowired
    private DrugSetMapper drugSetMapper;

    @Override
    public Response insertPacket(RedpacketRule redpacketRule) {
        ResponseHasData response = new ResponseHasData();
        try {
            redpacketRuleMapper.insertSelective(redpacketRule);
            response.setMsg("新增成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("新增失败");
            response.setStatus(1);
        }
        return response;
    }

    @Override
    public Response updatePacket(RedpacketRule redpacketRule) {
        ResponseHasData response = new ResponseHasData();
        try {
            redpacketRuleMapper.updateByPrimaryKeySelective(redpacketRule);
            response.setMsg("修改成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("修改失败");
            response.setStatus(1);
        }
        return response;
    }


    public Response selectPacket(Long packetId, Long drugNo, Integer page, Integer rows) {
        ResponseHasData response = new ResponseHasData();
        try {
            Page<?> pa =  PageHelper.startPage(page, rows);
            List<RedpacketRule> redpacketRules = redpacketRuleMapper.selectPacketByIdDrugNo(packetId,drugNo);
            for(RedpacketRule redpacketRule : redpacketRules){
                redpacketRule.setVersion(drugSetMapper.selectByPrimaryKey(redpacketRule.getDrugNo()).getVersion());
            }
            //查询结果总数
            Long total = pa.getTotal();
            //创建分页条件
            PageInfo<RedpacketRule> pageData = new PageInfo<RedpacketRule>(redpacketRules,total.intValue());

            response.setData(pageData);
            response.setMsg("查询成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("查询失败");
            response.setStatus(1);
        }
        return response;
    }


    public Response selectUsablePacket(Long ptNo, Long drugNo, Integer page, Integer rows) {
        ResponseHasData response = new ResponseHasData();
        try {
            Page<?> pa =  PageHelper.startPage(page, rows);
            List<RedpacketRecord> redpacketRecords = redpacketRecordMapper.selectByPtNoAndDrugNo(ptNo,drugNo);
            //查询结果总数
            Long total = pa.getTotal();
            //创建分页条件
            PageInfo<RedpacketRecord> pageData = new PageInfo<RedpacketRecord>(redpacketRecords,total.intValue());

            response.setData(pageData);
            response.setMsg("查询成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("查询失败");
            response.setStatus(1);
        }
        return response;
    }


    public Response deletePacket(Long packetId) {
        ResponseHasData response = new ResponseHasData();
        try {
            redpacketRuleMapper.deleteByPrimaryKey(packetId);
            response.setMsg("删除成功");
            response.setStatus(0);
        } catch (Exception e) {
            response.setMsg("删除失败");
            response.setStatus(1);
            e.printStackTrace();
        }
        return response;
    }
}
