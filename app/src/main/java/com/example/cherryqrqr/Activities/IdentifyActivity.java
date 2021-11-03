package com.example.cherryqrqr.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cherryqrqr.R;
import com.example.cherryqrqr.Utils.IdentifyWV;

public class IdentifyActivity extends AppCompatActivity {

    Button button_next2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);

        button_next2 = findViewById(R.id.button_next2);

        button_next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IdentifyActivity.this, IdentifyWV.class);
                startActivity(intent);
            }
        });
    }
}