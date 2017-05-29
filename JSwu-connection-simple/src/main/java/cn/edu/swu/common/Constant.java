package cn.edu.swu.common;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/29.
 * <p>
 * Email : chensiding@qq.com
 */
public class Constant {
    public static final String URL_LOGIN="http://i.swu.edu.cn/remote/service/process";
    // LOGIN_CONTENT中有%s占位符，需要使用swuid和password格式化
    public static final String LOGIN_CONTENT="{\"serviceAddress\":\"https://uaaap.swu.edu.cn/cas/ws/acpInfoManagerWS\",\"serviceType\":\"soap\",\"serviceSource\":\"td\",\"paramDataFormat\":\"xml\",\"httpMethod\":\"POST\",\"soapInterface\":\"getUserInfoByUserName\",\"params\":{\"userName\":\"%s\",\"passwd\":\"%s\",\"clientId\":\"yzsfwmh\",\"clientSecret\":\"1qazz@WSX3edc$RFV\",\"url\":\"http://i.swu.edu.cn\"},\"cDataPath\":[],\"namespace\":\"\",\"xml_json\":\"\",\"businessServiceName\":\"uaaplogin\"}";

    public static final String URL_JW = "http://jw.swu.edu.cn/jwglxt/idstar/index.jsp";

    public static final int DEFAULT_CONNECTION_TIMEOUT = 4000;
}
