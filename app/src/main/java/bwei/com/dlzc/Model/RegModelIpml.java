package bwei.com.dlzc.Model;

import com.google.gson.Gson;

import java.util.Map;

import bwei.com.dlzc.Http.HttpUtils;
import bwei.com.dlzc.Http.OKHttpListener;
import bwei.com.dlzc.Model.BeanUtils.LoginBean;

/**
 * Created by admin on 2018/5/8.
 */

public class RegModelIpml implements IRegModel{

    @Override
    public void showReg(String url, Map<String, String> map, final IReglisttnter iReglisttnter) {
        //在Model层进行网络请求
        HttpUtils httputils = HttpUtils.getHttputils();
        //使用POST方式进行请求
        httputils.postOkHttp(url, map);
        httputils.setFromHttpListener(new OKHttpListener() {
            @Override
            public void fromJosnSuccess(String json) {
                //请求成功
                //进行解析
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(json, LoginBean.class);
                String code = loginBean.getCode();
                if (code.equals("0")) {//判断当code等于0时为登录成功 否则为登录失败
                    iReglisttnter.showRegJsonSuccess(loginBean.getMsg());
                }else {
                    //请求失败  code不为0
                    iReglisttnter.showRegJsonError(loginBean.getMsg());
                }
            }

            @Override
            public void fromJsonError(String error) {
                //请求失败
                iReglisttnter.showRegJsonError(error);
            }
        });
    }
}
