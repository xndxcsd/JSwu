package cn.edu.swu;

import org.apache.http.NameValuePair;

import java.io.IOException;
import java.util.List;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/21.
 * <p>
 * Email : chensiding@qq.com
 */
public interface SwuConnection {


    void release() throws IOException;

    String post(String url, List<NameValuePair> nameValuePairList) throws IOException;

    String get(String url) throws IOException;

    void getAccessOfJW() throws IOException;

    String getSwuid();

    Boolean isOpen();
}
