package com.ecard.service.serviceImpl;

import com.ecard.dao.*;
import com.ecard.entity.*;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.queryResult.*;
import com.ecard.service.BuyService;
import com.ecard.service.DrugSetService;
import com.ecard.utils.HttpConnectionUtil;
import com.ecard.utils.SybUtil;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

@Service
@Transactional
public class BuyServiceImpl implements BuyService {
    @Autowired
    private DrugSetService drugSetService;
    @Autowired
    private PtInfoMapper ptInfoMapper;
    @Autowired
    private MallOrderMapper mallOrderMapper;
    @Autowired
    private MedRecordMapper medRecordMapper;
    @Autowired
    private MedItemMapper medItemMapper;
    @Autowired
    private MedOrderMapper medOrderMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private PtOpenMapper ptOpenMapper;
    @Autowired
    private TemporaryPtOpenMapper temporaryPtOpenMapper;
    @Autowired
    private DrugSetMapper drugSetMapper;
    @Autowired
    private UserQuestionMapper userQuestionMapper;
    @Autowired
    private QuestionTemplateMapper questionTemplateMapper;
    @Autowired
    private QuestionOptionsMapper questionOptionsMapper;
    @Autowired
    private ReceiptAddressMapper receiptAddressMapper;
    @Autowired
    private CloudPassInfoMapper cloudPassInfoMapper;
    @Autowired
    private MallAddressMapper mallAddressMapper;
    @Autowired
    private PostageMapper postageMapper;
    @Autowired
    private CountMapper countMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private RedpacketRecordMapper redpacketRecordMapper;
    @Autowired
    private RedpacketRuleMapper redpacketRuleMapper;
    @Autowired
    private DoctorWalletMapper doctorWalletMapper;
    @Autowired
    private DoctorKitingMapper doctorKitingMapper;
    @Autowired
    private CommonMasterMapper commonMasterMapper;

    public static final String SYB_CUSID = "5603310739216UZ";
    public static final String SYB_APPID = "00170977";
    public static final String SYB_APPKEY = "Yzy2019YizhenyunDevilSimPay";
    public static final String SYB_APIURL = "https://vsp.allinpay.com/apiweb/unitorder/pay";//生产环境
    public static String total_fee = "";// 订单总金额
    public static String paytype = "W02";
    public static String body = "易臻云";
    public static String remark = "";
    public static String notify_url = "http://www.yizhenyun.com.cn/yzypt/yzyptbr/sjResultPayWx";
    public static String reqsn = "";

    //String openId = "oLkiUwbC5SFlF0oyN5dcs_-Ujz8s";
    //String sjOpenId = "oEaBztxZAXW5qe7wLhpVLQHX9-9M";

    @Override
    public Response insertGoods(Long goodsNo, Long number, String remark, int platform) {
        ResponseHasData response = new ResponseHasData();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
        String openId = user.getOpenId();
        WeiXinUser sjUser = (WeiXinUser) session.getAttribute("sjCurrentUser");
        String sjOpenId = sjUser.getOpenId();


        try {
            List<PtOpen> ptOpen = ptOpenMapper.selectBtOpenId(openId);
            for(PtOpen ptOpen1 : ptOpen){
                ptOpen1.setSjOpenId(sjOpenId);
                ptOpenMapper.updateByPrimaryKeySelective(ptOpen1);
            }
            PtInfo ptInfo = ptInfoMapper.selectByPrimaryKey(ptOpen.get(0).getPtNo());

            //List<MallOrder> mallOrder1 = mallOrderMapper.selectByPtNoAndPayStatus1(ptInfo.getPtNo(),0);

            MallOrder order = mallOrderMapper.selectByPtNoAndUpdateTime(ptInfo.getPtNo());

            DrugSet drugSet = drugSetMapper.selectByPrimaryKey(goodsNo);

            Activity activity = new Activity();

            Goods goods = new Goods();

            if (drugSet.getVersion() == 4) {
                //优惠商品
                activity = activityMapper.selectByGoodsNoFlag(goodsNo, null);
            } else {
                //普通商品
                goods = goodsMapper.selectByPrimaryKey(goodsNo);
            }


            Long medRecordNo = medRecordMapper.selectId();
            Long mallNo = mallOrderMapper.selectId();

            MedRecord medRecord = new MedRecord();
            medRecord.setMedRecordNo(medRecordNo);
            medRecord.setPtNo(ptInfo.getPtNo());
            medRecord.setMedRecordStatus(2);
            if (order != null) {
                medRecord.setDrNo(order.getDrNo());
            } else {

                medRecord.setDrNo(Long.valueOf("2200000341"));
            }
            medRecord.setDiagContent(goods.getDiagContent());
            medRecord.setSubjective(goods.getSubjective());
            medRecord.setVisitDate(new Date());
            medRecord.setCreateTime(new Date());
            if (drugSet.getVersion() == 4) {
                medRecord.setDiagContent(activity.getDiagContent());
                medRecord.setSubjective(activity.getSubjective());
            } else {
                medRecord.setDiagContent(goods.getDiagContent());
                medRecord.setSubjective(goods.getSubjective());
            }
            medRecordMapper.insertSelective(medRecord);

            MedOrder medOrder = new MedOrder();
            medOrder.setOrderStatus(0);
            medOrder.setRemark(remark);
            medOrder.setUpdateTime(new Date());
            if (drugSet.getVersion() == 4) {
                medOrder.setDiagContent(activity.getDiagContent());
            } else {
                medOrder.setDiagContent(goods.getDiagContent());
            }

            medOrder.setOrderType(drugSet.getCategory().equals("1") ? 1 : 2);
            medOrder.setMedRecordNo(medRecord.getMedRecordNo().toString());
            //medOrder.setAddStatus(mallOrder1.size()>0?2:1);
            System.out.println("updatetime" + medOrder.getUpdateTime());
            medOrderMapper.insertSelective(medOrder);

            MedItem medItem = new MedItem();
            medItem.setDrugNo(goodsNo);
            medItem.setNumber(Double.valueOf(number));

            medItem.setMedOrderNo(medOrder.getOrderNo());

            if (drugSet.getVersion() == 4) {
                medItem.setWayNo(activity.getWayNo());
                medItem.setUsageNo(activity.getUsageNo());
            } else {
                medItem.setWayNo(goods.getWayNo());
                medItem.setUsageNo(goods.getUsageNo());
            }
            medItemMapper.insertSelective(medItem);

            MallOrder mallOrder = new MallOrder();
            mallOrder.setMallNo(mallNo);
            mallOrder.setOrderStatus(3);
            mallOrder.setCreateTime(new Date());
            mallOrder.setPtNo(ptInfo.getPtNo());
            mallOrder.setOrderTime(new Date());
            mallOrder.setMedOrderNo(medOrder.getOrderNo());
            mallOrder.setPayStatus(1);
            mallOrder.setRemark(remark);
            mallOrder.setPlatform(platform);
            System.out.println("eeeeeeeee"+mallOrder.getPlatform());
            if (drugSet.getVersion() == 4) {
                mallOrder.setActivityDetail(activity.getActivityDetail());
                if (activity.getIsRecipe() == 0) {
                    mallOrder.setVersion(5);
                }
                if (activity.getIsRecipe() == 1) {
                    mallOrder.setVersion(4);
                }
            } else {
                if (goods.getIsRecipe() == 0) {
                    mallOrder.setVersion(1);
                }
                if (goods.getIsRecipe() == 1) {
                    mallOrder.setVersion(3);
                }
                mallOrder.setActivityDetail("无");
            }


            if (order != null) {
                mallOrder.setDrNo(order.getDrNo());
            } else {
                mallOrder.setDrNo(Long.valueOf("2200000341"));
            }
            mallOrderMapper.insertSelective(mallOrder);

            MallMedOrderQr mallMedOrderQr = new MallMedOrderQr();
            mallMedOrderQr.setMallNo(mallOrder.getMallNo());
            mallMedOrderQr.setMedRecordNo(medRecordNo);

            response.setStatus(0);
            response.setMsg("创建订单成功");
            response.setData(mallMedOrderQr);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("创建订单失败");
        }

        return response;
    }


    public Response insertQuestion(UserQuestionQr userQuestion) {
        ResponseHasData response = new ResponseHasData();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
        String openId = user.getOpenId();

        try {
            List<PtOpen> ptOpen = ptOpenMapper.selectBtOpenId(openId);
            List<UserQuestion> question = userQuestion.getUserQuestion();
            for (UserQuestion question1 : question) {
                question1.setPtNo(ptOpen.get(0).getPtNo());
                userQuestionMapper.insertSelective(question1);
            }
            response.setMsg("成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("失败");
        }
        return response;
    }

    @Override
    public Response updateMallOrder(MallOrderQr mallOrderQr) {
        ResponseHasData response = new ResponseHasData();
        try {
            MallOrder mallOrder = mallOrderQr.getMallOrder();
            MedOrder medOrder = mallOrderQr.getMedOrder();
            medOrder.setRemark(mallOrder.getRemark());
            medOrder.setUpdateTime(new Date());
            MedRecord medRecord = mallOrderQr.getMedRecord();
            List<MedItemQr> medItemQr = mallOrderQr.getMedItemQr();

            mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
            medOrderMapper.updateByPrimaryKeySelective(medOrder);
            medRecordMapper.updateByPrimaryKeySelective(medRecord);
            for (MedItemQr medItemQr1 : medItemQr) {
                MedItem medItem = new MedItem();
                medItem.setMedItemNo(medItemQr1.getMedItemNo());
                medItem.setMedOrderNo(medItemQr1.getMedOrderNo());
                medItem.setDrugNo(medItemQr1.getDrugNo());
                medItem.setNumber(medItemQr1.getNumber());
                medItem.setExecDay(medItemQr1.getExecDay());
                medItem.setWayNo(medItemQr1.getWayNo());
                medItem.setExecWhen(medItemQr1.getExecWhen());
                medItem.setExecAim(medItemQr1.getExecAim());
                medItem.setDayDose(medItemQr1.getDayDose());
                medItem.setDose(medItemQr1.getDose());
                medItem.setDoseUnit(medItemQr1.getDoseUnit());
                medItem.setUsageNo(medItemQr1.getUsageNo());
                medItemMapper.updateByPrimaryKeySelective(medItem);
            }
            response.setMsg("修改成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("修改失败");
            response.setStatus(1);
        }
        return response;
    }

    @Override
    public ResponseHasData saveMrPicture(Long mallNo) {
        ResponseHasData response = new ResponseHasData();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
        String openId = user.getOpenId();

        try {
            MallOrder mallOrder = mallOrderMapper.selectByPrimaryKey(mallNo);
            PtInfo ptInfo = ptInfoMapper.selectByPrimaryKey(mallOrder.getPtNo());
            CloudPassInfo passInfo = cloudPassInfoMapper.selectByPtNo(ptInfo.getPtNo());
            MedOrder medOrder = medOrderMapper.selectByPrimaryKey(mallOrder.getMedOrderNo());
            ptInfo.setName(passInfo.getName());
            Long times = Long.valueOf(5 * 60 * 1000);
            System.out.println(times + "秒后推送");
            Timer timer = new Timer();
            MyTimerTask myTimerTask = new MyTimerTask(openId, ptInfo.getName(), medOrder.getMedRecordNo(), mallOrder.getShippingStatus(), medRecordMapper, medOrderMapper, mallOrder.getMedOrderNo(), mallOrder.getPlatform());
            //t通过timer定时定频率调用myTimerTask的业务逻辑
            timer.schedule(myTimerTask, times);
            response.setMsg("成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("失败");
            response.setStatus(1);
        }
        return response;
    }

    @Override
    public Response selectQuestion(Long goodsNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            QuestionQrr questionQr = new QuestionQrr();
            List<QuestionTemplate> questionTemplate = questionTemplateMapper.selectByGoodsNo(goodsNo);
            List<QuestionOptions> questionOptions = questionOptionsMapper.selectByTemplateNo(questionTemplate.get(0).getTemplateNo());
            questionQr.setQuestionOptions(questionOptions);
            questionQr.setQuestionTemplates(questionTemplate);
            response.setStatus(0);
            response.setMsg("查询成功");
            response.setData(questionQr);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询失败");
        }

        return response;
    }

    @Override
    public Response isNullQuestion(Long goodsNo) {
        ResponseHasData response = new ResponseHasData();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
        String openId = user.getOpenId();

        List<PtOpen> ptOpen = ptOpenMapper.selectBtOpenId(openId);
        PtInfo ptInfo = ptInfoMapper.selectByPrimaryKey(ptOpen.get(0).getPtNo());

        List<QuestionTemplate> questionTemplates = questionTemplateMapper.selectByGoodsNo(goodsNo);

        if (questionTemplates.size() != 0) {
            List<UserQuestion> question = userQuestionMapper.selectByPtNoAndTemId(ptInfo.getPtNo(), questionTemplates.get(0).getTemplateNo());
            int status = question.size() == 0 ? 0 : 1;

            response.setStatus(0);
            response.setMsg("查询成功");
            response.setData(status);
        } else {
            response.setStatus(0);
            response.setMsg("查询成功");
            response.setData(1);
        }


        return response;
    }

    @Override
    public Response notice(Long mallNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            System.out.println("-----------------------支付完成-----------------------");
            MallOrder mallOrder = mallOrderMapper.selectByPrimaryKey(Long.valueOf(mallNo));
            mallOrder.setPayStatus(0);
            mallOrder.setPayTime(new Date());
            mallOrderMapper.updateByPrimaryKeySelective(mallOrder);

            MedOrder medOrder = medOrderMapper.selectByPrimaryKey(mallOrder.getMedOrderNo());
            MedRecord medRecord = medRecordMapper.selectByPrimaryKey(medOrder.getMedRecordNo());
            medRecord.setMedRecordStatus(2);

            List<MedItemQr> medItems = medItemMapper.selectByMedOrderNo(medOrder.getOrderNo());
            BigDecimal sum = new BigDecimal("0");
            BigDecimal rental = new BigDecimal("0");
            for (MedItemQr medItemQr : medItems) {
                DrugSet drugSet = drugSetMapper.selectByPrimaryKey(medItemQr.getDrugNo());
                //计算优惠价格
                if (mallOrder.getVersion() == 4 || mallOrder.getVersion() == 5) {
                    //是优惠商品订单
                    Activity activity = activityMapper.selectByGoodsNoFlag(drugSet.getDrugSetNo(), null);
                    //1:买赠2:直减3:满减
                    if (activity.getActivityType().equals("1")) {
                        int total = activity.getLeftNumber() + activity.getRightNumber();
                        int number = new Double(medItemQr.getNumber() / total).intValue();
                        //计算实付
                        sum = sum.add(activity.getPresentPrice().multiply(new BigDecimal(number + "")));
                        //计算原价
                        rental = rental.add(activity.getOriginalPrice().multiply(new BigDecimal(number + "")));
                        System.out.println(sum);
                    } else if (activity.getActivityType().equals("2")) {
                        //计算实付
                        sum = sum.add(activity.getPresentPrice().multiply(new BigDecimal(medItemQr.getNumber().toString())));
                        //计算原价
                        rental = rental.add(activity.getOriginalPrice().multiply(new BigDecimal(medItemQr.getNumber().toString())));
                        System.out.println(sum);
                    } else if (activity.getActivityType().equals("3")) {
                        sum = sum.add(activity.getOriginalPrice().multiply(new BigDecimal(medItemQr.getNumber().toString())));
                        //满减个数
                        BigDecimal div = sum.divide(activity.getMoneyFull(), 0, BigDecimal.ROUND_HALF_DOWN);
                        //计算实付
                        sum = sum.subtract(div.multiply(activity.getMoneyOff()));
                        //计算原价
                        rental = rental.add(activity.getOriginalPrice().multiply(new BigDecimal(medItemQr.getNumber().toString())));
                        System.out.println(sum);
                    }
                } else {
                    //不是优惠商品订单
                    sum = sum.add(drugSet.getSaleGenPrice().multiply(new BigDecimal(medItemQr.getNumber().toString())));
                    System.out.println(sum);
                }

            }

            if (mallOrder.getShippingStatus() == 1) {
                if (medItems.size() == 1) {
                    double number = medItems.get(0).getNumber();
                    String s = number + "";
                    List<Postage> postages = postageMapper.selectByNo(medItems.get(0).getDrugNo());
                    if (postages.size() == 0) {
                        postages = postageMapper.selectByNo(Long.valueOf("0"));
                    }
                    System.out.println("购买数量：" + number);
                    String regex = postages.get(0).getCondition();
                    System.out.println("正则表达式：" + regex);
                    boolean flag = s.matches(regex);
                    System.out.println("flag:" + flag);
                    if (flag) {
                        sum = sum.add(postages.get(0).getPostage());
                    }
                }
            }
            mallOrder.setRental(rental);
            //实付金额减红包
            if (mallOrder.getPacketId() != null) {
                RedpacketRecord redpacketRecord = redpacketRecordMapper.selectByPrimaryKey(mallOrder.getPacketId());
                mallOrder.setOrderAmount(sum.subtract(redpacketRecord.getAmount()));
                redpacketRecord.setDisableFlag(1);
                redpacketRecordMapper.updateByPrimaryKeySelective(redpacketRecord);
            } else {
                mallOrder.setOrderAmount(sum);
            }
            mallOrder.setOrderStatus(1);
            mallOrderMapper.updateByPrimaryKeySelective(mallOrder);

            //查询是否送红包
            //是否绑定张建
            List<PtOpen> ptOpens = ptOpenMapper.selectByPtNo(mallOrder.getPtNo());
            for(PtOpen ptOpen : ptOpens){
                if(ptOpen.getDrNo()==Long.valueOf("2200000341")){
                    //1.判断是否是商品
                    if(mallOrder.getVersion()==1||mallOrder.getVersion()==3||mallOrder.getVersion()==4||mallOrder.getVersion()==5){
                        //2。查询是否有红包规则
                        List<RedpacketRule> redpacketRules = redpacketRuleMapper.selectByDrugNo(medItems.get(0).getDrugNo());
                        for(RedpacketRule redpacketRule:redpacketRules){
                            List<MallOrder> mallOrders = mallOrderMapper.selectByPtNoAndPayStatusPacketId(mallOrder.getPtNo(),0,mallOrder.getPacketId());
                            if(mallOrders.size()==0){
                                RedpacketRecord redpacketRecord = new RedpacketRecord();
                                redpacketRecord.setAmount(redpacketRule.getAmount());
                                redpacketRecord.setCreateDate(new Date());
                                redpacketRecord.setDisableFlag(0);
                                redpacketRecord.setDrugNo(redpacketRule.getDrugNo());
                                redpacketRecord.setPtNo(mallOrder.getPtNo());
                                redpacketRecord.setExpireDate(subMonth(new Date(),redpacketRule.getValidTime()));
                                redpacketRecord.setPacketRuleId(redpacketRule.getPacketId());
                                redpacketRecord.setTitle(redpacketRule.getTitle());
                                redpacketRecord.setDetails(redpacketRule.getDetails());
                                redpacketRecordMapper.insertSelective(redpacketRecord);
                                //返回红包数据
                                response.setData(redpacketRecord);
                            }
                        }
                    }
                }
            }


            List<CommonMaster> commonMasters = commonMasterMapper.selectByNo(97);
            //生成处方的订单，判断是否有挂号费
            if(mallOrder.getVersion()==1||mallOrder.getVersion()==5){
                for (MedItemQr medItemQr : medItems){
                    DrugSet drugSet = drugSetMapper.selectByPrimaryKey(medItemQr.getDrugNo());
                    if(drugSet.getIsRegistration()==0){
                        DoctorWallet doctorWallet = doctorWalletMapper.selectByDrNo(mallOrder.getDrNo());
                        if (doctorWallet==null){
                            DoctorWallet doctorWallet1 = new DoctorWallet();
                            doctorWallet1.setDrNo(mallOrder.getDrNo());
                            doctorWallet1.setAmount(new BigDecimal(commonMasters.get(0).getDescription()));
                            doctorWalletMapper.insertSelective(doctorWallet1);
                        }else{
                            doctorWallet.setAmount(doctorWallet.getAmount().add(new BigDecimal(commonMasters.get(0).getDescription())));
                            doctorWalletMapper.updateByPrimaryKeySelective(doctorWallet);
                        }
                        break;
                    }
                }
            }


            Long times = Long.valueOf(0);
            Timer timer = new Timer();
            MyTimerTaskCout myTimerTask = new MyTimerTaskCout(mallOrderMapper, medItemMapper, countMapper, drugSetMapper, medOrderMapper, mallNo);
            //t通过timer定时定频率调用myTimerTask的业务逻辑
            timer.schedule(myTimerTask, times);

            response.setMsg("支付成功");
            response.setStatus(0);
        } catch (Exception e) {
            response.setMsg("支付失败");
            response.setStatus(1);
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response updateShippingStatus(Long mallNo, int shippingStatus, Long address) {
        ResponseHasData response = new ResponseHasData();
        try {
            MallOrder mallOrder = mallOrderMapper.selectByPrimaryKey(Long.valueOf(mallNo));
            mallOrder.setShippingStatus(shippingStatus);
            if (address != null) {
                ReceiptAddress receiptAddress = receiptAddressMapper.selectByPrimaryKey(address);
                MallAddress mallAddress = new MallAddress();
                mallAddress.setAddressDetail(receiptAddress.getAddressDetail());
                mallAddress.setAreaCode(receiptAddress.getAreaCode());
                mallAddress.setCity(receiptAddress.getCity());
                mallAddress.setCounty(receiptAddress.getCounty());
                mallAddress.setCreateTime(new Date());
                mallAddress.setMark(receiptAddress.getMark());
                mallAddress.setName(receiptAddress.getName());
                mallAddress.setPhone(receiptAddress.getPhone());
                mallAddress.setPostalCode(receiptAddress.getPostalCode());
                mallAddress.setProvince(receiptAddress.getProvince());
                mallAddress.setPtNo(receiptAddress.getPtNo());
                mallAddressMapper.insertSelective(mallAddress);

                mallOrder.setAddress(mallAddress.getAddId().toString());
            }
            mallOrderMapper.updateByPrimaryKeySelective(mallOrder);

            response.setMsg("确认成功");
            response.setStatus(0);
        } catch (Exception e) {
            response.setMsg("确认失败");
            response.setStatus(1);
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response createSJMallOrder(MallOrderQrr mallOrderQr) {
        ResponseHasData response = new ResponseHasData();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
        String openId = user.getOpenId();
        try {
            List<PtOpen> ptOpen = ptOpenMapper.selectBtOpenId(openId);
            PtInfo ptInfo = ptInfoMapper.selectByPrimaryKey(ptOpen.get(0).getPtNo());
            MallOrder order = mallOrderMapper.selectByPtNoAndUpdateTime(ptInfo.getPtNo());
            Long medRecordNo = medRecordMapper.selectId();
            Long mallNo = mallOrderMapper.selectId();

            MedRecord medRecord = new MedRecord();
            medRecord.setMedRecordNo(medRecordNo);
            medRecord.setMedRecordStatus(2);
            medRecord.setPtNo(ptInfo.getPtNo());
            if (order != null) {
                medRecord.setDrNo(order.getDrNo());
            } else {
                medRecord.setDrNo(Long.valueOf("2200000229"));
            }
            medRecord.setVisitDate(new Date());
            medRecord.setCreateTime(new Date());
            medRecordMapper.insertSelective(medRecord);

            MedOrder medOrder = new MedOrder();
            medOrder.setOrderStatus(0);
            medOrder.setOrderType(2);
            medOrder.setMedRecordNo(medRecord.getMedRecordNo().toString());
            medOrderMapper.insertSelective(medOrder);


            MedItemQr medItem = mallOrderQr.getMedItemQrs();
            MedItem medItem1 = new MedItem();
            medItem1.setDrugNo(medItem.getDrugNo());
            medItem1.setNumber(medItem.getNumber());
            medItem1.setMedOrderNo(medOrder.getOrderNo());
            medItem1.setDose(medItem.getDose());
            medItem1.setDoseUnit(medItem.getDoseUnit());
            medItemMapper.insertSelective(medItem1);


            MallOrder mallOrder = new MallOrder();
            mallOrder.setShippingStatus(mallOrderQr.getMallOrder().getShippingStatus());

            mallOrder.setMallNo(mallNo);
            mallOrder.setOrderAmount(mallOrderQr.getMallOrder().getOrderAmount());
            mallOrder.setOrderStatus(3);
            mallOrder.setCreateTime(new Date());
            mallOrder.setPtNo(ptInfo.getPtNo());
            mallOrder.setOrderTime(new Date());
            mallOrder.setMedOrderNo(medOrder.getOrderNo());
            mallOrder.setPayStatus(1);
            mallOrder.setOrderStatus(1);
            //订单总额
            mallOrder.setRemark(mallOrderQr.getMallOrder().getRemark());
            mallOrder.setRental(mallOrderQr.getMallOrder().getRental());
            mallOrder.setOffMoney(mallOrderQr.getMallOrder().getOffMoney());
            String activityDetail = mallOrderQr.getMallOrder().getActivityDetail();
            if (!"".equals(activityDetail) && activityDetail != null) {
                mallOrder.setActivityDetail(activityDetail);
            }

            DrugSet drugSet = drugSetMapper.selectByPrimaryKey(medItem1.getDrugNo());
            int isRecipe = 1;
            if (drugSet.getVersion() == 4) {
                Activity activity = activityMapper.selectByGoodsNoFlag(drugSet.getDrugSetNo(), null);
                isRecipe = activity.getIsRecipe();
                if (isRecipe == 0) {
                    mallOrder.setVersion(5);
                }
                if (isRecipe == 1) {
                    mallOrder.setVersion(4);
                }
            } else if (drugSet.getVersion() == 2) {
                Goods goods = goodsMapper.selectByPrimaryKey(mallOrderQr.getMedItemQrs().getDrugNo());
                isRecipe = goods.getIsRecipe();
                if (isRecipe == 0) {
                    mallOrder.setVersion(1);
                }
                if (isRecipe == 1) {
                    mallOrder.setVersion(3);
                }
            }


            if (order != null) {
                mallOrder.setDrNo(order.getDrNo());
            } else {
                mallOrder.setDrNo(Long.valueOf("2200000229"));
            }
            if (mallOrderQr.getMallOrder().getAddress() != null && !"".equals(mallOrderQr.getMallOrder().getAddress())) {
                ReceiptAddress receiptAddress = receiptAddressMapper.selectByPrimaryKey(Long.valueOf(mallOrderQr.getMallOrder().getAddress()));
                MallAddress mallAddress = new MallAddress();
                mallAddress.setAddressDetail(receiptAddress.getAddressDetail());
                mallAddress.setAreaCode(receiptAddress.getAreaCode());
                mallAddress.setCity(receiptAddress.getCity());
                mallAddress.setCounty(receiptAddress.getCounty());
                mallAddress.setCreateTime(new Date());
                mallAddress.setMark(receiptAddress.getMark());
                mallAddress.setName(receiptAddress.getName());
                mallAddress.setPhone(receiptAddress.getPhone());
                mallAddress.setPostalCode(receiptAddress.getPostalCode());
                mallAddress.setProvince(receiptAddress.getProvince());
                mallAddress.setPtNo(receiptAddress.getPtNo());
                mallAddressMapper.insertSelective(mallAddress);

                mallOrder.setAddress(mallAddress.getAddId().toString());
            }
            mallOrderMapper.insertSelective(mallOrder);


            response.setStatus(0);
            response.setMsg("创建订单成功");
            response.setData(mallOrder.getMallNo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("创建订单失败");
        }

        return response;
    }

    @Override
    public Response isRegister() {
        ResponseHasData response = new ResponseHasData();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
        String openId = user.getOpenId();
        //String openId = "oLkiUwbC5SFlF0oyN5dcs_-Ujz8s2";
        try {
            List<PtOpen> ptOpen = ptOpenMapper.selectBtOpenId(openId);
            if (ptOpen.size() != 0) {
                PtInfoQr ptInfo = ptInfoMapper.selectByPtNo(ptOpen.get(0).getPtNo());
                if (!"".equals(ptInfo.getPhone()) && ptInfo.getPhone() != null) {
                    response.setData(0);
                    response.setStatus(0);
                    response.setMsg("无需补全");
                    System.out.println(response.getData());
                } else {
                    response.setStatus(0);
                    response.setMsg("请补全个人信息");
                    response.setData(1);
                    System.out.println(response.getData());
                }
            } else {
                response.setStatus(0);
                response.setMsg("请补全个人信息");
                response.setData(1);
                System.out.println(response.getData());
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("错误");
        }
        return response;
    }


    public void wxpay(String mallNo, Long packetId) throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SYB_APIURL);
        http.init();

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
        String openId = user.getOpenId();
        System.out.println(mallNo);
        MallOrder mallOrder = mallOrderMapper.selectByPrimaryKey(Long.parseLong(mallNo));
        MedOrder medOrder = medOrderMapper.selectByPrimaryKey(Long.valueOf(mallOrder.getMedOrderNo()));
        List<MedItemQr> medItems = medItemMapper.selectByMedOrderNo(medOrder.getOrderNo());

        BigDecimal sum = new BigDecimal("0");
        BigDecimal rental = new BigDecimal("0");
        for (MedItemQr medItemQr : medItems) {
            DrugSet drugSet = drugSetMapper.selectByPrimaryKey(medItemQr.getDrugNo());
            //计算优惠价格
            if (mallOrder.getVersion() == 4 || mallOrder.getVersion() == 5) {
                //是优惠商品订单
                Activity activity = activityMapper.selectByGoodsNoFlag(drugSet.getDrugSetNo(), null);
                //1:买赠2:直减3:满减
                if (activity.getActivityType().equals("1")) {
                    int total = activity.getLeftNumber() + activity.getRightNumber();
                    int number = new Double(medItemQr.getNumber() / total).intValue();
                    //计算实付
                    sum = sum.add(activity.getPresentPrice().multiply(new BigDecimal(number + "")));
                    //计算原价
                    rental = rental.add(activity.getOriginalPrice().multiply(new BigDecimal(number + "")));
                    System.out.println(sum);
                } else if (activity.getActivityType().equals("2")) {
                    //计算实付
                    sum = sum.add(activity.getPresentPrice().multiply(new BigDecimal(medItemQr.getNumber().toString())));
                    //计算原价
                    rental = rental.add(activity.getOriginalPrice().multiply(new BigDecimal(medItemQr.getNumber().toString())));
                    System.out.println(sum);
                } else if (activity.getActivityType().equals("3")) {
                    sum = sum.add(activity.getOriginalPrice().multiply(new BigDecimal(medItemQr.getNumber().toString())));
                    //满减个数
                    BigDecimal div = sum.divide(activity.getMoneyFull(), 0, BigDecimal.ROUND_HALF_DOWN);
                    //计算实付
                    sum = sum.subtract(div.multiply(activity.getMoneyOff()));
                    //计算原价
                    rental = rental.add(activity.getOriginalPrice().multiply(new BigDecimal(medItemQr.getNumber().toString())));
                    System.out.println(sum);
                }
            } else {
                //不是优惠商品订单
                sum = sum.add(drugSet.getSaleGenPrice().multiply(new BigDecimal(medItemQr.getNumber().toString())));
                System.out.println(sum);
            }

        }

        if (mallOrder.getShippingStatus() == 1) {
            if (medItems.size() == 1) {
                double number = medItems.get(0).getNumber();
                String s = number + "";
                List<Postage> postages = postageMapper.selectByNo(medItems.get(0).getDrugNo());
                if (postages.size() == 0) {
                    postages = postageMapper.selectByNo(Long.valueOf("0"));
                }
                System.out.println("购买数量：" + number);
                String regex = postages.get(0).getCondition();
                System.out.println("正则表达式：" + regex);
                boolean flag = s.matches(regex);
                System.out.println("flag:" + flag);
                if (flag) {
                    sum = sum.add(postages.get(0).getPostage());
                }
            }
        }
        /*mallOrder.setRental(rental);
        mallOrder.setOrderAmount(sum);
        mallOrder.setOrderStatus(1);
        mallOrderMapper.updateByPrimaryKeySelective(mallOrder);*/

        //计算红包
        if (packetId != null) {
            RedpacketRecord redpacketRecord = redpacketRecordMapper.selectByPrimaryKey(packetId);
            sum = sum.subtract(redpacketRecord.getAmount());
            mallOrder.setPacketId(packetId);
            mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
        } else {
            mallOrder.setPacketId(null);
        }


        //sum = new BigDecimal("0.01");

        sum = sum.multiply(new BigDecimal("100"));

        total_fee = "" + sum.intValue();
        reqsn = mallNo + getRandomString(5);


        System.out.println("total_fee" + total_fee);

        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("cusid", SYB_CUSID);//商户号    实际交易的商户号
        params.put("appid", SYB_APPID);//应用ID  平台分配的APPID
        params.put("version", "11");//版本号   接口版本号
        params.put("trxamt", total_fee);//交易金额   单位为分
        params.put("reqsn", reqsn);//商户交易单号   商户的交易订单号
        params.put("paytype", paytype);//交易方式   详见附录交易方式
        params.put("randomstr", SybUtil.getValidatecode(8));//随机字符串
        params.put("body", body);//订单标题    订单商品名称，为空则以商户名作为商品名称
        params.put("remark", remark);//备注
        params.put("acct", openId);//支付平台用户标识  JS支付时使用  微信支付-用户的微信openid  支付宝支付-用户user_id  微信小程序-用户小程序的openid
        //params.put("authcode", authcode);
        params.put("notify_url", notify_url);//交易结果通知地址  接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
        //params.put("limit_pay", limit_pay);//支付限制  no_credit--指定不能使用信用卡支付
        //params.put("idno", idno);//证件号  实名交易必填.填了此字段就会验证证件号和姓名
        //params.put("truename", truename);//付款人真实姓名   实名交易必填.填了此字段就会验证证件号和姓名
        //params.put("asinfo", asinfo);//非必填
        params.put("sign", SybUtil.sign(params, SYB_APPKEY));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        Map<String, String> map = handleResult(result);
        print(map);

        if ("SUCCESS".equals(map.get("retcode"))) {
            JSONObject jsonObject = new JSONObject(map.get("payinfo"));

            Map<String, String> ret = new HashMap<String, String>();

            ret.put("appid", jsonObject.getString("appId"));
            ret.put("package", jsonObject.getString("package"));
            ret.put("nonceStr", jsonObject.getString("nonceStr"));
            ret.put("timestamp", jsonObject.getString("timeStamp"));
            ret.put("signType", jsonObject.getString("signType"));
            ret.put("signature", jsonObject.getString("paySign"));

            ret.put("orderNo", String.valueOf(reqsn));

            print(ret);

            Gson g = new Gson();
            String json = g.toJson(ret);
            // HttpServletResponse response = ServletActionContext.getResponse();*/
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getResponse();
            response.setContentType("application/json;charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");
            PrintWriter pw = null;
            try {
                pw = response.getWriter();
            } catch (IOException e) {
                System.out.println("response.getWriter();报错");
            }
            pw.print(json);
            pw.flush();
            pw.close();
        }

    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }


    public static Map<String, String> handleResult(String result) throws Exception {
        Map map = SybUtil.json2Obj(result, Map.class);
        if (map == null) {
            throw new Exception("返回数据错误");
        }
        if ("SUCCESS".equals(map.get("retcode"))) {
            TreeMap tmap = new TreeMap();
            tmap.putAll(map);
            String sign = tmap.remove("sign").toString();
            String sign1 = SybUtil.sign(tmap, SYB_APPKEY);
            if (sign1.toLowerCase().equals(sign.toLowerCase())) {
                return map;
            } else {
                throw new Exception("验证签名失败");
            }

        } else {
            throw new Exception("dfdfdfdf" + map.get("retmsg").toString());
        }
    }


    /****
     * 传入具体日期 ，返回具体日期增加一个月。
     * @param date 日期(2017-04-13)
     * @return 2017-05-13
     * @throws ParseException
     */
    private  Date subMonth(Date date,int month) throws ParseException {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.MONTH, month);
        Date dt1 = rightNow.getTime();
        //String reStr = sdf.format(dt1);
        return dt1;
    }



    public static void print(Map<String, String> map) {
        System.out.println("返回数据如下:");
        if (map != null) {
            for (String key : map.keySet()) {
                System.out.println(key + ";" + map.get(key));
            }
        }
    }
}
