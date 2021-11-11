package com.example.cherryqrqr.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.cherryqrqr.R;
import com.example.cherryqrqr.Retrofit.Entities.UserCheckDTO;
import com.example.cherryqrqr.Retrofit.RetrofitInterface;
import com.example.cherryqrqr.Utils.SharedPreferenceUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FinshSignDiplayActivity extends AppCompatActivity {

    String R_TAG = "Retrofit";

    private String idToken; // 인증토큰
    private RetrofitInterface service; // 레트로핏 인터페이스 객체

    Button button_next3;
    SharedPreferenceUtils spu;
    String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finsh_sign_diplay);

        spu = new SharedPreferenceUtils(this);
//        spu.saveString(R.string.login_state, "login");

        state = spu.getString(R.string.login_state, "");
        Log.d("state", "Finish_state:"+state);

        button_next3 = findViewById(R.id.button_next3);

//        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
//        mUser.getIdToken(true)
//                .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<GetTokenResult> task) {
//                        if(task.isSuccessful()){
//                            idToken = task.getResult().getToken();
//                            Log.e("TAG", "idToken : " + idToken);
//                            //HTTPS를 통해 백엔드로 토큰전송
//                        } else {
//                            // Handle error -> task.getException();
//                        }
//                    }
//                });
//
//
//        // 레트로핏 인스턴스 생성
//        retrofit2.Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.8.46:18080/api/") // baseUrl 등록
//                .addConverterFactory(GsonConverterFactory.create()) // Gson 변환기 등록
//                .build();
//
//        // 레트로핏 인터페이스 객체 구현
//        service = retrofit.create(RetrofitInterface.class);
//
//
//        Call<UserCheckDTO> call = service.getCode(uid, idToken); // 토큰 값을 통해 검증
//        call.enqueue(new Callback<UserCheckDTO>() {
//            @Override
//            public void onResponse(Call<UserCheckDTO> call, Response<UserCheckDTO> response) {
//                Log.e(R_TAG, "onResponse");
//                if(response.isSuccessful()){ // 계정이 존재할 경우
//                    Log.e(R_TAG, "onResponse success");
//                    UserCheckDTO result = response.body();
//
//                    spu.saveString(R.string.sign_state, "member");
//                    Log.e(R_TAG, "유저존재!! sign_state : " + spu.getString(R.string.sign_state, ""));
//
//                }
//                else{ // 계정이 존재하지 않을 경우
//                    //실패
//                    Log.e(R_TAG, "onResponse fail");
//                    spu.saveString(R.string.sign_state, "no_member");
//                    Log.e(R_TAG, "유저존재하지 않음!! sign_state : " + spu.getString(R.string.sign_state, ""));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserCheckDTO> call, Throwable t) {
//                Log.e(R_TAG, "통신실패");
//            }
//        });

        button_next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinshSignDiplayActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}