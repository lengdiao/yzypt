package com.ecard.utils;

import com.ecard.ProjectConst;
import com.ecard.entity.AccessToken;
import com.ecard.entity.Template;
import com.ecard.entity.TemplateParam;
import com.ecard.entity.WxTemplate;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.MalformedURLException;
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



    /**
     * 审核通知
     */
    public static void remind(String openId, String name, Long medRecordNo, int shippingStatus) {
        try {
            AccessToken access_token = getAccessToken();
            URL tmpurl = new URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token.getToken());
            WxTemplate tp = new WxTemplate();
            tp.setTemplateId("qO3pqqFKUQIDd4MUzN8kg8D_fZ1WS6OaIDHwE4CrVSk");
            tp.setTopColor("#00DD00");
            tp.setToUser(openId);
            tp.setUrl("");
            List<TemplateParam> paras = new ArrayList<TemplateParam>();
            paras.add(new TemplateParam("first", "您好，您的处方有新动态！", "#173177"));
            paras.add(new TemplateParam("keyword1", name, "#173177"));
            paras.add(new TemplateParam("keyword2",medRecordNo.toString(), "#173177"));
            paras.add(new TemplateParam("keyword3", "审核通过","#173177"));
            if(shippingStatus==2||shippingStatus==3){
                paras.add(new TemplateParam("remark","", "#173177"));
            }

            tp.setTemplateParamList(paras);

            String response = PostUtil.sendPost(tmpurl, "application/x-www-form-urlencoded;charset=utf-8",
                    tp.toJSON());
        } catch (Exception e) {
            System.out.println("消息模板推送发送错误");
            e.printStackTrace();
        }
    }


    /**
     * 发货通知
     */
    public static void shippingRemind(String openId, String goodsName, int shippingStatus, String shippingCompany, String shippingNo) {
        try {
            AccessToken access_token = getAccessToken();
            URL tmpurl = new URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token.getToken());
            WxTemplate tp = new WxTemplate();
            tp.setTemplateId("lY37hiwo4LAYkB0QFwUF_Ky37fngpSrRkZNkn0gWPUo");
            tp.setTopColor("#00DD00");
            tp.setToUser(openId);
            tp.setUrl("");
            List<TemplateParam> paras = new ArrayList<TemplateParam>();
            paras.add(new TemplateParam("first", "处方审核通知", "#173177"));
            paras.add(new TemplateParam("keyword1", goodsName, "#173177"));
            if(shippingStatus==0){
                paras.add(new TemplateParam("keyword2","已发货", "#173177"));
            }
            paras.add(new TemplateParam("keyword3", shippingCompany,"#173177"));
            paras.add(new TemplateParam("keyword4", shippingNo,"#173177"));
            paras.add(new TemplateParam("remark","", "#173177"));


            tp.setTemplateParamList(paras);

            String response = PostUtil.sendPost(tmpurl, "application/x-www-form-urlencoded;charset=utf-8",
                    tp.toJSON());
        } catch (Exception e) {
            System.out.println("消息模板推送发送错误");
            e.printStackTrace();
        }
    }


    public  void newmsg(String openId,String msg,String drName) {
        AccessToken access_token = getAccessToken();
        System.out.println(access_token);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        URL tmpurl = null;
        try {
            tmpurl = new URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        WxTemplate tp = new WxTemplate();
        tp.setTemplateId("r75uxXH6RLNA11Kmhuap8b083iX_uqa59MpjfpGqk9M");
        tp.setTopColor("#00DD00");
        tp.setToUser(openId);
        tp.setUrl("");

        List<TemplateParam> paras=new ArrayList<TemplateParam>();
        paras.add(new TemplateParam("first","您好，您的反馈已经处理","#173177"));
        paras.add(new TemplateParam("keyword1",drName,"#173177"));
        paras.add(new TemplateParam("keyword2",new Date()+"","#173177"));
        paras.add(new TemplateParam("keyword3",msg,"#173177"));
        paras.add(new TemplateParam("remark","请及时登录公众号查看！","#173177"));
        tp.setTemplateParamList(paras);
        String response = null;
        try {
            response = PostUtil.sendPost(tmpurl,"application/x-www-form-urlencoded;charset=utf-8",tp.toJSON());
        } catch (IOException e1) {
            System.out.println("PostUtil报错");
        }
    }

}
