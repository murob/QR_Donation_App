package com.example.cherryqrqr.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
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
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView text1;
    private TextView text2;
    private SignInButton signInButton;
    private AppCompatButton button_bogi1, button_bogi2, button_bogi3;
    private Button button_next1;

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;

    private SharedPreferenceUtils spu;
    private final int RC_SIGN_IN = 333;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imageView = findViewById(R.id.imageView);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        signInButton = findViewById(R.id.signInButton);


        spu = new SharedPreferenceUtils(this);

        // 인증 데이터
        mAuth = FirebaseAuth.getInstance();

        // 로그인이 되어있다면 메인액티비티로 보내주는 코드
        if(mAuth.getCurrentUser() != null){
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        // 서클라이언트에 넣을 구글 로그인 옵션 설정
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("125362172131-4fss4v15c2kmaqgqck8rjfqsridkur8n.apps.googleusercontent.com")
                .requestEmail()
                .build();

        // 클라이언트 생
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // 클릭 확인
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent googleSignInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(googleSignInIntent, RC_SIGN_IN);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // 로그인 성공 확인
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(Constants.LOG_STRING, "google get id : " + account.getId());
                Log.d(Constants.LOG_STRING, "google get email : " + account.getEmail());

                firebaseAuthWithGoogle(account.getIdToken());

            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                //Log.w(Constants.LOG_STRING, "Google sign in failed", e);
            }
            Intent signIntent = new Intent(this, SignActivity.class);
            startActivity(signIntent);
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(Constants.LOG_STRING, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }
}