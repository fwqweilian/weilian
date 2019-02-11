package com.gemepro.common.utils;


/**
 * 短信发送工具类
 * 创建者  fwq78
 * 创建时间	2017年10月15日
 */
public class SmsUtil {
//    static final String signName = "威廉古堡";//签名
//    static final String domain = "http://gw.api.taobao.com/router/rest";
//    static final String accessKeyId = "23448649";//此处私钥
//    static final String accessKeySecret = "76d4b7d29f8487f7678b11b8d95a0f92";//此处私钥
//
//    /**
//     *  
//     *  发送短信验证码
//     *  @return 
//     */
//    public static String sendVerificationCode(String phone, String clientName) {
//        String result = null;
//        //验证码
//        String code = getCode();
//        //模板
//        String templateCode = "SMS_104265011";
//        TaobaoClient client = new DefaultTaobaoClient(domain, accessKeyId, accessKeySecret);
//        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//        req.setExtend("123456");
//        req.setSmsType("normal");
//        req.setSmsFreeSignName(signName);
//        req.setSmsParamString("{\"client\":\"" + clientName + "\",\"code\":\"" + code + "\"}");
//        req.setRecNum(phone);
//        req.setSmsTemplateCode(templateCode);
//        try {
//            AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
//            if (rsp != null && rsp.getResult().getSuccess()) {
//                result = code;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     *  
//     *  随机生成6位数字作为验证码 
//     *  @return 
//     */
//    public static String getCode() {
//        int code = (int) (Math.random() * 9000 + 100000);
//        return code + "";
//    }
}
