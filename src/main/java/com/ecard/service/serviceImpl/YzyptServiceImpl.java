package com.ecard.service.serviceImpl;

import com.ecard.ProjectConst;
import com.ecard.dao.*;
import com.ecard.entity.*;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.ResponseNoData;
import com.ecard.pojo.queryResult.MallOrderQr;
import com.ecard.pojo.queryResult.MedItemQr;
import com.ecard.pojo.queryResult.PtInfoQr;
import com.ecard.service.YzyptService;
import com.ecard.utils.AppUtil;
import com.ecard.utils.WeiXinUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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

    public void selectByOpenId(int status) {
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getResponse();
            HttpSession session = request.getSession();
            WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
            String openId = user.getOpenId();
            //String openId = "oJTU_09M70xDvS7xTU8hxrGujtxs";

            PtOpen open = ptOpenMapper.findByPtOpenId(openId);
            if(open==null) {
                String url = "http://www.yizhenyun.net.cn/blcfwp/wxregister/index.html#/register";
                response.sendRedirect(url);
            }else {
                if(status==0) {
                    String url = "http://www.yizhenyun.net.cn/blcfwp/wxregister/index.html#/register";
                    response.sendRedirect(url);
                }else if(status==1) {
                    String url = "http://www.yizhenyun.net.cn/blcfwp/wx/index.html#/myPrescription";
                    response.sendRedirect(url);
                }else if(status==2) {
                    String url = "http://www.yizhenyun.net.cn/blcfwp/wx/index.html#/guidance";
                    response.sendRedirect(url);
                }else if(status==3) {
                    String url = "http://www.yizhenyun.net.cn/blcfwp/wx/index.html#/myPrescription";
                    response.sendRedirect(url);
                }else if(status==4) {
                    String url = "http://www.yizhenyun.net.cn/blcfwp/wx/index.html#/mine";
                    response.sendRedirect(url);
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
    public Response selectMallOrder(Integer mallNo, Integer orderStatus, Integer ptNo, Integer drNo, Integer page, Integer rows) {
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
}
