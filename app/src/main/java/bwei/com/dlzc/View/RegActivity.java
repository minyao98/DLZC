package bwei.com.dlzc.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bwei.com.dlzc.Model.RegModelIpml;
import bwei.com.dlzc.Presenter.PresenterImpl;
import bwei.com.dlzc.R;

public class RegActivity extends AppCompatActivity implements View.OnClickListener,IRegView {

    private EditText login_mobile;
    private EditText login_password;
    private Button mButReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();

    }

    private void initView() {
        login_mobile = (EditText) findViewById(R.id.login_mobile);
        login_password = (EditText) findViewById(R.id.login_password);
        mButReg = (Button) findViewById(R.id.reg_but);
        mButReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_but:
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                PresenterImpl presenter = new PresenterImpl();
                presenter.getRegJson(new RegModelIpml(),this);
                break;
            default:
                break;
        }
    }

    @Override
    public void showLoginSuccess(String json) {
        Toast.makeText(this, "注册成功,请前往登录", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginError(String error) {
        Toast.makeText(this, "注册失败"+error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getMobile() {
        return login_mobile.getText().toString();
    }

    @Override
    public String getPassword() {
        return login_password.getText().toString();
    }
}
