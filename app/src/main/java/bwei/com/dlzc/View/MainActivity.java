package bwei.com.dlzc.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bwei.com.dlzc.Model.LoginModelIpml;
import bwei.com.dlzc.Model.RegModelIpml;
import bwei.com.dlzc.Presenter.PresenterImpl;
import bwei.com.dlzc.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener ,ILoginView{

    private EditText login_mobile;
    private EditText login_password;
    private Button login;
    private Button reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        login_mobile = (EditText) findViewById(R.id.login_mobile);
        login_password = (EditText) findViewById(R.id.login_password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        reg = (Button) findViewById(R.id.reg);
        reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                PresenterImpl presenter = new PresenterImpl();
                presenter.getLoginJson(new LoginModelIpml(),MainActivity.this);
                break;
            case R.id.reg:
                Intent intent = new Intent(MainActivity.this, RegActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void showLoginSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,Title.class);
        startActivity(intent);
    }

    @Override
    public void showLoginError(String error) {
        Toast.makeText(this, "登录失败："+error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getMobile() {
        return login_mobile.getText().toString();
    }

    @Override
    public String getPassword() {
        return login_password.getText().toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
