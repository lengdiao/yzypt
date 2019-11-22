package com.ecard.utils;

import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.codec.binary.Base64;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 文件上传测试类
 * 
 * @author yaoyu
 * @date 2018-7-30 14:32:55
 */

@RestController
@CrossOrigin
@Api(value = "图片上传Controller", tags = { "图片操作接口" })
@RequestMapping("/upload")
public class UploadUtil {

	private final ResourceLoader resourceLoader;


	@Autowired
	public UploadUtil(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	/**
	 *
	 * @param file
	 *            要上传的文件
	 * @return
	 */

	@RequestMapping(value = "/pictureUpload", method = RequestMethod.POST)
	@ApiOperation(value = "file 要上传的文件", httpMethod = "POST", notes = "图片与病例表编号一起上传时，新增操作，图片与图片编号一起上传时，修改操作")
	public Response upload(
			@ApiParam(name = "file", value = "图片", required = true) @RequestParam("file") String file,
			@ApiParam(name = "medRecordNo", value = "病例表编号", required = false) @RequestParam(value = "medRecordNo", required = false) String medRecordNo,
			@ApiParam(name = "mrPictureNo", value = "图片编号", required = false) @RequestParam(value = "mrPictureNo", required = false) String mrPictureNo) {
		ResponseHasData response = new ResponseHasData();
		// 要上传的目标文件存放路径
		String localPath = "webapps/zlyypt/WEB-INF/classes/static/images/";
		// String localPath = "D:";
		// 上传成功或者失败的提示							
		try {	
			//generateImage(file,localPath+mp.getMrPictureAddress()+".jpg");
			response.setMsg("上传更新成功");
			response.setStatus(0);
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMsg("上传失败");
			response.setStatus(1);
			return response;
		}
					
			

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

	  
	  public static boolean generateImage(String imgStr, String filename) {		        
		         if (imgStr == null) {
		            return false;
		        }
		        try {
		              // 解密
		              byte[] b = Base64.decodeBase64(imgStr);;
		              // 处理数据
		            for(int i = 0; i < b.length; ++i) {
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
}