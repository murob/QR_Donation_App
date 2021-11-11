package com.example.cherryqrqr.Utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.cherryqrqr.R;

public class QRScanWV extends AppCompatActivity {

    private WebView qr_scan_wv;
    private WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan_wv);

        qr_scan_wv = findViewById(R.id.qr_scan_wv);
        qr_scan_wv.getSettings().setLoadWithOverviewMode(true);
        qr_scan_wv.getSettings().setUseWideViewPort(true);

        qr_scan_wv.setWebViewClient(new WebViewClient());
        qr_scan_wv.setWebChromeClient(new WebChromeClient());

        settings = qr_scan_wv.getSettings();
        settings.setJavaScriptEnabled(true);

        qr_scan_wv.loadUrl(getResources().getString(R.string.local_url)+"public/qrScreen/1");
    }
}