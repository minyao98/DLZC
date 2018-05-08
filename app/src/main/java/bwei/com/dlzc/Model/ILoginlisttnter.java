package bwei.com.dlzc.Model;

/**
 * Created by admin on 2018/5/8.
 */

public interface ILoginlisttnter {
    //获取数据成功
    void showLoginJsonSuccess(String json);
    //获取数据失败
    void showLoginJsonError(String error);
}
