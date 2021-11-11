package com.example.cherryqrqr.Utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.cherryqrqr.Activities.FinshSignDiplayActivity;
import com.example.cherryqrqr.R;

public class IdentifyWV extends AppCompatActivity {

    WebView identify_wv;
    WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_wv1);

        identify_wv = findViewById(R.id.identify_wv);
        identify_wv.getSettings().setLoadWithOverviewMode(true);
        identify_wv.getSettings().setUseWideViewPort(true);

        identify_wv.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(url.equals(getResources().getString(R.string.local_url)+"public/joinScreen/5")) {
                    Intent intent = new Intent(IdentifyWV.this, FinshSignDiplayActivity.class);
                    startActivity(intent);
                }

            }
        });
        Log.d("test", "여기로 찍히나");

        identify_wv.setWebChromeClient(new WebChromeClient());

        settings = identify_wv.getSettings();
        settings.setJavaScriptEnabled(true);

        identify_wv.loadUrl(getResources().getString(R.string.local_url)+"public/joinScreen/1");
    }


}