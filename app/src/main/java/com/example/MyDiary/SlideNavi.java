package com.example.MyDiary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SlideNavi extends AppCompatActivity {
    private TextView tv_id, tv_nick; //id,nick

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidenavi);

        tv_id = findViewById(R.id.tv_id);
        tv_nick = findViewById(R.id.tv_nick);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userNick = intent.getStringExtra("userNick");

        tv_id.setText(userID);
        tv_nick.setText(userNick);
    }
}
