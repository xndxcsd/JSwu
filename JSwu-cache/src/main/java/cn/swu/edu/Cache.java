package cn.swu.edu;

import javafx.beans.binding.ObjectExpression;

import java.lang.reflect.Method;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/21.
 * <p>
 * Email : chensiding@qq.com
 */
public interface Cache {

    public String getCache(Method method);

    public void putCache(Method method, Object object);

    public void clear(Method method);

    public void clearAll();





}
