package cn.edu.swu;

import cn.swu.edu.Cache;
import cn.swu.edu.DefaultCache;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/20.
 * <p>
 * Email : chensiding@qq.com
 */
public class SwuConfig {

    private String swuid;
    private String password;

    private Integer cacheTime;

    private Cache cache;
    private GraderFactory graderFactory;
    private SwuConnectionManager swuConnectionManager;

    private SwuConfig(String swuid, String password, Integer cacheTime, Cache cache, GraderFactory graderFactory, SwuConnectionManager swuConnectionManager) {
        this.swuid = swuid;
        this.password = password;
        this.cacheTime = cacheTime;
        this.cache = cache;
        this.graderFactory = graderFactory;
        this.swuConnectionManager = swuConnectionManager;
    }

    public SwuConnectionManager getSwuConnectionManager() {
        return swuConnectionManager;
    }

    public String getSwuid() {
        return swuid;
    }

    public String getPassword() {
        return password;
    }

    public Integer getCacheTime() {
        return cacheTime;
    }


    public Cache getCache() {
        return cache;
    }

    public GraderFactory getGraderFactory() {
        return graderFactory;
    }

    public static class Builder {

        private String swuid;
        private String password;

        private Integer cacheTime;

        private Cache cache;
        private GraderFactory graderFactory;
        private SwuConnectionManager swuConnectionManager;

        public Builder(String swuid, String password) {
            this.swuid = swuid;
            this.password = password;
        }

        public Builder setSwuConnectionManager(SwuConnectionManager swuConnectionManager) {
            this.swuConnectionManager = swuConnectionManager;
            return this;
        }

        public Builder setSwuid(String swuid) {
            this.swuid = swuid;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setCacheTime(Integer cacheTime) {
            this.cacheTime = cacheTime;
            return this;
        }


        public Builder setCache(Cache cache) {
            this.cache = cache;
            return this;
        }

        public Builder setGraderFactory(GraderFactory graderFactory) {
            this.graderFactory = graderFactory;
            return this;
        }

        public SwuConfig build() {

            if (this.cache == null) {
                if (this.cacheTime == null) {
                    this.cache = new DefaultCache();
                } else {
                    this.cache = new DefaultCache(this.cacheTime);
                }
            }
            if (this.graderFactory == null) {
                this.graderFactory = new SimpleGraderFactory(this.swuid);
            }
            if (this.swuConnectionManager == null) {
                this.swuConnectionManager = DefaultSwuConnectionManager.getInstance();
            }

            return new SwuConfig(this.swuid, this.password, this.cacheTime, this.cache, this.graderFactory, this.swuConnectionManager);
        }


    }
}
