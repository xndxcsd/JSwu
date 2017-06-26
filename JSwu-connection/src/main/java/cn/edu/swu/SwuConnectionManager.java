package cn.edu.swu;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/20.
 * <p>
 * Email : chensiding@qq.com
 */
public interface SwuConnectionManager {

    SwuConnection getConnection(String swuid);

    SwuConnection getIfPresentOrPut(String swuid, String password);

    void release(SwuConnection swuConnection);

    void putIfAbsent(SwuConnection swuConnection);


}
