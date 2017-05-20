package cn.edu.swu;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/21.
 * <p>
 * Email : chensiding@qq.com
 */
public class DefaultSwuConnectionManager implements SwuConnectionManager {
    private static DefaultSwuConnectionManager ourInstance = new DefaultSwuConnectionManager();

    public static DefaultSwuConnectionManager getInstance() {
        return ourInstance;
    }

    private DefaultSwuConnectionManager() {
    }

    public SwuConnection getConnection(String swuid) {
        return null;
    }

    public void putConnection(SwuConnection swuConnection) {

    }

    public void release(SwuConnection swuConnection) {

    }

    public void putIfAbsent(SwuConnection swuConnection) {

    }


    //FIXME



}
