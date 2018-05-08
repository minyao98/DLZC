package bwei.com.dlzc.Http;

/**
 * 获取数据的监听 by ASUS on 2018/4/23.
 */

public interface OKHttpListener {
    //获取数据成功时回调
    void fromJosnSuccess(String json);

    //获取数据失败时回调
    void fromJsonError(String error);
}
