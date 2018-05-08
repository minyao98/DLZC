package bwei.com.dlzc.Presenter;

import java.util.HashMap;
import java.util.Map;

import bwei.com.dlzc.Http.HttpConfig;
import bwei.com.dlzc.Model.ILoginModel;
import bwei.com.dlzc.Model.ILoginlisttnter;
import bwei.com.dlzc.Model.IRegModel;
import bwei.com.dlzc.Model.IReglisttnter;
import bwei.com.dlzc.View.ILoginView;
import bwei.com.dlzc.View.IRegView;

/**
 * Created by admin on 2018/5/8.
 */

public class PresenterImpl implements IPresenter{

    @Override
    public void getLoginJson(ILoginModel iLoginModel, final ILoginView iLoginView) {
        //创建Map集合
        Map<String, String> map = new HashMap<>();
        map.put("mobile", iLoginView.getMobile());
        map.put("password", iLoginView.getPassword());
        iLoginModel.showLogin(HttpConfig.login_url, map, new ILoginlisttnter() {
            @Override
            public void showLoginJsonSuccess(String json) {
                //登录成功
                iLoginView.showLoginSuccess();
            }

            @Override
            public void showLoginJsonError(String error) {
                iLoginView.showLoginError(error);//登录失败
            }
        });
    }

    @Override
    public void getRegJson(IRegModel iRegModel, final IRegView iRegView) {
        //创建Map集合
        Map<String, String> map = new HashMap<>();
        map.put("mobile", iRegView.getMobile());
        map.put("password", iRegView.getPassword());
        iRegModel.showReg(HttpConfig.login_url, map, new IReglisttnter() {

            @Override
            public void showRegJsonSuccess(String json) {
                iRegView.showLoginSuccess(json);
            }

            @Override
            public void showRegJsonError(String error) {
                iRegView.showLoginError(error);
            }
        });
    }
}
