package cn.edu.swu;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/6/4.
 * <p>
 * Email : chensiding@qq.com
 */
public class Years {

    public synchronized static String getYear(Integer year) {

        // 教务系统只能查询2009年后的成绩，当传入<2009的数字时，返回查询所有学年成绩的""
        if (year == null || year < 2009) return "";
        return String.valueOf(year);
    }
}
