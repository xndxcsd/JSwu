package cn.edu.swu;
import org.apache.http.NameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/21.
 * <p>
 * Email : chensiding@qq.com
 */
public interface SwuConnection {


    public void release() throws IOException;

    public String post(String url, List<NameValuePair> nameValuePairList) throws IOException;

    public String get(String url);

    public void getAccessOfJW();

}
