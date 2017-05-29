package cn.edu.swu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/21.
 * <p>
 * Email : chensiding@qq.com
 */
public class DefaultSwuConnectionManager implements SwuConnectionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSwuConnection.class);

    private static DefaultSwuConnectionManager ourInstance = new DefaultSwuConnectionManager();

    public static DefaultSwuConnectionManager getInstance() {
        return ourInstance;
    }

    private DefaultSwuConnectionManager() {

    }

    private static ConcurrentHashMap<String,SwuConnection> CACHE= new ConcurrentHashMap<String,SwuConnection>();

    public SwuConnection getConnection(String swuid) {
        return CACHE.get(swuid);
    }

    @Override
    public SwuConnection getIfPresentOrPut(String swuid, String password) {
        SwuConnection swuConnection = CACHE.get(swuid);
        if (swuConnection == null){
            synchronized (this){
                if(swuConnection == null){
                    swuConnection = initSwuConnection(swuid, password);
                    CACHE.put(swuid,swuConnection);
                }
            }
        }
        return swuConnection;
    }

    public void putConnection(SwuConnection swuConnection) {

    }

    public void release(SwuConnection swuConnection) {

    }

    public void putIfAbsent(SwuConnection swuConnection) {

    }

    private SwuConnection initSwuConnection(String swuid, String password){
        SwuConnection swuConnection = null;
        try {
            swuConnection = new DefaultSwuConnection(swuid, password);
        } catch (IOException e) {
            LOGGER.error("无法建立网络连接，请检查网络环境，swuid={}",swuid);
        }
        return swuConnection;
    }


    //FIXME



}
