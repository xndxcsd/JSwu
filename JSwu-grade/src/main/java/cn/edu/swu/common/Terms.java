package cn.edu.swu.common;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/6/4.
 * <p>
 * Email : chensiding@qq.com
 */
public class Terms {
    public static final String ONE = "3";
    public static final String TWO = "12";
    public static final String THREE = "16";

    public synchronized static String getTerm(Integer term){

        if (term == null) return "";
        switch (term) {
            case 1:
                return ONE;
            case 2:
                return TWO;
            case 3:
                return THREE;
            default:
                return "";
        }
    }

}
