package com.example.cherryqrqr.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cherryqrqr.R;
import com.example.cherryqrqr.Retrofit.Entities.JoinDTO;
import com.example.cherryqrqr.Retrofit.RetrofitInterface;
import com.example.cherryqrqr.Utils.AccountWV;
import com.example.cherryqrqr.Utils.PinResetWV;
import com.example.cherryqrqr.Utils.SharedPreferenceUtils;
import com.example.cherryqrqr.Utils.TermOfService;
import com.example.cherryqrqr.Utils.TermsPrivacy;
import com.example.cherryqrqr.Utils.UserNotice;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MySetting extends AppCompatActivity {

    private TextView change_pw, account, caution, question_email, service_txt, question, my_txt;
    private ImageButton change_pw_btn, service_txt_btn, caution_btn, my_txt_btn, account_btn;
    private Button logout_btn, signout_btn;

    private FirebaseAuth mAuth;
    private SharedPreferenceUtils spu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting);

        change_pw = findViewById(R.id.change_pw);
        account = findViewById(R.id.account);
        question_email = findViewById(R.id.question_email);
        service_txt = findViewById(R.id.service_txt);
        question = findViewById(R.id.question);
        my_txt = findViewById(R.id.my_txt);

        change_pw_btn = findViewById(R.id.change_pw_btn);
        service_txt_btn = findViewById(R.id.service_txt_btn);
        caution_btn = findViewById(R.id.caution_btn);
        my_txt_btn = findViewById(R.id.my_txt_btn);
        account_btn = findViewById(R.id.account_btn);

        logout_btn = findViewById(R.id.logout_btn);
        signout_btn = findViewById(R.id.signout_btn);

        spu = new SharedPreferenceUtils(this);

        mAuth = FirebaseAuth.getInstance();

        change_pw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MySetting.this, PinResetWV.class);
                startActivity(intent);
            }
        });

        account_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MySetting.this, AccountWV.class);
                startActivity(intent);
            }
        });

        service_txt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MySetting.this, TermOfService.class);
                startActivity(intent);
            }
        });

        my_txt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MySetting.this, TermsPrivacy.class);
                startActivity(intent);
            }
        });

        caution_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MySetting.this, UserNotice.class);
                startActivity(intent);
            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
                spu.saveString(R.string.login_state, "log_out");
                Intent intent = new Intent(MySetting.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        signout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                mAuth.getCurrentUser().delete();
                spu.saveString(R.string.login_state, "log_out");
                spu.saveString(R.string.sign_state, "no_member");

                // ???????????? ???????????? ??????
                retrofit2.Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(getResources().getString(R.string.local_url)+"api/") // baseUrl ??????
                        .addConverterFactory(GsonConverterFactory.create()) // Gson ????????? ??????
                        .build();

                // ???????????? ??????????????? ?????? ??????
                RetrofitInterface service = retrofit.create(RetrofitInterface.class);

                // ???????????? ????????????
                Call<JoinDTO> withdrawalCall = service.getPost(spu.getString(R.string.idToken,"")); // ?????? ?????? ?????? ??????
                withdrawalCall.enqueue(new Callback<JoinDTO>() {
                    @Override
                    public void onResponse(Call<JoinDTO> call, Response<JoinDTO> response) {
                        Log.e("???", "onResponse");
                        if (response.isSuccessful()) {
                            Log.e("Resonponse", "??????! response.body() : " + response.body());
                            JoinDTO result = response.body();
                            Log.e("result", "result.code : " + result.getCode());

                            spu.saveString(R.string.sign_state, "no_member");
                            spu.saveString(R.string.login_state, "log_out");
                            Log.e("??????", "???????????? sign_state : " + spu.getString(R.string.sign_state, ""));
                            Log.e("??????", "??????????????? sign_state : " + spu.getString(R.string.login_state, ""));

                            Intent intent = new Intent(MySetting.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            //??????
                            Log.e("??????", "???????????? ?????? ????????? onResponse ??????!!");
                            Log.e("??????", "?????????????????? ??????!! sign_state : " + spu.getString(R.string.sign_state, ""));
                        }
                    }
                    @Override
                    public void onFailure(Call<JoinDTO> call, Throwable t) {
                        Log.e("withdrawal", "?????????????????? ????????????!" + t.getMessage());
                    }
                });



                Intent intent = new Intent(MySetting.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}