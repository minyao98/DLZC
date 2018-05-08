package bwei.com.dlzc.Model;

/**
 * Created by admin on 2018/5/8.
 */

public interface IReglisttnter {
    //获取数据成功
    void showRegJsonSuccess(String json);
    //获取数据失败
    void showRegJsonError(String error);
}
