package cn.edu.swu;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.*;


/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/21.
 * <p>
 * Email : chensiding@qq.com
 */
public class DefaultCache implements Cache {

    public static final Logger LOGGER = LoggerFactory.getLogger(DefaultCache.class);

    private int cacheTime;
    private boolean isFresh;
    private FreshCache freshCache;

    private static final ConcurrentHashMap<Integer, Object> CACHE = new ConcurrentHashMap<Integer, Object>();

    public static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1, new ThreadFactory() {
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    public DefaultCache() {

    }

    public DefaultCache(int cacheTime) {
        this.cacheTime = cacheTime;
        this.isFresh = true;
        freshCache = new FreshCache();
        startFreshCache();
    }

    private void startFreshCache() {
        scheduler.scheduleAtFixedRate(freshCache, 0, 10000, TimeUnit.MILLISECONDS);
    }

    public Object getCache(Integer hash) {
        return CACHE.get(hash);
    }

    public void putCache(Integer hash, Object object) {
        if (isFresh) freshCache.put(hash);
        CACHE.put(hash, object);
    }

    public void remove(Integer hash) {
        if (isFresh) freshCache.remove(hash);
        CACHE.remove(hash);
    }

    public void removeAll() {
        if (isFresh) freshCache.removeAll();
        CACHE.clear();
    }

    @Override
    public int size() {
        return CACHE.size();
    }

    private class FreshCache extends TimerTask {

        private HashMap<Integer, Integer> countTimeMap = new HashMap<Integer, Integer>();

        FreshCache() {
            mark = cacheTime / 10000;
        }

        private int mark;

        public void run() {
            Iterator<Map.Entry<Integer, Integer>> iter = countTimeMap.entrySet().iterator();

            while (iter.hasNext()) {
                Map.Entry<Integer, Integer> entry = iter.next();
                int count = entry.getValue();
                if (count >= mark) {
                    DefaultCache.this.remove(entry.getKey());
                    countTimeMap.remove(entry.getKey());
                } else {
                    countTimeMap.replace(entry.getKey(), entry.getValue() + 1);
                }
            }
        }

        public void put(Integer hash) {
            countTimeMap.put(hash, 0);
        }

        public void remove(Integer hash) {
            countTimeMap.remove(hash);
        }

        public void removeAll() {
            countTimeMap.clear();
        }

    }

}
