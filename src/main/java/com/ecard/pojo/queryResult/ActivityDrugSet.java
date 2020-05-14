package com.ecard.pojo.queryResult;

import com.ecard.entity.Activity;
import com.ecard.entity.DrugSet;

public class ActivityDrugSet {
    private Activity activity;
    private DrugSet drugSet;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public DrugSet getDrugSet() {
        return drugSet;
    }

    public void setDrugSet(DrugSet drugSet) {
        this.drugSet = drugSet;
    }
}
