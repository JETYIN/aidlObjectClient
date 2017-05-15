package com.work.client.aidlandroidclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.work.service.aidlandroidservice.ILoginAidl;
import com.work.service.aidlandroidservice.User;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText userNameET, userPassET, num1ET, num2ET;
    private Button button;

    private String action = "com.remoteAidlService.tag";

    private ILoginAidl myAidl;
    private boolean isBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //oncreate就绑定远程服务
        bindAidlService();
    }

    /**
     * 获取服务端连接对象
     **/
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//此处获取到binder驱动对象，需要将其转化为java
            myAidl = ILoginAidl.Stub.asInterface(service);
            isBinder = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    //绑定服务
    private void bindAidlService() {
        Intent intent = new Intent();
        intent.setAction(action);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private void initView() {
        userNameET = (EditText) findViewById(R.id.userName_et);

        userPassET = (EditText) findViewById(R.id.userPass_et);
        num1ET = (EditText) findViewById(R.id.num1_et);
        num2ET = (EditText) findViewById(R.id.num2_et);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    private String getET(EditText et) {
        return et.getText().toString();
    }

    @Override
    public void onClick(View v) {
        //点击按钮绑定服务
        try {
            //调用add方法
            int result = myAidl.add(10, 40);
            showToast(String.valueOf(result));

            //调用登录方法
            boolean isLogin=myAidl.isLogin(new User(getET(userNameET),getET(userPassET)));
            showToast(String.valueOf(isLogin));
            change();
        } catch (Exception e) {
        }

    }

    private void change(){
        if(new User("1","1").equals(new User("1","1"))){
            showToast("equal");
        }else{
            showToast("noequal");
        }
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBinder) {
            unbindService(connection);
        }
    }
}
