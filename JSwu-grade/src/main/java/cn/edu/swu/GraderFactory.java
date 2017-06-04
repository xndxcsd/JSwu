package cn.edu.swu;

import java.io.IOException;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/21.
 * <p>
 * Email : chensiding@qq.com
 */
public interface GraderFactory {
    public Grader getGrader() throws IOException;

}
