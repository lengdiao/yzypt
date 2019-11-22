package com.ecard.controller;

import com.ecard.entity.WeiXinUser;
import com.ecard.pojo.Response;
import com.ecard.service.YzyptService;
import com.ecard.utils.WeiXinUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/yzypt")
public class YzyptController {
    @Autowired
    private YzyptService yzyptService;

    /**
     * 条件查询所有订单
     *
     * @return
     */
    @PostMapping("/selectMallOrder")
    @ApiOperation(value = "条件查询所有订单", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "mallNo", value = "订单编号", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "orderStatus", value = "处方单状态", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "ptNo", value = "患者编号", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "drNo", value = "医生编号", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "rows", value = "显示条数", dataType = "Integer")
    })
    public Response selectMallOrder(
            @RequestParam(value = "mallNo", required = false) Integer mallNo,
            @RequestParam(value = "orderStatus", required = false) Integer orderStatus,
            @RequestParam(value = "ptNo", required = false) Integer ptNo,
            @RequestParam(value = "drNo", required = false) Integer drNo,
            @RequestParam(value="page", required=false, defaultValue="1") Integer page,
            @RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
        Response response = yzyptService.selectMallOrder(mallNo, orderStatus,ptNo,drNo,page,rows);
        return response;
    }


    /**
     * 修改密码
     * @return
     */
    @ApiOperation(value="修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "phone", value = "手机号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "oldPassword", value = "旧密码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "newPassword", value = "新密码", dataType = "String")
    })
    @PostMapping("/updateByPhoneAndPass")
    public Response updateByPhoneAndPass(
            @RequestParam(value ="phone") String phone,
            @RequestParam(value ="oldPassword") String oldPassword,
            @RequestParam(value ="newPassword") String newPassword) {
        Response response = yzyptService.updateByPhoneAndPass(phone,oldPassword,newPassword);
        return response;
    }


    /**
     * 发送手机验证码
     *
     * @param phone
     * @return
     */
    @ApiOperation(value="发送手机验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "phone", value = "手机号码", dataType = "String")
    })
    @PostMapping(value = "/sednSms")
    public Response sendCodeSms(@RequestParam("phone") String phone) {
        Response response = yzyptService.sendCodeSms(phone);
        return response;
    }


    /**
     * 用户未登录，忘记密码 ---填写新密码
     * @param code  手机验证码
     * @param newCode
     * @return
     */
    @ApiOperation(value="用户未登录，忘记密码 ---填写新密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "phone", value = "手机号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "code", value = "验证码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "newCode", value = "新密码", dataType = "String")
    })
    @PostMapping("/updateByCode")
    public Response updateByCode(@RequestParam(value="phone",required=false) String phone,
                                 @RequestParam(value="code",required=false) String code,
                                 @RequestParam(value="newCode",required=false) String newCode) {
        Response response = yzyptService.updateByCode(phone,code,newCode);
        return response;
    }


    /**
     * 进行网页授权，便于获取到用户的绑定的内容
     * @param request
     * @param session
     * @param map
     * @return
     */
    @RequestMapping("/tologin/userinfo")
    public void check(@RequestParam(value="status") int status) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        HttpSession session = request.getSession();
        //首先判断一下session中，是否有保存着的当前用户的信息，有的话，就不需要进行重复请求信息
        WeiXinUser weiXinUser = null ;
        if(session.getAttribute("currentUser") != null){
            weiXinUser = (WeiXinUser) session.getAttribute("currentUser");
            System.out.println("session中存在openId");
            yzyptService.selectByOpenId(status);
        }else {
            /**
             * 进行获取openId，必须的一个参数，这个是当进行了授权页面的时候，再重定向了我们自己的一个页面的时候，
             * 会在request页面中，新增这个字段信息，要结合这个ProjectConst.Get_WEIXINPAGE_Code这个常量思考
             */
            String code = request.getParameter("code");
            System.out.println("code"+code);
            try {
                //得到当前用户的信息(具体信息就看weixinUser这个javabean)
                weiXinUser = getTheCode(session, code);
                //将获取到的用户信息，放入到session中
                session.setAttribute("currentUser", weiXinUser);

                yzyptService.selectByOpenId(status);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // map.put("weiXinUser", weiXinUser);
    }

    /**
     * 获取用户的openId
     * @param session
     * @param code
     * @return 返回封装的微信用户的对象
     */
    private WeiXinUser getTheCode(HttpSession session, String code) {
        Map<String , String> authInfo = new HashMap<>();
        String openId = "";
        if (code != null)
        {
            // 调用根据用户的code得到需要的授权信息
            authInfo= yzyptService.getAuthInfo(code);
            //获取到openId
            openId = authInfo.get("Openid");
            System.out.println("wxcontroller:openId:"+openId);
        }
        // 获取基础刷新的接口访问凭证（目前还没明白为什么用authInfo.get("AccessToken");这里面的access_token就不行）
        String accessToken = WeiXinUtils.getAccessToken().getToken();
        //获取到微信用户的信息
        WeiXinUser userinfo = yzyptService.getUserInfo(accessToken ,openId);

        return userinfo;
    }


	
}
