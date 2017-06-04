package cn.edu.swu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static ConcurrentHashMap<String, SwuConnection> CACHE = new ConcurrentHashMap<String, SwuConnection>();

    public SwuConnection getConnection(String swuid) {
        return CACHE.get(swuid);
    }

    @Override
    public SwuConnection getIfPresentOrPut(String swuid, String password) {
        SwuConnection swuConnection = CACHE.get(swuid);
        if (swuConnection == null) {
            synchronized (this) {
                if (swuConnection == null) {
                    swuConnection = initSwuConnection(swuid, password);
                    CACHE.put(swuid, swuConnection);
                }
            }
        }
        return swuConnection;
    }


    public void release(SwuConnection swuConnection) {
        LOGGER.debug("close connection , swuid:{}", swuConnection.getSwuid());
        CACHE.remove(swuConnection.getSwuid());
    }

    public void putIfAbsent(SwuConnection swuConnection) {
        SwuConnection s = CACHE.putIfAbsent(swuConnection.getSwuid(), swuConnection);
        if (s == null) {
            LOGGER.debug("put connection , swuid:{}", swuConnection.getSwuid());
        }
    }

    private SwuConnection initSwuConnection(String swuid, String password) {
        LOGGER.debug("init connection {}:{}", swuid, password);
        SwuConnection swuConnection = new DefaultSwuConnection(swuid, password);
        return swuConnection;
    }


}
