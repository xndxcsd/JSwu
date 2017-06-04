package cn.edu.swu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/21.
 * <p>
 * Email : chensiding@qq.com
 */
public class SimpleGraderFactory implements GraderFactory {

    private String swuid;

    public static final Logger LOGGER = LoggerFactory.getLogger(SimpleGraderFactory.class);

    private static final ConcurrentHashMap<String, Grader> CACHE = new ConcurrentHashMap<String, Grader>();

    public SimpleGraderFactory(String swuid) {
        this.swuid = swuid;
    }

    public Grader getGrader() throws IOException {
        Grader grader = CACHE.get(this.swuid);
        if (grader == null) {
            synchronized (this) {
                if (grader == null) {
                    grader = initSimpleGrader(this.swuid);
                    CACHE.put(this.swuid, grader);
                }
            }
        }
        return grader;
    }

    private SimpleGrader initSimpleGrader(String swuid) throws IOException {
        LOGGER.debug("create simpleGrader , swuid:{}", swuid);
        return new SimpleGrader(swuid);
    }


}
