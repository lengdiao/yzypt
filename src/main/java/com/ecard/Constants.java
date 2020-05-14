package com.ecard;

public class Constants {

	/** 云用户角色(平台工作人员) */
	public static final String CLOUD_PASS_KIND_400WORKER = "1";

	/** 云用户角色(药店操作人员) */
	public static final String CLOUD_PASS_KIND_DRUGSTORE = "2";
	
	/** 云用户角色(经销商操作人员) */
	public static final String CLOUD_PASS_KIND_DEALER = "3";
	
	/** 云用户角色(医生) */
	public static final String CLOUD_PASS_KIND_DOCTOR = "4";

	/** 云用户角色(超级管理员) */
	public static final String CLOUD_PASS_KIND_SYSTEM = "5";
	
	/** 微信公众号平台(苯烯莫德) */
	public static final Integer WX_BXMD = 1;
	/** 微信公众号平台(金苞谷) */
	public static final Integer WX_JBG = 2;
	/** 微信公众号平台(玻尿酸) */
	public static final Integer WX_BNS = 3;
	
	/** 返回数据成功状态  --success */
	public static final int STATUS_SUCCESS = 0;
	
	/** 返回数据失败状态  --fail*/
	public static final int STATUS_FAIL = 1;
	
	/** 系统用户 */
	public static final String SYSTEM_USER = "9999999999";
	
	/**默认注册密码*/
	public static final String PASSWORDS = "123456";
	
	/**
	 * 发货地址默认状态 1为默认0为未默认
	 */
	public static final Integer ADDRESS_MARK_ON = 1;
	public static final Integer ADDRESS_MARK_OFF = 0;
	
	/**
	 * 支付状态 0为成功，1为失败
	 */
	public static final Integer PAY_STATUS_SUCCESS = 0;
	public static final Integer PAY_STATUS_FAIL = 1;
	
	/**
	 * 0为停用  1为未停用 2为添加至购物车，如选择购买后改至0
	 */
	public static final Integer CACHE_DISEASE_ON = 1;
	public static final Integer CACHE_DISEASE_OFF = 0;
	public static final Integer CACHE_DISEASE_UNFINISHED = 2;
	
	/**
	 * 订单状态 0为成功，1为需补齐信息
	 */
	public static final Integer ORDER_STATUS_SUCCESS = 0;
	public static final Integer ORDER_STATUS_FAIL = 1;
	/**
	 * 状态0 跳转补全用户信息
	 */
	public static final Integer SKIP_UPDATE_PTINFO = 0;
	/**
	 *  状态2跳转补全地址
	 */
	public static final Integer SKIP_UPDATE_RECEIPTADDRESS = 2;
	

	
	/**
	 * 状态2未配送 1为配饰
	 */
	public static final Integer SHOPPING_STATUS_FAIL = 2;
	public static final Integer SHOPPING_STATUS_SUCCESS = 1;
	
	
	

    
    
	/** 支付宝私钥 **/
	public static final String  ALIPAY_RSA_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALd230KF+bvfrf4m"+
"B/N0Si93rprmLQGdzAbD92f0mTEQ3lFYzWwQPXDuVRzyogqrGvbLYw7As+gjrqGa"+
"IKESvnAPEIvGlu+CL7eln5JXFruy5JMEbjedzt66+UyZoNYzqQ11B16mbyF1euyw"+
"3p3mV8Ch6bURjYUh06IN7nMtf7xdAgMBAAECgYBiufpMrxIDssvLvyV6MjMVth7k"+
"xkaj3wmC/oOtkme5iq1IMzRgGaEmm2p76GmguhEJi8tjjfmCS2lXFzzF0PNfKkf9"+
"pQPP3OW0XM8ESXoJuLZoxKDLSf97HHSzEC0qWB0v0u3nYQoHZmfjSGOMkquACoVn"+
"c9OaZrmoFPhcwP04FQJBAOevDVx7wWxr4y8nrIWPxWRVxJSFvEa7wetKJZXlLRLn"+
"+TEzsmawrAZCI0z2wrMiDaHE3pV6+/sTT7Cr/hRXdtsCQQDKuD7es+awQJKKdsHj"+
"+hvaDSbI+meXFAEtU1Sm+h6rYYuK4uR9z9zGwx87bIju5yNccA4K7WeevZneBa45"+
"LTMnAkEAvep3D2q64X+G2W6yM0HpUxgjboQ6E9lxWsiLCta0Hh1d3gE8qoUEXpT0"+
"jvBwJZsyhXr14p7P076+wloait4vpQJAF2maETxzyy1Z/1Xh5MORDejw7stExYUQ"+
"8bwrDbHbcu2sIRk9TG6CUA5fAAzz76uQQO/MH2T1z7aUG7lKOt5GhwJACInbZ5FC"+
"MbZlsthaZiKOm7WpTGUuTjoA8d/MvALICX1K4cCRq+0n9LJOi9KJ+eparozuxc0T"+
"G1gIbUcjSB5lVA==";
	
	public static final String ALIPAY_RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYI"
			+ "SRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdss"
			+ "ymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
	public static final int CATEGORY_MALL_CHARGE_ORDER_SETTINGS = 91;
	public static final int CATEGORY_SMS_STATUS = 92;
	
	/** 微信支付 **/
	public static final String WX_appid = "wx4866cfc91aab273c";//应用ID
	public static final String WX_mch_id = "1380793602";//商户号
	public static final String WX_body = "payCenter";//商品描述
	public static final String WX_detail = "yzb";//商品详情
	public static final String WX_attach = "yz";//附加数据
	public static final String WX_fee_type = "CNY";//货币类型
	public static final String WX_goods_tag = "WXG";//商品标记
	public static final String WX_notify_url = "http://115.231.106.181:8090/zjuchWeb/resultpaywx.action";//通知地址
	public static final String WX_trade_type = "APP";//交易类型
	public static final String WX_limit_pay = "no_credit";//指定支付方式
	public static final String WX_package = "Sign=WXPay";//扩展字段
	public static final String WX_key = "LPZ4SMZF0HWTYNXBCWDRXXF79RO2EHVT";//微信密钥

	/** 支付宝支付 **/
	public static final String ALI_NOTIFY_URL = "http://115.231.106.181:8090/zjuchWeb/resultpayalipay";//回调接口地址
	public static final String ALI_SIGN_TYPE="RSA";//
	public static final String ALI_CHARSET="utf-8";
	public static final String ALI_APP_ID="2016082401796135";
	public static final String ALI_METHOD="alipay.trade.app.pay";
	public static final String ALI_FORMAT="JSON";
	public static final String ALI_VERSION="1.0";
	public static final String ALI_PRODUCT_CODE="QUICK_MSECURITY_PAY";
	public static final String ALI_SELLER_ID="2088421650667483";
	public static final String ALI_TIMEOUT_EXPRESS="10m";
	
	public static final String ALI_RESULT_TRADE_FINISHED="TRADE_FINISHED";
	public static final String ALI_RESULT_TRADE_SUCCESS = "TRADE_SUCCESS";
	public static final String ALI_RESULT_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
	public static final String ALI_RESULT_TRADE_CLOSED = "TRADE_CLOSED";

	/** 其他途径注册无手机号用户 初始默认手机号 **/
	public static final String DEFAULT_PHONE_NUMBER = "123456";
	
}

