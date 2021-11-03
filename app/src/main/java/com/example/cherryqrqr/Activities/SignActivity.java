package com.example.cherryqrqr.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cherryqrqr.R;
import com.example.cherryqrqr.Utils.TermOfService;
import com.example.cherryqrqr.Utils.TermsPrivacy;
import com.example.cherryqrqr.Utils.UserNotice;
import com.google.android.gms.common.SignInButton;

public class SignActivity extends AppCompatActivity {

    private TextView sign_tv1;
    private CheckBox checkBox_all, checkBox_1, checkBox_2, checkBox_3;
    private Button button_next1;

    private SignInButton signInButton;
    private AppCompatButton button_bogi1, button_bogi2, button_bogi3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        sign_tv1 = findViewById(R.id.sign_tv1);
        checkBox_all = findViewById(R.id.checkBox_all);
        checkBox_1 = findViewById(R.id.checkBox_1);
        checkBox_2 = findViewById(R.id.checkBox_2);
        checkBox_3 = findViewById(R.id.checkBox_3);

        signInButton = findViewById(R.id.signInButton);
        button_bogi1 = findViewById(R.id.button_bogi1);
        button_bogi2 = findViewById(R.id.button_bogi2);
        button_bogi3 = findViewById(R.id.button_bogi3);
        button_next1 = findViewById(R.id.button_next1);

        checkBox_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox_all.isChecked()){
                    checkBox_1.setChecked(true);
                    checkBox_2.setChecked(true);
                    checkBox_3.setChecked(true);
                } else if(!checkBox_all.isChecked()){
                    checkBox_1.setChecked(false);
                    checkBox_2.setChecked(false);
                    checkBox_3.setChecked(false);
                }
            }
        });

        checkBox_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox_all.isChecked()){
                    checkBox_all.setChecked(false);
                } else if(checkBox_1.isChecked() && checkBox_2.isChecked() && checkBox_3.isChecked()){
                    checkBox_all.setChecked(true);
                }
            }
        });

        checkBox_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox_all.isChecked()){
                    checkBox_all.setChecked(false);
                } else if(checkBox_1.isChecked() && checkBox_2.isChecked() && checkBox_3.isChecked()){
                    checkBox_all.setChecked(true);
                }
            }
        });

        checkBox_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox_all.isChecked()){
                    checkBox_all.setChecked(false);
                } else if(checkBox_1.isChecked() && checkBox_2.isChecked() && checkBox_3.isChecked()){
                    checkBox_all.setChecked(true);
                }
            }
        });

        button_bogi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignActivity.this, TermOfService.class);
                startActivity(intent);
            }
        });

        button_bogi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignActivity.this, TermsPrivacy.class);
                startActivity(intent);
            }
        });

        button_bogi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignActivity.this, UserNotice.class);
                startActivity(intent);
            }
        });

        button_next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox_all.isChecked()){
                    Intent intent = new Intent(SignActivity.this, IdentifyActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "약관 동의 후 진행해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}