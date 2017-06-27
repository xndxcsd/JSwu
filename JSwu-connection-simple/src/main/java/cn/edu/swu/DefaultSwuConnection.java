package cn.edu.swu;


import cn.edu.swu.common.LoginResponseBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

    private final String password;
    private final String swuid;

    public static final Logger LOGGER = LoggerFactory.getLogger(DefaultSwuConnection.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    /*设置请求配置,设置了连接超时和读取超时*/
    private static final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT)
            .setSocketTimeout(DEFAULT_CONNECTION_TIMEOUT)
            .build();
    private CloseableHttpClient httpClient;

    private Boolean opened=false;


    public DefaultSwuConnection(String swuid, String password) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClient = httpClientBuilder
                .useSystemProperties()
                .build();
        this.swuid = swuid;
        this.password = password;
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

            LOGGER.debug("发送认证成功");
             /*获得响应*/
            HttpEntity httpEntity = closeableHttpResponse.getEntity();
            String response = EntityUtils.toString(httpEntity);
            // release
            EntityUtils.consume(httpEntity);
            closeableHttpResponse.close();
            return response;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            IOException e2= new IOException("接口参数错误");
            e2.printStackTrace();
            throw e2;
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
                LOGGER.debug("network problem : can't connect jw");
                throw new IOException("network problem : can't connect jw");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public void getAccessOfJW() throws IOException {
        List<NameValuePair> entity = new ArrayList<>();
        entity.add(new BasicNameValuePair("IDToken0", ""));
        entity.add(new BasicNameValuePair("IDToken1", swuid));
        entity.add(new BasicNameValuePair("IDToken2", password));
        entity.add(new BasicNameValuePair("IDButton", "Submit"));
        entity.add(new BasicNameValuePair("goto", "aHR0cDovL2p3LnN3dS5lZHUuY24vandnbHh0L2lkc3Rhci9pbmRleC5qc3A="));
        entity.add(new BasicNameValuePair("encoded", "true"));
        entity.add(new BasicNameValuePair("gx_charset", "UTF-8"));

        post(URL_LOGIN, entity);
        if (!isSuccessAccessJW(get(URL_JW))) {
            LOGGER.debug("登陆失败，用户名或密码错误 swuid : {} password : {} ",swuid,password);
            new IllegalAccessException("用户名或密码错误").printStackTrace();
        }
    }

    @Override
    public String getSwuid() {
        return this.swuid;
    }

    @Override
    public Boolean isOpen() {
        return this.opened;
    }

    private Boolean isSuccessAccessJW(String response){
        Boolean ans =!response.contains("http://ids1.swu.edu.cn:81/amserver/UI/Login?goto=http%3A%2F%2Fjw.swu.edu.cn%2Fjwglxt%2Fidstar%2Findex.jsp");
        return ans && (opened = ans);
    }
}
