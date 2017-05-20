package cn.edu.swu;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/21.
 * <p>
 * Email : chensiding@qq.com
 */
public class SimpleGrader implements Grader,ConnectionInject {

    private String swuid;
    private String password;
    private SwuConnection swuConnection;
    public static final String urlGradeSearch = "http://jw.swu.edu.cn/jwglxt/cjcx/cjcx_cxDgXscj.html?" +
            "doType=query&gnmkdmKey=N305005&sessionUserKey=";
    public SimpleGrader(String swuid, String password) {
        this.swuid = swuid;
        this.password = password;
        this.swuConnection = getSwuConnection(swuid);
    }

    public String getJsonGrade() {
//        this.swuConnection.post();

        //这里应该有bug
        List<BasicNameValuePair> nameValuePair = new ArrayList<BasicNameValuePair>();

        nameValuePair.add(new BasicNameValuePair("_search", "false"));
        nameValuePair.add(new BasicNameValuePair("nd", "1451922678091"));
        nameValuePair.add(new BasicNameValuePair("queryModel.currentPage", "1"));
        nameValuePair.add(new BasicNameValuePair("queryModel.showCount", "150"));
        nameValuePair.add(new BasicNameValuePair("queryModel.sortName", ""));
        nameValuePair.add(new BasicNameValuePair("queryModel.sortOrder", "asc"));
        nameValuePair.add(new BasicNameValuePair("time", "1"));
        nameValuePair.add(new BasicNameValuePair("xnm", ""));

        //提交的表单中， 学期的对应关系是这样的，3->1 12->2 16->3 所以需要转换一下
        nameValuePair.add(new BasicNameValuePair("xqm", ""));

//        this.swuConnection.post(urlGradeSearch+this.swuid, nameValuePair);

        return "TODO";
    }


}
