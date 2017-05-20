package cn.swu.edu;


import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/21.
 * <p>
 * Email : chensiding@qq.com
 */
public class DefaultCache implements Cache {

    private static final long DEFAULT_CACHE_TIME = 10000000L;

    //FIXME the key is too big
    private final ConcurrentHashMap<Method, Object> CACHE = new ConcurrentHashMap<Method, Object>();


    public String getCache(Method method) {
        return null;
    }

    public void putCache(Method method, Object object) {

    }

    public void clear(Method method) {

    }

    public void clearAll() {

    }
}
