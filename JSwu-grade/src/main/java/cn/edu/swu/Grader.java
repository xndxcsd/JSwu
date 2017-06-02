package cn.edu.swu;

import java.util.List;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/21.
 * <p>
 * Email : chensiding@qq.com
 */
public interface Grader {

    String getJsonGrades();

    String getJsonGrades(int year);

    String getJsonGrades(int year, int term);

    List<Grade> getGrades();

    List<Grade> getGrades(int year);

    List<Grade> getGrades(int year, int term);

}
