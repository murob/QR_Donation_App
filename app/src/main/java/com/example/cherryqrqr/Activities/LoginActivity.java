package com.example.cherryqrqr.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cherryqrqr.Constants.Constants;
import com.example.cherryqrqr.R;
import com.example.cherryqrqr.Retrofit.Entities.JoinDTO;
import com.example.cherryqrqr.Retrofit.RetrofitInterface;
import com.example.cherryqrqr.Utils.SharedPreferenceUtils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GoogleAuthProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    String R_TAG = "Retrofit";

    private ImageView imageView;
    private TextView text1;
    private TextView text2;
    private SignInButton signInButton;

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleApiClient mGoogleApiClient;
    private final int RC_SIGN_IN = 333;

    private SharedPreferenceUtils spu;
    private String loginState;
    private String signState;

    private String idToken; // 파이어베이스 연동을 위해 구글로그인으로 발급 받은 idToken
    private RetrofitInterface service; // 레트로핏 인터페이스 객체

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // layout_id
        imageView = findViewById(R.id.imageView);//로고
        text1 = findViewById(R.id.text1);//글1
        text2 = findViewById(R.id.text2);//글2
        signInButton = findViewById(R.id.signInButton); //구글로그인 버튼

        spu = new SharedPreferenceUtils(this);
        loginState = spu.getString(R.string.login_state,"");
        signState = spu.getString(R.string.sign_state, "");
        Log.e("loginState", "login_state : "+loginState);

        // 파이어베이스 인증을 위한 인스턴스
        mAuth = FirebaseAuth.getInstance();

        // 초기화시 구글 로그인을 앱에 통합한다.
        // 서버 클라이언트 id를 requestIdToken 메서드에 전달한다.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("125362172131-58ttiqksba2992vu3nugj180dk45bbms.apps.googleusercontent.com")
                .requestEmail()
                .build();

        // 클라이언트 생성
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Log.d("TAG", "Login fail");
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signOut();
        Log.e("mAuth", "mAuth :" + mAuth);
        Log.e("dd", getResources().getString(R.string.local_url)+"api/");

        // 레트로핏 인스턴스 생성
        retrofit2.Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.local_url)+"api/") // baseUrl 등록
                .addConverterFactory(GsonConverterFactory.create()) // Gson 변환기 등록
                .build();

        // 레트로핏 인터페이스 객체 구현
        service = retrofit.create(RetrofitInterface.class);
        spu.saveString(R.string.retrofit, service.toString());
        // 클릭 확인
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginState.equals("log_out")) { // 로그아웃일 경우 구글 계정 선택화면을 띄워준다.
                    Log.e("onClick", "구글계정 선택화면 띄움");
                    Intent googleSignInIntent = mGoogleSignInClient.getSignInIntent(); // 로그인 통합 페이지 넘김
                    startActivityForResult(googleSignInIntent, RC_SIGN_IN);
                } else { // 로그인 상태일 경우 메인액티비티로 보내준다.
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
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data); // 구글 로그인 성공시 넘어오는 토큰값을 가지고 있는 Task
            Log.e("TASK", "구글 로그인 성공시 넘어오는 토큰값을 가지고 있는 Task:"+task);
            try {
                // 로그인 성공 확인
                Log.e("TRY", "구글 로그인 성공으로 try문 진입");
                GoogleSignInAccount account = task.getResult(ApiException.class); // task로 파이어베이스와 연동하는데 사용하는 구글 idToken을 가져오는데 사용하는 account 생성

                Log.e(Constants.LOG_STRING, "google get id : " + account.getId());
                Log.e(Constants.LOG_STRING, "google get email : " + account.getEmail());

                firebaseAuthWithGoogle(account.getIdToken()); // 구글 로그인으로 발급받은 idToken을 사용하여 파이어베이스와 연동

            } catch (ApiException e) {
                Log.e("catch", "onActivityResult catch문");
            }
            Log.e("MY_LOG", "Login callback 호출됨");
        }
    }

    // 파이어베이스로 값을 넘겨줌
    // 사용자가 정상적으로 로그인하면 GoogleSignInAccount 객체에서 ID토큰 가져와서
    // Firebase 사용자 인증 정보로 교환하고 해당 정보를 사용해 Firebase 인증
    private void firebaseAuthWithGoogle(String idToken) {
        Log.e("Firebase", "Firebase 진입, idToken : " + idToken);


        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null); // 구글로그인을 통해 얻어온 idToken을 AuthCredential 객체에 넣어서 구글권한을 위임?
        FirebaseAuth.getInstance().signInWithCredential(credential) // 파이어베이스 권한 인스턴스에 구글 로그인 권한을 부여함
//        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.e("onCoplete", "onComplete 진입");

//                        FirebaseUser user = mAuth.getCurrentUser();
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        Log.e("FirebaseUser", "FirebaseUser : " + user);
                        Log.e("FirebaseUser", "user.getUid() : " + user.getUid());

                        if (task.isSuccessful()) {
                            user.getIdToken(true)
                                    .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                                        public void onComplete(@NonNull Task<GetTokenResult> task) {
                                            Log.e("userIdToken onComplete", "userIdToken의 onComplete로 진입");  // 여기로 진입을 못하고 있네..(해결)
                                            if (task.isSuccessful()){
                                                Log.e("userIdToken onComplete", "userIdToken의 onComplete task 성공");

                                                //res에 파이어베이스에서 발급받은 토큰을 저장하여 전역적으로 사용
                                                spu.saveString(R.string.idToken, task.getResult().getToken());
                                                Log.e("loginActivity", "여기서 토큰 값 뭐로 저장 ? : " + R.string.idToken);
                                                Log.e("loginActivity", "토큰값 어케 저장 ? : " + spu.getString(R.string.idToken, ""));

                                                Call<JoinDTO> call = service.getCode(task.getResult().getToken()); // 토큰 값을 통해 검증
                                                call.enqueue(new Callback<JoinDTO>() {
                                                    @Override
                                                    public void onResponse(Call<JoinDTO> call, Response<JoinDTO> response) {
                                                        Log.e(R_TAG, "onResponse");
                                                        if (response.isSuccessful()) { // 계정이 존재할 경우
                                                            Log.e(R_TAG, "onResponse success");
                                                            JoinDTO result = response.body();

                                                            // 서버에 user가 등록되어 있으므로 sign_state를 member로 설정한다.
                                                            spu.saveString(R.string.sign_state, "member");
                                                            Log.e(R_TAG, "유저존재!! sign_state : " + spu.getString(R.string.sign_state, ""));

                                                            // 서버에 user가 등록되어 있으므로 바로 메인액티비티로 넘어간다.
                                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                            startActivity(intent);

                                                        } else { // 계정이 존재하지 않을 경우 // 조인을 통해 서버에 가입시킨다.
                                                            //실패
                                                            Log.e(R_TAG, "onResponse fail");
                                                            spu.saveString(R.string.sign_state, "no_member");
                                                            Log.e(R_TAG, "유저존재하지 않음!! sign_state : " + spu.getString(R.string.sign_state, ""));

                                                            // task(비동기 방식임)가 완료 된 후 가져온 id토큰과 uid를 통해서 레트로핏을 연동한다.
                                                            // 서버에 user가 존재하지 않으므로 레트로핏을 통해 join 처리를 진행한다.
                                                            Call<JoinDTO> joinCall = service.getCode(user.getUid(), task.getResult().getToken()); // 토큰 값을 통해 검증
                                                            joinCall.enqueue(new Callback<JoinDTO>() {
                                                                @Override
                                                                public void onResponse(Call<JoinDTO> call, Response<JoinDTO> response) {
                                                                    Log.e(R_TAG, "onResponse");
                                                                    if (response.isSuccessful()) {
                                                                        Log.e("Resonponse", "성공! response.body() : " + response.body());
                                                                        JoinDTO result = response.body();
                                                                        Log.e("result", "result.code : " + result.getCode());

                                                                        spu.saveString(R.string.sign_state, "member");
                                                                        spu.saveString(R.string.login_state, "login");
                                                                        Log.e(R_TAG, "가입상태 sign_state : " + spu.getString(R.string.sign_state, ""));
                                                                        Log.e(R_TAG, "로그인상태 sign_state : " + spu.getString(R.string.login_state, ""));

                                                                        Intent intent = new Intent(LoginActivity.this, SignActivity.class);
                                                                        startActivity(intent);
                                                                    } else {
                                                                        //실패
                                                                        Log.e(R_TAG, "레트로핏 통신 응답값 onResponse 실패!!");
                                                                        spu.saveString(R.string.sign_state, "no_member");
                                                                        Log.e(R_TAG, "유저존재하지 않음!! sign_state : " + spu.getString(R.string.sign_state, ""));
                                                                    }
                                                                }
                                                                @Override
                                                                public void onFailure(Call<JoinDTO> call, Throwable t) {
                                                                    Log.e("join", "join실패");
                                                                }
                                                            });
                                                        }
                                                    }
                                                    @Override
                                                    public void onFailure(Call<JoinDTO> call, Throwable t) {
                                                        Log.e(R_TAG, "통신실패");
                                                    }
                                                });
                                            } else {
                                                Log.e("userIdToken onComplete", "userIdToken의 onComplete task 실패");
                                            }
                                        }
                                    });
                        }
                    }
                });
    }

    // 파이어베이스 로그인정보 삭제 후 구글로그인 시에 account 선택하는 화면이 뜨게 하는 설정
    // 앱에 연결된 계정을 지운다. 다시 로그인하려면 사용자가 자신의 계정을 다시 선택해야한다.
    public void signOut() {
        mGoogleApiClient.connect();
        mGoogleApiClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {
//                mAuth.signOut();
                if(mGoogleApiClient.isConnected()){
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                }
            }
            @Override
            public void onConnectionSuspended(int i) {}
        });
    }

}