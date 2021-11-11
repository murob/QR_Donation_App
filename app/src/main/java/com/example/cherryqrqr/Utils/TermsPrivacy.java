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

public class TermsPrivacy extends AppCompatActivity {

    private WebView wv_privacy;
    private WebSettings wv_settings;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_privacy);

        wv_privacy = (WebView) findViewById(R.id.wv_privacy);

        wv_privacy.setWebViewClient(new WebViewClient());
        wv_privacy.setWebChromeClient(new WebChromeClient());
        wv_settings = wv_privacy.getSettings();
        wv_settings.setJavaScriptEnabled(true);

        wv_privacy.loadUrl(getResources().getString(R.string.local_url)+"public/terms/privacyPolicy");

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