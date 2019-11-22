package com.ecard.utils;

import com.ecard.pojo.ResponseHasData;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URL;

public class GetOpenIdUtils {
	//小程序
	@Autowired
	public static String appId = "wx7a632e0e822430ec";
	@Autowired
	public static String appSecret = "94894b46a63042a6618e06b91b112590";


	public static void findOpenId() {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
			try {									
				String	url = 
						"https://open.weixin.qq.com/connect/oauth2/authorize?redirect_uri=http://www.yizhenyun.com.cn/glzy/glzyWx/getOpenId?&appid="
								+ appId + "&response_type=code&scope=snsapi_base&state=STATE&connect_redirect=1#wechat_redirect";
				response.sendRedirect(url);
			} catch (IOException e) {
				System.out.println("报错");}
	}
	
	public static ResponseHasData getOpenid(String code) {
		ResponseHasData response = new ResponseHasData();
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			System.out.println("getOpenid进来了！！！！！！！！"+request);
			URL url = new URL("https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&" +
			        "secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code");
			String resultStr = PostUtil.sendGet(url);
			JSONObject jsonObject = null;
			try {
				jsonObject = new JSONObject(resultStr);
			} catch (JSONException e) {
					System.out.println("jsonObject报错");
			}		 
			String access_token = jsonObject.optString("access_token");
			String expires_in = jsonObject.optString("expires_in");
			String refresh_token = jsonObject.optString("refresh_token");
			String openid = jsonObject.optString("openid");
			String scope = jsonObject.optString("scope");
			HttpSession session = request.getSession();
			session.setAttribute("openId", openid);
			System.out.println("openId:"+session.getAttribute("openId"));
			System.out.println(session.getId());
			System.out.println("得到的openid为:"+openid);
			response.setMsg("获取openId成功");
			response.setStatus(0);
		} catch (IOException e) {
			response.setMsg("获取openId成功");
			response.setStatus(0);
		}
		return response;

	}
	
	
	public static String getOpenId(String code) {
		ResponseHasData response = new ResponseHasData();
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			System.out.println("getOpenid进来了！！！！！！！！"+request);
			URL url = new URL("https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&" +
			        "secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code");
			String resultStr = PostUtil.sendGet(url);
			JSONObject jsonObject = null;
			try {
				jsonObject = new JSONObject(resultStr);
			} catch (JSONException e) {
					System.out.println("jsonObject报错");
			}		 
			String access_token = jsonObject.optString("access_token");
			String expires_in = jsonObject.optString("expires_in");
			String refresh_token = jsonObject.optString("refresh_token");
			String openid = jsonObject.optString("openid");
			String scope = jsonObject.optString("scope");
			HttpSession session = request.getSession();
			session.setAttribute("openId", openid);
			System.out.println("openId:"+session.getAttribute("openId"));
			System.out.println(session.getId());
			System.out.println("得到的openid为:"+openid);
			return openid;
		} catch (IOException e) {
			e.printStackTrace();
			return "空";
		}
		
		

	}
}
