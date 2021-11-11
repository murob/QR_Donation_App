package com.example.cherryqrqr.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.cherryqrqr.R;
import com.example.cherryqrqr.Utils.SharedPreferenceUtils;

public class SplashActivity extends AppCompatActivity {

    SharedPreferenceUtils spu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        spu = new SharedPreferenceUtils(this);
        spu.saveString(R.string.login_state, "log_out");
//        spu.saveString(R.string.sign_state, "no_member");

        String state = spu.getString(R.string.login_state,""); // 로그인 상태 확인을 위한 변수지정
        Log.d("state", "state:"+state);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                openMainActivity();
            }
        }, 3000);
    }

    private void openMainActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}