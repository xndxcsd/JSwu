package cn.edu.swu;

import java.io.IOException;

/**
 * Created by 西南大学开源协会 陈思定  on 2017/9/18.
 * <p>
 * Email : sidingchan@gmail.com
 */
public interface SchedulerFactory {
    Scheduler getScheduler() throws IOException;
}
