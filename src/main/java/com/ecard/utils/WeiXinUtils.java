package com.ecard.utils;

import com.ecard.ProjectConst;
import com.ecard.entity.AccessToken;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author scw
 * @create 2018-01-17 14:13
 * @desc 用户获取access_token,众号调用各接口时都需使用access_token
 **/
public class WeiXinUtils {

    /**
     * Get请求，方便到一个url接口来获取结果
     * @param url
     * @return
     */
    public static JSONObject doGetStr(String url){
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        try{
            HttpResponse response = defaultHttpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if(entity != null){
                String result = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.fromObject(result);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    
    

     /**
        * 获取access_token
     * @return
     */
    public static AccessToken getAccessToken(){
        AccessToken accessToken = new AccessToken();
        String url = ProjectConst.ACCESS_TOKEN_URL.replace("APPID" ,ProjectConst.PROJECT_APPID).replace("APPSECRET",ProjectConst.PROJECT_APPSECRET);
        System.out.println("url"+url);
        JSONObject jsonObject = doGetStr(url);
        if(jsonObject !=null){
            accessToken.setToken(jsonObject.getString("access_token"));
            accessToken.setExpireIn(jsonObject.getInt("expires_in"));
        }
        return accessToken;
    }
    /**
     * 获取Jsapi_ticket
     * @param access_token 
  * @return
  */
    public static String getJsapi_ticket(String access_token){
        String url = ProjectConst.jsapi_ticket_URL.replace("ACCESS_TOKEN" ,access_token);
        System.out.println("url"+url);
        JSONObject jsonObject = doGetStr(url);
        String jsapi_ticket=null;
        if(jsonObject !=null){
           jsapi_ticket = jsonObject.getString("ticket");
        }
        return jsapi_ticket;
    }

}
