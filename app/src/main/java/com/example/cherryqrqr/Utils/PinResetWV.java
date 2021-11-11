package com.example.cherryqrqr.Utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.cherryqrqr.R;

public class PinResetWV extends AppCompatActivity {

    private WebView reset_wv;
    private WebSettings wv_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_reset_wv);

        reset_wv = findViewById(R.id.reset_wv);
        reset_wv.getSettings().setLoadWithOverviewMode(true);
        reset_wv.getSettings().setUseWideViewPort(true);

        reset_wv.setWebViewClient(new WebViewClient());
        reset_wv.setWebChromeClient(new WebChromeClient());
        wv_settings = reset_wv.getSettings();
        wv_settings.setJavaScriptEnabled(true);

        reset_wv.loadUrl(getResources().getString(R.string.local_url)+"public/pinResetScreen/1");
    }
}