package com.example.MyDiary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {
    private String strNick, strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        strNick = intent.getStringExtra("nickname");
        strEmail = intent.getStringExtra("email");

        TextView ka_nick = findViewById(R.id.ka_nick);
        TextView ka_email = findViewById(R.id.ka_email);

        //닉네임 set
        ka_nick.setText(strNick);
        //이메일 set
        ka_email.setText(strEmail);

    }
}