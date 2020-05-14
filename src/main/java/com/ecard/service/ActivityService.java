package com.ecard.service;

import com.ecard.pojo.Response;
import com.ecard.pojo.queryResult.ActivityDrugSet;

public interface ActivityService {
    Response insertActivity(ActivityDrugSet activityDrugSet);

    Response updateActivity(ActivityDrugSet activityDrugSet);
}
