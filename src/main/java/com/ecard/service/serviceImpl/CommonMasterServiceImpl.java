package com.ecard.service.serviceImpl;

import com.ecard.dao.CommonMasterMapper;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.service.CommonMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommonMasterServiceImpl implements CommonMasterService {
    @Autowired
    private CommonMasterMapper commonMasterMapper;

    @Override
    public Response selectUsage() {
        ResponseHasData response = new ResponseHasData();
        try {
            List<String> smallCategoryNames = commonMasterMapper.selectByNo(7);
            response.setMsg("查询成功");
            response.setData(smallCategoryNames);
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("查询失败");
            response.setStatus(1);
        }
        return response;
    }

    @Override
    public Response selectWay() {
        ResponseHasData response = new ResponseHasData();
        try {
            List<String> ways = commonMasterMapper.selectByNo(8);
            response.setMsg("查询成功");
            response.setData(ways);
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("查询失败");
            response.setStatus(1);;
        }
        return response;
    }

    @Override
    public Response selectExecWhen() {
        ResponseHasData response = new ResponseHasData();
        try {
            List<String> execWhens = commonMasterMapper.selectByNo(21);
            response.setMsg("查询成功");
            response.setData(execWhens);
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("查询失败");
            response.setStatus(1);
        }
        return response;
    }

    @Override
    public Response selectDecoctionWay() {
        ResponseHasData response = new ResponseHasData();
        try {
            List<String> decoctionWays = commonMasterMapper.selectByNo(31);
            response.setMsg("查询成功");
            response.setData(decoctionWays);
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("查询失败");
            response.setStatus(1);
        }
        return response;
    }
}
