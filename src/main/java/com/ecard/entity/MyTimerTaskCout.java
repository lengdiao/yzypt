package com.ecard.entity;

import com.ecard.dao.*;
import com.ecard.pojo.queryResult.MedItemQr;
import com.ecard.utils.WeiXinUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

public class MyTimerTaskCout extends TimerTask {

    private MallOrderMapper mallOrderMapper;
    private MedItemMapper medItemMapper;
    private CountMapper countMapper;
    private DrugSetMapper drugSetMapper;
    private MedOrderMapper medOrderMapper;
    private Long mallNo;
    private ChiCountMapper chiCountMapper;
    private ActivityMapper activityMapper;

    public MyTimerTaskCout(MallOrderMapper mallOrderMapper, MedItemMapper medItemMapper,
                           CountMapper countMapper, DrugSetMapper drugSetMapper, MedOrderMapper medOrderMapper, Long mallNo) {
        this.countMapper = countMapper;
        this.mallOrderMapper = mallOrderMapper;
        this.drugSetMapper = drugSetMapper;
        this.medItemMapper = medItemMapper;
        this.medOrderMapper = medOrderMapper;
        this.mallNo = mallNo;
    }

    @Override
    public void run() {
        try {
            System.out.println("-------------------计算增量存量----------------------");
            MallOrder mallOrder = mallOrderMapper.selectByPrimaryKey(Long.valueOf(mallNo));
            MedOrder medOrder = medOrderMapper.selectByPrimaryKey(mallOrder.getMedOrderNo());

            //------------------计算增量存量----------------------
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            String sDate = simpleDateFormat.format(new Date());
            Date date = simpleDateFormat.parse(sDate);


            if(medOrder.getOrderType()==2){
                //本处方的药品
                List<MedItemQr> medItemList = medItemMapper.selectByMedOrderNo(medOrder.getOrderNo());

                //查询是否首次购买
                List<MallOrder> mallOrders = mallOrderMapper.selectByPtNoAndMallNo(mallOrder.getPtNo(),mallNo);
                if(mallOrders.size()==0){
                    //首次购买
                    for(MedItemQr medItemQr:medItemList){
                        Count count = countMapper.selectByDrNoDrugNoDate(mallOrder.getDrNo(),medItemQr.getDrugNo(),date);
                        if(count==null){
                            //该医生该月没有该药品记录
                            Count count1 = new Count();
                            count1.setDrNo(mallOrder.getDrNo());
                            count1.setDrugNo(medItemQr.getDrugNo());
                            count1.setYearMonth(new Date());
                            DrugSet drugSet = drugSetMapper.selectByPrimaryKey(medItemQr.getDrugNo());
                            if(drugSet.getVersion()==1||drugSet.getVersion()==2||drugSet.getVersion()==3){
                                count1.setAddAmount(drugSet.getSaleGenPrice().multiply(new BigDecimal(medItemQr.getNumber())));
                            }else if(drugSet.getVersion()==4){
                                Activity activity = activityMapper.selectByGoodsNoFlag(drugSet.getDrugSetNo(),null);
                                count1.setAddAmount(activity.getPresentPrice().multiply(new BigDecimal(medItemQr.getNumber())));
                            }

                            count1.setAddSum(medItemQr.getNumber());
                            System.out.println("111medItemQr.getNumber()="+medItemQr.getNumber());
                            countMapper.insertSelective(count1);
                        }else {
                            //该医生该月有该药品记录
                            DrugSet drugSet = drugSetMapper.selectByPrimaryKey(medItemQr.getDrugNo());
                            if(drugSet.getVersion()==1||drugSet.getVersion()==2||drugSet.getVersion()==3){
                                count.setAddAmount(count.getAddAmount().add(drugSet.getSaleGenPrice().multiply(new BigDecimal(medItemQr.getNumber()))));
                            }else if(drugSet.getVersion()==4){
                                Activity activity = activityMapper.selectByGoodsNoFlag(drugSet.getDrugSetNo(),null);
                                count.setAddAmount(count.getAddAmount().add(activity.getPresentPrice().multiply(new BigDecimal(medItemQr.getNumber()))));
                            }

                            count.setAddSum(count.getAddSum()+medItemQr.getNumber());
                            System.out.println("222medItemQr.getNumber()="+medItemQr.getNumber());
                            countMapper.updateByPrimaryKeySelective(count);
                        }
                    }
                }else {
                    //不是首次购买
                    int a = 0;
                    for(MedItemQr medItemQr:medItemList){
                        Count count = countMapper.selectByDrNoDrugNoDate(mallOrder.getDrNo(),medItemQr.getDrugNo(),date);
                        for(MallOrder mallOrder1 : mallOrders){
                            //有过往处方单
                            List<MedItem> medItems = medItemMapper.selectByMallNo(mallOrder1.getMallNo());
                            for(MedItem medItem:medItems){
                                System.out.println("medItem.getDrugNo()"+medItem.getDrugNo());
                                System.out.println("medItemQr.getDrugNo()"+medItemQr.getDrugNo());
                                if(medItem.getDrugNo().equals(medItemQr.getDrugNo())){
                                    //过往处方单有该药品
                                    System.out.println("相等");
                                    a=1;
                                    System.out.println("a="+a);
                                    break;
                                }
                            }
                        }
                        System.out.println("最后a="+a);
                        if(a==0){
                            //查询过往处方单没有该药品
                            if(count==null){
                                //该医生该月没有该药品记录
                                Count count1 = new Count();
                                count1.setDrNo(mallOrder.getDrNo());
                                count1.setDrugNo(medItemQr.getDrugNo());
                                count1.setYearMonth(new Date());
                                DrugSet drugSet = drugSetMapper.selectByPrimaryKey(medItemQr.getDrugNo());
                                if(drugSet.getVersion()==1||drugSet.getVersion()==2||drugSet.getVersion()==3){
                                    count1.setAddAmount(drugSet.getSaleGenPrice().multiply(new BigDecimal(medItemQr.getNumber())));
                                }else if(drugSet.getVersion()==4){
                                    Activity activity = activityMapper.selectByGoodsNoFlag(drugSet.getDrugSetNo(),null);
                                    count1.setAddAmount(activity.getPresentPrice().multiply(new BigDecimal(medItemQr.getNumber())));
                                }
                                count1.setAddSum(medItemQr.getNumber());
                                System.out.println("333medItemQr.getNumber()="+medItemQr.getNumber());
                                countMapper.insertSelective(count1);
                            }else{
                                //该医生该月有该药品记录
                                DrugSet drugSet = drugSetMapper.selectByPrimaryKey(medItemQr.getDrugNo());
                                if(drugSet.getVersion()==1||drugSet.getVersion()==2||drugSet.getVersion()==3){
                                    count.setAddAmount(count.getSaveAmount().add(drugSet.getSaleGenPrice().multiply(new BigDecimal(medItemQr.getNumber()))));
                                }else if(drugSet.getVersion()==4){
                                    Activity activity = activityMapper.selectByGoodsNoFlag(drugSet.getDrugSetNo(),null);
                                    count.setAddAmount(count.getSaveAmount().add(activity.getPresentPrice().multiply(new BigDecimal(medItemQr.getNumber()))));
                                }
                                count.setAddSum(count.getSaveSum()+medItemQr.getNumber());
                                System.out.println("444medItemQr.getNumber()="+medItemQr.getNumber());
                                countMapper.updateByPrimaryKeySelective(count);
                            }
                        }else {
                            //过往处方单有该药品
                            if(count==null){
                                //该医生该月没有该药品记录
                                Count count1 = new Count();
                                count1.setDrNo(mallOrder.getDrNo());
                                count1.setDrugNo(medItemQr.getDrugNo());
                                count1.setYearMonth(new Date());
                                DrugSet drugSet = drugSetMapper.selectByPrimaryKey(medItemQr.getDrugNo());
                                if(drugSet.getVersion()==1||drugSet.getVersion()==2||drugSet.getVersion()==3){
                                    count1.setSaveAmount(drugSet.getSaleGenPrice().multiply(new BigDecimal(medItemQr.getNumber())));
                                }else if(drugSet.getVersion()==4){
                                    Activity activity = activityMapper.selectByGoodsNoFlag(drugSet.getDrugSetNo(),null);
                                    count1.setSaveAmount(activity.getPresentPrice().multiply(new BigDecimal(medItemQr.getNumber())));
                                }
                                count1.setSaveSum(medItemQr.getNumber());
                                System.out.println("333medItemQr.getNumber()="+medItemQr.getNumber());
                                countMapper.insertSelective(count1);
                            }else{
                                //该医生该月有该药品记录
                                DrugSet drugSet = drugSetMapper.selectByPrimaryKey(medItemQr.getDrugNo());
                                if(drugSet.getVersion()==1||drugSet.getVersion()==2||drugSet.getVersion()==3){
                                    count.setSaveAmount(count.getSaveAmount().add(drugSet.getSaleGenPrice().multiply(new BigDecimal(medItemQr.getNumber()))));
                                }else if(drugSet.getVersion()==4){
                                    Activity activity = activityMapper.selectByGoodsNoFlag(drugSet.getDrugSetNo(),null);
                                    count.setSaveAmount(count.getSaveAmount().add(activity.getPresentPrice().multiply(new BigDecimal(medItemQr.getNumber()))));
                                }
                                count.setSaveSum(count.getSaveSum()+medItemQr.getNumber());
                                System.out.println("444medItemQr.getNumber()="+medItemQr.getNumber());
                                countMapper.updateByPrimaryKeySelective(count);
                            }
                        }
                    }
                }
                System.out.println("-------------------计算增量存量结束----------------------");
            }else{
                //本处方的药品
                List<MedItemQr> medItemList = medItemMapper.selectByMedOrderNo(medOrder.getOrderNo());

                //查询是否首次购买
                List<MallOrder> mallOrders = mallOrderMapper.selectByPtNoAndMallNo(mallOrder.getPtNo(),mallNo);
                if(mallOrders.size()==0){
                    //首次购买-增量金额
                    ChiCount chiCount  = chiCountMapper.selectByDrNoDrugNoDate(mallOrder.getDrNo(),date);
                    if(chiCount!=null) {
                        //有记录
                        chiCount.setAddAmount(chiCount.getAddAmount().add(medOrder.getOrderAmount()));
                        chiCount.setAddSum(chiCount.getAddSum()+medOrder.getPrescriptionNum());
                        chiCountMapper.updateByPrimaryKeySelective(chiCount);
                    }else{
                        //没有记录
                        ChiCount chiCount1 = new ChiCount();
                        chiCount1.setAddSum(Double.valueOf(medOrder.getPrescriptionNum()));
                        chiCount1.setAddAmount(medOrder.getOrderAmount());
                        chiCount1.setDrNo(mallOrder.getDrNo());
                        chiCount1.setYearMonth(new Date());
                        chiCountMapper.insertSelective(chiCount1);
                    }
                }else {
                    //不是首次购买-存量金额
                    ChiCount chiCount  = chiCountMapper.selectByDrNoDrugNoDate(mallOrder.getDrNo(),date);
                    if(chiCount!=null) {
                        //有记录
                        chiCount.setSaveAmount(chiCount.getSaveAmount().add(medOrder.getOrderAmount()));
                        chiCount.setSaveSum(chiCount.getSaveSum()+medOrder.getPrescriptionNum());
                        chiCountMapper.updateByPrimaryKeySelective(chiCount);
                    }else{
                        //没有记录
                        ChiCount chiCount1 = new ChiCount();
                        chiCount1.setSaveSum(Double.valueOf(medOrder.getPrescriptionNum()));
                        chiCount1.setSaveAmount(medOrder.getOrderAmount());
                        chiCount1.setDrNo(mallOrder.getDrNo());
                        chiCount1.setYearMonth(new Date());
                        chiCountMapper.insertSelective(chiCount1);
                    }

                }
                System.out.println("-------------------计算增量存量结束----------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------------计算增量存量错误----------------------");
        }
    }
}
