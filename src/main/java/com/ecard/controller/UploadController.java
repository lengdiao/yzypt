package com.ecard.controller;

import com.ecard.Constants;
import com.ecard.dao.DrInfoMapper;
import com.ecard.dao.DrugSetPictureMapper;
import com.ecard.entity.DrInfo;
import com.ecard.entity.DrugSetPicture;
import com.ecard.entity.MrPicture;
import com.ecard.dao.MrPictureMapper;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.queryResult.DrInfoQr;
import io.swagger.annotations.*;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.List;

/**
 * 文件上传测试类
 *
 * @author yaoyu
 * @date 2018-7-30 14:32:55
 */

@RestController
@CrossOrigin
@Api(value = "图片上传Controller", tags = {"图片操作接口"})
@RequestMapping("/upload")
public class UploadController {

    private final ResourceLoader resourceLoader;

    @Autowired
    private MrPictureMapper mrPictureMapper;
    @Autowired
    private DrugSetPictureMapper drugSetPictureMapper;
    @Autowired
	private DrInfoMapper drInfoMapper;

    @Autowired
    public UploadController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * @param file 要上传的文件
     * @return
     */

    @RequestMapping(value = "/headUpload", method = RequestMethod.POST)
    @ApiOperation(value = "上传头像", httpMethod = "POST", notes = "")
    public Response headUpload(
            @ApiParam(name = "file", value = "头像", required = false)
            @RequestParam("file") String file,
            @ApiParam(name = "drNo", value = "医生编号", required = false)
            @RequestParam(value = "drNo", required = false) Long drNo) {
        ResponseHasData response = new ResponseHasData();
        // 要上传的目标文件存放路径
        String localPath = "webapps/yzypt/WEB-INF/classes/static/drImage/";
        // 上传成功或者失败的提示

        //随机数
        int random =(int)(Math.random()*(15-5+1)+5);

        try {
            generateImage(file, localPath + "dr" + drNo + random + ".jpg");
			DrInfo drInfo = drInfoMapper.selectByPrimaryKey(drNo);
			drInfo.setHeadImg("https://www.yizhenyun.com.cn/yzypt/drImage/dr" + drNo + random + ".jpg");
			drInfoMapper.updateByPrimaryKeySelective(drInfo);

            response.setMsg("上传更新成功");
            response.setStatus(Constants.STATUS_SUCCESS);
            response.setData("https://www.yizhenyun.com.cn/yzypt/drImage/dr" + drNo + random + ".jpg");
            System.out.println("https://www.yizhenyun.com.cn/yzypt/drImage/dr" + drNo + random + ".jpg");
            return response;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response.setMsg("上传失败");
            response.setStatus(Constants.STATUS_FAIL);
            return response;
        }


    }


    /**
     * @param file 要上传的文件
     * @return
     */

    @RequestMapping(value = "/pictureUpload", method = RequestMethod.POST)
    @ApiOperation(value = "file 要上传的文件", httpMethod = "POST", notes = "图片与病例表编号一起上传时，新增操作，图片与图片编号一起上传时，修改操作")
    public Response upload(
            @ApiParam(name = "file", value = "图片", required = true)
            @RequestParam("file") String file,
            @ApiParam(name = "medRecordNo", value = "病例表编号", required = false)
            @RequestParam(value = "medRecordNo", required = false) String medRecordNo,
            @ApiParam(name = "mrPictureNo", value = "图片编号", required = false)
            @RequestParam(value = "mrPictureNo", required = false) String mrPictureNo) {
        ResponseHasData response = new ResponseHasData();
        // 要上传的目标文件存放路径
        String localPath = "webapps/yzypt/WEB-INF/classes/static/images/";
        // String localPath = "D:";
        // 上传成功或者失败的提示
        if (medRecordNo == null || medRecordNo.equals("")) {
            if (mrPictureNo == null || mrPictureNo.equals("")) {
                response.setMsg("medRecordNo和mrPictureNo均未上传报错");
                response.setStatus(Constants.STATUS_FAIL);
                return response;
            } else {
                try {
                    MrPicture mp = mrPictureMapper.selectByPrimaryKey(Long.parseLong(mrPictureNo));
/*						byte[] filebyte=decode(file);
						System.out.println(filebyte.toString());		
						File picture=new File(localPath+mp.getMrPictureAddress()+".jpg");
						FileImageOutputStream imageOutput = new FileImageOutputStream(picture);
						imageOutput.write(filebyte, 0, filebyte.length);
						imageOutput.close();
						System.out.println("Make Picture success,Please find image in " + localPath);	*/
                    generateImage(file, localPath + mp.getMrPictureAddress() + ".jpg");
                    response.setMsg("上传更新成功");
                    response.setStatus(Constants.STATUS_SUCCESS);
                    response.setData("https://www.yizhenyun.com.cn/yzypt/images/" + mp.getMrPictureAddress() + ".jpg");
                    System.out.println("https://www.yizhenyun.com.cn/yzypt/images/" + mp.getMrPictureAddress() + ".jpg");
                    return response;
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    response.setMsg("上传失败");
                    response.setStatus(Constants.STATUS_FAIL);
                    return response;
                }
            }
        } else {
            MrPicture[] mp = mrPictureMapper.selectByMedRecordNo(medRecordNo);
            if (mp.length >= 6) {
                response.setMsg("图片已满6张");
                response.setStatus(Constants.STATUS_FAIL);
                return response;
            }
            try {
                String fileName = medRecordNo + (mp.length + 1);
						/*byte[] filebyte=decode(file);
						System.out.println(filebyte.toString());		
						File picture=new File(localPath+fileName+".jpg");
						FileImageOutputStream imageOutput = new FileImageOutputStream(picture);
						imageOutput.write(filebyte, 0, filebyte.length);
						imageOutput.close();*/
                generateImage(file, localPath + fileName + ".jpg");
                MrPicture nmp = new MrPicture();
                nmp.setCreateTime(new Date());
                nmp.setMedRecordNo(medRecordNo);
                nmp.setCreateUser(Long.parseLong(Constants.SYSTEM_USER));
                nmp.setMrPictureAddress("https://www.yizhenyun.com.cn/yzypt/images/" + fileName + ".jpg");
                mrPictureMapper.insertSelective(nmp);
                response.setMsg("上传成功");
                response.setData("https://www.yizhenyun.com.cn/yzypt/images/" + fileName + ".jpg");
                System.out.println("https://www.yizhenyun.com.cn/yzypt/images/" + fileName + ".jpg");
                response.setStatus(Constants.STATUS_SUCCESS);
                return response;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response.setMsg("上传失败");
                response.setStatus(Constants.STATUS_FAIL);
                return response;
            }

        }

    }


    @RequestMapping(value = "/drugsetPictureUpload", method = RequestMethod.POST)
    @ApiOperation(value = "上传药品图片", httpMethod = "POST", notes = "")
    public Response drugsetPictureUpload(
            @ApiParam(name = "file", value = "图片", required = true)
            @RequestParam("file") String file,
            @ApiParam(name = "goodsNo", value = "药品编号", required = false)
            @RequestParam(value = "goodsNo", required = false) Long goodsNo,
            @ApiParam(name = "number", value = "图片顺序编号", required = false)
            @RequestParam(value = "number", required = false) int number,
            @ApiParam(name = "status", value = "标识（1：标题图、2：内容图）", required = false)
            @RequestParam(value = "status", required = false) int status) {
        ResponseHasData response = new ResponseHasData();
        // 要上传的目标文件存放路径
        String localPath = "webapps/yzypt/WEB-INF/classes/static/images/";
        // String localPath = "D:";
        // 上传成功或者失败的提示
        String path = "https://www.yizhenyun.com.cn/yzypt/images/";
        try {
            if (status == 1) {
                generateImage(file, localPath + goodsNo + "a" + ".jpg");
                DrugSetPicture drugSetPicture = new DrugSetPicture();
                List<DrugSetPicture> drugSetPictures = drugSetPictureMapper.selectByGoodsNo(goodsNo);
                if (drugSetPictures.size() == 0) {
                    //新增
                    drugSetPicture.setGoodsNo(goodsNo);
                    drugSetPicture.setTitleImg(path + goodsNo + "a.jpg");
                    drugSetPictureMapper.insertSelective(drugSetPicture);
                } else {
                    for (DrugSetPicture drugSetPicture1 : drugSetPictures) {
                        drugSetPicture1.setTitleImg(path + goodsNo + "a.jpg");
                        drugSetPictureMapper.updateByPrimaryKeySelective(drugSetPicture1);
                    }
                }
                response.setMsg("上传更新成功");
                response.setStatus(Constants.STATUS_SUCCESS);
                response.setData(path + goodsNo + "a" + ".jpg");
            } else {
                System.out.println("number:" + number);
                //status=2
                generateImage(file, localPath + goodsNo + "b" + number + ".jpg");
                DrugSetPicture drugSetPicture = new DrugSetPicture();
                List<DrugSetPicture> drugSetPictures = drugSetPictureMapper.selectByGoodsNoAndNumber(goodsNo, number);
                if (drugSetPictures.size() == 0) {
                    //没有goodsNo,number
                    List<DrugSetPicture> pictures = drugSetPictureMapper.selectByGoodsNo1(goodsNo);
                    if (pictures.size() == 0) {
                        //没有goodsNo
                        drugSetPicture.setNumber(number);
                        drugSetPicture.setGoodsNo(goodsNo);
                        drugSetPicture.setDetailsImg(path + goodsNo + "b" + number + ".jpg");
                        drugSetPictureMapper.insertSelective(drugSetPicture);
                    } else {
                        //有goodsNo
                        drugSetPicture.setId(pictures.get(0).getId());
                        drugSetPicture.setTitleImg(pictures.get(0).getTitleImg());
                        drugSetPicture.setNumber(number);
                        drugSetPicture.setGoodsNo(goodsNo);
                        drugSetPicture.setDetailsImg(path + goodsNo + "b" + number + ".jpg");
                        drugSetPictureMapper.updateByPrimaryKeySelective(drugSetPicture);
                    }

                }
                response.setMsg("上传更新成功");
                response.setStatus(Constants.STATUS_SUCCESS);
                response.setData(path + goodsNo + "b" + number + ".jpg");

            }


            return response;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response.setMsg("上传失败");
            response.setStatus(Constants.STATUS_FAIL);
            return response;
        }


    }

    /**
     * 删除商品图片
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除商品图片", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "goodsNo", value = "商品编号", dataType = "Long"),
            @ApiImplicitParam(paramType = "query", name = "number", value = "图片顺序号", dataType = "int")
    })
    public Response delete(
            @RequestParam(value = "goodsNo", required = false) Long goodsNo,
            @RequestParam(value = "number", required = false) int number
    ) {
        Response response = new ResponseHasData();
        List<DrugSetPicture> drugSetPictures = drugSetPictureMapper.selectByGoodsNoAndNumber(goodsNo, number);
        drugSetPictureMapper.deleteByPrimaryKey(drugSetPictures.get(0).getId());
        return response;
    }


    public static byte[] decode(String str) {
        byte[] bt = null;
        try {
            bt = Base64.decodeBase64(str);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bt;
    }


    public boolean generateImage(String imgStr, String filename) {
        if (imgStr == null) {
            return false;
        }
        try {
            // 解密
            byte[] b = Base64.decodeBase64(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(filename);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }


    /**
     * 上传本地文件到微信获取mediaId
     */

    public static String upload() {
        String mediaId = null;
        try {
            File file = new File("/Users/hzh/Downloads/2.jpeg");
            if (!file.exists() || !file.isFile()) {
                throw new IOException("文件不存在");
            }

            String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE".replace("ACCESS_TOKEN", "30_NdmrHFkzTXhKKlIfYmScDNsBBJ8c8cJhDoM0bmZgF0mZpGFvKps4DCcqx6_lzHN7h7LGOzX7mfVrMexxWU9bLHHszSgfOfF2Emeg0Rsj_iubWP59MSunmt7NjHyLnVq_GXRSr-VNkgSXeCz7FJXeAFAXBP").replace("TYPE", "image");

            URL urlObj = new URL(url);
            //连接
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);

            //设置请求头信息
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");

            //设置边界
            String BOUNDARY = "----------" + System.currentTimeMillis();
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

            StringBuilder sb = new StringBuilder();
            sb.append("--");
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");

            byte[] head = sb.toString().getBytes("utf-8");

            //获得输出流
            OutputStream out = new DataOutputStream(con.getOutputStream());
            //输出表头
            out.write(head);

            //文件正文部分
            //把文件已流文件的方式 推入到url中
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();

            //结尾部分
            byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分隔线

            out.write(foot);

            out.flush();
            out.close();

            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = null;
            String result = null;
            try {
                //定义BufferedReader输入流来读取URL的响应
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                if (result == null) {
                    result = buffer.toString();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }

            JSONObject jsonObj = JSONObject.fromObject(result);
            System.out.println(jsonObj);
            String typeName = "media_id";
//		if(!"image".equals(type)){
//			typeName = type + "_media_id";
//		}
            mediaId = jsonObj.getString(typeName);
            System.out.println(mediaId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mediaId;
        //3k8wm756ofCsREthEwyxmardfoGsW2Tjj6h-5ldSeQFiQ22wwoWoMwGbXEH5xvXb
    }


    /**
     * 上传其他永久素材(图片素材的上限为5000，其他类型为1000)
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/uploadWxImg")
    public static JSONObject addMaterialEver(String type, String token) {
        try {
            File file = new File("/Users/hzh/Documents/易臻云项目/yzypt页面/医生端操作手册截图/guidance1.png");
            //上传素材
            String path = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + token + "&type=" + type;
            String result = connectHttpsByPost(path, null, file);
            result = result.replaceAll("[\\\\]", "");
            System.out.println("result:" + result);
            JSONObject resultJSON = JSONObject.fromObject(result);
            if (resultJSON != null) {
                if (resultJSON.get("media_id") != null) {
                    System.out.println("上传" + type + "永久素材成功");
                    return resultJSON;
                } else {
                    System.out.println("上传" + type + "永久素材失败");
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String connectHttpsByPost(String path, String KK, File file) throws
            IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        URL urlObj = new URL(path);
        //连接
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        String result = null;
        con.setRequestMethod("POST");
        con.setDoInput(true);

        con.setDoOutput(true);

        con.setUseCaches(false); // post方式不能使用缓存

        // 设置请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");
        // 设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type",
                "multipart/form-data; boundary="
                        + BOUNDARY);

        // 请求正文信息
        // 第一部分：
        StringBuilder sb = new StringBuilder();
        sb.append("--"); // 必须多两道线
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"media\";filelength=\"" + file.length() + "\";filename=\""

                + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        byte[] head = sb.toString().getBytes("utf-8");
        // 获得输出流
        OutputStream out = new DataOutputStream(con.getOutputStream());
        // 输出表头
        out.write(head);

        // 文件正文部分
        // 把文件已流文件的方式 推入到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();
        // 结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
        out.write(foot);
        out.flush();
        out.close();
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        try {
            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return result;
    }


}