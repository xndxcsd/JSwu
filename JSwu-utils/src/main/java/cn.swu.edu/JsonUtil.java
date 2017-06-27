package cn.swu.edu;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by 西南大学开源协会 陈思定  on 2017/6/27.
 * <p>
 * Email : sidingchan@gmail.com
 */
public class JsonUtil {

    private JsonUtil(){};
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Boolean isJsonFormat(String json) {
        try {
            objectMapper.readTree(json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
