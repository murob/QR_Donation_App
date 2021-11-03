package com.example.cherryqrqr.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cherryqrqr.Constants.Constants;
import com.example.cherryqrqr.R;
import com.example.cherryqrqr.Utils.SharedPreferenceUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView text1;
    private TextView text2;
    private SignInButton signInButton;

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private final int RC_SIGN_IN = 333;

    private SharedPreferenceUtils spu;
    private Context mContext;
    private String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // layout_id
        imageView = findViewById(R.id.imageView);//로고
        text1 = findViewById(R.id.text1);//글1
        text2 = findViewById(R.id.text2);//글2
        signInButton = findViewById(R.id.signInButton);//구글로그인 버튼

        spu = new SharedPreferenceUtils(this);
        state = spu.getString(R.string.user_id,"");

        Log.d("state", "state:"+state);

        // 인증 데이터
        mAuth = FirebaseAuth.getInstance();

        // 클라이언트에서 넣을 구글 로그인 옵션 설정
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.user_token))
                .requestEmail()
                .build();

        // 클라이언트 생성
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // 클릭 확인
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(state.equals("logout")){
                    Intent googleSignInIntent = mGoogleSignInClient.getSignInIntent();
                    startActivityForResult(googleSignInIntent, RC_SIGN_IN);
                } else if(state.equals("login")){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // 로그인 성공 확인
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(Constants.LOG_STRING, "google get id : " + account.getId());
                Log.d(Constants.LOG_STRING, "google get email : " + account.getEmail());
                Log.e("try", "여기로 들어오나?");

                firebaseAuthWithGoogle(account.getIdToken());

            } catch (ApiException e) {
                Log.e("catch", "여기 들어오나?");
            }
            Log.e("MY_LOG", "Login callback 호출됨");
//            Intent intent = new Intent(LoginActivity.this, SignActivity.class);
//            startActivity(intent);
        }
    }


    // 파이어베이스로 값을 넘겨줌
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(Constants.LOG_STRING, "signInWithCredential:success");
                        }
                        FirebaseUser user = mAuth.getCurrentUser();
                        user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                            @Override
                            public void onComplete(@NonNull Task<GetTokenResult> task) {
                                Log.d(Constants.LOG_STRING, "ID TOKEN : " + task.getResult().getToken());
                            }
                        });
                        Intent intent = new Intent(LoginActivity.this, SignActivity.class);
                        startActivity(intent);
                    }
                });
    }
}