package cn.edu.swu;

import java.lang.reflect.Proxy;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/21.
 * <p>
 * Email : chensiding@qq.com
 */
public class SwuManager {

    private SwuConfig swuConfig;

    public SwuManager(SwuConfig swuConfig) {
        this.swuConfig = swuConfig;
    }

    public Grader getGrader() {
        GraderProxy graderProxy = new GraderProxy(swuConfig);
        Grader grader = (Grader) Proxy.newProxyInstance(Grader.class.getClassLoader(), new Class[]{Grader.class}, graderProxy);
        return grader;
    }


}
