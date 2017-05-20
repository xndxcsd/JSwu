package cn.edu.swu;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/27.
 * <p>
 * Email : chensiding@qq.com
 */
public interface ConnectionInject {

    default SwuConnection getSwuConnection(String swuid) {
        return DefaultSwuConnectionManager.getInstance().getConnection(swuid);
    }

}
