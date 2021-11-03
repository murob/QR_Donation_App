package com.example.cherryqrqr.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cherryqrqr.R;
import com.example.cherryqrqr.Utils.AccountWV;
import com.example.cherryqrqr.Utils.TermOfService;
import com.example.cherryqrqr.Utils.TermsPrivacy;
import com.example.cherryqrqr.Utils.UserNotice;

public class MySetting extends AppCompatActivity {

    private TextView change_pw, account, caution, question_email, service_txt, question, my_txt;
    private ImageButton change_pw_btn, service_txt_btn, caution_btn, my_txt_btn, account_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting);

        change_pw = findViewById(R.id.change_pw);
        account = findViewById(R.id.account);
        question_email = findViewById(R.id.question_email);
        service_txt = findViewById(R.id.service_txt);
        question = findViewById(R.id.question);
        my_txt = findViewById(R.id.my_txt);

        change_pw_btn = findViewById(R.id.change_pw_btn);
        service_txt_btn = findViewById(R.id.service_txt_btn);
        caution_btn = findViewById(R.id.caution_btn);
        my_txt_btn = findViewById(R.id.my_txt_btn);
        account_btn = findViewById(R.id.account_btn);

        account_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MySetting.this, AccountWV.class);
                startActivity(intent);
            }
        });

        service_txt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MySetting.this, TermOfService.class);
                startActivity(intent);
            }
        });

        my_txt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MySetting.this, TermsPrivacy.class);
                startActivity(intent);
            }
        });

        caution_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MySetting.this, UserNotice.class);
                startActivity(intent);
            }
        });
    }
}