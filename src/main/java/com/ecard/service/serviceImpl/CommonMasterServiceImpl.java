package com.ecard.service.serviceImpl;

import com.ecard.dao.CommonMasterMapper;
import com.ecard.entity.CommonMaster;
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
    public Response select(Integer bigCategoryNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            List<CommonMaster> smallCategoryNames = commonMasterMapper.selectByNo(bigCategoryNo);
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
}
