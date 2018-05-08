package bwei.com.dlzc.Presenter;

import bwei.com.dlzc.Model.ILoginModel;
import bwei.com.dlzc.Model.IRegModel;
import bwei.com.dlzc.View.ILoginView;
import bwei.com.dlzc.View.IRegView;

/**
 * Created by admin on 2018/5/8.
 */

public interface IPresenter {
    //登录的回调方法
    void getLoginJson(ILoginModel iLoginModel,ILoginView iLoginView);
    //注册的回调方法
    void getRegJson(IRegModel iRegModel, IRegView iRegView);
}
