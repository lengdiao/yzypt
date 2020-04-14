package com.ecard.service.serviceImpl;

import com.ecard.Constants;
import com.ecard.ProjectConst;
import com.ecard.dao.*;
import com.ecard.entity.*;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.ResponseNoData;
import com.ecard.pojo.queryResult.*;
import com.ecard.service.YzyptService;
import com.ecard.utils.AppUtil;
import com.ecard.utils.RoleUtil;
import com.ecard.utils.Sha1;
import com.ecard.utils.WeiXinUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class YzyptServiceImpl implements YzyptService {
    @Autowired
    private DrInfoMapper drInfoMapper;
    @Autowired
    private CloudPassInfoMapper cloudPassInfoMapper;
    @Autowired
    private MallOrderMapper mallOrderMapper;
    @Autowired
    private ApiDiseaseMapper apiDiseaseMapper;
    @Autowired
    private TemplateMapper templateMapper;
    @Autowired
    private MedOrderMapper medOrderMapper;
    @Autowired
    private MedRecordMapper medRecordMapper;
    @Autowired
    private MedItemMapper medItemMapper;
    @Autowired
    private DrugSetMapper drugSetMapper;
    @Autowired
    private PtInfoMapper ptInfoMapper;
    @Autowired
    private DrOpenIdMapper drOpenIdMapper;
    @Autowired
    private PtOpenMapper ptOpenMapper;
    @Autowired
    private DiseaseMasterMapper diseaseMasterMapper;
    @Autowired
    private SednsmsMapper sednsmsMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private MrPictureMapper mrPictureMapper;
    @Autowired
    private ReceiptAddressMapper receiptAddressMapper;
    @Autowired
    private DrugStoreMapper drugStoreMapper;
    @Autowired
    private DisInfoMapper disInfoMapper;
    @Autowired
    private DrDisRelationMapper drDisRelationMapper;
    @Autowired
    private MallAddressMapper mallAddressMapper;
    @Autowired
    private CountMapper countMapper;
    @Autowired
    private ChiCountMapper chiCountMapper;

    public void selectByOpenId(int status) {
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getResponse();
            HttpSession session = request.getSession();
            WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
            String openId = user.getOpenId();
            //String openId = "oLkiUwbC5SFlF0oyN5dcs_-Ujz8s";

            List<PtOpen> open = ptOpenMapper.selectBtOpenId(openId);
            if(open.size()==0) {
                //注册页面
                String url = null;
                if(status==1){
                    //跳转易臻云平台
                    url = "https://www.yizhenyun.com.cn/yzypt/wx/register/index.html#/register?status=2";
                }else if(status==2){
                    //跳转自主下单
                    url = "https://www.yizhenyun.com.cn/yzypt/wx/zzxd/index.html#/home";
                }
                response.sendRedirect(url);
            }else {
                PtInfoQr ptInfo = ptInfoMapper.selectByPtNo(open.get(0).getPtNo());
                if("".equals(ptInfo.getPhone())||ptInfo.getPhone()==null){
                    //需要补全信息
                    if(status==1){
                        //跳转易臻云平台
                        String url = "https://www.yizhenyun.com.cn/yzypt/wx/register/index.html#/register?status=2";
                        response.sendRedirect(url);
                    }else if(status==2){
                        //跳转自主下单
                        String url = "https://www.yizhenyun.com.cn/yzypt/wx/zzxd/index.html#/home";
                        response.sendRedirect(url);
                    }
                }else{
                    if(status==1) {
                        String url = "https://www.yizhenyun.com.cn/yzypt/wx/br/index.html#/myDoctor";
                        response.sendRedirect(url);
                    }else if(status==2) {
                        String url = "https://www.yizhenyun.com.cn/yzypt/wx/zzxd/index.html#/home";
                        response.sendRedirect(url);
                    }
                }

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }





    /**
     * 获取微信用户的信息
     * @param accessToken
     * @param openId
     * @return
     */
    public WeiXinUser getUserInfo(String accessToken, String openId) {
        WeiXinUser weixinUserInfo = null;
        // 拼接获取用户信息接口的请求地址
        String requestUrl = ProjectConst.GET_WEIXIN_USER_URL.replace("ACCESS_TOKEN", accessToken).replace(
                "OPENID", openId);
        // 获取用户信息(返回的是Json格式内容)
        JSONObject jsonObject = WeiXinUtils.doGetStr(requestUrl);

        if (null != jsonObject) {
            try {
                //封装获取到的用户信息
                weixinUserInfo = new WeiXinUser();
                // 用户的标识
                weixinUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
                weixinUserInfo.setNickname(jsonObject.getString("nickname"));
                // 用户的性别（1是男性，2是女性，0是未知）
                weixinUserInfo.setSex(jsonObject.getInt("sex"));
                // 用户所在国家
                weixinUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                weixinUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                weixinUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
            } catch (Exception e) {
                if (0 == weixinUserInfo.getSubscribe()) {
                    System.out.println("用户并没有关注本公众号");
                } else {
                    int errorCode = jsonObject.getInt("errcode");
                    String errorMsg = jsonObject.getString("errmsg");
                    System.out.println("由于"+errorCode +"错误码；错误信息为："+errorMsg+"；导致获取用户信息失败");
                }
            }
        }
        return weixinUserInfo;
    }

    /**
     * 进行用户授权，获取到需要的授权字段，比如openId
     * @param code 识别得到用户id必须的一个值
     * 得到网页授权凭证和用户id
     * @return
     */
    public Map<String, String> oauth2GetOpenid(String code) {
        //自己的配置appid（公众号进行查阅）
        String appid = ProjectConst.PROJECT_APPID;
        //自己的配置APPSECRET;（公众号进行查阅）
        String appsecret = ProjectConst.PROJECT_APPSECRET;
        //拼接用户授权接口信息
        String requestUrl = ProjectConst.GET_WEBAUTH_URL.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", code);
        //存储获取到的授权字段信息
        Map<String, String> result = new HashMap<String, String>();
        try {
            JSONObject OpenidJSONO = WeiXinUtils.doGetStr(requestUrl);
            //OpenidJSONO可以得到的内容：access_token expires_in  refresh_token openid scope
            String Openid = String.valueOf(OpenidJSONO.get("openid"));
            String AccessToken = String.valueOf(OpenidJSONO.get("access_token"));
            System.out.println("AccessToken"+AccessToken);
            //用户保存的作用域
            String Scope = String.valueOf(OpenidJSONO.get("scope"));
            String refresh_token = String.valueOf(OpenidJSONO.get("refresh_token"));
            result.put("Openid", Openid);
            result.put("AccessToken", AccessToken);
            result.put("scope", Scope);
            result.put("refresh_token", refresh_token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取到微信用户的唯一的OpendID
     * @param code  这是要获取OpendId的必须的一个参数
     * @return
     */
    public Map<String , String> getAuthInfo(String code) {
        //进行授权验证，获取到OpenID字段等信息
        Map<String, String> result = oauth2GetOpenid(code);
        // 从这里可以得到用户openid
        String openId = result.get("Openid");
        System.out.println("openId="+openId);
        return result;
    }

    @Override
    public Response selectMallOrder(Long mallNo, Integer orderStatus, Integer ptNo, Integer drNo, Integer page, Integer rows) {
        ResponseHasData response = new ResponseHasData();

        try {
            Page<?> pa =  PageHelper.startPage(page, rows);
            List<MallOrderQr> mallOrderQrs = new ArrayList<MallOrderQr>();
            List<MallOrder> mallOrders = mallOrderMapper.selectByMnOsPnDn(mallNo,orderStatus,ptNo,drNo);
            if(mallOrders.size()!=0){
                for(MallOrder mallOrder : mallOrders){
                    MallOrderQr mallOrderQr = new MallOrderQr();
                    mallOrderQr.setMallOrder(mallOrder);
                    MedOrder medOrder = medOrderMapper.selectByPrimaryKey(mallOrder.getMedOrderNo());
                    mallOrderQr.setMedOrder(medOrder);
                    MedRecord medRecord = medRecordMapper.selectByPrimaryKey(medOrder.getMedRecordNo());
                    mallOrderQr.setMedRecord(medRecord);
                    MrPicture[] mrPictures = mrPictureMapper.selectByMedRecordNo(medOrder.getMedRecordNo());
                    mallOrderQr.setMrPictures(mrPictures);
                    if(mallOrder.getAddress()!=null&&!"".equals(mallOrder.getAddress())){
                        ReceiptAddress receiptAddress = mallAddressMapper.selectByPrimaryKey1(Long.valueOf(mallOrder.getAddress()));
                        mallOrderQr.setReceiptAddress(receiptAddress);
                    }
                    List<MedItemQr> medItemQrs = medItemMapper.selectByMedOrderNo(medOrder.getOrderNo());
                    mallOrderQr.setMedItemQr(medItemQrs);
                    PtInfoQr ptInfo = ptInfoMapper.selectByPtNo(mallOrder.getPtNo());
                    mallOrderQr.setPtInfoQr(ptInfo);
                    mallOrderQrs.add(mallOrderQr);
                }
            }
            Long total = pa.getTotal();
            //创建分页条件
            PageInfo<MallOrderQr> pageData = new PageInfo<MallOrderQr>(mallOrderQrs,total.intValue());

            response.setData(pageData);
            response.setMsg("查询成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("查询失败");
            response.setStatus(0);
        }
        return response;
    }

    @Override
    public Response updateByPhoneAndPass(String phone, String oldPassword, String newPassword) {
        ResponseHasData response = new ResponseHasData();
        try {
            if(phone==null||"".equals(phone)) {
                response.setStatus(1);
                response.setMsg("电话号码为空");
            }else if(oldPassword==null||"".equals(oldPassword)) {
                response.setStatus(1);
                response.setMsg("原密码为空");
            }else if(newPassword==null||"".equals(newPassword)) {
                response.setStatus(1);
                response.setMsg("新密码为空");
            }else {
                CloudPassInfo cloudPassInfo = cloudPassInfoMapper.selectByPhone(phone);
                if(oldPassword.endsWith(cloudPassInfo.getPassword())) {
                    cloudPassInfo.setPassword(newPassword);
                    cloudPassInfoMapper.updateByPrimaryKeySelective(cloudPassInfo);
                    response.setStatus(0);
                    response.setMsg("修改成功");
                }else {
                    response.setStatus(0);
                    response.setMsg("原密码错误");
                }
            }
        } catch (Exception e) {
            response.setStatus(1);
            response.setMsg("修改失败");
        }
        return response;
    }

    @Override
    public Response sendCodeSms(String phone) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Response response = new ResponseHasData();
        Sednsms sednsms = null;
        if (!StringUtils.isEmpty(phone) && phone.length() == 11) {
            try {
                String code = String.valueOf(Math.round(((Math.random() * 9 + 1) * 100000)));
                String content = "您的注册验证码为：" + code;

                // 发送验证码
                boolean sms = AppUtil.sendSms(phone, content);
                if (sms) {
                    HttpSession session = request.getSession();
                    session.setAttribute(phone, code);
                    sednsms = sednsmsMapper.selectByPhone(phone);
                    Sednsms sednsmsObject = new Sednsms();
                    sednsmsObject.setCode(code);
                    sednsmsObject.setExpiryDate(new Date(new Date().getTime() + 600000));
                    if (sednsms == null) {
                        sednsmsObject.setPhone(phone);
                        sednsmsObject.setCreateTime(new Date());
                        sednsmsMapper.save(sednsmsObject);
                        response.setMsg(phone + "发送注册验证码成功-----");
                    } else {
                        sednsms.setCode(code);
                        sednsms.setPhone(phone);
                        sednsms.setExpiryDate(new Date(new Date().getTime() + 600000));
                        sednsmsMapper.update(sednsms);
                        response.setMsg(phone + "更新验证码成功-----");
                    }
                    return response;
                } else {
                    response.setMsg(phone + "发送注册验证码失败-----");
                    response.setStatus(1);
                    return response;
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.setMsg(phone + "发送注册验证码失败-----");
                response.setStatus(1);
                return response;
            }
        } else {
            response.setMsg("手机号码有误");
            response.setStatus(1);
            return response;
        }
    }


    public Response updateByCode(String phone, String code, String newCode) {
        Response response = new ResponseNoData();
        CloudPassInfo cloudPassInfo = new CloudPassInfo();
        Sednsms sednsms = new Sednsms();
        try {
            if (StringUtils.isEmpty(phone)) {
                response.setStatus(1);
                response.setMsg("手机号不能为空");
                return response;
            }
            cloudPassInfo = cloudPassInfoMapper.selectByPhone(phone);
            if(cloudPassInfo==null) {
                response.setStatus(1);
                response.setMsg("没有此用户");
                return response;
            }
            sednsms = sednsmsMapper.selectByPhone(phone);
            if (sednsms == null) {
                response.setStatus(1);
                response.setMsg("请点击获取验证码");
                return response;
            }
            if (!sednsms.getCode().equals(code)) {
                response.setStatus(1);
                response.setMsg("验证码错误！");
                return response;
            }
            System.out.println("截止时间查看 ： " + sednsms.getExpiryDate());
            if (sednsms.getExpiryDate().getTime() < new Date().getTime()) {
                response.setStatus(1);
                response.setMsg("验证码失效！");
                return response;
            }
            if (StringUtils.isNotEmpty(newCode)) {
                cloudPassInfo.setPassword(newCode);
                cloudPassInfoMapper.updateByPrimaryKeySelective(cloudPassInfo);
                response.setStatus(0);
                response.setMsg("修改成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("填写新密码失败");
            return response;
        }
        response.setStatus(0);
        response.setMsg("填写新密码成功");
        return response;

    }

    public Response login(String phone, String passWord) {
        ResponseHasData response = new ResponseHasData();
        List<CloudPassInfo> cloudPassInfos = new ArrayList<CloudPassInfo>();
        CloudPassInfo cloudPassInfo = new CloudPassInfo();
        System.out.println("service 进来了 ");
        if (phone==null||phone.equals("")) {
            response.setStatus(1);
            response.setMsg("登录账号不能为空");
            return response;
        }
        if (phone.length() == 11) {
            cloudPassInfos = cloudPassInfoMapper.findByPhoneRole(phone);
        }
        if (cloudPassInfos.size()==0) {
            response.setStatus(1);
            response.setMsg("登陆账号不存在");
            return response;
        }else if(cloudPassInfos.size()==1){
            cloudPassInfo = cloudPassInfoMapper.selectByPhoneAndPasswordNull(phone);
        }else if(cloudPassInfos.size()>1){
            cloudPassInfo = cloudPassInfoMapper.findByPhoneDisRole(phone);
            if (cloudPassInfo==null){
                cloudPassInfo = cloudPassInfoMapper.selectByPhoneDrugStore(phone);
            }
        }
        //String cloudPassNo = String.valueOf(c);
        // shiro 校验
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(String.valueOf(cloudPassInfo.getCloudPassNo()), passWord);
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);
            UserRole urRole = userRoleMapper.selectByCloudPassNo(cloudPassInfo.getCloudPassNo());
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("cloudPassNo", cloudPassInfo.getCloudPassNo());
            data.put("phone", cloudPassInfo.getPhone());
            data.put("name", cloudPassInfo.getName());
            Long roleNo = urRole.getRoleNo();
            data.put("roleNo", roleNo);
            if(roleNo==2){//医生
                DrInfoQr drInfo = drInfoMapper.selectByCloudPassNo(cloudPassInfo.getCloudPassNo());
                data.put("drNo",drInfo.getDrNo());
            }else if(roleNo==3||roleNo==4||roleNo==6){//药店
                DrugStore drugStore = drugStoreMapper.selectByCloudPassNo(cloudPassInfo.getCloudPassNo());
                data.put("drugStoreNo",drugStore.getDrugStoreNo());
            }else if(roleNo==5||roleNo==7){
                Long disNo = disInfoMapper.selectByCloudPassNo(cloudPassInfo.getCloudPassNo()).getDisNo();
                DisInfo disInfo = disInfoMapper.selectByPrimaryKey(disNo);
                data.put("disNo", disNo);
                data.put("disLevel",disInfo.getDisLevel());
                if(disInfo.getDisLevel().equals("2")){
                    DisInfo disInfo1 = disInfoMapper.select1DisNo(disNo);
                    data.put("disLeader",disInfo1.getDisNo());
                }
            }


            response.setData(data);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("登录失败，请输入正确的账号和密码");
            return response;
        }
        response.setStatus(0);
        response.setMsg("登录成功");
        return response;
    }

    public Response getSign(String url) {
        String nonce_str = randomStr(32);
        String timestamp = create_timestamp();
        ResponseHasData response = new ResponseHasData();
        AccessToken accessToken=WeiXinUtils.getAccessToken();
        String jsapi_ticket = WeiXinUtils.getJsapi_ticket(accessToken.getToken());
        System.out.println("jsapi_ticket="+jsapi_ticket);
        String string1 ="jsapi_ticket="+jsapi_ticket+"&noncestr="+nonce_str+"&timestamp="+timestamp+"&url="+url;
        String signature = Sha1.getSha1(string1);
        Map<String , String > map = new HashMap<String,String>();
        map.put("signature", signature);
        map.put("noncestr", nonce_str);
        map.put("timestamp", timestamp);
        map.put("appid", "wx5a917cabd0432687");
        response.setData(map);
        response.setMsg("获取成功");
        response.setStatus(0);
        return response;
    }

    @Override
    public Response findMallOrder(Long mallNo, Long drugStoreNo, Integer orderStatus, String startDate, String endDate, Integer page, Integer rows, Long drNo, Long disNo1, Long disNo2) {
        ResponseHasData response = new ResponseHasData();
        try {
            List<MallOrderQr> mallOrderQrs = new ArrayList<MallOrderQr>();
            Page<?> pa =  PageHelper.startPage(page, rows);
            List<MallOrder> mallOrders = mallOrderMapper.selectByMoOsSdEd(drugStoreNo,mallNo,orderStatus,startDate,endDate,drNo,disNo1,disNo2);
            if(mallOrders.size()!=0){
                for(MallOrder mallOrder : mallOrders){
                    DrInfoQr drInfoQr = drInfoMapper.selectByDrNo(mallOrder.getDrNo());
                    MallOrderQr mallOrderQr = new MallOrderQr();
                    mallOrderQr.setMallOrder(mallOrder);
                    MedOrder medOrder = medOrderMapper.selectByPrimaryKey(mallOrder.getMedOrderNo());
                    if(medOrder.getOrderType()==1){
                        BigDecimal unitPrice = medOrder.getOrderAmount().divide(new BigDecimal(medOrder.getPrescriptionNum().toString()));
                        medOrder.setUnitPrice(unitPrice);
                    }
                    mallOrderQr.setMedOrder(medOrder);
                    MedRecord medRecord = medRecordMapper.selectByPrimaryKey(medOrder.getMedRecordNo());
                    String diagContentName = medRecord.getDiagContent();
                    if(diagContentName!=null&&!"".equals(diagContentName)){
                        String[] diag = diagContentName.split(",");
                        if(medOrder.getOrderType()==1){
                            diagContentName = "";
                            //中医
                            for(int i = 0 ; i < diag.length ; i++){
                                String[] diag1 = diag[i].split("-");
                                diagContentName += diseaseMasterMapper.selectByPrimaryKey(Long.valueOf(diag1[0])).getChtName()+"-";
                                diagContentName += diseaseMasterMapper.selectByPrimaryKey(Long.valueOf(diag1[1])).getChtName()+",";
                                System.out.println("中医"+diagContentName);
                            }
                            diagContentName = diagContentName.substring(0,diagContentName.length()-1);
                        }else{
                            diagContentName = "";
                            //西医
                            for(int i = 0 ; i < diag.length ; i++){
                                diagContentName += diseaseMasterMapper.selectByPrimaryKey(Long.valueOf(diag[i])).getChtName()+",";
                                System.out.println("西医"+diagContentName);
                            }
                            diagContentName = diagContentName.substring(0,diagContentName.length()-1);
                        }
                        medRecord.setDiagContentName(diagContentName);

                    }
                    mallOrderQr.setMedRecord(medRecord);


                    MrPicture[] mrPictures = mrPictureMapper.selectByMedRecordNo(medOrder.getMedRecordNo());
                    mallOrderQr.setMrPictures(mrPictures);
                    List<MedItemQr> medItemQrs = medItemMapper.selectByMedOrderNo(medOrder.getOrderNo());
                    for (MedItemQr medItemQr:medItemQrs){
                        DrugSet drugSet = drugSetMapper.selectByPrimaryKey(medItemQr.getDrugNo());
                        medItemQr.setDrugSet(drugSet);
                    }


                    if(mallOrder.getAddress()!=null&&!"".equals(mallOrder.getAddress())){
                        ReceiptAddress receiptAddress = mallAddressMapper.selectByPrimaryKey1(Long.valueOf(mallOrder.getAddress()));
                        mallOrderQr.setReceiptAddress(receiptAddress);
                    }
                    mallOrderQr.setMedItemQr(medItemQrs);
                    PtInfoQr ptInfo = ptInfoMapper.selectByPtNo(mallOrder.getPtNo());
                    if(ptInfo.getIdNo()!=null&&!"null".equals(ptInfo.getIdNo())&&!"".equals(ptInfo.getIdNo())
                    ){
                        int age = IdNOToAge(ptInfo.getIdNo());
                        ptInfo.setAge(age);
                    }
                    mallOrderQr.setPtInfoQr(ptInfo);
                    mallOrderQr.setDrInfoQr(drInfoQr);

                    DisInfoQr disInfoQr = disInfoMapper.selectNameByDrNo(mallOrder.getDrNo());
                    mallOrderQr.setDis1Name(disInfoQr.getDisLeaderName());
                    mallOrderQr.setDis2Name(disInfoQr.getDisName());

                    mallOrderQrs.add(mallOrderQr);

                }

            }
            Long total = pa.getTotal();
            //创建分页条件
            PageInfo<MallOrderQr> pageData = new PageInfo<MallOrderQr>(mallOrderQrs,total.intValue());

            response.setData(pageData);
            response.setMsg("查询成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("查询失败");
            response.setStatus(0);
        }
        return response;
    }

    @Override
    public Response insertDiseaseMaster(DiseaseMaster diseaseMaster) {
        ResponseHasData response = new ResponseHasData();
        try {
            List<DiseaseMaster> diseaseMaster1 = diseaseMasterMapper.selectByIcdAndName(diseaseMaster.getChtName(),diseaseMaster.getIcdCode());
            if(diseaseMaster1.size()!=0){
                response.setStatus(1);
                response.setMsg("已经存在该疾病");
                return response;
            }
            diseaseMasterMapper.insertSelective(diseaseMaster);
            response.setStatus(0);
            response.setMsg("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("添加失败");
        }
        return response;
    }

    @Override
    public Response updateDiseaseMaster(DiseaseMaster diseaseMaster) {
        ResponseHasData response = new ResponseHasData();
        try {
            List<DiseaseMaster> diseaseMaster1 = diseaseMasterMapper.selectByIcdAndNameAndNo(diseaseMaster.getChtName(),diseaseMaster.getIcdCode(),diseaseMaster.getDNo());
            if(diseaseMaster1!=null){
                response.setStatus(1);
                response.setMsg("已经存在该疾病");
                return response;
            }
            diseaseMasterMapper.updateByPrimaryKeySelective(diseaseMaster);
            response.setStatus(0);
            response.setMsg("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("更新失败");
        }
        return response;
    }

    @Override
    public Response export(Long mallNo, Integer orderStatus, Integer ptNo, Integer drNo, String startDate, String endDate) {
        ResponseHasData response = new ResponseHasData();

        try {
            List<MallOrderQr> mallOrderQrs = new ArrayList<MallOrderQr>();
            List<MallOrder> mallOrders = mallOrderMapper.selectByMnOsPnDns(mallNo,orderStatus,ptNo,drNo,startDate,endDate);
            if(mallOrders.size()!=0){
                for(MallOrder mallOrder : mallOrders){
                    MallOrderQr mallOrderQr = new MallOrderQr();
                    mallOrderQr.setMallOrder(mallOrder);
                    MedOrder medOrder = medOrderMapper.selectByPrimaryKey(mallOrder.getMedOrderNo());
                    mallOrderQr.setMedOrder(medOrder);
                    MedRecord medRecord = medRecordMapper.selectByPrimaryKey(medOrder.getMedRecordNo());
                    mallOrderQr.setMedRecord(medRecord);
                    MrPicture[] mrPictures = mrPictureMapper.selectByMedRecordNo(medOrder.getMedRecordNo());
                    mallOrderQr.setMrPictures(mrPictures);
                    if(mallOrder.getAddress()!=null&&!"".equals(mallOrder.getAddress())){
                        ReceiptAddress receiptAddress = receiptAddressMapper.selectByPrimaryKey(Long.valueOf(mallOrder.getAddress()));
                        mallOrderQr.setReceiptAddress(receiptAddress);
                    }
                    List<MedItemQr> medItemQrs = medItemMapper.selectByMedOrderNo(medOrder.getOrderNo());
                    mallOrderQr.setMedItemQr(medItemQrs);
                    PtInfoQr ptInfo = ptInfoMapper.selectByPtNo(mallOrder.getPtNo());
                    mallOrderQr.setPtInfoQr(ptInfo);
                    mallOrderQrs.add(mallOrderQr);
                }
            }

            response.setData(mallOrderQrs);
            response.setMsg("查询成功");
            response.setStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMsg("查询失败");
            response.setStatus(0);
        }
        return response;
    }

    @Override
    public Response saveDis(DisInfoQr disInfoQr) {
        ResponseHasData response = new ResponseHasData();

        try {
            // 创建CloudPassInfo
            List<CloudPassInfo> cpi = cloudPassInfoMapper.findByPhoneDis(null,disInfoQr.getPhone());
            if (cpi.size()!=0) {
                response.setStatus(Constants.STATUS_FAIL);
                response.setMsg("注册手机号码已存在，请更换注册手机号");
                return response;
            }
            CloudPassInfo cloudPassInfo = new CloudPassInfo();
            cloudPassInfo.setName(disInfoQr.getName());
            cloudPassInfo.setPhone(disInfoQr.getPhone());
            cloudPassInfo.setPassword(Constants.PASSWORDS);
            // cloudPassInfo.setCloudpasskind(Constants.CLOUD_PASS_KIND_DOCTOR);
            cloudPassInfo.setCloudPassKind(disInfoQr.getCloudPassKind());
            cloudPassInfo.setIdNo(disInfoQr.getIdNo());
            cloudPassInfo.setDisableFlag(disInfoQr.getDisableFlag());
            cloudPassInfo.setCreateTime(new Date());
            cloudPassInfo.setCreateUser(Constants.SYSTEM_USER);
            cloudPassInfoMapper.insertSelective(cloudPassInfo);
            Long cloudPassNo = cloudPassInfo.getCloudPassNo();
            // 授权
            if(disInfoQr.getDisLevel().equals("1")){
                RoleUtil.accredit(cloudPassNo, "一级代表");
            }else if(disInfoQr.getDisLevel().equals("2")){
                RoleUtil.accredit(cloudPassNo, "二级代表");
            }

            // 创建DisInfo
            DisInfo disInfo = new DisInfo();
            disInfo.setCloudPassNo(cloudPassNo);
            disInfo.setDisName(disInfoQr.getName());
            disInfo.setTelePhone(disInfoQr.getPhone());
            disInfo.setAddress(disInfoQr.getAddress());
            disInfo.setEmail(disInfoQr.getEmail());
            disInfo.setDisLeader(disInfoQr.getDisLeader());
            disInfo.setDisableFlag(disInfoQr.getDisableFlag());
            disInfo.setDisLevel(disInfoQr.getDisLevel());
            disInfo.setCreateTime(new Date());
            disInfo.setCreateUser(Constants.SYSTEM_USER);
            disInfoMapper.insert(disInfo);
            response.setStatus(Constants.STATUS_SUCCESS);
            response.setMsg("新增成功");
        } catch (Exception e) {
            response.setStatus(Constants.STATUS_FAIL);
            response.setMsg("新增失败");
            e.printStackTrace();
            return response;
        }
        return response;
    }

    @Override
    public Response selectByDisNo(Long disno) {
        ResponseHasData response = new ResponseHasData();
        try {
            DisInfoQr disInfoQr = disInfoMapper.selectByNo(disno);
            if(disInfoQr.getDisLevel().equals(1)) {
                disInfoQr.setDisLevelName("经理");
            }
            if(disInfoQr.getDisLevel().equals(2)) {
                disInfoQr.setDisLevelName("代表");
            }
            response.setStatus(Constants.STATUS_SUCCESS);
            response.setMsg("查询成功");
            response.setData(disInfoQr);
        } catch (Exception e) {
            response.setStatus(Constants.STATUS_FAIL);
            response.setMsg("查询失败");
            return response;
        }
        return response;
    }

    @Override
    public Response updateDis(DisInfoQr disInfoQr) {
        ResponseHasData response = new ResponseHasData();
        System.out.println(disInfoQr.getAddress()+"地址");
        // 判断传入主键是否为空
        Long cloudPassNo = disInfoQr.getCloudPassNo();
        Long disNo = disInfoQr.getDisNo();
        if (cloudPassNo == null || disNo == null) {
            response.setStatus(Constants.STATUS_FAIL);
            response.setMsg("传入cloudpassno或drno不能为空");
            return response;
        }
        List<CloudPassInfo> cpi = cloudPassInfoMapper.findByPhoneDis(disInfoQr.getDisNo(),disInfoQr.getPhone());
        if (cpi.size()!=0) {
            response.setStatus(Constants.STATUS_FAIL);
            response.setMsg("注册手机号码已存在，请更换注册手机号");
            return response;
        }


        try {
            /** 工作人员对经销商的信息修改 */
            // 修改CloudPassInfo
            CloudPassInfo cloudPassInfo = cloudPassInfoMapper.selectByPrimaryKey(cloudPassNo);
            cloudPassInfo.setName(disInfoQr.getName());
            cloudPassInfo.setIdNo(disInfoQr.getIdNo());
            cloudPassInfo.setDisableFlag(disInfoQr.getDisableFlag());
            cloudPassInfo.setUpdateTime(new Date());
            cloudPassInfo.setUpdateUser(Constants.SYSTEM_USER);
            cloudPassInfo.setPhone(disInfoQr.getPhone());
            cloudPassInfoMapper.updateByPrimaryKey(cloudPassInfo);
            // 修改DisInfo
            DisInfo disInfo = disInfoMapper.selectByPrimaryKey(disNo);
            disInfo.setDisName(disInfoQr.getName());
            disInfo.setTelePhone(disInfoQr.getPhone());
            disInfo.setAddress(disInfoQr.getAddress());
            disInfo.setDisLevel(disInfoQr.getDisLevel());
            disInfo.setDisLeader(disInfoQr.getDisLeader());
            disInfo.setDisableFlag(disInfoQr.getDisableFlag());
            disInfo.setEmail(disInfoQr.getEmail());
            disInfo.setUpdateTime(new Date());
            disInfo.setUpdateUser(Constants.SYSTEM_USER);
            disInfoMapper.updateByPrimaryKeySelective(disInfo);
            response.setStatus(Constants.STATUS_SUCCESS);
            response.setMsg("修改成功");
        } catch (Exception e) {
            response.setStatus(Constants.STATUS_FAIL);
            response.setMsg("修改失败");
            return response;
        }
        return response;
    }

    @Override
    public Response getDisLeader(String disLevel) {
        ResponseHasData response = new ResponseHasData();
        if (disLevel.equals("1")) {
            response.setStatus(Constants.STATUS_SUCCESS);
            response.setMsg("等级为1,查询成功  ！！！");
        }
        List<DisInfo> list = new ArrayList<>();
        if (disLevel.equals("2")) {
            try {
                list = disInfoMapper.listByDisLevel("1");
                response.setData(list);
                response.setStatus(Constants.STATUS_SUCCESS);
                response.setMsg("查询成功  ！！！");
            } catch (Exception e) {
                response.setStatus(Constants.STATUS_FAIL);
                response.setMsg("查询下拉框失败！！");
                e.printStackTrace();
                return response;
            }
        }
        return response;
    }

    @Override
    public Response insertDr(String disNo, String name, String hospital, String phone, String chiefNo,
                             String practiceProfile, String title, String leaderDrNo, String drTitleCert,
                             String drPracticeRegCert, String consultingHour, String idNo, int disableFlag, int type,
                             String province, String city) {
        ResponseHasData response = new ResponseHasData();
        DrInfo di = new DrInfo();
        CloudPassInfo cpi = new CloudPassInfo();
        DrDisRelation ddr = new DrDisRelation();
        try {
            CloudPassInfo CPI = cloudPassInfoMapper.selectByPhoneAndDoctor(phone);
            if (CPI == null || CPI.equals("")) {
            } else {
                response.setMsg("新增失败,存在相同手机号");
                response.setStatus(Constants.STATUS_FAIL);
                return response;
            }
            cpi.setName(name);
            cpi.setPassword("123456");
            cpi.setPhone(phone);
            cpi.setCreateTime(new Date());
            cpi.setCreateUser(Constants.SYSTEM_USER);
            cpi.setIdNo(idNo);
            cpi.setDisableFlag(disableFlag);
            cloudPassInfoMapper.insertSelective(cpi);
            RoleUtil.accredit(cpi.getCloudPassNo(),"医生");
            di.setChiefNo(chiefNo);
            di.setCloudPassNo(cpi.getCloudPassNo());
            di.setConsultingHour(consultingHour);
            di.setCreateTime(new Date());
            di.setCreateUser(Constants.SYSTEM_USER);
            di.setDisableFlag(Constants.STATUS_SUCCESS);
            di.setHospital(hospital);
            di.setProvince(province);
            di.setCity(city);
            if (leaderDrNo == null || leaderDrNo.equals("")) {
            } else {
                di.setLeaderDrNo(Long.valueOf(leaderDrNo));
            }
            di.setPracticeProfile(practiceProfile);
            di.setTitle(title);
            di.setDrTitleCert(drTitleCert);
            di.setDrPracticeRegCert(drPracticeRegCert);
            di.setType(type);
            drInfoMapper.insertSelective(di);

            ddr.setCreateTime(new Date());
            ddr.setCreateUser(Constants.SYSTEM_USER);
            ddr.setDisableFlag(Constants.STATUS_SUCCESS);
            ddr.setDisNo(Long.valueOf(disNo));
            ddr.setDrNo(di.getDrNo());
            drDisRelationMapper.insertSelective(ddr);
            response.setMsg("新增成功");
            response.setStatus(Constants.STATUS_SUCCESS);
            return response;
        } catch (NumberFormatException e) {
            response.setMsg("新增失败");
            response.setStatus(Constants.STATUS_FAIL);
            return response;
        }
    }

    @Override
    public Response updateDr(String drNo, String name, String hospital, String phone, String chiefNo,
                             String practiceProfile, String title, String drTitleCert, String drPracticeRegCert,
                             String consultingHour, String leaderDrNo, String idNo, int disableFlag, int type,
                             String province, String city) {
        ResponseHasData response = new ResponseHasData();
        try {

            CloudPassInfo info = cloudPassInfoMapper.selectByPhoneIdNoDrNo(Long.valueOf(drNo),phone,idNo);
            if(info!=null){
                response.setStatus(1);
                response.setMsg("手机号码或身份证号码重复");
                return response;
            }

            DrInfo di = drInfoMapper.selectByPrimaryKey(Long.valueOf(drNo));
            System.out.println(di);
            di.setChiefNo(chiefNo);
            di.setConsultingHour(consultingHour);
            di.setUpdateTime(new Date());
            di.setUpdateUser(Constants.SYSTEM_USER);
            di.setDisableFlag(Constants.STATUS_SUCCESS);
            di.setHospital(hospital);
            di.setPracticeProfile(practiceProfile);
            di.setTitle(title);
            di.setCity(city);
            di.setProvince(province);
            if(leaderDrNo==null||leaderDrNo.equals("")||leaderDrNo.equals("null")) {
                di.setLeaderDrNo(null);
            }else {
                di.setLeaderDrNo(Long.parseLong(leaderDrNo));
            }
            di.setDrTitleCert(drTitleCert);
            di.setDrPracticeRegCert(drPracticeRegCert);
            di.setType(type);
            drInfoMapper.updateByPrimaryKeySelective(di);
            CloudPassInfo cpi = cloudPassInfoMapper.selectByPrimaryKey(di.getCloudPassNo());
            cpi.setName(name);
            cpi.setPhone(phone);
            cpi.setUpdateTime(new Date());
            cpi.setUpdateUser(Constants.SYSTEM_USER);
            cpi.setIdNo(idNo);
            cpi.setDisableFlag(disableFlag);
            cloudPassInfoMapper.updateByPrimaryKeySelective(cpi);
            response.setMsg("修改成功");
            response.setStatus(Constants.STATUS_SUCCESS);
            return response;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.setMsg("修改失败");
            response.setStatus(Constants.STATUS_FAIL);
            return response;
        }
    }

    @Override
    public Response count(int medOrderType, String drName, Long drugNo, String drPhone, Long disNo1, Long disNo2, String startDate, String endDate, Long drNo, Integer page, Integer rows) {
        ResponseHasData response = new ResponseHasData();
        try {

            if(medOrderType==1){
                //中药处方
                Page<?> pa =  PageHelper.startPage(page, rows);
                List<PcCountQr> pcCountQrs = chiCountMapper.selectByTypeDrNameDate(drName,drugNo,disNo1,disNo2,startDate,endDate,drPhone,drNo);
                //查询结果总数
                Long total = pa.getTotal();
                //创建分页条件
                PageInfo<PcCountQr> pageData = new PageInfo<PcCountQr>(pcCountQrs,total.intValue());

                response.setStatus(0);
                response.setMsg("查询成功");
                response.setData(pageData);
            }else{
                System.out.println(drName);
                System.out.println(medOrderType);
                //西药处方
                Page<?> pa =  PageHelper.startPage(page, rows);
                List<PcCountQr> pcCountQrs = countMapper.count1(drName,drugNo,drPhone,disNo1,disNo2,startDate,endDate,drNo);
                for (PcCountQr pcCountQr:pcCountQrs){
                    pcCountQr.setSum(pcCountQr.getAddSum()+pcCountQr.getSaveSum());
                }
                //查询结果总数
                Long total = pa.getTotal();
                //创建分页条件
                PageInfo<PcCountQr> pageData = new PageInfo<PcCountQr>(pcCountQrs,total.intValue());

                response.setStatus(0);
                response.setMsg("查询成功");
                response.setData(pageData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询失败");
        }
        return response;
    }


    public Response disList(Long disno, String IdNo, String name, String disLevel, Long disLeader, String sendDate,
                            String endDate, Integer disableFlag, Integer page, Integer rows) {
        ResponseHasData response = new ResponseHasData();
        try {

            Page<?> pa = PageHelper.startPage(page, rows);
            List<DisInfoQr> piq = disInfoMapper.selectList(disno, IdNo, name, disLevel, disLeader, sendDate, endDate,
                    disableFlag);
            System.out.println("查询列表长度 ： " + piq.size());
            for (DisInfoQr d : piq) {
                if (d.getDisLevel().equals("1")) {
                    d.setDisLevelName("一级代表");
                } else {
                    d.setDisLevelName("二级代表");
                }
            }
            // 查询结果总数
            Long total = pa.getTotal();
            // 创建分页条件
            PageInfo<DisInfoQr> pageData = new PageInfo<DisInfoQr>(piq, total.intValue());
            response.setStatus(Constants.STATUS_SUCCESS);
            response.setMsg("查询成功");
            response.setData(pageData);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Constants.STATUS_FAIL);
            response.setMsg("查询失败");
            return response;
        }
        return response;
    }


    public Response selectMyDr(String disNo, String name, Integer page, Integer rows) {
        ResponseHasData response = new ResponseHasData();
        try {
            String phone = null;
            if (name == null || name.equals("")) {
            } else {
                if (name.length() == 11) {
                    phone = name;
                    name = null;
                }
            }
            Page<?> pa = PageHelper.startPage(page, rows);
            List<DrInfoQr> drq = drDisRelationMapper.selectByDisNo(Long.valueOf(disNo), name, phone);
            for(DrInfoQr drInfoQr:drq){
                DrInfoQr drInfoQr1 = drInfoMapper.selectByDrNo(drInfoQr.getDrNo());
                drInfoQr.setDisableFlag(drInfoQr1.getDisableFlag());
                drInfoQr.setIdNo(drInfoQr1.getIdNo());
                drInfoQr.setType(drInfoQr1.getType());
                if(drInfoQr1.getIdNo()!=null&&!drInfoQr1.getIdNo().equals("null")&&!drInfoQr1.getIdNo().equals("")){
                    drInfoQr.setAge(IdNOToAge(drInfoQr1.getIdNo()));
                }
            }
            Long total = pa.getTotal();
            PageInfo<DrInfoQr> pageData = new PageInfo<DrInfoQr>(drq, total.intValue());
            response.setData(pageData);
            response.setMsg("查询成功");
            response.setStatus(Constants.STATUS_SUCCESS);
            return response;
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response.setMsg("查询失败");
            response.setStatus(Constants.STATUS_FAIL);
            return response;
        }
    }

    @Override
    public Response getDisList(String disNo) {
        ResponseHasData response = new ResponseHasData();
        try {
            List<DisInfoQr> drq = disInfoMapper.getDisList(Long.valueOf(disNo));
            response.setData(drq);
            response.setMsg("查询成功");
            response.setStatus(Constants.STATUS_SUCCESS);
            return response;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response.setMsg("查询失败");
            response.setStatus(Constants.STATUS_FAIL);
            return response;
        }
    }


    public void test() {
        DrInfo drInfo = new DrInfo();
        drInfo.setType(1);
        drInfoMapper.insertSelective(drInfo);
        test1();
    }

    public void test1() {
        DrInfo drInfo = new DrInfo();
        drInfo.setType(1);
        drInfoMapper.insertSelective(drInfo);
        drInfo.setType(2);
        drInfo.setDrNo(null);
        drInfoMapper.insertSelective(drInfo);
        drInfo.getCompany().equals("1");
    }


    public static int IdNOToAge(String IdNO){
        int leh = IdNO.length();
        String dates="";
        if (leh == 18) {
            int se = Integer.valueOf(IdNO.substring(leh - 1)) % 2;
            dates = IdNO.substring(6, 10);
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year=df.format(new Date());
            int u=Integer.parseInt(year)-Integer.parseInt(dates);
            return u;
        }else{
            dates = IdNO.substring(6, 8);
            return Integer.parseInt(dates);
        }

    }


    public static String randomStr(int n) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 32; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000L);
    }

}

