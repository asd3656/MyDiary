package com.example.MyDiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;


public class MainActivity extends AppCompatActivity {

    // 로그인
    private TextView tv_id, tv_nick; //id,password
    //바텀 네비게이션 뷰
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Frag1 frag1;
    private Frag2 frag2;
    private Frag3 frag3;
    private Frag4 frag4;
    //슬라이드네비
    private DrawerLayout drawerLayout;
    private View drawerView;
    //카카오 로그인
    private ISessionCallback mSessionCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //앱 실행시 생명주기 설정
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getAppKeyHash(); //카카오 로그인 해시값 구하기

        //카카오 로그인
        mSessionCallback = new ISessionCallback() {
            @Override
            public void onSessionOpened() {
                // 로그인요청
                UserManagement.getInstance().me(new MeV2ResponseCallback() {
                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        // 로그인 실패(요청했으나)
                        Toast.makeText(MainActivity.this, "로그인 도중에 오류가 발생했습니다", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        // 세션이 닫힘
                        Toast.makeText(MainActivity.this, "세션이 닫혔습니다.. 다시 시도해주세요", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(MeV2Response result) {
                        // 로그인 성공
                        //로그인 후 이동할 액티비티 설정
                        Intent intent = new Intent(MainActivity.this, SubActivity.class);
                        intent.putExtra("nickname",result.getKakaoAccount().getProfile().getNickname());
                        intent.putExtra("email",result.getKakaoAccount().getEmail());
                        startActivity(intent);

                        Toast.makeText(MainActivity.this, "로그인에 성공했습니다!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onSessionOpenFailed(KakaoException exception) {

            }
        };
        Session.getCurrentSession().addCallback(mSessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();

        //일반 로그인
        tv_id = findViewById(R.id.tv_id);
        tv_nick = findViewById(R.id.tv_nick);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userNick = intent.getStringExtra("userNick");

        tv_id.setText(userID);
        tv_nick.setText(userNick);

        Bundle bundle = new Bundle();
        bundle.putString("userNick",userNick);
        frag2.setArguments(bundle);

        //바텀 네비 시작
        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        setFrag(0);
                        break;
                    case R.id.action_addbox:
                        setFrag(1);
                        break;
                    case R.id.action_accountbox:
                        setFrag(2);
                        break;
                    case R.id.action_diaryadd:
                        setFrag(3);
                        break;
                }
                return true;
            }
        });
        frag1 = new Frag1(); //객체생성
        frag2 = new Frag2();
        frag3 = new Frag3();
        frag4 = new Frag4();
        setFrag(0); //첫 프래그먼트 화면을 지정.(case번호)

        //슬라이드네비 시작
        drawerLayout = (DrawerLayout)findViewById(R.id.slidenavi_layout);
        drawerView = (View)findViewById(R.id.slidedrawer);

        ImageView btn_close = (ImageView) findViewById(R.id.btn_close); //버튼클릭시 네비 닫기
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }//onCreate 종료

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() { //네비 슬라이드 기능
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) { //기능 호출

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

    //프래그먼트 교체가 일어나는 실행문
    private void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction(); //프래그먼트 교체될때 트랜젝션
        switch (n) {
            case 0:
                ft.replace(R.id.main_frame,frag1);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame,frag2);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame,frag3);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame,frag4);
                ft.commit();
                break;
        }

    }//프래그먼트 교체 실행문 종료

    //카카오 로그인 해시값 구하기 온크리에이트 바로 밑에 부분이랑 같이 Fnc7bLN4EAEqXzj35TG8UaBWbCw=
//    private void getAppKeyHash() {
//        try{
//            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
//            for(Signature signature : info.signatures) {
//                MessageDigest md;
//                md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                String something = new String(Base64.encode(md.digest(),0));
//                Log.e("Hash key", something);
//            }
//        } catch (Exception e) {
//            Log.e("name not found", e.toString());
//        }
//    }

}//MainActivity 종료