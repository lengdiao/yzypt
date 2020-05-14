package com.ecard.pojo.queryResult;

import com.ecard.entity.Activity;
import com.ecard.entity.DrugSet;
import com.ecard.entity.Goods;

public class DrugSetGoodsQr {
    private DrugSet drugSet;
    private Goods goods;
    private Activity activity;

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

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
