package cn.edu.swu;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/20.
 * <p>
 * Email : chensiding@qq.com
 */
public interface SwuConnectionManager {

    public SwuConnection getConnection(String swuid);

    public SwuConnection getIfPresentOrPut(String swuid, String password);

    public void putConnection(SwuConnection swuConnection);

    public void release(SwuConnection swuConnection);

    public void putIfAbsent(SwuConnection swuConnection);


}
