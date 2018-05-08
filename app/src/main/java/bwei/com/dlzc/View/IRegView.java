package bwei.com.dlzc.View;

/**
 * Created by admin on 2018/5/8.
 */

public interface IRegView {
    //登录成功的回调
    void showLoginSuccess(String json);
    //登录失败的回调
    void showLoginError(String error);
    //获取输入的手机号
    String getMobile();
    //获取输入的密码
    String getPassword();
}
