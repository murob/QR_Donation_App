package com.example.cherryqrqr.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.cherryqrqr.R;

public class UserNotice extends AppCompatActivity {

    private WebView wv_notice;
    private Button button;
    private WebSettings wv_settings;
    private String url = "http://192.168.8.55:18080/public/terms/userNotice";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_notice);

        wv_notice = (WebView) findViewById(R.id.wv_notice);

        wv_notice.setWebViewClient(new WebViewClient());
        wv_notice.setWebChromeClient(new WebChromeClient());
        wv_settings = wv_notice.getSettings();
        wv_settings.setJavaScriptEnabled(true);

        wv_notice.loadUrl(url);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserNotice.this, SignActivity.class);
                startActivity(intent);
            }
        });
    }
}