package cn.edu.swu;
import org.apache.http.message.BasicNameValuePair;
import java.util.List;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/21.
 * <p>
 * Email : chensiding@qq.com
 */
public interface SwuConnection {

    public SwuConnection init(String swuid,String password);

    public void release();

    public void post(String url, List<BasicNameValuePair> nameValuePairList);

}
