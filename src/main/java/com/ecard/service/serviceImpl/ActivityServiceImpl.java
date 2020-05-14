package com.ecard.service.serviceImpl;

import com.ecard.dao.ActivityMapper;
import com.ecard.dao.DrugSetMapper;
import com.ecard.entity.Activity;
import com.ecard.entity.DrugSet;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.queryResult.ActivityDrugSet;
import com.ecard.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private DrugSetMapper drugSetMapper;
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public Response insertActivity(ActivityDrugSet activityDrugSet) {
        ResponseHasData response = new ResponseHasData();
        DrugSet drugSet = activityDrugSet.getDrugSet();
        Activity activity = activityDrugSet.getActivity();
        try {
            drugSet.setItemName(activity.getActivityName());
            drugSet.setCreateTime(new Date());
            drugSet.setDisableFlag(activity.getDisableFlag());
            drugSetMapper.insertSelective(drugSet);

            activity.setGoodsNo(drugSet.getDrugSetNo());
            activityMapper.insertSelective(activity);
            response.setStatus(0);
            response.setMsg("添加优惠商品成功");
            response.setData(activity);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("添加优惠商品失败");
        }
        return response;
    }

    @Override
    public Response updateActivity(ActivityDrugSet activityDrugSet) {
        ResponseHasData response = new ResponseHasData();
        DrugSet drugSet = activityDrugSet.getDrugSet();
        Activity activity = activityDrugSet.getActivity();

        try {
            drugSet.setItemName(activity.getActivityName());
            drugSet.setUpdateTime(new Date());
            drugSet.setDisableFlag(activity.getDisableFlag());
            drugSetMapper.updateByPrimaryKeySelective(drugSet);
            activityMapper.updateByPrimaryKeySelective(activity);
            response.setStatus(0);
            response.setMsg("修改优惠商品成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("修改优惠商品失败");
        }
        return response;
    }
}
