package com.ecard.controller;

import com.ecard.entity.Articles;
import com.ecard.entity.Image;
import com.ecard.entity.ImageMessage;
import com.ecard.entity.News;
import com.ecard.pojo.Response;
import com.ecard.service.PtInfoService;
import com.ecard.utils.AppUtil;
import com.thoughtworks.xstream.XStream;
import io.swagger.annotations.ApiOperation;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/yzyptbr")
@CrossOrigin
public class WeiXinFirmController {

    @Autowired
    private PtInfoService ptInfoService;

    //json:{"media_id":"5fG3ww6_uL89ynLE5x4mHdmpvIbcm6HvVci0RjEv5Nk","url":"http://mmbiz.qpic.cn/sz_mmbiz_jpg/NSWia7o9Kx5DkPLw4zW7DKaksRSIQ0DwzpX0DAgbbNiaSVMG63oVxuia53rtL3MUHkQUeuia1sX024u0icdm8HmExtg/0?wx_fmt=jpeg","item":[]}
    private String media_id = "5fG3ww6_uL89ynLE5x4mHdmpvIbcm6HvVci0RjEv5Nk";


    @PostMapping(value = "/wxFirm")
    @ApiOperation(value = "与前端无关", httpMethod = "POST")
    public String wxFirm(@RequestParam(value = "signature", required = false) String signature,
                         @RequestParam(value = "timestamp", required = false) String timestamp,
                         @RequestParam(value = "nonce", required = false) String nonce,
                         @RequestParam(value = "echostr", required = false) String echostr) {
        /*String str= null;
        try {
            str = wxFirm1(signature, timestamp, nonce, echostr);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;*/
        //保留
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();

            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletResponse response = requestAttributes.getResponse();

            response.setContentType("application/xml;charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");

            BufferedReader bufferReader = request.getReader();
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = bufferReader.readLine()) != null) {
                buffer.append(line);
            }
            String postData = buffer.toString();
            System.out.println("微信推送的消息或者事件：" + postData);
            Document doc = DocumentHelper.parseText(postData);
            Element rootRes = doc.getRootElement();
            String ToUserName = rootRes.element("ToUserName") == null ? ""
                    : AppUtil.getStr(rootRes.element("ToUserName").getData());
            String FromUserName = rootRes.element("FromUserName") == null ? ""
                    : AppUtil.getStr(rootRes.element("FromUserName").getData());
            String CreateTime = rootRes.element("CreateTime") == null ? ""
                    : AppUtil.getStr(rootRes.element("CreateTime").getData());
            String MsgType = rootRes.element("MsgType") == null ? ""
                    : AppUtil.getStr(rootRes.element("MsgType").getData());
            String Event = rootRes.element("Event") == null ? "" : AppUtil.getStr(rootRes.element("Event").getData());
            String EventKey = rootRes.element("EventKey") == null ? ""
                    : AppUtil.getStr(rootRes.element("EventKey").getData());
            String Content = rootRes.element("Content") == null ? ""
                    : AppUtil.getStr(rootRes.element("Content").getData());
            System.out.println("ToUserName:" + ToUserName);
            System.out.println("FromUserName:" + FromUserName);
            System.out.println("CreateTime:" + CreateTime);
            System.out.println("MsgType:" + MsgType);
            System.out.println("Event:" + Event);
            System.out.println("EventKey:" + EventKey);
            System.out.println("Content:" + Content);
            System.out.println("留言消息外");

			 /*if("text".equals(MsgType)) {
				  System.out.println("留言信息内");
			  OpenIdUtil opUtil=new OpenIdUtil();
			  AppAndCss[] AS=appAndCssMapper.selectByAppNameAndType("秒凝",1);
			  for(AppAndCss As:AS) {
			  opUtil.getOpenidsmss(As, CreateTime, Content);
			  }
			  }
			 */
            /*Element root = DocumentHelper.createElement("xml");
            Document document = DocumentHelper.createDocument(root);
            root.addElement("ToUserName").setText(FromUserName);
            root.addElement("FromUserName").setText(ToUserName);
            root.addElement("CreateTime").setText(Long.toString(System.currentTimeMillis() / 1000));
            root.addElement("MsgType").setText("text");
            String content = null;
            //用户未关注
            if ("event".equals(MsgType)) {
                if ("subscribe".equals(Event)) {
                    if (EventKey!=null&&!"".equals(EventKey)) {
                        String[] EventKeyStrs = EventKey.split("_");
                        Response response1 = ptInfoService.binging(EventKeyStrs[1], FromUserName);
                        System.out.println("subscribe+EventKey" + EventKey);
                        if (response1.getStatus() == 0) {
                            content = "感谢您的关注";// 绑定成功
                        } else {
                            content = "感谢您的关注";// 绑定失败
                        }
                    } else {
                        content = "感谢您的关注，请先注册后进行问诊";// 未走绑定
                        System.out.println("走else");
                    }
                    root.addElement("Content").setText(content);
                    AppUtil.outXml(document.asXML().toString());
                }
            }
            //用户已关注
            if ("event".equals(MsgType)) {
                if (("SCAN").equals(Event)) {
                    if (EventKey!=null&&!"".equals(EventKey)) {
                        Response response1 = ptInfoService.binging(EventKey, FromUserName);
                        System.out.println("subscribe+EventKey" + EventKey);
                        if (response1.getStatus() == 0) {
                            content = "感谢您的关注";
                        } else {
                            content = response1.getMsg();
                        }
                    } else {
                        content = "您好！欢迎关注公众号";
                        System.out.println("走else");
                    }
                    root.addElement("Content").setText(content);
                    AppUtil.outXml(document.asXML().toString());

                }
            }*/
            //推送图片
            /*PrintWriter out = response.getWriter();
            String imageMessage = initImageMessage(media_id, ToUserName, FromUserName);
            System.out.println(imageMessage);
            out.print(imageMessage);*/


            //推送图文

            //保留
            News newmsg = new News();
            Articles article = new Articles();

            //用户未关注
            /*if ("event".equals(MsgType)) {
                if ("subscribe".equals(Event)) {
                    if (EventKey!=null&&!"".equals(EventKey)) {
                        if (EventKey!=null&&!"".equals(EventKey)) {
                            String[] EventKeyStrs = EventKey.split("_");
                            Response response1 = ptInfoService.binging(EventKeyStrs[1], FromUserName);
                            System.out.println("subscribe+EventKey" + EventKey);
                            newmsg.setToUserName(FromUserName);
                            newmsg.setFromUserName(ToUserName);
                            newmsg.setCreateTime(new Date().getTime());
                            newmsg.setMsgType("news");
                            article.setDescription("感谢您的关注"); //图文消息的描述
                            article.setPicUrl("https://www.yizhenyun.com.cn/yzypt/news/news.jpg"); //图文消息图片地址
                            article.setTitle("易臻云");  //图文消息标题
                            article.setUrl("https://www.yizhenyun.com.cn/yzypt/news/news.html");  //图文url链接
                            List<Articles> list=new ArrayList<Articles>();
                            list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
                            newmsg.setArticleCount(list.size()+"");
                            newmsg.setArticles(list);
                        }

                    } else {
                        // 未走绑定
                        newmsg.setToUserName(FromUserName);
                        newmsg.setFromUserName(ToUserName);
                        newmsg.setCreateTime(new Date().getTime());
                        newmsg.setMsgType("news");
                        article.setDescription("感谢您的关注，请先注册后进行问诊"); //图文消息的描述
                        article.setPicUrl("https://www.yizhenyun.com.cn/yzypt/news/news.jpg"); //图文消息图片地址
                        article.setTitle("易臻云");  //图文消息标题
                        article.setUrl("https://www.yizhenyun.com.cn/yzypt/news/news.html");  //图文url链接
                        List<Articles> list=new ArrayList<Articles>();
                        list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
                        newmsg.setArticleCount(list.size()+"");
                        newmsg.setArticles(list);
                    }
                    XStream xstream = new XStream();//xstream.jar,xmlpull.jar
                    xstream.alias("xml", newmsg.getClass());//置换根节点
                    xstream.alias("item", article.getClass());
                    xstream.toXML(newmsg);

                    PrintWriter out = response.getWriter();
                    String imageMessage = xstream.toXML(newmsg);
                    System.out.println(imageMessage);
                    out.print(imageMessage);

                }
            }
            //用户已关注
            if ("event".equals(MsgType)) {
                if (("SCAN").equals(Event)) {
                    if (EventKey!=null&&!"".equals(EventKey)) {
                        newmsg.setToUserName(FromUserName);
                        newmsg.setFromUserName(ToUserName);
                        newmsg.setCreateTime(new Date().getTime());
                        newmsg.setMsgType("news");
                        article.setDescription("感谢您的关注"); //图文消息的描述
                        article.setPicUrl("https://www.yizhenyun.com.cn/yzypt/news/news.jpg"); //图文消息图片地址
                        article.setTitle("易臻云");  //图文消息标题
                        article.setUrl("https://www.yizhenyun.com.cn/yzypt/news/news.html");  //图文url链接
                        List<Articles> list=new ArrayList<Articles>();
                        list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
                        newmsg.setArticleCount(list.size()+"");
                        newmsg.setArticles(list);
                    } else {
                        newmsg.setToUserName(FromUserName);
                        newmsg.setFromUserName(ToUserName);
                        newmsg.setCreateTime(new Date().getTime());
                        newmsg.setMsgType("news");
                        article.setDescription("您好！欢迎关注公众号"); //图文消息的描述
                        article.setPicUrl("https://www.yizhenyun.com.cn/yzypt/news/news.jpg"); //图文消息图片地址
                        article.setTitle("易臻云");  //图文消息标题
                        article.setUrl("https://www.yizhenyun.com.cn/yzypt/news/news.html");  //图文url链接
                        List<Articles> list=new ArrayList<Articles>();
                        list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
                        newmsg.setArticleCount(list.size()+"");
                        newmsg.setArticles(list);
                    }
                    XStream xstream = new XStream();//xstream.jar,xmlpull.jar
                    xstream.alias("xml", newmsg.getClass());//置换根节点
                    xstream.alias("item", article.getClass());
                    xstream.toXML(newmsg);

                    PrintWriter out = response.getWriter();
                    String imageMessage = xstream.toXML(newmsg);
                    System.out.println(imageMessage);
                    out.print(imageMessage);


                }
            }*/

            //保留
            if ("event".equals(MsgType)) {
                if ("subscribe".equals(Event)) {
                    if (EventKey != null && !"".equals(EventKey)) {
                        System.out.println("EventKey:"+EventKey);
                        String[] EventKeyStrs = EventKey.split("_");
                        Response response1 = ptInfoService.binging(EventKeyStrs[1], FromUserName);
                        System.out.println("subscribe+EventKey" + EventKey);
                        newmsg.setToUserName(FromUserName);
                        newmsg.setFromUserName(ToUserName);
                        newmsg.setCreateTime(new Date().getTime());
                        newmsg.setMsgType("news");
                        article.setDescription("感谢您的关注"); //图文消息的描述
                        article.setPicUrl("http://mmbiz.qpic.cn/sz_mmbiz_jpg/NSWia7o9Kx5DkPLw4zW7DKaksRSIQ0DwzpX0DAgbbNiaSVMG63oVxuia53rtL3MUHkQUeuia1sX024u0icdm8HmExtg/0?wx_fmt=jpeg"); //图文消息图片地址
                        article.setTitle("易臻云");  //图文消息标题
                        article.setUrl("http://mmbiz.qpic.cn/sz_mmbiz_jpg/NSWia7o9Kx5DkPLw4zW7DKaksRSIQ0DwzpX0DAgbbNiaSVMG63oVxuia53rtL3MUHkQUeuia1sX024u0icdm8HmExtg/0?wx_fmt=jpeg");  //图文url链接
                        List<Articles> list = new ArrayList<Articles>();
                        list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
                        newmsg.setArticleCount(list.size() + "");
                        newmsg.setArticles(list);
                    } else {
                        // 未走绑定
                        System.out.println("走else");
                    }
                }
            }
            if ("event".equals(MsgType)) {
                if (("SCAN").equals(Event)) {
                    if (EventKey != null && !"".equals(EventKey)) {
                        Response response1 = ptInfoService.binging(EventKey, FromUserName);
                        System.out.println("subscribe+EventKey" + EventKey);
                        if (response1.getStatus() == 0) {
                            //绑定成功
                            newmsg.setToUserName(FromUserName);
                            newmsg.setFromUserName(ToUserName);
                            newmsg.setCreateTime(new Date().getTime());
                            newmsg.setMsgType("news");
                            article.setDescription("感谢您的关注"); //图文消息的描述
                            article.setPicUrl("http://mmbiz.qpic.cn/sz_mmbiz_jpg/NSWia7o9Kx5DkPLw4zW7DKaksRSIQ0DwzpX0DAgbbNiaSVMG63oVxuia53rtL3MUHkQUeuia1sX024u0icdm8HmExtg/0?wx_fmt=jpeg"); //图文消息图片地址
                            article.setTitle("易臻云");  //图文消息标题
                            article.setUrl("http://mmbiz.qpic.cn/sz_mmbiz_jpg/NSWia7o9Kx5DkPLw4zW7DKaksRSIQ0DwzpX0DAgbbNiaSVMG63oVxuia53rtL3MUHkQUeuia1sX024u0icdm8HmExtg/0?wx_fmt=jpeg");  //图文url链接
                            List<Articles> list = new ArrayList<Articles>();
                            list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
                            newmsg.setArticleCount(list.size() + "");
                            newmsg.setArticles(list);
                        } else {
                            newmsg.setToUserName(FromUserName);
                            newmsg.setFromUserName(ToUserName);
                            newmsg.setCreateTime(new Date().getTime());
                            newmsg.setMsgType("news");
                            article.setDescription("您好！欢迎关注公众号"); //图文消息的描述
                            article.setPicUrl("http://mmbiz.qpic.cn/sz_mmbiz_jpg/NSWia7o9Kx5DkPLw4zW7DKaksRSIQ0DwzpX0DAgbbNiaSVMG63oVxuia53rtL3MUHkQUeuia1sX024u0icdm8HmExtg/0?wx_fmt=jpeg"); //图文消息图片地址
                            article.setTitle("易臻云");  //图文消息标题
                            article.setUrl("http://mmbiz.qpic.cn/sz_mmbiz_jpg/NSWia7o9Kx5DkPLw4zW7DKaksRSIQ0DwzpX0DAgbbNiaSVMG63oVxuia53rtL3MUHkQUeuia1sX024u0icdm8HmExtg/0?wx_fmt=jpeg");  //图文url链接
                            List<Articles> list = new ArrayList<Articles>();
                            list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
                            newmsg.setArticleCount(list.size() + "");
                            newmsg.setArticles(list);
                        }
                    }
                    XStream xstream = new XStream();//xstream.jar,xmlpull.jar
                    xstream.alias("xml", newmsg.getClass());//置换根节点
                    xstream.alias("item", article.getClass());
                    xstream.toXML(newmsg);

                    PrintWriter out = response.getWriter();
                    String imageMessage = xstream.toXML(newmsg);
                    System.out.println(imageMessage);
                    out.print(imageMessage);
                }
            }


            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 组装图片xml
     *
     * @param MediaId
     * @param toUserName
     * @param fromUserName
     * @return
     * @MethodName：initImageMessage
     * @author:maliran
     * @ReturnType:String
     */
    public static String initImageMessage(String MediaId, String toUserName, String fromUserName) {
        String message = null;
        com.ecard.entity.Image image = new Image();
        ImageMessage imageMessage = new ImageMessage();
        image.setMediaId(MediaId);
        imageMessage.setFromUserName(toUserName);
        imageMessage.setToUserName(fromUserName);
        imageMessage.setCreateTime(new Date().toString());
        imageMessage.setImage(image);
        imageMessage.setMsgType("image");
        message = imageMessageToXml(imageMessage);
        return message;
    }


    /**
     * 图片转成xml
     *
     * @param
     * @return
     * @MethodName：textMessageToXml
     * @author:maliran
     * @ReturnType:String
     */
    public static String imageMessageToXml(ImageMessage imageMessage) {

        XStream xstream = new XStream();//xstream.jar,xmlpull.jar
        xstream.alias("xml", imageMessage.getClass());//置换根节点
        //System.out.println(xstream.toXML(imageMessage));
        return xstream.toXML(imageMessage);
    }

    public String wxFirm1(String signature, String timestamp, String nonce,String echostr) throws Exception {
        String checktext = null;
        System.out.println("signature"+signature);
        System.out.println("timestamp"+timestamp);
        System.out.println("nonce"+nonce);
        System.out.println("echostr"+echostr);
        if (null != signature) {
            // 对ToKen,timestamp,nonce 按字典排序
            String[] paramArr = new String[] { "wx11115", timestamp, nonce };
            Arrays.sort(paramArr);
            // 将排序后的结果拼成一个字符串
            String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);

            try {
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                // 对接后的字符串进行sha1加密
                byte[] digest = md.digest(content.toString().getBytes());
                checktext = byteToStr(digest);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        System.out.println(checktext);
        // 将加密后的字符串与signature进行对比
        if(checktext.equals(signature.toUpperCase())) {
            return echostr;
        }else {
            return "false";
        }

    }

    private static String byteToStr(byte[] byteArrays) {
        String str = "";
        for (int i = 0; i < byteArrays.length; i++) {
            str += byteToHexStr(byteArrays[i]);
        }
        return str;
    }



    private static String byteToHexStr(byte myByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tampArr = new char[2];
        tampArr[0] = Digit[(myByte >>> 4) & 0X0F];
        tampArr[1] = Digit[myByte & 0X0F];
        String str = new String(tampArr);
        return str;
    }
}
