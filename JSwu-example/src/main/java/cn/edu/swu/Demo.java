package cn.edu.swu;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/21.
 * <p>
 * Email : chensiding@qq.com
 */
public class Demo {

    public static void main(String[] args) {
        SwuConfig swuConfig = new SwuConfig.Builder("your_swuid", "your_password")
                .build();
        SwuManager swuManager = new SwuManager(swuConfig);

        Grader grader = swuManager.getGrader();
        System.out.println(grader.getJsonGrades());
    }
}
