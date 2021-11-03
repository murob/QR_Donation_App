package com.example.cherryqrqr.Utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.example.cherryqrqr.R;

public class AccountWV extends AppCompatActivity {

    private WebView webView;
    private ImageButton back_btn;
    private WebSettings wv_settings;
    private String url = "http://192.168.8.43:18080/public/accountScreen";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_wv);

        webView = findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        wv_settings = webView.getSettings();
        wv_settings.setJavaScriptEnabled(true);

        webView.loadUrl(url);

        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
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