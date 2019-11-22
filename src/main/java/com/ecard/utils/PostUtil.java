package com.ecard.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * Description:
 * Created by 少坤 on 2015/12/15.
 */
public class PostUtil {

    /***
     * 发送post
     *
     * @param url      目标接口url
     * @param header   头类型
     * @param postData 发送数据
     * @return 接口返回内容 String型
     * @throws IOException
     */
    public static String sendPost(URL url, String header, String postData) throws NoRouteToHostException, SocketTimeoutException, IOException {
        HttpURLConnection connection = null;
        StringBuffer response;
        try {
            connection = (HttpURLConnection) url.openConnection();
            // 设置http连接属性
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST"); // 可以根据需要 提交
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setConnectTimeout(10000);//连接超时10s
            connection.setReadTimeout(100000);//读取返回信息超时45s
            String contentType = header + ";charset=utf-8";//指定发送格式以及编码
            connection.setRequestProperty("Content-Type", contentType); // 设定请求格式
            connection.setRequestProperty("Accept", "text/json");// 设定响应的信息的格式为json
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.connect();


            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.write(postData.getBytes("utf-8"));//输出流
            wr.flush();
            wr.close();

            // 读取返回信息
            InputStream is = connection.getInputStream();//获取流
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            response = new StringBuffer();
            while ((line = rd.readLine()) != null) {//构建返回字符串
                response.append(line);
                response.append('\r');
            }
            rd.close();//关闭流
            System.out.println(response.toString());
            return response.toString();
        } catch (Exception e) {
            return null;
        } finally {
            System.out.println("URL: " + connection.getURL().toString());
            System.out.println("Content-Type: " + connection.getContentType());
            System.out.println("Method: " + connection.getRequestMethod());
            System.out.println("Output: " + postData);
            System.out.println("HTTP-Status: " + connection.getResponseMessage());

            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /***
     * 发送post  xml类型的数据
     *
     * @param url      目标接口url
     * @param header   头类型
     * @param postData 发送数据
     * @return 接口返回内容 String型
     * @throws IOException
     */
    public static String sendPostXml(URL url, String header, String postData) throws NoRouteToHostException, SocketTimeoutException, IOException {
        HttpURLConnection connection = null;
        StringBuffer response;
        try {
            connection = (HttpURLConnection) url.openConnection();
            // 设置http连接属性
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST"); // 可以根据需要 提交
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setConnectTimeout(10000);//连接超时10s
            connection.setReadTimeout(100000);//读取返回信息超时45s
            String contentType = header + ";charset=utf-8";//指定发送格式以及编码
            connection.setRequestProperty("Content-Type", contentType); // 设定请求格式
            connection.setRequestProperty("Accept", "text/xml");// 设定响应的信息的格式为json
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.connect();


            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.write(postData.getBytes("utf-8"));//输出流
            wr.flush();
            wr.close();

            // 读取返回信息
            InputStream is = connection.getInputStream();//获取流

            BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            response = new StringBuffer();
            while ((line = rd.readLine()) != null) {//构建返回字符串
                response.append(line);
//                response.append('\r');
            }
            rd.close();//关闭流
            System.out.println(response.toString());
            return response.toString();
        } catch (Exception e) {
            return null;
        } finally {
            System.out.println("URL: " + connection.getURL().toString());
            System.out.println("Content-Type: " + connection.getContentType());
            System.out.println("Method: " + connection.getRequestMethod());
            System.out.println("Output: "+postData);
            System.out.println("HTTP-Status: " + connection.getResponseMessage());

            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /***
     * 发送get  xml类型的数据
     *
     * @param url      目标接口url
     * @return 接口返回内容 String型
     * @throws IOException
     */
    public static String sendGet(URL url) throws NoRouteToHostException, SocketTimeoutException, IOException {
        HttpURLConnection connection = null;
        StringBuffer response;
        try {
            connection = (HttpURLConnection) url.openConnection();
            // 设置http连接属性
            connection.setRequestMethod("GET"); // 可以根据需要 提交

            connection.setDoOutput(false);
            connection.setDoInput(true);
            //设置连接超时时间和读取超时时间
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.connect();

            // 读取返回信息
            InputStream is = connection.getInputStream();//获取流

            BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            response = new StringBuffer();
            while ((line = rd.readLine()) != null) {//构建返回字符串
                response.append(line);
//                response.append('\r');
            }
            rd.close();//关闭流
            System.out.println(response.toString());
            return response.toString();
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        } finally {
            System.out.println("URL: " + connection.getURL().toString());
            System.out.println("Content-Type: " + connection.getContentType());
            System.out.println("Method: " + connection.getRequestMethod());
            System.out.println("HTTP-Status: " + connection.getResponseMessage());

            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}
