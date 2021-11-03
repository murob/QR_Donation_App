package com.example.cherryqrqr.Utils;

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

public class TermOfService extends AppCompatActivity {

    private WebView wv_service;
    private Button button;
    private WebSettings wv_settings;
    private String url = "http://192.168.8.43:18080/public/terms/termsOfService";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_of_service);

        wv_service = (WebView) findViewById(R.id.wv_service);

        wv_service.setWebViewClient(new WebViewClient());
        wv_service.setWebChromeClient(new WebChromeClient());
        wv_settings = wv_service.getSettings();
        wv_settings.setJavaScriptEnabled(true);


        wv_service.loadUrl(url);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}