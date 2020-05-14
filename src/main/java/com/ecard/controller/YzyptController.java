package com.ecard.controller;

import com.ecard.entity.DiseaseMaster;
import com.ecard.entity.WeiXinUser;
import com.ecard.pojo.Response;
import com.ecard.pojo.queryResult.DisInfoQr;
import com.ecard.service.YzyptService;
import com.ecard.utils.WeiXinUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/yzypt")
@Api(value = "后台Controller", tags = {"后台操作接口"})
@CrossOrigin
public class YzyptController {
    @Autowired
    private YzyptService yzyptService;

    @PostMapping("/login")
    @ApiOperation(value = "后台登陆", httpMethod = "POST")
    public Response login(@ApiParam(name = "phone", value = "手机号", required = true) @RequestParam("phone") String phone,
                          @ApiParam(name = "passWord", value = "密码", required = true) @RequestParam("passWord") String passWord) {
        Response response = yzyptService.login(phone, passWord);
        return response;
    }

    /**
     * 条件查询所有订单
     *
     * @return
     */
    @PostMapping("/selectMallOrder")
    @ApiOperation(value = "条件查询所有订单", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "mallNo", value = "订单编号", dataType = "Long"),
            @ApiImplicitParam(paramType = "query", name = "orderStatus", value = "处方单状态", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "ptNo", value = "患者编号", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "drNo", value = "医生编号", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "page", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "rows", value = "显示条数", dataType = "Integer")
    })
    public Response selectMallOrder(
            @RequestParam(value = "mallNo", required = false) Long mallNo,
            @RequestParam(value = "orderStatus", required = false) Integer orderStatus,
            @RequestParam(value = "ptNo", required = false) Integer ptNo,
            @RequestParam(value = "drNo", required = false) Integer drNo,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows) {
        Response response = yzyptService.selectMallOrder(mallNo, orderStatus, ptNo, drNo, page, rows);
        return response;
    }


    /**
     * 后台条件查询订单
     *
     * @return
     */
    @PostMapping("/findMallOrder")
    @ApiOperation(value = "后台条件查询订单", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "mallNo", value = "订单编号", dataType = "Long"),
            @ApiImplicitParam(paramType = "query", name = "drugStoreNo", value = "药店编号", dataType = "Long"),
            @ApiImplicitParam(paramType = "query", name = "orderStatus", value = "处方单状态", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "startDate", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "endDate", value = "结束时间", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "drNo", value = "医生编号", dataType = "Long"),
            @ApiImplicitParam(paramType = "query", name = "disNo1", value = "一级代表编号", dataType = "Long"),
            @ApiImplicitParam(paramType = "query", name = "disNo2", value = "二级代表编号", dataType = "Long"),
            @ApiImplicitParam(paramType = "query", name = "page", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "rows", value = "显示条数", dataType = "Integer")
    })
    public Response findMallOrder(
            @RequestParam(value = "mallNo", required = false) Long mallNo,
            @RequestParam(value = "drugStoreNo", required = false) Long drugStoreNo,
            @RequestParam(value = "orderStatus", required = false) Integer orderStatus,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "drNo", required = false) Long drNo,
            @RequestParam(value = "disNo1", required = false) Long disNo1,
            @RequestParam(value = "disNo2", required = false) Long disNo2,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows) {
        Response response = yzyptService.findMallOrder(mallNo, drugStoreNo, orderStatus, startDate, endDate, page, rows, drNo, disNo1, disNo2);
        return response;
    }


    /**
     * 数据导出
     *
     * @return
     */
    @PostMapping("/export")
    @ApiOperation(value = "后台条件查询订单", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "mallNo", value = "订单编号", dataType = "Long"),
            @ApiImplicitParam(paramType = "query", name = "orderStatus", value = "处方单状态", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "ptNo", value = "患者编号", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "drNo", value = "医生编号", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "startDate", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "endDate", value = "结束时间", dataType = "String")
    })
    public Response export(
            @RequestParam(value = "mallNo", required = false) Long mallNo,
            @RequestParam(value = "orderStatus", required = false) Integer orderStatus,
            @RequestParam(value = "ptNo", required = false) Integer ptNo,
            @RequestParam(value = "drNo", required = false) Integer drNo,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate) {
        Response response = yzyptService.export(mallNo, orderStatus, ptNo, drNo, startDate, endDate);
        return response;
    }


    /**
     * 修改密码
     *
     * @return
     */
    @ApiOperation(value = "修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "phone", value = "手机号码", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "oldPassword", value = "旧密码", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "newPassword", value = "新密码", dataType = "String")
    })
    @PostMapping("/updateByPhoneAndPass")
    public Response updateByPhoneAndPass(
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "oldPassword") String oldPassword,
            @RequestParam(value = "newPassword") String newPassword) {
        Response response = yzyptService.updateByPhoneAndPass(phone, oldPassword, newPassword);
        return response;
    }


    /**
     * 发送手机验证码
     *
     * @param phone
     * @return
     */
    @ApiOperation(value = "发送手机验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "phone", value = "手机号码", dataType = "String")
    })
    @PostMapping(value = "/sednSms")
    public Response sendCodeSms(@RequestParam("phone") String phone) {
        Response response = yzyptService.sendCodeSms(phone);
        return response;
    }


    /**
     * 用户未登录，忘记密码 ---填写新密码
     *
     * @param code    手机验证码
     * @param newCode
     * @return
     */
    @ApiOperation(value = "用户未登录，忘记密码 ---填写新密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "phone", value = "手机号码", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "code", value = "验证码", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "newCode", value = "新密码", dataType = "String")
    })
    @PostMapping("/updateByCode")
    public Response updateByCode(@RequestParam(value = "phone", required = false) String phone,
                                 @RequestParam(value = "code", required = false) String code,
                                 @RequestParam(value = "newCode", required = false) String newCode) {
        Response response = yzyptService.updateByCode(phone, code, newCode);
        return response;
    }


    //访问微信接口跳转  platform=1易臻云 2视界
    @RequestMapping(value = "/getRedirect",method = RequestMethod.GET)
    public void getRedirect(@RequestParam(value = "status", required = false) String status,
                            @RequestParam(value = "platform", required = false) int platform) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        try {
            String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                    "redirect_uri=https://www.yizhenyun.com.cn/yzypt/yzypt/tologin/userinfo?status="
                    + status + "," + platform + "&appid=" + "wx4224aa99aacaba5b"
                    + "&response_type=code&scope=snsapi_base&state=STATE&connect_redirect=1#wechat_redirect";
            System.out.println(url);
            response.sendRedirect(url);
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
    public void check(@RequestParam(value = "status") String status) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        HttpSession session = request.getSession();
        //首先判断一下session中，是否有保存着的当前用户的信息，有的话，就不需要进行重复请求信息
        WeiXinUser weiXinUser = null;
        WeiXinUser sjWeiXinUser = null;
        if (session.getAttribute("currentUser") != null) {
            weiXinUser = (WeiXinUser) session.getAttribute("currentUser");
            System.out.println("session中存在openId");
            yzyptService.selectByOpenId(Integer.parseInt(status.split(",")[0]), Integer.parseInt(status.split(",")[1]));
        } else {
            /**
             * 进行获取openId，必须的一个参数，这个是当进行了授权页面的时候，再重定向了我们自己的一个页面的时候，
             * 会在request页面中，新增这个字段信息，要结合这个ProjectConst.Get_WEIXINPAGE_Code这个常量思考
             */
            String code = request.getParameter("code");
            System.out.println("code" + code);
            try {
                sjWeiXinUser = getTheCode1(session,code);

                session.setAttribute("sjCurrentUser",sjWeiXinUser);

                String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                        "redirect_uri=https://www.yizhenyun.com.cn/yzypt/yzypt/tologin/userinfo1?status="
                        + status +"&appid=" + "wx5a917cabd0432687"
                        + "&response_type=code&scope=snsapi_base&state=STATE&connect_redirect=1#wechat_redirect";
                System.out.println(url);
                response.sendRedirect(url);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // map.put("weiXinUser", weiXinUser);
    }


    @RequestMapping("/tologin/userinfo1")
    public void check1(@RequestParam(value = "status") String status) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        HttpSession session = request.getSession();
        //首先判断一下session中，是否有保存着的当前用户的信息，有的话，就不需要进行重复请求信息
        WeiXinUser weiXinUser = null;
        WeiXinUser sjWeiXinUser = null;
        if (session.getAttribute("currentUser") != null) {
            weiXinUser = (WeiXinUser) session.getAttribute("currentUser");
            System.out.println("session中存在openId");
            yzyptService.selectByOpenId(Integer.parseInt(status.split(",")[0]), Integer.parseInt(status.split(",")[1]));
        } else {
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

                yzyptService.selectByOpenId(Integer.parseInt(status.split(",")[0]), Integer.parseInt(status.split(",")[1]));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // map.put("weiXinUser", weiXinUser);
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
            authInfo = yzyptService.getAuthInfo(code);
            //获取到openId
            openId = authInfo.get("Openid");
            System.out.println("wxcontroller:openId:" + openId);
        }
        // 获取基础刷新的接口访问凭证（目前还没明白为什么用authInfo.get("AccessToken");这里面的access_token就不行）
        String accessToken = WeiXinUtils.getAccessToken().getToken();
        //获取到微信用户的信息
        WeiXinUser userinfo = yzyptService.getUserInfo(accessToken, openId);

        return userinfo;
    }

    private WeiXinUser getTheCode1(HttpSession session, String code) {
        Map<String, String> authInfo = new HashMap<>();
        String openId = "";
        if (code != null) {
            // 调用根据用户的code得到需要的授权信息
            authInfo = yzyptService.getAuthInfo1(code);
            //获取到openId
            openId = authInfo.get("Openid");
            System.out.println("wxcontroller:openId:" + openId);
        }
        // 获取基础刷新的接口访问凭证（目前还没明白为什么用authInfo.get("AccessToken");这里面的access_token就不行）
        String accessToken = WeiXinUtils.getSjAccessToken().getToken();
        //获取到微信用户的信息
        WeiXinUser userinfo = yzyptService.getUserInfo(accessToken, openId);

        return userinfo;
    }



    @GetMapping("/getSign")
    public Response getSign(@RequestParam(value = "url",required = false) String url) {
        Response response = yzyptService.getSign(url);
        return response;
    }


    /**
     * 增加疾病
     */
    @PostMapping("/insertDiseaseMaster")
    @ApiOperation(value = "增加疾病", httpMethod = "POST")
    public Response insertDiseaseMaster(@RequestBody DiseaseMaster DiseaseMaster) {
        Response response = yzyptService.insertDiseaseMaster(DiseaseMaster);
        return response;
    }

    /**
     * 修改疾病
     */
    @PostMapping("/updateDiseaseMaster")
    @ApiOperation(value = "修改疾病", httpMethod = "POST")
    public Response updateDiseaseMaster(@RequestBody DiseaseMaster DiseaseMaster) {
        Response response = yzyptService.updateDiseaseMaster(DiseaseMaster);
        return response;
    }


    /**
     * 新增经销商
     *
     * @param disInfoQr
     * @return
     */
    @PostMapping("/saveDis")
    @ApiOperation(value = "新增代表", httpMethod = "POST")
    public Response saveDis(@RequestBody DisInfoQr disInfoQr) {
        Response response = yzyptService.saveDis(disInfoQr);
        return response;
    }

    /**
     * 查询代表详情
     *
     * @param disno
     * @return
     */
    @GetMapping("/selectByDisNo")
    @ApiOperation(value = "查询代表详情", httpMethod = "GET")
    public Response selectByDisNo(
            @ApiParam(name = "disNo", value = "代表编号", required = true) @RequestParam("disNo") Long disno) {
        Response response = yzyptService.selectByDisNo(disno);
        return response;
    }


    @GetMapping("/test")
    public void test() {
        yzyptService.test();
    }


    /**
     * 修改代表信息
     *
     * @param disInfoQr
     * @return
     */
    @PostMapping("/updateDis")
    @ApiOperation(value = "修改代表信息", httpMethod = "POST")
    public Response updateDis(@RequestBody DisInfoQr disInfoQr) {
        Response response = yzyptService.updateDis(disInfoQr);
        return response;
    }


    /**
     * 新增经销商时，等级下拉框
     */
    @GetMapping("/getDisLeader")
    @ApiOperation(value = "根据代表等级查询上级代表", httpMethod = "GET")
    public Response getDisLeader(
            @ApiParam(name = "disLevel", value = "代表等级", required = true) @RequestParam(value = "disLevel", required = true) String disLevel) {
        Response response = yzyptService.getDisLeader(disLevel);
        return response;
    }

    /**
     * 一级代表查询下属代表
     */
    @GetMapping("/getDisList")
    @ApiOperation(value = "一级代表查询下属代表", httpMethod = "GET")
    public Response getDisList(
            @ApiParam(name = "disNo", value = "一级代表编号", required = true)
            @RequestParam(value = "disNo", required = true) String disNo) {
        Response response = yzyptService.getDisList(disNo);
        return response;
    }

    /**
     * 代表查询绑定医生
     *
     * @param disNo
     * @return
     */
    @ApiOperation(value = "代表查询绑定医生", httpMethod = "GET", notes = "name可为姓名，可为手机号，后台自行判断")
    @GetMapping("/selectMyDr")
    public Response selectMyDr(
            @ApiParam(name = "disNo", value = "代表编号", required = true) @RequestParam(value = "disNo", required = true) String disNo,
            @ApiParam(name = "name", value = "名字或者手机号", required = false) @RequestParam(value = "name", required = false) String name, @ApiParam(name = "page", value = "页", required = false) @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @ApiParam(name = "rows", value = "行", required = false) @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows) {
        Response response = yzyptService.selectMyDr(disNo, name, page, rows);
        return response;
    }


    /**
     * 代表新增医生
     *
     * @param disNo
     * @param name
     * @param divNo
     * @param hospital
     * @param phone
     * @param chiefNo
     * @param practiceProfile
     * @param title
     * @param drTitleCert
     * @param drPracticeRegCert
     * @param consultingHour
     * @return
     */
    @PostMapping("/insertDr")
    @ApiOperation(value = "新增医生", httpMethod = "POST")
    public Response insertDr(
            @ApiParam(name = "disNo", value = "代表编号", required = false)
            @RequestParam(value = "disNo", required = false) String disNo,
            @ApiParam(name = "name", value = "医生姓名", required = true)
            @RequestParam(value = "name", required = true) String name,
            @ApiParam(name = "hospital", value = "所属医院", required = false)
            @RequestParam(value = "hospital", required = false) String hospital,
            @ApiParam(name = "phone", value = "联系方式", required = false)
            @RequestParam(value = "phone", required = false) String phone,
            @ApiParam(name = "chiefNo", value = "所属科室", required = true)
            @RequestParam(value = "chiefNo", required = true) String chiefNo,
            @ApiParam(name = "practiceProfile", value = "个人简介", required = false)
            @RequestParam(value = "practiceProfile", required = false) String practiceProfile,
            @ApiParam(name = "title", value = "职称", required = false)
            @RequestParam(value = "title", required = false) String title,
            @ApiParam(name = "leaderDrNo", value = "上级医生云账号编号", required = false)
            @RequestParam(value = "leaderDrNo", required = false) String leaderDrNo,
            @ApiParam(name = "drTitleCert", value = "医生资格证编号", required = false)
            @RequestParam(value = "drTitleCert", required = false) String drTitleCert,
            @ApiParam(name = "drPracticeRegCert", value = "医生执业证编号", required = false)
            @RequestParam(value = "drPracticeRegCert", required = false) String drPracticeRegCert,
            @ApiParam(name = "consultingHour", value = "值班时间", required = false)
            @RequestParam(value = "consultingHour", required = false) String consultingHour,
            @ApiParam(name = "idNo", value = "身份证", required = false)
            @RequestParam(value = "idNo", required = false) String idNo,
            @ApiParam(name = "disableFlag", value = "值班时间", required = false)
            @RequestParam(value = "disableFlag", required = false) int disableFlag,
            @ApiParam(name = "type", value = "1:中医2:西医")
            @RequestParam(value = "type") int type,
            @ApiParam(name = "province", value = "省名", required = false)
            @RequestParam(value = "province", required = false) String province,
            @ApiParam(name = "city", value = "市名", required = false)
            @RequestParam(value = "city", required = false) String city,
            @ApiParam(name = "platform", value = "平台标识", required = false)
            @RequestParam(value = "platform", required = false) int platform) {
        Response response = yzyptService.insertDr(disNo, name, hospital, phone, chiefNo, practiceProfile, title,
                leaderDrNo, drTitleCert, drPracticeRegCert, consultingHour, idNo, disableFlag, type, province, city,platform);
        return response;
    }


    /**
     * 代表修改医生
     *
     * @param drNo
     * @param name
     * @param divNo
     * @param hospital
     * @param phone
     * @param chiefNo
     * @param practiceProfile
     * @param title
     * @param drTitleCert
     * @param drPracticeRegCert
     * @param consultingHour
     * @return
     */
    @PostMapping("/updateDr")
    @ApiOperation(value = "修改医生", httpMethod = "POST")
    public Response updateDr(
            @ApiParam(name = "drNo", value = "医生编号", required = true)
            @RequestParam(value = "drNo", required = true) String drNo,
            @ApiParam(name = "name", value = "医生姓名", required = true)
            @RequestParam(value = "name", required = true) String name,
            @ApiParam(name = "hospital", value = "所属医院", required = false)
            @RequestParam(value = "hospital", required = false) String hospital,
            @ApiParam(name = "phone", value = "联系方式", required = true)
            @RequestParam(value = "phone", required = true) String phone,
            @ApiParam(name = "chiefNo", value = "所属科室", required = false)
            @RequestParam(value = "chiefNo", required = false) String chiefNo,
            @ApiParam(name = "practiceProfile", value = "个人简介", required = false)
            @RequestParam(value = "practiceProfile", required = false) String practiceProfile,
            @ApiParam(name = "title", value = "职称", required = false)
            @RequestParam(value = "title", required = false) String title,
            @ApiParam(name = "leaderDrNo", value = "上级医生云账号编号", required = false)
            @RequestParam(value = "leaderDrNo", required = false) String leaderDrNo,
            @ApiParam(name = "drTitleCert", value = "医生资格证编号", required = false)
            @RequestParam(value = "drTitleCert", required = false) String drTitleCert,
            @ApiParam(name = "drPracticeRegCert", value = "医生执业证编号", required = false)
            @RequestParam(value = "drPracticeRegCert", required = false) String drPracticeRegCert,
            @ApiParam(name = "consultingHour", value = "值班时间", required = false)
            @RequestParam(value = "consultingHour", required = false) String consultingHour,
            @ApiParam(name = "idNo", value = "身份证", required = false)
            @RequestParam(value = "idNo", required = false) String idNo,
            @ApiParam(name = "disableFlag", value = "值班时间", required = false)
            @RequestParam(value = "disableFlag", required = false) int disableFlag,
            @ApiParam(name = "type", value = "1:中医2:西医")
            @RequestParam(value = "type") int type,
            @ApiParam(name = "province", value = "省名", required = false)
            @RequestParam(value = "province", required = false) String province,
            @ApiParam(name = "city", value = "市名", required = false)
            @RequestParam(value = "city", required = false) String city,
            @ApiParam(name = "platform", value = "平台标识", required = false)
            @RequestParam(value = "platform", required = false) int platform) {
        Response response = yzyptService.updateDr(drNo, name, hospital, phone, chiefNo, practiceProfile, title,
                drTitleCert, drPracticeRegCert, consultingHour, leaderDrNo, idNo, disableFlag, type, province, city,platform);
        return response;
    }


    /**
     * 代表列表
     */
    @GetMapping("/disList")
    @ApiOperation(value = "查询代表列表", httpMethod = "GET")
    public Response disList(
            @ApiParam(name = "disNo", value = "当前登录的代表编号", required = false) @RequestParam(value = "disNo", required = false) Long disno,
            @ApiParam(name = "IdNo", value = "证件号", required = false) @RequestParam(value = "IdNo", required = false) String IdNo,
            @ApiParam(name = "name", value = "被查代表姓名", required = false) @RequestParam(value = "name", required = false) String name,
            @ApiParam(name = "disLevel", value = "代表等级 1，首席 2.执行", required = false) @RequestParam(value = "disLevel", required = false) String disLevel,
            @ApiParam(name = "disLeader", value = "上级代表云通行证账号", required = false) @RequestParam(value = "disLeader", required = false) Long disLeader,
            @ApiParam(name = "sendDate", value = "录入起始时间", required = false) @RequestParam(value = "sendDate", required = false) String sendDate,
            @ApiParam(name = "endDate", value = "录入截至时间", required = false) @RequestParam(value = "endDate", required = false) String endDate,
            @ApiParam(name = "disableFlag", value = "启停标识 启0停1", required = false) @RequestParam(value = "disableFlag", required = false) Integer disableFlag,
            @ApiParam(name = "page", value = "页", required = false) @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @ApiParam(name = "rows", value = "行", required = false) @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows) {
        Response response = yzyptService.disList(disno, IdNo, name, disLevel, disLeader, sendDate, endDate,
                disableFlag, page, rows);
        return response;

    }


    @PostMapping("/count")
    @ApiOperation(value = "统计", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "medOrderType", value = "1中药2西药", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "drName", value = "医生名字", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "drPhone", value = "医生手机号", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "drugNo", value = "药品编号", dataType = "Long"),
            @ApiImplicitParam(paramType = "query", name = "disNo1", value = "一级代表编号", dataType = "Long"),
            @ApiImplicitParam(paramType = "query", name = "disNo2", value = "二级代表编号", dataType = "Long"),
            @ApiImplicitParam(paramType = "query", name = "startDate", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "endDate", value = "结束时间", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "drNo", value = "医生编号", dataType = "Long"),
            @ApiImplicitParam(paramType = "query", name = "page", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "rows", value = "显示条数", dataType = "Integer")
    })
    public Response count(
            @RequestParam(value = "medOrderType") int medOrderType,
            @RequestParam(value = "drName", required = false) String drName,
            @RequestParam(value = "drPhone", required = false) String drPhone,
            @RequestParam(value = "drugNo", required = false) Long drugNo,
            @RequestParam(value = "disNo1", required = false) Long disNo1,
            @RequestParam(value = "disNo2", required = false) Long disNo2,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "drNo", required = false) Long drNo,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows
    ) {
        System.out.println(drNo);
        Response response = yzyptService.count(medOrderType, drName, drugNo, drPhone, disNo1, disNo2, startDate, endDate, drNo, page, rows);
        return response;
    }





}
