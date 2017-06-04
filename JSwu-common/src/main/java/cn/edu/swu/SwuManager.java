package cn.edu.swu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/21.
 * <p>
 * Email : chensiding@qq.com
 */
public class SwuManager {


    private static final Logger LOGGER = LoggerFactory.getLogger(SwuManager.class);

    private SwuConfig swuConfig;

    public SwuManager(SwuConfig swuConfig) {
        this.swuConfig = swuConfig;
    }

    public Grader getGrader() {
        LOGGER.debug("get Grader Invoker , swuid:{}", swuConfig.getSwuid());
        GraderProxy graderProxy = new GraderProxy(swuConfig);
        Grader grader = (Grader) Proxy.newProxyInstance(Grader.class.getClassLoader(), new Class[]{Grader.class}, graderProxy);
        return grader;
    }


}
