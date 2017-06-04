package cn.swu.edu;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/21.
 * <p>
 * Email : chensiding@qq.com
 */
public interface Cache {

    public Object getCache(Integer hash);

    public void putCache(Integer hash, Object object);

    public void remove(Integer hash);

    public void removeAll();


}
