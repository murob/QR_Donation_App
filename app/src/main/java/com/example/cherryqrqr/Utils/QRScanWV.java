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
import com.example.cherryqrqr.Activities.LoginActivity;
import com.example.cherryqrqr.Activities.MainActivity;
import com.example.cherryqrqr.Activities.SignActivity;
import com.example.cherryqrqr.R;
import com.example.cherryqrqr.Retrofit.Entities.DonateDTO;
import com.example.cherryqrqr.Retrofit.Entities.JoinDTO;
import com.example.cherryqrqr.Retrofit.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QRScanWV extends AppCompatActivity {

    private WebView qr_scan_wv;
    private WebSettings settings;
    private SharedPreferenceUtils spu;
    String R_TAG = "Retrofit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan_wv);

        qr_scan_wv = findViewById(R.id.qr_scan_wv);
        qr_scan_wv.getSettings().setLoadWithOverviewMode(true);
        qr_scan_wv.getSettings().setUseWideViewPort(true);

        qr_scan_wv.setWebViewClient(new WebViewClient());
        qr_scan_wv.setWebChromeClient(new WebChromeClient());

        spu = new SharedPreferenceUtils(this);



        /////

        // 레트로핏 인스턴스 생성
        retrofit2.Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.local_url) + "api/") // baseUrl 등록
                .addConverterFactory(GsonConverterFactory.create()) // Gson 변환기 등록
                .build();

        // 레트로핏 인터페이스 객체 구현
        RetrofitInterface service = retrofit.create(RetrofitInterface.class);

        Log.e("QRSCanWV", "여기서 spu에 저장된 토큰값 : " + spu.getString(R.string.idToken, ""));

        Call<DonateDTO> DonateCall = service.getCode(spu.getString(R.string.idToken, ""), getIntent().getStringExtra("mrhstld"), getIntent().getStringExtra("setleAmt")); // 토큰 값을 통해 검증
        DonateCall.enqueue(new Callback<DonateDTO>() {
            @Override
            public void onResponse(Call<DonateDTO> call, Response<DonateDTO> response) {
                Log.e(R_TAG, "onResponse");
                if (response.isSuccessful()) {
                    Log.e("donate Response", "donate성공! response.body() : " + response.body());
                    DonateDTO result = response.body();
                    Log.e("result", "result.code : " + result.getCode());

//                    spu.saveString(R.string.sign_state, "member");
//                    spu.saveString(R.string.login_state, "login");
//                    Log.e(R_TAG, "가입상태 sign_state : " + spu.getString(R.string.sign_state, ""));
//                    Log.e(R_TAG, "로그인상태 sign_state : " + spu.getString(R.string.login_state, ""));

                } else {
                    //실패
                    Log.e(R_TAG, "레트로핏 통신 응답값 onResponse, donate실패!!");
                }
            }

            @Override
            public void onFailure(Call<DonateDTO> call, Throwable t) {
                Log.e("donate", "donate실패");
            }
        });

        /////



        qr_scan_wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                if(url.equals(getResources().getString(R.string.local_url)+"public/qrScreen/14")) {
                if (url.contains("deeplinktest")) {
                    Intent intent = new Intent(QRScanWV.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });

        settings = qr_scan_wv.getSettings();
        settings.setJavaScriptEnabled(true);

        Log.e("QRScanWV", "여기는 QRScan액티비티");
        Log.e("상점명", "상점명 : " + getIntent().getStringExtra("mrhstld"));
        Log.e("기부금액", "기부금액 : " + getIntent().getStringExtra("setleAmt"));

        qr_scan_wv.loadUrl(getResources().getString(R.string.local_url) + "public/qrScreen/1");
    }
}