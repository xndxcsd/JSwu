package cn.edu.swu;


import cn.edu.swu.common.LoginResponseBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BasicClientCookie2;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static cn.edu.swu.common.Constant.*;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/29.
 * <p>
 * Email : chensiding@qq.com
 */
public class DefaultSwuConnection implements SwuConnection {

    public static final Logger LOGGER = LoggerFactory.getLogger(DefaultSwuConnection.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    /*设置请求配置,设置了连接超时和读取超时*/
    private static final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT)
            .setSocketTimeout(DEFAULT_CONNECTION_TIMEOUT)
            .build();
    private CloseableHttpClient httpClient;
    private CookieStore cookieStore = new BasicCookieStore();

    public DefaultSwuConnection(String swuid, String password) throws IOException {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClient = httpClientBuilder
                .useSystemProperties()
                .setDefaultCookieStore(cookieStore)
                .build();

        HttpPost httpPost = new HttpPost(URL_LOGIN);
        //TODO Log for timeout
        httpPost.setConfig(requestConfig);
        String entity = String.format(LOGIN_CONTENT, swuid, password);
        String base64entity = Base64.getEncoder().encodeToString(entity.getBytes());
        List<NameValuePair> data = new ArrayList<>();
        data.add(new BasicNameValuePair("serviceInfo", base64entity));
        setCookies(post(URL_LOGIN, data));
    }

    @Override
    public void release() throws IOException {
        httpClient.close();
    }

    @Override
    public String post(String url, List<NameValuePair> nameValuePairList) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList));
            CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
            if (closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                 /*获得响应*/
                HttpEntity httpEntity = closeableHttpResponse.getEntity();
                String response = EntityUtils.toString(httpEntity);
                // release
                EntityUtils.consume(httpEntity);
                closeableHttpResponse.close();
                return response;
            } else {
                //TODO LOG
                throw new IOException("can't connect jw");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new IOException("won't happen");
        }
    }

    @Override
    public String get(String url) {

        //TODO fix this method
        String response = null;
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);

        try (CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet)) {

            if (closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity httpEntity = closeableHttpResponse.getEntity();
                response = EntityUtils.toString(httpEntity);
                EntityUtils.consume(httpEntity);
            } else {
                throw new IOException("can't connect jw");
            }

        } catch (IOException e) {
            //
        }
        return response;
    }

    @Override
    public void getAccessOfJW() {
        get(URL_JW);
    }

    private void setCookies(String str) throws IOException {
        try {
            LoginResponseBean bean = objectMapper.readValue(str, LoginResponseBean.class);
            if (!bean.isSuccess()) {
                throw new IOException("西南大学校园认证失败");
            }
            String tgt = bean.getData().getGetUserInfoByUserNameResponse().getReturnX().getInfo().getAttributes().getTgt();
            String cookie = new String(Base64.getDecoder().decode(tgt.getBytes()));

            BasicClientCookie c1 = new BasicClientCookie("rtx_rep", "no");
            c1.setDomain("i.swu.edu.cn");   //设置范围
            c1.setPath("/");
            BasicClientCookie c2 = new BasicClientCookie("CASTGC", cookie);
            c2.setDomain("i.swu.edu.cn");   //设置范围
            c2.setPath("/");

            cookieStore.addCookie(c1);
            cookieStore.addCookie(c2);
//            List<Cookie> cookies = cookieStore.getCookies();
//            for (int i = 0; i < cookies.size(); i++) {
//                System.out.println("Local cookie: " + cookies.get(i));
//            }
        } catch (IOException e) {
            LOGGER.error("无法解析返回值 : {}", str, e);
        }


    }
}
