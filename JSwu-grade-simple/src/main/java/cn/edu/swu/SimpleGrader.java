package cn.edu.swu;

import cn.edu.swu.common.Terms;
import cn.edu.swu.common.Years;
import cn.swu.edu.JsonUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static cn.edu.swu.common.Constant.URL_GRADE;

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
        this.swuConnection.getAccessOfJW();
    }

    public String getJsonGrades() {
        return getJsonGrades(null, null);
    }

    public String getJsonGrades(Integer year) {
        return getJsonGrades(year, null);
    }

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
