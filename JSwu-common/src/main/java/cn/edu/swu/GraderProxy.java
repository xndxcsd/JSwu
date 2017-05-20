package cn.edu.swu;

import cn.swu.edu.Cache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/21.
 * <p>
 * Email : chensiding@qq.com
 */
public class GraderProxy implements InvocationHandler {

    private SwuConfig swuConfig;

    public GraderProxy(SwuConfig swuConfig) {
        this.swuConfig = swuConfig;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Cache cache = swuConfig.getCache();

        String gradeData = cache.getCache(method);

        if (gradeData != null) return gradeData;

        //TODO setConnection

        Grader grader = swuConfig.getGraderFactory().getGrader();
        Object result = method.invoke(grader, args);
        cache.putCache(method, result);
        return result;
    }
}
