package bwei.com.dlzc.Http;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络请求工具类 by ASUS on 2018/4/23.
 */

public class HttpUtils {
    //单例模式
    private static HttpUtils httpUtils = null;
    private MyHandler myHandler = new MyHandler();
    //请求成功
    public static final int SUCCESS = 0;
    //请求失败
    public static final int ERROR = 1;
    private OKHttpListener fromHttpListener;

    public static HttpUtils getHttputils() {
        if (httpUtils == null) {
            httpUtils = new HttpUtils();
        }
        return httpUtils;
    }

    //get请求方式
    public void getOkHttp(String url) {
        //获取ok实例
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            //请求失败
            @Override
            public void onFailure(Call call, IOException e) {
                //得到Message
                Message message = myHandler.obtainMessage();
                message.what = ERROR;
                message.obj = e.getMessage();//请求失败
                //向Handler发送消息
                myHandler.handleMessage(message);
            }

            //请求成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                //得到Message
                Message message = myHandler.obtainMessage();
                message.what = SUCCESS;
                message.obj = json;//请求成功
                //向Handler发送消息
                myHandler.handleMessage(message);
            }
        });
    }

    //post请求方式
    public void postOkHttp(String url, Map<String, String> map) {
        //获取ok实例
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        //创建请求体
        FormBody.Builder builder = new FormBody.Builder();
        //得到map集合中的key的集合
        Set<String> keySet = map.keySet();
        //便利集合
        for (String key : keySet
                ) {
            //根据key找到值
            String value = map.get(key);
            builder.add(key,value);
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            //请求失败
            @Override
            public void onFailure(Call call, IOException e) {
                //得到Message
                Message message = myHandler.obtainMessage();
                message.what = ERROR;
                message.obj = e.getMessage();//请求失败
                //向Handler发送消息
                myHandler.sendMessage(message);
            }

            //请求成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                //得到Message
                Message message = myHandler.obtainMessage();
                message.what = SUCCESS;
                message.obj = json;//请求成功
                //向Handler发送消息
                myHandler.sendMessage(message);
            }
        });
    }

    //创建Handler向主线程发送json字符串
    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            //监听是否获取数据成功或者失败
            switch (msg.what) {
                case SUCCESS:
                    //获取成功
                    String json = (String) msg.obj;
                    fromHttpListener.fromJosnSuccess(json);
                    break;
                case ERROR:
                    //获取失败
                    String error = (String) msg.obj;
                    fromHttpListener.fromJsonError(error);
                    break;
            }
        }
    }

    //向外提供获取数据的监听 向外发送数据
    public void setFromHttpListener(OKHttpListener fromHttpListener) {
        this.fromHttpListener = fromHttpListener;
    }
}
