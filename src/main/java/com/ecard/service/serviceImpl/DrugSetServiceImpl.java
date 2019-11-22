package com.ecard.service.serviceImpl;

import com.ecard.dao.DrugSetMapper;
import com.ecard.entity.DrugSet;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.service.DrugSetService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DrugSetServiceImpl implements DrugSetService {
    @Autowired
    private DrugSetMapper drugSetMapper;

    @Override
    public Response insert(DrugSet drugSet) {
        ResponseHasData response = new ResponseHasData();
        try {
            drugSetMapper.insertSelective(drugSet);
            response.setStatus(0);
            response.setMsg("新增药品成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("新增药品失败");
        }
        return response;
    }

    @Override
    public Response update(DrugSet drugSet) {
        ResponseHasData response = new ResponseHasData();
        try {
            if(drugSet.getDrugSetNo()==null){
                response.setStatus(1);
                response.setMsg("缺少drugSetNo参数");
                return response;
            }
            response.setStatus(0);
            response.setMsg("更新药品成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("更新药品失败");
        }
        return response;
    }

    @Override
    public Response select(Integer category, String keyword1, Integer disableFlag, Integer page, Integer rows) {
        ResponseHasData response = new ResponseHasData();
        try {
            Page<?> pa =  PageHelper.startPage(page, rows);
            List<DrugSet> piq = drugSetMapper.selectByCaKeyDis(category,keyword1,disableFlag);
            //查询结果总数
            Long total = pa.getTotal();
            //创建分页条件
            PageInfo<DrugSet> pageData = new PageInfo<DrugSet>(piq,total.intValue());

            response.setStatus(0);
            response.setMsg("查询药品成功");
            response.setData(pageData);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询药品失败");
        }
        return response;
    }
}
