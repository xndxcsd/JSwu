package cn.edu.swu;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static cn.edu.swu.Constant.URL_GRADE;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/21.
 * <p>
 * Email : chensiding@qq.com
 */

public class SimpleGrader implements Grader, ConnectionInject {

    private String swuid;
    private SwuConnection swuConnection;

    public SimpleGrader(String swuid) throws IOException {
        this.swuid = swuid;
        // 接口注入连接
        this.swuConnection = getSwuConnection(swuid);
        if(!this.swuConnection.isOpen())
            this.swuConnection.getAccessOfJW();
    }

    /**
     * 查询已获得的所有课程成绩
     * @return Json格式的成绩列表
     */
    public String getJsonGrades() {
        return getJsonGrades(null, null);
    }

    /**
     * 查询某学年所有成绩，当传入null时，查询所有成绩。当传入非法学年时，等同于传入null。
     * @param year 学年
     * @return Json格式的成绩列表
     */
    public String getJsonGrades(Integer year) {
        return getJsonGrades(year, null);
    }

    /**
     * 传入学年和学期查询成绩。
     * <p>
     *     当参数为null时，查询所有合法值。如查询2017年1、2、3学期的成绩，可作如下调用
     * </p>
     * <p>
     * <code>
     *     this.getJsonGrades(2017,null);
     *
     *
     * </code>
     * </p>
     * 另外，如下
     * <p>
     * <code>
     *     this.getJsonGrades(null,2);
     * </code>
     * </p>
     * 查询所有学年的第二学期成绩的调用方式是不合法的，应选取其他方式实现。
     * 当传入非法参数时，等同于传入null
     * @param  year 第一个参数-学年
     * @param  term 第二个参数-学期
     * @return json格式的成绩列表
     */
    public String getJsonGrades(Integer year, Integer term) {
        // TODO check parameter: throw IllegalArgumentException
        if (!swuConnection.isOpen()) {
            return "";
        }
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
        nameValuePair.add(new BasicNameValuePair("_search", "false"));
        nameValuePair.add(new BasicNameValuePair("nd", "1451922678091"));
        nameValuePair.add(new BasicNameValuePair("queryModel.currentPage", "1"));
        nameValuePair.add(new BasicNameValuePair("queryModel.showCount", "150"));
        nameValuePair.add(new BasicNameValuePair("queryModel.sortName", ""));
        nameValuePair.add(new BasicNameValuePair("queryModel.sortOrder", "asc"));
        nameValuePair.add(new BasicNameValuePair("time", "0"));
        nameValuePair.add(new BasicNameValuePair("xnm", Years.getYear(year)));
        nameValuePair.add(new BasicNameValuePair("xqm", Terms.getTerm(term)));


        String response = null;
        try {
            response = this.swuConnection.post(URL_GRADE + this.swuid, nameValuePair);
        } catch (IOException e) {
            return e.getMessage();
        }
        return response;
    }


}
