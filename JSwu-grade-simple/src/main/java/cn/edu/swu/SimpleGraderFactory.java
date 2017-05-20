package cn.edu.swu;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/21.
 * <p>
 * Email : chensiding@qq.com
 */
public class SimpleGraderFactory implements GraderFactory {

    private String swuid;
    private String password;

    private static final ConcurrentHashMap<String, Grader> CACHE = new ConcurrentHashMap<String, Grader>();

    public SimpleGraderFactory(String swuid, String password) {
        this.swuid = swuid;
        this.password = password;
    }

    public Grader getGrader() {
        Grader grader = CACHE.get(this.swuid);
        if (grader == null) {
            //FIXME
            synchronized (this) {
                if (grader == null) {
                    grader = new SimpleGrader(this.swuid, this.password);
                    CACHE.put(this.swuid, grader);
                }
            }
        }
        return grader;
    }


}
