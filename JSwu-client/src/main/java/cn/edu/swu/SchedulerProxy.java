package cn.edu.swu;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by 西南大学开源协会 陈思定  on 2017/9/22.
 * <p>
 * Email : sidingchan@gmail.com
 */
public class SchedulerProxy implements InvocationHandler {

    SwuConfig swuConfig;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Cache cache = swuConfig.getCache();
        String scheduleData = (String) cache.getCache(method.hashCode());

        if (scheduleData != null) return scheduleData;

        SwuConnection swuConnection = swuConfig.getSwuConnectionManager().getIfPresentOrPut(swuConfig.getSwuid(), swuConfig.getPassword());

        Scheduler scheduler = swuConfig.getSchedulerFactory().getScheduler();
        Object result = method.invoke(scheduler, args);
        cache.putCache(method.hashCode(), result);
        return result;
    }

    public SchedulerProxy(SwuConfig swuConfig) {
        this.swuConfig = swuConfig;
    }
}
