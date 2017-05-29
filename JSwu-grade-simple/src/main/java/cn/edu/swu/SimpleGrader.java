package cn.edu.swu;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static cn.edu.swu.common.Constant.*;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/21.
 * <p>
 * Email : chensiding@qq.com
 */

public class SimpleGrader implements Grader,ConnectionInject {

    private String swuid;
    private String password;
    private SwuConnection swuConnection;

    public SimpleGrader(String swuid, String password) {
        this.swuid = swuid;
        this.password = password;
        // get connection from ConnectionInject
        this.swuConnection = getSwuConnection(swuid);
        this.swuConnection.getAccessOfJW();
    }

    public String getJsonGrade() {

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
        nameValuePair.add(new BasicNameValuePair("_search", "false"));
        nameValuePair.add(new BasicNameValuePair("nd", "1451922678091"));
        nameValuePair.add(new BasicNameValuePair("queryModel.currentPage", "1"));
        nameValuePair.add(new BasicNameValuePair("queryModel.showCount", "150"));
        nameValuePair.add(new BasicNameValuePair("queryModel.sortName", ""));
        nameValuePair.add(new BasicNameValuePair("queryModel.sortOrder", "asc"));
        nameValuePair.add(new BasicNameValuePair("time", "1"));
        nameValuePair.add(new BasicNameValuePair("xnm", ""));
        nameValuePair.add(new BasicNameValuePair("xqm", ""));


        String response = null;
        try {
            response = this.swuConnection.post(URL_GRADE+this.swuid, nameValuePair);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


}
