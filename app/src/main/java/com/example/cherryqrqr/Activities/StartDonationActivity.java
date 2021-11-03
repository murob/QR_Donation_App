package com.example.cherryqrqr.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cherryqrqr.R;
import com.example.cherryqrqr.Utils.SharedPreferenceUtils;

public class StartDonationActivity extends AppCompatActivity {

    private TextView dona_contant;
    private EditText dona_account;
    private Button button_dona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_donation);

        dona_contant = (TextView) findViewById(R.id.dona_contant);
        dona_account = (EditText) findViewById(R.id.dona_account);
        button_dona = (Button) findViewById(R.id.button_dona);

        Intent intent = getIntent();
        String a = intent.getStringExtra("1");

        dona_contant.setText(a);

    }
}