package com.example.cherryqrqr.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.cherryqrqr.R;

public class IdentifyWV extends AppCompatActivity {

    WebView identify_wv;
    WebSettings settings;
    String url = "http://192.168.8.55:18080/public/joinScreen/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_wv1);

        identify_wv = findViewById(R.id.identify_wv);
        identify_wv.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(url.equals("http://192.168.8.55:18080/public/joinScreen/5")) {
                    Intent intent = new Intent(IdentifyWV.this, FinshSignDiplayActivity.class);
                    startActivity(intent);
                }
            }
        });
        identify_wv.setWebChromeClient(new WebChromeClient());

        settings = identify_wv.getSettings();
        settings.setJavaScriptEnabled(true);

        identify_wv.loadUrl(url);
    }


}