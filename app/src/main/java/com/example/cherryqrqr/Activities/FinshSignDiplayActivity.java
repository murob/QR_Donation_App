package com.example.cherryqrqr.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.cherryqrqr.R;
import com.example.cherryqrqr.Utils.SharedPreferenceUtils;

public class FinshSignDiplayActivity extends AppCompatActivity {

    Button button_next3;
    SharedPreferenceUtils spu;
    String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finsh_sign_diplay);

        spu = new SharedPreferenceUtils(this);
        spu.saveString(R.string.user_id, "login");
        state = spu.getString(R.string.user_id, "");
        Log.d("state", "Finish_state:"+state);

        button_next3 = findViewById(R.id.button_next3);

        button_next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinshSignDiplayActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}