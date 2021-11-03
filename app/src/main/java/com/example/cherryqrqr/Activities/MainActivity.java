package com.example.cherryqrqr.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cherryqrqr.R;
import com.example.cherryqrqr.Utils.QRScanWV;
import com.example.cherryqrqr.Utils.SharedPreferenceUtils;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private ImageView qr_scan;
    private ImageButton dona_list;
    private ImageView pref;

    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qr_scan = findViewById(R.id.qr_scan);
        dona_list = findViewById(R.id.dona_list);
        pref = findViewById(R.id.pref);

        qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrScan = new IntentIntegrator(MainActivity.this);
                qrScan.setOrientationLocked(false);
                qrScan.initiateScan();
            }
        });

        pref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MySetting.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

            //intent에 값을 저장하는 처리방식
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        Intent intent = new Intent(MainActivity.this, StartDonationActivity.class);
//        if(result != null){
//            if(result.getContents() == null){ //qr코드가 없으면
//                Toast.makeText(MainActivity.this, "취소!", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(MainActivity.this, "스캔완료", Toast.LENGTH_SHORT).show(); //qr코드 결과가 있으면
//                try {
//                    JSONObject obj = new JSONObject(result.getContents()); //data를 json으로 변환
//                    Log.d("obj", "json:"+obj.getString("1"));
//
//                    intent.putExtra("1", obj.getString("1"));
//                    startActivity(intent);
//                } catch (JSONException e){
//                    e.printStackTrace();
//                }
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }


        //WebView를 사용한 처리방식
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        Intent intent = new Intent(MainActivity.this, QRScanWV.class);
        if(result != null){
            if(result.getContents() == null){ //qr코드가 없으면
                Toast.makeText(MainActivity.this, "취소!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "스캔완료", Toast.LENGTH_SHORT).show(); //qr코드 결과가 있으면
                try {
                    JSONObject obj = new JSONObject(result.getContents()); //data를 json으로 변환
                    startActivity(intent);
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}