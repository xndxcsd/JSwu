package cn.edu.swu;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static cn.edu.swu.Constant.URL_SCHEDULE;

/**
 * Created by 西南大学开源协会 陈思定  on 2017/9/19.
 * <p>
 * Email : sidingchan@gmail.com
 */
public class SimpleScheduler implements Scheduler,ConnectionInject {

    private String swuid;
    private SwuConnection swuConnection;

    public SimpleScheduler(String swuid) throws IOException {
        this.swuid = swuid;
        // 接口注入连接
        this.swuConnection = getSwuConnection(swuid);
        if(!this.swuConnection.isOpen())
            this.swuConnection.getAccessOfJW();
    }

    /**
     * 传入学年和学期查询课表。
     * <p>
     *     如查询2016年1学期的课表，可作如下调用
     * </p>
     * <p>
     * <code>
     *     this.getJsonSchedule(2016,1);
     * </code>
     * </p>
     * 当传入参数没有意义时，如
     * <code>this.getJsonSchedule(1024,1024)</code>，函数仍能正常返回，但返回值中没有有效数据，
     * 故函数调用者应确保传入参数的正确性。
     *
     * @param  year 第一个参数-学年
     * @param  term 第二个参数-学期
     * @return json格式的课表列表
     */
    @Override
    public String getJsonSchedule(Integer year, Integer term) {
        if (!swuConnection.isOpen()) {
            return "";
        }
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
        nameValuePair.add(new BasicNameValuePair("xnm", Years.getYear(year)));
        nameValuePair.add(new BasicNameValuePair("xqm", Terms.getTerm(term)));

        String response = null;
        try {
            response = this.swuConnection.post(URL_SCHEDULE + this.swuid, nameValuePair);
        } catch (IOException e) {
            return e.getMessage();
        }
        //TODO 进一步处理该Json数据，加工成更清晰的Json格式
        return response;
    }
}
