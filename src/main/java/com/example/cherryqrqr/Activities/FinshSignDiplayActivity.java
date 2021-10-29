package com.example.cherryqrqr.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cherryqrqr.R;

public class FinshSignDiplayActivity extends AppCompatActivity {

    Button button_next3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finsh_sign_diplay);

        button_next3 = findViewById(R.id.button_next3);

        button_next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinshSignDiplayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}