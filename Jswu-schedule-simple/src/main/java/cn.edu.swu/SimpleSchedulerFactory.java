package cn.edu.swu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 西南大学开源协会 陈思定  on 2017/9/19.
 * <p>
 * Email : sidingchan@gmail.com
 */
//TODO change the factories to abstract factory
public class SimpleSchedulerFactory implements SchedulerFactory {

    private String swuid;

    public static final Logger LOGGER = LoggerFactory.getLogger(SimpleSchedulerFactory.class);

    private static final ConcurrentHashMap<String, Scheduler> CACHE = new ConcurrentHashMap<String, Scheduler>();

    public SimpleSchedulerFactory(String swuid) {
        this.swuid = swuid;
    }

    public Scheduler getScheduler() throws IOException {
        Scheduler scheduler = CACHE.get(this.swuid);
        if (scheduler == null) {
            synchronized (this) {
                if (scheduler == null) {
                    scheduler = initSimpleScheduler(this.swuid);
                    CACHE.put(this.swuid, scheduler);
                }
            }
        }
        return scheduler;
    }

    private SimpleScheduler initSimpleScheduler(String swuid) throws IOException {
        LOGGER.debug("create simpleScheduler , swuid:{}", swuid);
        return new SimpleScheduler(swuid);
    }
}
