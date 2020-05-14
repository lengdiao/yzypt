package com.ecard.controller;

import com.ecard.ProjectConst;
import com.ecard.dao.*;
import com.ecard.entity.*;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.queryResult.MallMedOrderQr;
import com.ecard.pojo.queryResult.PtInfoQr;
import com.ecard.service.DrugSetService;
import com.ecard.utils.WeiXinUtils;
import io.swagger.annotations.Api;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/share")
@CrossOrigin
public class ShareController {
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
    //访问微信接口跳转  platform=1易臻云 2视界
    @RequestMapping(value = "/getRedirect", method = RequestMethod.GET)
    public void getRedirect(
                            @RequestParam(value = "platform", required = false) int platform,
                            @RequestParam(value = "redirectUrl", required = false) String redirectUrl,
                            @RequestParam(value = "drugSetNo", required = false) Long drugSetNo,
                            @RequestParam(value = "goodsNo", required = false) Long goodsNo,
                            @RequestParam(value = "url", required = false) String url) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        try {
            String urls = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                    "redirect_uri=https://www.yizhenyun.com.cn/yzypt/share/tologin/userinfo?platform="
                     +platform+ "," + redirectUrl + "," + drugSetNo + "," +goodsNo+ "," +url+ "&appid=" + "wx4224aa99aacaba5b"
                    + "&response_type=code&scope=snsapi_base&state=STATE&connect_redirect=1#wechat_redirect";


            System.out.println(urls);
            response.sendRedirect(urls);
        } catch (Exception e) {
            System.out.println("报错");
            e.printStackTrace();
        }
    }


    /**
     * 进行网页授权，便于获取到用户的绑定的内容
     *
     * @return
     */
    @RequestMapping("/tologin/userinfo")
    public void check(@RequestParam(value = "platform") String platform) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        HttpSession session = request.getSession();
        //首先判断一下session中，是否有保存着的当前用户的信息，有的话，就不需要进行重复请求信息
        WeiXinUser weiXinUser = null;
        WeiXinUser sjWeiXinUser = null;
        /**
         * 进行获取openId，必须的一个参数，这个是当进行了授权页面的时候，再重定向了我们自己的一个页面的时候，
         * 会在request页面中，新增这个字段信息，要结合这个ProjectConst.Get_WEIXINPAGE_Code这个常量思考
         */
        String code = request.getParameter("code");
        System.out.println("code" + code);
        try {
            sjWeiXinUser = getTheCode1(session, code);

            session.setAttribute("sjCurrentUser", sjWeiXinUser);

            String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                    "redirect_uri=https://www.yizhenyun.com.cn/yzypt/share/tologin/userinfo1?platform="
                    + platform + "&appid=" + "wx5a917cabd0432687"
                    + "&response_type=code&scope=snsapi_base&state=STATE&connect_redirect=1#wechat_redirect";
            System.out.println(url);
            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // map.put("weiXinUser", weiXinUser);
    }


    @RequestMapping("/tologin/userinfo1")
    public void check1(@RequestParam(value = "platform") String platform) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        HttpSession session = request.getSession();

        ResponseHasData res  = new ResponseHasData();
        //首先判断一下session中，是否有保存着的当前用户的信息，有的话，就不需要进行重复请求信息
        WeiXinUser weiXinUser = null;
        WeiXinUser sjWeiXinUser = null;
        /**
         * 进行获取openId，必须的一个参数，这个是当进行了授权页面的时候，再重定向了我们自己的一个页面的时候，
         * 会在request页面中，新增这个字段信息，要结合这个ProjectConst.Get_WEIXINPAGE_Code这个常量思考
         */
        String code = request.getParameter("code");
        System.out.println("code" + code);
        try {
            //得到当前用户的信息(具体信息就看weixinUser这个javabean)
            weiXinUser = getTheCode(session, code);
            //将获取到的用户信息，放入到session中
            session.setAttribute("currentUser", weiXinUser);
            sjWeiXinUser = (WeiXinUser)session.getAttribute("sjWeiXinUser");

            List<PtOpen> ptOp = ptOpenMapper.selectBtOpenId(weiXinUser.getOpenId());
            if (ptOp.size() != 0) {
                PtInfoQr pt = ptInfoMapper.selectByPtNo(ptOp.get(0).getPtNo());
                if (!"".equals(pt.getPhone()) && pt.getPhone() != null) {
                    String redirectUrl = platform.split(",")[1];
                    redirectUrl = "https://www.yizhenyun.com.cn/yzypt/wx/zzxd/index.html/#/"+redirectUrl+"?status="+1+"&platform="+platform.split(",")[0]+"&drugSetNo="+
                    platform.split(",")[2]+"&isOpenId=1&goodsNo="+platform.split(",")[3];
                    response.sendRedirect(redirectUrl);
                } else {
                    String redirectUrl = "https://www.yizhenyun.com.cn/yzypt/wx/register/index.html/#/register?"+"status="+1+"&platform="+platform.split(",")[0]+"&isOpenId=1&goodsNo="+platform.split(",")[3]+"&drugSetNo="+platform.split(",")[2]+"&redirectUrl="+platform.split(",")[1];
                    response.sendRedirect(redirectUrl);
                }
            } else {
                String redirectUrl = "https://www.yizhenyun.com.cn/yzypt/wx/register/index.html/#/register?"+"status="+1+"&platform="+platform.split(",")[0]+"&isOpenId=1&goodsNo="+platform.split(",")[3]+"&drugSetNo="+platform.split(",")[2]+"&redirectUrl="+platform.split(",")[1];
                response.sendRedirect(redirectUrl);
            }

            //selectByOpenId(Integer.parseInt(status.split(",")[0]), Integer.parseInt(status.split(",")[1]),status.split(",")[2]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取用户的openId
     *
     * @param session
     * @param code
     * @return 返回封装的微信用户的对象
     */
    private WeiXinUser getTheCode(HttpSession session, String code) {
        Map<String, String> authInfo = new HashMap<>();
        String openId = "";
        if (code != null) {
            // 调用根据用户的code得到需要的授权信息
            authInfo = getAuthInfo(code);
            //获取到openId
            openId = authInfo.get("Openid");
            System.out.println("wxcontroller:openId:" + openId);
        }
        // 获取基础刷新的接口访问凭证（目前还没明白为什么用authInfo.get("AccessToken");这里面的access_token就不行）
        String accessToken = WeiXinUtils.getAccessToken().getToken();
        //获取到微信用户的信息
        WeiXinUser userinfo = getUserInfo(accessToken, openId);

        return userinfo;
    }


    private WeiXinUser getTheCode1(HttpSession session, String code) {
        Map<String, String> authInfo = new HashMap<>();
        String openId = "";
        if (code != null) {
            // 调用根据用户的code得到需要的授权信息
            authInfo = getAuthInfo1(code);
            //获取到openId
            openId = authInfo.get("Openid");
            System.out.println("wxcontroller:openId:" + openId);
        }
        // 获取基础刷新的接口访问凭证（目前还没明白为什么用authInfo.get("AccessToken");这里面的access_token就不行）
        String accessToken = WeiXinUtils.getSjAccessToken().getToken();
        //获取到微信用户的信息
        WeiXinUser userinfo = getUserInfo(accessToken, openId);

        return userinfo;
    }












    //platform 1:易臻云 2：视界
    public void selectByOpenId(int status, int platform, String redirectUrl) {
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getResponse();
            HttpSession session = request.getSession();
            WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
            String openId = user.getOpenId();
            //String openId = "oLkiUwbC5SFlF0oyN5dcs_-Ujz8s";

            redirectUrl = redirectUrl.replace("|","&");
            redirectUrl = redirectUrl+"&status="+status+"&platform="+platform;

            response.sendRedirect(redirectUrl);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    /**
     * 获取微信用户的信息
     *
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
                    System.out.println("由于" + errorCode + "错误码；错误信息为：" + errorMsg + "；导致获取用户信息失败");
                }
            }
        }
        return weixinUserInfo;
    }

    /**
     * 进行用户授权，获取到需要的授权字段，比如openId
     *
     * @param code 识别得到用户id必须的一个值
     *             得到网页授权凭证和用户id
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
            System.out.println("AccessToken" + AccessToken);
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

    public Map<String, String> oauth2GetOpenid1(String code) {
        //自己的配置appid（公众号进行查阅）
        String appid = ProjectConst.PROJECT_APPID_SJ;
        //自己的配置APPSECRET;（公众号进行查阅）
        String appsecret = ProjectConst.PROJECT_APPSECRET_SJ;
        //拼接用户授权接口信息
        String requestUrl = ProjectConst.GET_WEBAUTH_URL.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", code);
        //存储获取到的授权字段信息
        Map<String, String> result = new HashMap<String, String>();
        try {
            JSONObject OpenidJSONO = WeiXinUtils.doGetStr(requestUrl);
            //OpenidJSONO可以得到的内容：access_token expires_in  refresh_token openid scope
            String Openid = String.valueOf(OpenidJSONO.get("openid"));
            String AccessToken = String.valueOf(OpenidJSONO.get("access_token"));
            System.out.println("AccessToken" + AccessToken);
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
     *
     * @param code 这是要获取OpendId的必须的一个参数
     * @return
     */
    public Map<String, String> getAuthInfo(String code) {
        //进行授权验证，获取到OpenID字段等信息
        Map<String, String> result = oauth2GetOpenid(code);
        // 从这里可以得到用户openid
        String openId = result.get("Openid");
        System.out.println("openId=" + openId);
        return result;
    }

    public Map<String, String> getAuthInfo1(String code) {
        //进行授权验证，获取到OpenID字段等信息
        Map<String, String> result = oauth2GetOpenid1(code);
        // 从这里可以得到用户openid
        String openId = result.get("Openid");
        System.out.println("openId=" + openId);
        return result;
    }
}
